// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.Lib;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.HttpTestURL;

/**
 * Load test add-on module.
 */
public class LimitResponseContent implements LoadtestPluginInterface
{
	private Integer limitMaxContent = null;		// input parameter #1 - label "Max. Response Content (bytes)"

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
		return "Limit Response Content";
	}


	public String getPluginDescription()
	{
		return "Limit the receiving of response content data to a specified size. Further reading of data from the web server during load test execution is aborted for the configured URL when the maximum size is reached.\n\nInput Parameter 'Max. Response Content (bytes)':\nAbort further reading of the response content data when the given value in bytes is exceeded (-1 = unlimited, recommended values are between 32768 and 4194304, the default value is 65536).\n\nNote 1: if (only) the limit is exceeded, the URL call is basically normal completed without any error. However, you have to modify additionally the response-size check configuration for the corresponding URL in the 'HTTP Response Verification' menu because the size of the received content data maybe smaller than at the time when the recoding of the web surfing session was made.\n\nNote 2: received content data in compressed form are not automatically decompressed when this plug-in is used AND when the limit has been exceeded.\n\nNote 3: when the limit is exceeded the network connection to the web server is closed and a new network connection is opened by the next URL call.\n\n";
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
		return LoadtestPluginInterface.EXEC_ORDER_BEFORE;
	}


	public boolean allowMultipleUsage()
	{
		return true;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[1];
		labels[0] = "Max. Response Content (bytes)";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		return null;
	}


	public int allowOptionalInputParameter()
	{
		return 0;		// optional input parameters starting from parameter #1
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
	 * Initialize plug-in at start of loop (new instance per loop).
	 */
	public void construct(Object context)
	{
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}


	/**
	 * Transfer input parameter before execute() is called.
	 *
	 * input parameter #1: (int) limitMaxContentKb / default value = '-1' / label "Max. Response Content (bytes)" / [optional]
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				limitMaxContent = new Integer((String) parameterValue);
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in before URL call.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		int vMaxReadContentBytes = -1;

		if (limitMaxContent == null)
			vMaxReadContentBytes = 65536;	// 64 kB
		else
		{
			if (limitMaxContent.intValue() >= 0)
				vMaxReadContentBytes = limitMaxContent.intValue();
		}

		HttpTestURL httpTestURL = pluginContext.getHttpTestURL();
		httpTestURL.setLimitMaxReceivedContentSize(vMaxReadContentBytes);

		if (vMaxReadContentBytes != -1)
			logVector.log("plug-in: max. received response content size limited to " + Lib.formatInt(vMaxReadContentBytes) + " bytes");
		else
			logVector.log("plug-in: max. received response content size not limited");
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
	 * Finalize plug-in at end of loop.
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

			LimitResponseContent plugin = new LimitResponseContent();
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

