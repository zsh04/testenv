// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.PerformanceData;
import dfischer.utils.CookieHandler;
import dfischer.utils.HttpTestURL;
import dfischer.utils.HttpLoadTest;

/**
 * Load test add-on module.
 */
public class DeferLoadTestStart implements LoadtestPluginInterface
{
	private long vDeferMinutes = 0l;		// input parameter #1 - label "Defer Load Test Start (Minutes)"

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
		return "Defer Load Test Start";
	}


	public String getPluginDescription()
	{
		return "Defers the start of the load test for a configurable number of minutes.";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_GLOBAL;
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
		String[] labels = new String[1];
		labels[0] = "Defer Load Test (Minutes)";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		LoadtestPluginFixedUserInputField[] userInputFields = new LoadtestPluginFixedUserInputField[1];
		userInputFields[0] = new LoadtestPluginFixedUserInputField("vDeferMinutes", true, "0");
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
	//
	// This part requires manual programming (see sample code section below)
	// ----------------------------------------------------------------------------


	/**
	 * Initialize plug-in at start of load test.
	 */
	public void construct(Object context)
	{
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}


	/**
	 * Transfer input parameter before execute() is called.
	 *
	 * input parameter #1: (long) vDeferMinutes / default value = '0' / label "Defer Load Test Start (Minutes)"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vDeferMinutes = Long.valueOf((String) parameterValue).longValue();
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in at start of load test.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		HttpLoadTest httpLoadTest = pluginContext.getHttpLoadTest();
		PerformanceData performanceData = pluginContext.getPerformanceData();

		// defer load test execution
		long deferMillis = vDeferMinutes * 60l * 1000l;
		long realStartMillis = System.currentTimeMillis() + deferMillis;
		while (System.currentTimeMillis() < realStartMillis)
		{
			try
			{
				Thread.currentThread().sleep(5000);
			}
			catch (InterruptedException ie) { System.exit(0); }
			httpLoadTest.testDurationStart = System.currentTimeMillis();

			// load test aborted by user during deferment ?
			if (httpLoadTest.abortedByRemote())
			{
				System.out.println("");
				System.out.println("*** Defered Load Test aborted by User Action before start ***");
				System.out.println("");
				System.exit(0);
			}
		}

		// reset load test start
		performanceData.setStartDate();
		performanceData.resetSnapshots();
		performanceData.setEndDate();
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
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}



	// ----------------------------------------------------------------------------
	// PART 3: stand-alone test utility - optional - used for plug-in development
	// ----------------------------------------------------------------------------

	/*
	public static void main(String[] args)
	{
		try
		{
			// vvv --- sample code --- vvv

			DeferLoadTestStart plugin = new DeferLoadTestStart();
			plugin.construct(null);
			plugin.setInputParameter(0, args[0]);
			plugin.execute(null);
			System.out.println(plugin.getOutputParameter(0));
			plugin.deconstruct(null);

			// ^^^ --- sample code --- ^^^
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	*/


}	// end of class

