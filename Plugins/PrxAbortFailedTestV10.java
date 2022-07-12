/******************************************************
 * File: PrxAbortFailedTestV10.java
 */

import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.PerformanceData;


/**
 * see getPluginDescription()
 */
public class PrxAbortFailedTestV10 implements LoadtestPluginInterface
{
	private int	testIntervalMinutes = -1;		// input parameter
	private int	failedLoopsThreshold = -1;		// input parameter

	private LogVector logVector = null;			// internal log vector
	
	private long	lastTestTime = -1;
	private long 	lastPassedLoops = 0;
	private long	lastFailedLoops = 0;
	

	public int getPluginType()
	{
		return GenericPluginInterface.TYPE_LOADTEST_EXEC;
	}
	
	
	public String getPluginName()
	{
		return "Abort Failed Test (V1.0)";
	}
	
	
	public String getPluginDescription()
	{
		return "This plug-in aborts a test-run if within a defined time interval of minutes the failure rate " +
	           "of the executed loops is greater than a customizable threshold in percent.\n\n" +
			   "Input parameter:\n" +
			   " - test interval in minutes\n" +
			   " - threshold of failed loops in percent (0..100)\n";
	}
	
	
	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
	}
	
	
	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_LOOP;
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
		labels[0] = "Test Interval (Minutes)";
		labels[1] = "Threshold - Failed Loops (%)";
		return labels;
	}
	
	
	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		LoadtestPluginFixedUserInputField[] result = new LoadtestPluginFixedUserInputField[2];
		result[0] = new LoadtestPluginFixedUserInputField("testIntervalMinutes", true, "5");
		result[1] = new LoadtestPluginFixedUserInputField("failedLoopsThreshold", true, "80");
		return result;		
	}
	
	
	public int allowOptionalInputParameter()
	{
		return -1;
	}
	
	
	public String[] getOutputParameterLabels()
	{
		return new String[0];
	}
	
	
	public int allowOptionalOutputParameter()
	{
		return -1;
	}
	
	
	public void construct(Object context)
	{
	}
	
	
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch(parameterNumber)
		{
			case 0:
				testIntervalMinutes = Integer.valueOf((String) parameterValue).intValue();
				break;
			case 1:
				failedLoopsThreshold = Integer.valueOf((String) parameterValue).intValue();
				break;
			default:
				break;
		}
	}
	
	
	public void execute(Object context)
	{
		logVector = new LogVector();
		
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		PerformanceData performanceData = pluginContext.getPerformanceData();
		
		if (((lastTestTime == -1) && (performanceData.getTestDurationMillis() > ((long)testIntervalMinutes * 60l * 1000l))) ||
			((lastTestTime != -1) && ((System.currentTimeMillis() - lastTestTime) >= ((long)testIntervalMinutes * 60l * 1000l))))
		{
			long passedLoops = performanceData.getPassedLoops();
			long failedLoops = performanceData.getFailedLoops();
			long totalLoops = passedLoops + failedLoops;
			
			long deltaPassedLoops = passedLoops - lastPassedLoops;
			long deltaFailedLoops = failedLoops - lastFailedLoops;
			long deltaTotalLoops = deltaPassedLoops + deltaFailedLoops;
			
			if (deltaTotalLoops > 0)
			{
				float failedPercent = (100.0f * ((float) deltaFailedLoops)) / ((float) deltaTotalLoops);
				logVector.log("percentage of failed loops within the last " + testIntervalMinutes + " minutes = " + failedPercent + " %");
				if (failedPercent >= ((float) failedLoopsThreshold))
				{
					pluginContext.getHttpLoadTest().triggerAbort();
					logVector.log("!!! LOAD TEST ABORTED BY PLUG-IN !!!");
				}
			}
			
			lastTestTime = System.currentTimeMillis();
			lastPassedLoops = passedLoops;
			lastFailedLoops = failedLoops;
		}
	}
	
	
	public Object getOutputParameter(int parameterNumber)
	{
		return null;
	}
	
	
	public LogVector getLogVector()
	{
		return logVector;
	}
	
	
	public void deconstruct(Object context)
	{
	}

}
