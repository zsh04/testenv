// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.PerformanceData;
import dfischer.utils.PerformanceDataRealTimeComment;


/**
 * Load test add-on module.
 */
public class DelayFullLoad extends Thread implements LoadtestPluginInterface
{
	private int vDelayNumberOfUsers = 100;		// input parameter #1 - label "Delay Load - Users (number)"
	private long vDelayTimeSeconds = 180;		// input parameter #2 - label "Delay Load - Time (seconds)"

	private volatile static	Thread					internalDelayThread = null;
	private volatile static	LoadtestPluginContext	internalDelayThreadPluginContext = null;
	private volatile static	long					loadLevelReachTime = -1;
	private volatile static	boolean					abortInternalThread = false;

	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data



	// ----------------------------------------------------------------------------
	// PART 1: var handler GUI definition and load test integration
	//
	// Note: automatically generated - no manual programming required for this part
	// ----------------------------------------------------------------------------


	public int getPluginType()
	{
		return GenericPluginInterface.TYPE_LOADTEST_EXEC;
	}


	public String getPluginName()
	{
		return "Delay Full Load";
	}


	public String getPluginDescription()
	{
		return "Limits the load - respectively the number of the simulated users - for a configurable time. After this time is elapsed the load is increased up to the originally number of planned users.\n\n" +
				"This plug-in can be used to warm-up the web server (to initialize the web server cache with a limited number of users) for a configurable time before the full load is released.\n\n" +
				"Note for cluster jobs: the plug-in works only correctly if all of the Exec Agents are using nearly the same number of simulated users.";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_USER;
	}


	public int getAllowedExecOrder()
	{
		return LoadtestPluginInterface.EXEC_ORDER_BEFORE;
	}


	public boolean allowMultipleUsage()
	{
		return false;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[2];
		labels[0] = "Delay Load - Users (number)";
		labels[1] = "Delay Load - Time (seconds)";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		LoadtestPluginFixedUserInputField[] userInputFields = new LoadtestPluginFixedUserInputField[2];
		userInputFields[0] = new LoadtestPluginFixedUserInputField("vDelayLoadNumberOfUsers", true, "100");
		userInputFields[1] = new LoadtestPluginFixedUserInputField("vDelayLoadTimeSeconds", true, "180");
		return userInputFields;
	}


	public int allowOptionalInputParameter()
	{
		return -1;		// all input parameters required
	}


	public String[] getOutputParameterLabels()
	{
		String[] labels = new String[0];
		return labels;
	}


	public int allowOptionalOutputParameter()
	{
		return -1;		// all output parameters required
	}


	public LogVector getLogVector()
	{
		return logVector;
	}



	// ----------------------------------------------------------------------------
	// PART 2: runtime behavior / plug-in functionality
	// ----------------------------------------------------------------------------


	/**
	 * Initialize plug-in at start of load test.
	 */
	public void construct(Object context)
	{
	}


	/**
	 * Transfer input parameter before execute() is called.
	 *
	 * input parameter #1: (int) vDelayNumberOfUsers / default value = '100' / label "Delay Load - Users (number)"
	 * input parameter #2: (long) vDelayTimeSeconds / default value = '180' / label "Delay Load - Time (seconds)"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vDelayNumberOfUsers = Integer.valueOf((String) parameterValue).intValue();
				break;
			case 1:
				vDelayTimeSeconds = Long.valueOf((String) parameterValue).longValue();
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in at start of user (per user).
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		HttpLoadTest loadTest = pluginContext.getHttpLoadTest();
		PerformanceData performanceData = pluginContext.getPerformanceData();

		int clusterMemberLocalId = loadTest.getClusterMemberLocalId();
		int totalClusterMembers = loadTest.getClusterTotalMemberCount();

		// first simulated user - start internal delay thread
		if (internalDelayThread == null)
		{
			internalDelayThreadPluginContext = pluginContext;
			internalDelayThread = new Thread(this);
			internalDelayThread.start();
		}

		// count total number of already started users
        if (loadTest.getLoadTestTreads() != null)
        {
            HttpLoadTest[] loadTestUserThreads = loadTest.getLoadTestTreads();
            int startedUserCount = 0;
            for (int x = 0; x < loadTestUserThreads.length; x++)
            {
                if (loadTestUserThreads[x] != null)
                    startedUserCount++;
            }

            // delay startup of this user ?
            int delayUserNr = vDelayNumberOfUsers;
            if (totalClusterMembers > 1)
                delayUserNr = vDelayNumberOfUsers / totalClusterMembers;	// divide if cluster job

            if (startedUserCount >= delayUserNr)
            {
                if (loadLevelReachTime == -1)
                {
                    // first blocked user
                    loadLevelReachTime = System.currentTimeMillis();

                    // add real time comment - load limited
                    if ((clusterMemberLocalId == -1) || (clusterMemberLocalId == 0))
                    {
                        String comment = "load limited to " + vDelayNumberOfUsers + " user";
                        if (vDelayNumberOfUsers > 1)
                            comment = comment + "s";
                        PerformanceDataRealTimeComment realTimeComment = new PerformanceDataRealTimeComment(System.currentTimeMillis() - performanceData.getStartDate(), comment);
                        performanceData.addRealTimeComment(realTimeComment);
                    }
                }

                // wait for end of internal delay thread
                try { internalDelayThread.join(); } catch (InterruptedException ie) {}
            }
        }
        else
            throw new RuntimeException("incompatible: this plug-in does not support Prx version 5.2-O or later");
	}


	/**
	 * Internal thread which delays the start of the outstanding users as long as it is not terminated.
	 */
	public void run()
	{
		PerformanceData performanceData = internalDelayThreadPluginContext.getPerformanceData();
		int samplingInterval = performanceData.getSamplingInterval();

		int clusterMemberLocalId = internalDelayThreadPluginContext.getHttpLoadTest().getClusterMemberLocalId();

		// execute internal delay thread until vDelayTimeSeconds is elapsed after first load level is reached
		while (!internalDelayThreadPluginContext.getHttpLoadTest().abortedByRemote())
		{
			// end of load test before load limitation unblocked ?
			if (abortInternalThread)
				return;

			// check if load delay time has expired
			if (loadLevelReachTime != -1)
			{
				if ((System.currentTimeMillis() - loadLevelReachTime) > (((long) vDelayTimeSeconds) * 1000l))
					break;		// outstanding users are now unfreezed
			}

			// add periodically performance data snapshots because the load test main program may be blocked in the thread constructor of the virtual users
			if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
			{
				performanceData.addSnapshot();
				performanceData.setEndDate();
			}

			try { Thread.currentThread().sleep(500); } catch (InterruptedException ie) {}
		}

		// add real time comment - load limitation unblocked
		if ((clusterMemberLocalId == -1) || (clusterMemberLocalId == 0))
		{
			PerformanceDataRealTimeComment realTimeComment = new PerformanceDataRealTimeComment(System.currentTimeMillis() - performanceData.getStartDate(), "load limitation unblocked");
			performanceData.addRealTimeComment(realTimeComment);
		}

		// end of thread: internalDelayThread.join() is now unfreezed in execute() - for all outstanding users
	}


	/**
	 * Return plug-in output parameter.
	 */
	public Object getOutputParameter(int parameterNumber)
	{
		switch (parameterNumber)
		{
			default:
				return null;
		}
	}


	/**
	 * Finalize plug-in at end of load test.
	 */
	public void deconstruct(Object context)
	{
		abortInternalThread = true;
	}


}	// end of class

