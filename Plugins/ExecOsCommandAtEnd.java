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

import java.io.*;
import java.util.Map;
import java.util.Vector;
import dfischer.utils.KeyValueParser;


/**
 * Load test add-on module.
 */
public class ExecOsCommandAtEnd extends Thread implements LoadtestPluginInterface
{
	private String vOScommand = "";			// input parameter #1 - label "OS Command"
	private String vEnvParams = "";			// input parameter #2 - label "ENV Params"
	private String vOncePerCluster = "";	// input parameter #3 - label "only once per cluster"

	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	private Process process = null;


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
		return "Exec OS Command at End of Test";
	}


	public String getPluginDescription()
	{
		return "Executes an OS command on the load generator just before the load test ends - after the activity of all simulated users is completed, but before the *.prxres file is created.\n\nParameter 1: OS command inclusive all command arguments\nOptional Parameter 2: additional environment variables, name=value (separated by commas)\nOptional Parameter 3: 'true' OR '1' = execute OS command only once per cluster\n\nWindows example: 'cmd.exe /C dir'\nUnix example: 'bash -c \"ls -al\"'";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_GLOBAL;
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
		String[] labels = new String[3];
		labels[0] = "OS Command";
		labels[1] = "ENV Params";
		labels[2] = "only once per cluster";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		return null;
	}


	public int allowOptionalInputParameter()
	{
		return 1;
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
	 * input parameter #1: (String) vOScommand / default value = '' / label "OS Command"
	 * input parameter #2: (String) vEnvParams / default value = '' / label "ENV Params"
	 * input parameter #3: (String) vOncePerCluster / default value = '' / label "only once per cluster"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vOScommand = (String) parameterValue;
				break;
			case 1:
				vEnvParams = (String) parameterValue;
				break;
			case 2:
				vOncePerCluster = (String) parameterValue;
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in at end of load test.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		// check if OS command must be executed only once per cluster
		boolean oncePerCluster = false;
		if (vOncePerCluster != null)
		{
			if (vOncePerCluster.trim().equalsIgnoreCase("1") || vOncePerCluster.trim().equalsIgnoreCase("true"))
				oncePerCluster = true;
		}

		HttpLoadTest httpLoadTest = pluginContext.getHttpLoadTest();
		int clusterMemberLocalId = httpLoadTest.getClusterMemberLocalId();
		if ((clusterMemberLocalId > 0) && oncePerCluster)
			return;

		try
		{
			Vector<String> commandVector = convertCommandToArgs(vOScommand);

			logVector.log("Execute OS Command:");
			for (int x = 0; x < commandVector.size(); x++)
				logVector.log("[" + x + "] = '" + commandVector.elementAt(x) + "'");

			ProcessBuilder pb = new ProcessBuilder(commandVector);

			// check if additional environment variables must be set
			if (vEnvParams != null)
			{
				if (vEnvParams.trim().length() > 0)
				{
					KeyValueParser kvp = new KeyValueParser(vEnvParams);
					String[] envKeys = kvp.getKeys();
					String[] envValues = kvp.getValues();

					for (int x = 0; x < envKeys.length; x++)
						pb.environment().put(envKeys[x], envValues[x]);
				}
			}

			// start OS command
			pb.redirectErrorStream(true);
			process = pb.start();
			Thread t = new Thread(this);
			t.start();

			// wait for completion
			// process.waitFor(); 	// this may be wrong - better wait until no output is available - see next line
			t.join(120000);			// wait max 2 minutes, then continue with ending the load test
		}
		catch (Exception e)
		{
			logVector.log(e);
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


	/**
	 * Read the output data of the executed OS command.
	 */
	public void run()
	{
		BufferedReader bin = null;

		try
		{
			bin = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = bin.readLine();
			while (line != null)
			{
				logVector.log(line);
				line = bin.readLine();
			}
			bin.close();
		}
		catch (Exception e)
		{
			logVector.log(e);
		}
	}


	/**
	 * Split the OS command into arguments.
	 */
	private Vector<String> convertCommandToArgs(String command)
	{
		Vector<String> v = new Vector<String>();

		StringBuffer arg = new StringBuffer();
		boolean inQuote = false;
		for (int x = 0; x < command.length(); x++)
		{
			char c = command.charAt(x);

			if ((c == '"') && (!inQuote))
			{
				inQuote = true;
				continue;
			}

			if ((c == '"') && (inQuote))
			{
				inQuote = false;
				continue;
			}

			if ((c == ' ') && (!inQuote))
			{
				if (arg.length() > 0)
				{
					v.addElement(arg.toString());
				}
				arg = new StringBuffer();
				continue;
			}

			arg.append(c);
		}
		if (arg.length() > 0)
			v.addElement(arg.toString());

		return v;
	}



}	// end of class

