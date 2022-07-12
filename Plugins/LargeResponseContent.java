// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.Lib;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.HttpTestURL;

/**
 * Load test add-on module.
 */
public class LargeResponseContent implements LoadtestPluginInterface
{
	private Integer vMaxStoredContentKb = null;		// input parameter #1 - label "Max. Stored Content (kilobytes)"

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
		return "Large Response Content";
	}


	public String getPluginDescription()
	{
		return "Allows to receive response content data of a large size (up to 2 GB) for one or several URLs. Note that all response data are read as usual during load test execution, but that only a part of them are stored internally. This means that error snapshots which are made in case of failures may not contain all received response content data.\n\nInput Parameter 'Max. Stored Content (kilobytes)':\nLimits storing of the receiving content data up to the given value in kilobytes (-1 = unlimited, recommended values are between 64 and 256, the default value is 128).\n\nNote 1: received content data in compressed form are not automatically decompressed when this plug-in is used AND when the limit of max. stored content data has been exceeded.\n\nNote 2: the real size of the stored content data maybe smaller or larger than the specified maximum value (+/- 32 kilobytes).\n\n";
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
		labels[0] = "Max. Stored Content (kilobytes)";
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
	 * Initialize plug-in at start of each loop test.
	 */
	public void construct(Object context)
	{
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}


	/**
	 * Transfer input parameter before execute() is called.
	 *
	 * input parameter #1: (int) vMaxStoredContentKb / default value = '128' / label "Max. Stored Content (kilobytes)" / [optional]
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vMaxStoredContentKb = new Integer((String) parameterValue);
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

		int vMaxStoredContentBytes = -1;

		if (vMaxStoredContentKb == null)
			vMaxStoredContentBytes = 128 * 1024;
		else
		{
			if (vMaxStoredContentKb.intValue() >= 0)
				vMaxStoredContentBytes = vMaxStoredContentKb.intValue() * 1024;
		}

		HttpTestURL httpTestURL = pluginContext.getHttpTestURL();
		httpTestURL.setStoreMaxReceivedContentSize(vMaxStoredContentBytes);

		if (vMaxStoredContentBytes != -1)
			logVector.log("plug-in: max. stored response content data limited to " + Lib.formatInt(vMaxStoredContentBytes) + " bytes");
		else
			logVector.log("plug-in: max. stored response content data not limited");
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
	 * Finalize plug-in at end of each loop.
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

			LargeResponseContent plugin = new LargeResponseContent();
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

