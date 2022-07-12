// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.PerformanceData;
import dfischer.utils.CookieHandler;
import dfischer.utils.HttpTestURL;

import dfischer.utils.DataCollectionSequence;
import dfischer.utils.DataCollectionFloatItem;


/**
 * Load test add-on module.
 */
public class ExternalMeasuringDataExample extends Thread implements LoadtestPluginInterface
{
	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	private PerformanceData performanceData = null;					// load test result
	private Thread dataCollectThread = null;						// data collecting thread
	private DataCollectionSequence dataCollectionSequence = null;	// external data


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
		return "Example - External Measuring Data";
	}


	public String getPluginDescription()
	{
		return "This is only a framework about how external measuring data can be integrated into ZebraTester.\n\n" +
		       "You have to extend this plug-in with own Java code which gathers the data from external sources.";
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
		String[] labels = new String[0];
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		return null;
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
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		performanceData = pluginContext.getPerformanceData();

		// start data collecting thread
		dataCollectThread = new Thread(this);
		dataCollectThread.start();
	}


	/**
	 * Local thread - used to collect external measuring data
	 */
	public void run()
	{
		// create data structure for external data and define GUI diagram settings
		dataCollectionSequence = new DataCollectionSequence(1, "Database Calls", "", "Calls per Second", null);

		// collect external measuring data in a loop
		while (!isInterrupted())
		{
			// get external data snapshot
			float externalValue = 10.0f; //  <<< actual value of external data
										 //  [ add your own code here to
										 //    accumulate external data ]

			// add external data snapshot to data collection
			DataCollectionFloatItem dataItem = new DataCollectionFloatItem(System.currentTimeMillis(), externalValue);
			dataCollectionSequence.addItem(dataItem);

			// sleep for one sampling interval. The sampling interval is
			// arbitrary configurable on the GUI when starting the load test.
			try
			{
				sleep(performanceData.getSamplingInterval() * 1000);
			}
			catch (InterruptedException e)
			{
				interrupt();
			}
		}
	}


	/**
	 * Transfer input parameter before execute() is called.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
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
		// no action in this method
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
		// try to stop data collecting thread
		dataCollectThread.interrupt();
		try
		{
			dataCollectThread.join(10000);
		}
		catch (InterruptedException e) {}

		// add external measuring data to load test result
		performanceData.addDataCollectionSequence(dataCollectionSequence);
	}



}	// end of class

