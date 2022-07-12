// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.PerformanceData;
import dfischer.utils.PerformanceDataRecord;
import dfischer.utils.CookieHandler;
import dfischer.utils.HttpTestURL;

import java.util.Arrays;
import java.util.HashMap;

import dfischer.utils.DataCollectionSequence;
import dfischer.utils.DataCollectionFloatItem;


/**
 * Load test add-on module.
 */
public class WebTransactionDiagramsperPage extends Thread implements LoadtestPluginInterface
{
	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	private Thread collectingThread = null;
	private PerformanceData performanceData = null;
	private HashMap<Integer,DataCollectionSequence> pageDiagramMap = new HashMap<Integer,DataCollectionSequence>();


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
		return "Web Transaction Diagrams per Page";
	}


	public String getPluginDescription()
	{
		return "Add Web Transaction Diagrams per Page to the Load Test Result.";
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
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
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
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		performanceData = pluginContext.getPerformanceData();

		collectingThread = new Thread(this);
		collectingThread.start();
	}


	public void run()
	{
		long lastTimeStamp = -1;

		HashMap<Integer,Long> lastMeasuredPageMap = null;
		while (!isInterrupted())
		{
			HashMap<Integer,Long> pageMap = new HashMap<Integer,Long>(); 	// key = page-number (1..n), value = number of passed URL calls

			// get passed URL calls per page
			int[] pageBreakIndex = performanceData.getPageBreakIndexes();
			for (int x = 0; x < pageBreakIndex.length; x++)
			{
				PerformanceDataRecord[] dataRecord = performanceData.getPerformanceDataRecordsOfPage(x + 1);
				long totalPassedUrlCallsOfPage = 0;
				for (int y = 0; y < dataRecord.length; y++)
				{
					if (dataRecord[y].getDataType() == PerformanceDataRecord.TYPE_PERFORMANCE_DATA)
					totalPassedUrlCallsOfPage = totalPassedUrlCallsOfPage + dataRecord[y].getPassedCount();
				}
				pageMap.put(new Integer(x + 1), new Long(totalPassedUrlCallsOfPage));
			}
			long currentTime = System.currentTimeMillis();

			// calculate web transaction rate per page
			if ((lastTimeStamp != -1) && (lastMeasuredPageMap != null))
			{
				Integer[] pageKeys = new Integer[0];
				pageKeys = pageMap.keySet().toArray(pageKeys);
				Arrays.sort(pageKeys);

				for (int x = 0; x < pageKeys.length; x++)
				{
					long newUrlCallsCount = pageMap.get(pageKeys[x]).longValue();

					long oldUrlCallsCount = 0;
					if (lastMeasuredPageMap.containsKey(pageKeys[x]))
						oldUrlCallsCount = lastMeasuredPageMap.get(pageKeys[x]).longValue();

					long deltaUrlCalls = newUrlCallsCount - oldUrlCallsCount;
					float deltaSeconds = ((float) (currentTime - lastTimeStamp)) / 1000.0f;

					logVector.log("Page " + pageKeys[x].intValue() + ": deltaUrlCalls = " + deltaUrlCalls + ", deltaSeconds = " + deltaSeconds);

					DataCollectionSequence dc = null;
					if (pageDiagramMap.containsKey(pageKeys[x]))
						dc = pageDiagramMap.get(pageKeys[x]);
					else
					{
						dc = new DataCollectionSequence(pageKeys[x].intValue(), "Web Transaction Rate of Page " + pageKeys[x].intValue(), "", "URL Calls per Second", null);
						dc.addClusterOption(DataCollectionSequence.CLUSTER_OPTION_MERGE_FLOAT_ITEMS_TO_SUM, null);
						pageDiagramMap.put(pageKeys[x], dc);
					}

					DataCollectionFloatItem webTransRateItem = new DataCollectionFloatItem(currentTime, (float) deltaUrlCalls / (float) deltaSeconds);
					dc.addItem(webTransRateItem);
				}
			}

			// save current data as old data
			lastTimeStamp = currentTime;
			lastMeasuredPageMap = pageMap;

			// sleep for one sampling interval
			try
			{
				Thread.currentThread().sleep(((long) performanceData.getSamplingInterval()) * 1000l);
			}
			catch (InterruptedException ie)
			{
				interrupt();
				break;
			}
		}
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
		// stop data collecting thread
		collectingThread.interrupt();
		try
		{
			collectingThread.join(10000);
		}
		catch (InterruptedException e) {}

		// add external measuring data to load test result
		Integer[] diagramKeys = new Integer[0];
		diagramKeys = pageDiagramMap.keySet().toArray(diagramKeys);
		Arrays.sort(diagramKeys);

		for (int x = 0; x < diagramKeys.length; x++)
			performanceData.addDataCollectionSequence(pageDiagramMap.get(diagramKeys[x]));

		performanceData.setEndDate();
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

			WebTransactionDiagramsperPage plugin = new WebTransactionDiagramsperPage();
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

