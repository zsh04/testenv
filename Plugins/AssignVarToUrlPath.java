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

import dfischer.utils.ParseUrl;

/**
 * Load test add-on module.
 */
public class AssignVarToUrlPath implements LoadtestPluginInterface
{
	private String vUrlPath = "";		// input parameter #1 - label "URL Path"

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
		return "Assign Var to URL Path";
	}


	public String getPluginDescription()
	{
		return "Replace the full path of a URL inclusive optional CGI parameters with the content of the input variable.\n\nExample of a full URL path:\n/cgi-bin/sript1.pl?search=car\n\nNote: the protocol (http or https), the DNS name or the host name, and the (optional) IP-port of the recorded URL is not replaced.\n\nThe input variable can contain a complete URL (for example http://www.acme.com:8080/img/x.gif) as well as only a full path of an URL (for example /img/x.gif).\n\n";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
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
		labels[0] = "URL Path";
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
	 *
	 * input parameter #1: (String) vUrlPath / default value = '' / label "URL Path"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vUrlPath = (String) parameterValue;
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
		PerformanceData performanceData = pluginContext.getPerformanceData();

		// extract relative path from input variable - used in case if a complete URL is passed
		ParseUrl parseUrl = new ParseUrl(vUrlPath);
		if (!parseUrl.isValid())
		{
			String errorMessage = "invalid input for plug-in AssignVarToUrlPath: " + vUrlPath;
			logVector.log("*** error **** " + errorMessage);
			throw new RuntimeException(errorMessage);
		}
		String usedPath = parseUrl.getFileOrBlank();
		if (!usedPath.startsWith("/"))
			usedPath = "/" + usedPath;

		// get access to current request header
		HttpTestURL httpTestURL = pluginContext.getHttpTestURL();
		dfischer.utils.HttpRequestHeader requestHeader = httpTestURL.getRequestHeaderObject();

		// special handling for unencrypted proxy requests
		if (requestHeader.isHttpProxyRequest())
		{
			String proxyPath = "http://" + requestHeader.getTargetHost();
			if (requestHeader.getTargetPort() != 80)
				proxyPath = proxyPath + ":" + requestHeader.getTargetPort();
			usedPath = proxyPath + usedPath;
		}

		// replace path of current URL
		requestHeader.setTargetPath(usedPath);

		// update also information text about URL
		performanceData.replaceInfoText(pluginContext.getThreadStep(), httpTestURL.getRequestInfoText());
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


}	// end of class

