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
import dfischer.utils.Cookie;
import dfischer.utils.HttpTestURL;

/**
 * Load test add-on module.
 */
public class GetCookieValue implements LoadtestPluginInterface
{
	private String vCookieName = "";		// input parameter #1 - label "Cookie Name"

	private String vCookieValue = "";		// output parameter #1 - label "Cookie Value"

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
		return "Get Cookie Value";
	}


	public String getPluginDescription()
	{
		return "Extracts the value of a received cookie from the cookie storage of a virtual users into a GUI variable.\n\n" +
			   "Note: Cookies are automatically extracted by ZebraTester and automatically transferred back to the Web server inside " +
			   "the HTTP(S) request header of succeeding URL calls. " +
			   "In such a common case there is no need to use this plug-in. However in the unusual " +
			   "case that a cookie value is extracted in the web browser by using JavaScript, and then transferred back to the Web server " +
			   "as a CGI parameter of a succeeding URL call, you can use this plug-in to emulate such a JavaScript functionality.";

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
		return true;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[1];
		labels[0] = "Cookie Name";
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
		String[] labels = new String[1];
		labels[0] = "Cookie Value";
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
	 * input parameter #1: (String) vCookieName / default value = '' / label "Cookie Name"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vCookieName = (String) parameterValue;
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in after URL call.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		CookieHandler cookieHandler = pluginContext.getCookieHandler();
		Cookie cookie = cookieHandler.getCookie(vCookieName);
		if (cookie != null)
		{
			vCookieValue = cookie.COK_Value;
			logVector.log("value of cookie \"" + vCookieName + "\" extracted: \"" + vCookieValue + "\"");
		}
		else
		{
			vCookieValue = null;
			logVector.log("*** error - cookie \"" + vCookieName + "\" not found in cookie store of virtual user ***");
		}
	}


	/**
	 * Return plug-in output parameter.
	 *
	 * output parameter #1: (String) vCookieValue / default value = '' / label "Cookie Value"
	 *
	 * Note: all output parameters are always converted to strings.
	 */
	public Object getOutputParameter(int parameterNumber)
	{
		switch (parameterNumber)
		{
			case 0:
				return vCookieValue;
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

			GetCookieValue plugin = new GetCookieValue();
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

