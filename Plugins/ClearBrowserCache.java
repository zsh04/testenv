// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LogVector;
import dfischer.utils.RequestResponseLRUCache;

/**
 * Load test add-on module.
 */
public class ClearBrowserCache implements LoadtestPluginInterface
{

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
        return "Clear Browser User Cache";
    }


    public String getPluginDescription()
    {
        return "This plugin will empty the user cache created, so that from next iteration ZT will create a new cache.";
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
        return LoadtestPluginInterface.EXEC_ORDER_AFTER;
    }


    public boolean allowMultipleUsage()
    {
        return true;
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
            default:
                break;
        }
    }


    /**
     * Execute plug-in after the loop.
     *
     * Intrinsic plug-in implementation.
     */
    public void execute(Object context)
    {
        logVector = new LogVector();
        LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

        // vvv --- plug-in code --- vvv
        try
        {
            pluginContext.getHttpLoadTest().getOwnLoadTestUserContext().setRequestResponseLRUCache(new RequestResponseLRUCache());
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

