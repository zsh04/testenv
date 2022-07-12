// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.VarInputFileLineParser;

import java.io.*;
import java.util.Vector;


/**
 * Load test add-on module.
 */
public class LargeInputFile implements LoadtestPluginInterface
{
	private String vFilePath = "";		// input parameter #1 - label "Absolute File Path"
	private String vVarDelemiter = "#";	// input parameter #2 - label "Var Delemiter Char"

	private String vLineColumn1 = "";		// output parameter #1 - label "Line Column 1"
	private String vLineColumn2 = "";		// output parameter #2 - label "Line Column 2"
	private String vLineColumn3 = "";		// output parameter #3 - label "Line Column 3"
	private String vLineColumn4 = "";		// output parameter #4 - label "Line Column 4"
	private String vLineColumn5 = "";		// output parameter #5 - label "Line Column 5"
	private String vLineColumn6 = "";		// output parameter #6 - label "Line Column 6"

	private BufferedReader bin = null;

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
		return "Large Input File";
	}


	public String getPluginDescription()
	{
		return "Read data from a large input file which has an unlimited size. " +
		       "As plug-in input parameter an absolute file path is required. Up to 6 output variables are supported. " +
		       "The default value for the variable delimiter character within a line is ; (semicolon). " +
		       "Empty lines, and comment out lines which are starting with a # character, are skipped.\n\n" +
		       "Note: the input file must not be zipped together with the load test program - which means that in case of remote " +
		       "execution - or when executing a cluster job - the input file must be manually pre-installed on the Exec Agents.";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_NOT_FIXED;
	}


	public int getAllowedExecOrder()
	{
		return LoadtestPluginInterface.EXEC_ORDER_NOT_FIXED;
	}


	public boolean allowMultipleUsage()
	{
		return true;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[2];
		labels[0] = "Absolute File Path";
		labels[1] = "Var Delemiter Char";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		return null;
	}


	public int allowOptionalInputParameter()
	{
		return 1;		// optional input parameters starting from parameter #2
	}


	public String[] getOutputParameterLabels()
	{
		String[] labels = new String[6];
		labels[0] = "Line Column 1";
		labels[1] = "Line Column 2";
		labels[2] = "Line Column 3";
		labels[3] = "Line Column 4";
		labels[4] = "Line Column 5";
		labels[5] = "Line Column 6";
		return labels;
	}


	public int allowOptionalOutputParameter()
	{
		return 1;		// optional output parameters starting from parameter #2
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
	 * input parameter #1: (String) vFilePath / default value = '' / label "Absolute File Path"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vFilePath = (String) parameterValue;
				break;
			case 1:
				vVarDelemiter = (String) parameterValue;
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in .
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();

		// open input file
		if (bin == null)
		{
			try
			{
				bin = new BufferedReader(new InputStreamReader(new FileInputStream(vFilePath)));
			}
			catch (Exception e)
			{
				logVector.log("*** error: unable to open input file '" + vFilePath + "' *** ");
				logVector.log(e);
				throw new RuntimeException("unable to open input file '" + vFilePath + "'");
			}
		}

		try
		{
			// read line
			String line = bin.readLine();
			while (true)
			{
				if (line == null)
				{
					// eof - reopen file
					bin.close();
					bin = new BufferedReader(new InputStreamReader(new FileInputStream(vFilePath)));
					line = bin.readLine();
					if (line == null)
					{
						logVector.log("*** error: no data in input file '" + vFilePath + "' *** ");
						throw new RuntimeException("no data in input file '" + vFilePath + "'");
					}
				}

				// skip comments
				if ((line.length() > 0) && (line.charAt(0) == '#'))
				{
					line = bin.readLine();
					continue;
				}

				// skip empty line
				line = line.trim();
				if (line.length() == 0)
				{
					line = bin.readLine();
					continue;
				}

				// valid line
				break;
			}

			// split line into tokens
			VarInputFileLineParser lineParser = new VarInputFileLineParser(line, vVarDelemiter.charAt(0) , true);
			Vector resultVector = lineParser.getValueVector();
			for (int x = 0; x < resultVector.size(); x++)
			{
				switch (x)
				{
					case 0:
						vLineColumn1 = (String) resultVector.elementAt(0);
						break;
					case 1:
						vLineColumn2 = (String) resultVector.elementAt(1);
						break;
					case 2:
						vLineColumn3 = (String) resultVector.elementAt(2);
						break;
					case 3:
						vLineColumn4 = (String) resultVector.elementAt(3);
						break;
					case 4:
						vLineColumn5 = (String) resultVector.elementAt(4);
						break;
					case 5:
						vLineColumn6 = (String) resultVector.elementAt(5);
						break;
					default:
						break;
				}

			}
		}
		catch (Exception e)
		{
			logVector.log("*** error: unable to read line from input file '" + vFilePath + "' *** ");
			logVector.log(e);
			throw new RuntimeException("unable to read line from input file '" + vFilePath + "'");
		}
	}


	/**
	 * Return plug-in output parameter.
	 *
	 * output parameter #1: (String) vLineColumn1 / default value = '' / label "Line Column 1"
	 * output parameter #2: (String) vLineColumn2 / default value = '' / label "Line Column 2" / [optional]
	 * output parameter #3: (String) vLineColumn3 / default value = '' / label "Line Column 3" / [optional]
	 * output parameter #4: (String) vLineColumn4 / default value = '' / label "Line Column 4" / [optional]
	 * output parameter #5: (String) vLineColumn5 / default value = '' / label "Line Column 5" / [optional]
	 * output parameter #6: (String) vLineColumn6 / default value = '' / label "Line Column 6" / [optional]
	 *
	 * Note: all output parameters are always converted to strings.
	 */
	public Object getOutputParameter(int parameterNumber)
	{
		switch (parameterNumber)
		{
			case 0:
				return vLineColumn1;
			case 1:
				return vLineColumn2;
			case 2:
				return vLineColumn3;
			case 3:
				return vLineColumn4;
			case 4:
				return vLineColumn5;
			case 5:
				return vLineColumn6;
			default:
				return null;
		}
	}


	/**
	 * Finalize plug-in at end of load test.
	 */
	public void deconstruct(Object context)
	{
		if (bin != null)
		{
			try
			{
				bin.close();
			}
			catch (Exception e) {}
		}
	}


}	// end of class

