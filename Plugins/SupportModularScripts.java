/******************************************************
 * File: SupportModularScripts.java
 * Created by sreejith.sreenivasan on 07/04/2020
 */

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LogVector;

import java.util.HashMap;
import java.util.Map;

/**
 * Load test add-on module.
 */
public class SupportModularScripts implements LoadtestPluginInterface
{

	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	private long maxTestDuration = -1;

	private long testStartTime = -1;

	private boolean replayFlag = false;

	private static Map<Integer, String> userTrackingMap = new HashMap<>();



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
		return "SupportModularScripts";
	}
	
	
	public String getPluginDescription()
	{
		return "This plugin will support modular scripting by making sure the flow of the pages in the ZT script\n\n";
	}
	
	
	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_LOOP;
	}
	
	
	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_URL;
	}
	
	
	public int getAllowedExecOrder()
	{
		return LoadtestPluginInterface.EXEC_ORDER_AFTER;
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
	 * Initialize plug-in at the start of each user.
	 */
	public void construct(Object context)
	{
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		testStartTime = pluginContext.getPerformanceData().getStartDate();
		maxTestDuration = pluginContext.getPerformanceData().getMaxTestDuration();
		maxTestDuration = maxTestDuration * 1000L;

		if(userTrackingMap.containsKey(pluginContext.getUserNr()))
		{
			pluginContext.getHttpLoadTest().setNonModularMode(false);
		}
		else
		{
			pluginContext.getHttpLoadTest().setNonModularMode(true);
			userTrackingMap.put(pluginContext.getUserNr(), null);
		}

		replayFlag = (pluginContext.getHttpLoadTest().remainingLoops > 0);
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
	 * Execute plug-in after an URL.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{

		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		pluginContext.getHttpLoadTest().setNonModularMode(true);

		if(replayFlag && pluginContext.getHttpLoadTest().remainingLoops > 1)
		{
			pluginContext.getHttpLoadTest().setNonModularMode(false);
		}

		if(!replayFlag)
		{
			long newMaxTestDuration = pluginContext.getPerformanceData().getMaxTestDuration() * 1000L;

			if (newMaxTestDuration != maxTestDuration)
				maxTestDuration = newMaxTestDuration;

			long elapsedTime = System.currentTimeMillis() - testStartTime;

			if (elapsedTime < maxTestDuration)
			{
				pluginContext.getHttpLoadTest().setNonModularMode(false);
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
	 * Finalize plug-in at end of each user.
	 */
	public void deconstruct(Object context)
	{
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}

}	// end of class

