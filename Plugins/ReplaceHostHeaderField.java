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

import java.util.Vector;


/**
 * Load test add-on module.
 */
public class ReplaceHostHeaderField implements LoadtestPluginInterface
{
	private String vOldHeaderHost = "www.";		// input parameter #1 - label "Old Header IP or DNS"
	private String vNewHeaderHost = "www.";		// input parameter #2 - label "New Header IP or DNS"

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
		return "Replace Host Header Field";
	}


	public String getPluginDescription()
	{
		return "Replaces (only) the Host Header Field in HTTP(S) requests. The IP address or the DNS name to which the HTTP(S) requests are send are not modified by this plug-in.\n\nNote: If the host name is already replaced by a GUI-variable, you have to use the value of the variable for the plug-in input parameter 'Old Header IP or DNS', instead of using the recorded host name.";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_ALL_URLS;
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
		String[] labels = new String[2];
		labels[0] = "Old Header IP or DNS";
		labels[1] = "New Header IP or DNS";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		LoadtestPluginFixedUserInputField[] userInputFields = new LoadtestPluginFixedUserInputField[2];
		userInputFields[0] = new LoadtestPluginFixedUserInputField("vOldHeaderHost", true, "www.");
		userInputFields[1] = new LoadtestPluginFixedUserInputField("vNewHeaderHost", true, "www.");
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
	 * input parameter #1: (String) vOldHeaderHost / default value = 'www.' / label "Old Header IP or DNS"
	 * input parameter #2: (String) vNewHeaderHost / default value = 'www.' / label "New Header IP or DNS"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vOldHeaderHost = (String) parameterValue;
				break;
			case 1:
				vNewHeaderHost = (String) parameterValue;
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in before every URL call.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		HttpTestURL httpTestURL = pluginContext.getHttpTestURL();
		dfischer.utils.HttpRequestHeader requestHeader = httpTestURL.getRequestHeaderObject();

		Vector headerVector = requestHeader.getHeaderVector();
		for (int x = 0; x < headerVector.size(); x++)
		{
			String headerLine = (String) headerVector.elementAt(x);
			int fieldDelimiterIndex = headerLine.indexOf(":");
			if (fieldDelimiterIndex != -1)
			{
				String fieldName = headerLine.substring(0, fieldDelimiterIndex).trim();
				if (fieldName.equalsIgnoreCase("host"))
				{
					String oldHostFieldValue = headerLine.substring(fieldDelimiterIndex + 1).trim();
					if (oldHostFieldValue.compareTo(vOldHeaderHost) == 0)
					{
						headerVector.setElementAt("Host: " + vNewHeaderHost, x);
						logVector.log("Host header field replaced: old value = " + oldHostFieldValue + ", new value = " + vNewHeaderHost);
						break;
					}
				}
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

			ReplaceHostHeaderField plugin = new ReplaceHostHeaderField();
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

