// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import java.util.Vector;

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.PerformanceData;
import dfischer.utils.CookieHandler;
import dfischer.utils.Cookie;
import dfischer.utils.HttpTestURL;

import dfischer.utils.HttpRequestHeader;

/**
 * Load test add-on module.
 */
public class CookieInjector implements LoadtestPluginInterface
{
	private String cookieName = "";		// input parameter #1 - label "Cookie Name"
	private String cookieValue = "";	// input parameter #2 - label "Cookie Value"

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
		return "Cookie Injector";
	}


	public String getPluginDescription()
	{
		return "Adds a cookie to the cookie storage of a virtual user.\n\n" +
		"Note: cookies are automatically handled by ZebraTester if they are received in a HTTP(S) response header " +
		"from the web server. In such a common case there is no need to use this plug-in. However in the unusual " +
		"case that a cookie is set by the web browser itself (by using JavaScript) you can use this plug-in to emulate such a JavaScript functionality.";
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
		String[] labels = new String[2];
		labels[0] = "Cookie Name";
		labels[1] = "Cookie Value";
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
	 * input parameter #1: (String) cookieName / default value = '' / label "Cookie Name"
	 * input parameter #2: (String) cookieValue / default value = '' / label "Cookie Value"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				cookieName = (String) parameterValue;
				break;
			case 1:
				cookieValue = (String) parameterValue;
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

		// vvv --- plug-in code --- vvv

		HttpTestURL  httpTestURL = pluginContext.getHttpTestURL();
		HttpRequestHeader requestHeader = httpTestURL.getRequestHeaderObject();

		CookieHandler cookieHandler = pluginContext.getCookieHandler();

		String cookiePath = "/";
		String cookieDomain = requestHeader.getTargetHost();

		try
		{
			String cookieLine = cookieName + "=" + cookieValue + "; path=" + cookiePath + "; domain=" + cookieDomain + ";";
			logVector.log("Cookie line = " + cookieLine);

			Cookie newCookie = new Cookie(cookieLine, cookiePath, cookieDomain);
			cookieHandler.setCookie(newCookie);
			logVector.log("Cookie injected: name = " + cookieName + ", value = " + cookieValue + ", domain = " + cookieDomain + ", path = " + cookiePath);

			// workaround - for this first request the cookie handler has already been called - so only for this case add the cookie also manually
			Vector headerVector = requestHeader.getHeaderVector();
			headerVector.addElement("Cookie: " + cookieName + "=" + cookieValue);
		}
		catch (Exception e)
		{
			logVector.log(e);
		}

		// ^^^ --- plug-in code --- ^^^
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

			CookieInjector plugin = new CookieInjector();
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

