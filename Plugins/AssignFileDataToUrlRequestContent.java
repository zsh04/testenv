// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.HttpTestURL;

import java.util.HashMap;


/**
 * Load test add-on module.
 */
public class AssignFileDataToUrlRequestContent implements LoadtestPluginInterface
{
	private String vDataFileName = "RequestContent.dat";		// input parameter #1 - label "Data File Name"
	private HashMap dataCache = new HashMap();

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
		return "Assign File Data to Request Content";
	}


	public String getPluginDescription()
	{
		return "Read the data of a file and assign it to the request content of an URL call (only useful for HTTP/S POST requests and some WebDAV methods).\n\nNote: if a simple file name without path is used as input parameter then the file must be zipped together with the load test program. In such a case the file is automatically transferred to the Exec Agents.\n\n";
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
		labels[0] = "Data File Name";
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
	// This part requires manual programming
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
	 * input parameter #1: (String) vDataFileName / default value = 'RequestContent.dat' / label "Data File Name"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vDataFileName = (String) parameterValue;
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

		// check if file is in cache
		byte[] fileData = null;
		Object o = dataCache.get(vDataFileName);
		if (o != null)
			fileData = (byte[]) o;
		else
		{
			// file not in cache: read file data and add it to cache
			try
			{
				fileData = HttpLoadTest.contentFromDiskFile(vDataFileName);
				dataCache.put(vDataFileName, fileData);
			}
			catch (Exception e)
			{
				logVector.log("*** error: unable to read data from file '" + vDataFileName + "' *** ");
				logVector.log(e);
				throw new RuntimeException("unable to read data from file '" + vDataFileName + "'");
			}
		}

		// assign data to HTTP request content of URL call
		HttpTestURL httpTestURL = pluginContext.getHttpTestURL();
		httpTestURL.setRequestContent(fileData);
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
