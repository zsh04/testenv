// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.VarInputFileLineParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

/**
 * Load test add-on module.
 */
public class InputFileList implements LoadtestPluginInterface
{
	private String vMetaFileName = "";		// input parameter #1 - label "Meta File Name"
	private String vVarDelimChar = ";";		// input parameter #2 - label "Variable Delimiter Char"

	private String vLineColumn1 = "";		// output parameter #1 - label "Line Column 1"
	private String vLineColumn2 = "";		// output parameter #2 - label "Line Column 2"
	private String vLineColumn3 = "";		// output parameter #3 - label "Line Column 3"
	private String vLineColumn4 = "";		// output parameter #4 - label "Line Column 4"
	private String vLineColumn5 = "";		// output parameter #5 - label "Line Column 5"
	private String vLineColumn6 = "";		// output parameter #6 - label "Line Column 6"
	private String vLineColumn7 = "";		// output parameter #7 - label "Line Column 7"
	private String vLineColumn8 = "";		// output parameter #8 - label "Line Column 8"
	private String vLineColumn9 = "";		// output parameter #9 - label "Line Column 9"
	private String vLineColumn10 = "";		// output parameter #10 - label "Line Column 10"
	private String vLineColumn11 = "";		// output parameter #11 - label "Line Column 11"
	private String vLineColumn12 = "";		// output parameter #12 - label "Line Column 12"
	private String vLineColumn13 = "";		// output parameter #13 - label "Line Column 13"
	private String vLineColumn14 = "";		// output parameter #14 - label "Line Column 14"
	private String vLineColumn15 = "";		// output parameter #15 - label "Line Column 15"
	private String vLineColumn16 = "";		// output parameter #16 - label "Line Column 16"
	private String vLineColumn17 = "";		// output parameter #17 - label "Line Column 17"
	private String vLineColumn18 = "";		// output parameter #18 - label "Line Column 18"
	private String vLineColumn19 = "";		// output parameter #19 - label "Line Column 19"
	private String vLineColumn20 = "";		// output parameter #20 - label "Line Column 20"

	private static Vector inputFileNameVector = null;	// static - list of input files for all users
	private BufferedReader binUser = null;				// non static - input file per user
	private int loopCounter = 0;						// loop counter per user - ony for debug output

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
		return "Input File List";
	}


	public String getPluginDescription()
	{
		return "Reads from a meta file a list of input files and assigns each simulated user an own input file. The simulated users are reading a new line from their input file each time before they are executing a new loop.\n\nMeta File: each line must contain only one file name. If more users are simulated than lines are available the load test will be aborted.\n\nReading the user specific input files: the default variable delimiter character is ; (semicolon) and can be modified by an optional input parameter. If EOF occurs the input file is reopened. Up to 20 variables can be extracted per line.\n\nThe meta file as well as the input files support commented out lines which are starting with a # (hash character).\n\nLoad test execution: before load test executing can start you have to ZIP the load test program (*.class) together with this plug-in (InputFileList.class) and together with the meta file and all of the user specific input files to one ZIP archive. After that you must execute the ZIP archive as load test.";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_USER;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_LOOP;
	}


	public int getAllowedExecOrder()
	{
		return LoadtestPluginInterface.EXEC_ORDER_BEFORE;
	}


	public boolean allowMultipleUsage()
	{
		return false;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[2];
		labels[0] = "Meta File Name";
		labels[1] = "Variable Delimiter Char";
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
		String[] labels = new String[20];
		labels[0] = "Line Column 1";
		labels[1] = "Line Column 2";
		labels[2] = "Line Column 3";
		labels[3] = "Line Column 4";
		labels[4] = "Line Column 5";
		labels[5] = "Line Column 6";
		labels[6] = "Line Column 7";
		labels[7] = "Line Column 8";
		labels[8] = "Line Column 9";
		labels[9] = "Line Column 10";
		labels[10] = "Line Column 11";
		labels[11] = "Line Column 12";
		labels[12] = "Line Column 13";
		labels[13] = "Line Column 14";
		labels[14] = "Line Column 15";
		labels[15] = "Line Column 16";
		labels[16] = "Line Column 17";
		labels[17] = "Line Column 18";
		labels[18] = "Line Column 19";
		labels[19] = "Line Column 20";

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
	// This part requires manual programming (see sample code section below)
	// ----------------------------------------------------------------------------


	/**
	 * Initialize plug-in at start of user (new instance per user).
	 */
	public void construct(Object context)
	{
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}


	/**
	 * Transfer input parameter before execute() is called.
	 *
	 * input parameter #1: (String) vMetaFileName / default value = '' / label "Meta File Name"
	 * input parameter #2: (String) vVarDelimChar / default value = '' / label "Variable Delimiter Char" / [optional]
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vMetaFileName = (String) parameterValue;
				break;
			case 1:
				vVarDelimChar = (String) parameterValue;
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in at start of loop (per loop).
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		logVector.log("# user no. = " + pluginContext.getUserNr() + ", loop no. = " + loopCounter);

		// read meta file
		if (inputFileNameVector == null)
		{
			inputFileNameVector = new Vector();

			try
			{
				BufferedReader bin = new BufferedReader(new FileReader(vMetaFileName));
				String line = bin.readLine();
				while (line != null)
				{
					if ((line.trim().length() == 0) || line.startsWith("#"))
					{
						line = bin.readLine();
						continue;
					}

					inputFileNameVector.addElement(line.trim());
					line = bin.readLine();
				}

				bin.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("" + e);
			}
		}


		// open user specific input file
		if (binUser == null)
		{
			try
			{
				binUser = new BufferedReader(new FileReader((String) inputFileNameVector.elementAt(pluginContext.getUserNr())));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("" + e);
			}
		}

		// read next line from user specific input file
		try
		{
			// read line
			String line = binUser.readLine();
			while (true)
			{
				if (line == null)
				{
					// eof - reopen file
					binUser.close();
					binUser = new BufferedReader(new FileReader((String) inputFileNameVector.elementAt(pluginContext.getUserNr())));
					line = binUser.readLine();
					if (line == null)
					{
						logVector.log("*** error: no data in input file '" + (String) inputFileNameVector.elementAt(pluginContext.getUserNr()) + "' *** ");
						throw new RuntimeException("no data in input file '" + (String) inputFileNameVector.elementAt(pluginContext.getUserNr()) + "'");
					}
				}

				// skip comments
				if ((line.length() > 0) && (line.charAt(0) == '#'))
				{
					line = binUser.readLine();
					continue;
				}

				// skip empty line
				line = line.trim();
				if (line.length() == 0)
				{
					line = binUser.readLine();
					continue;
				}

				// valid line
				break;
			}

			// split line into tokens
			VarInputFileLineParser lineParser = new VarInputFileLineParser(line, vVarDelimChar.charAt(0) , true);
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
					case 6:
						vLineColumn7 = (String) resultVector.elementAt(6);
						break;
					case 7:
						vLineColumn8 = (String) resultVector.elementAt(7);
						break;
					case 8:
						vLineColumn9 = (String) resultVector.elementAt(8);
						break;
					case 9:
						vLineColumn10 = (String) resultVector.elementAt(9);
						break;
					case 10:
						vLineColumn11 = (String) resultVector.elementAt(10);
						break;
					case 11:
						vLineColumn12 = (String) resultVector.elementAt(11);
						break;
					case 12:
						vLineColumn13 = (String) resultVector.elementAt(12);
						break;
					case 13:
						vLineColumn14 = (String) resultVector.elementAt(13);
						break;
					case 14:
						vLineColumn15 = (String) resultVector.elementAt(14);
						break;
					case 15:
						vLineColumn16 = (String) resultVector.elementAt(15);
						break;
					case 16:
						vLineColumn17 = (String) resultVector.elementAt(16);
						break;
					case 17:
						vLineColumn18 = (String) resultVector.elementAt(17);
						break;
					case 18:
						vLineColumn19 = (String) resultVector.elementAt(18);
						break;
					case 19:
						vLineColumn20 = (String) resultVector.elementAt(19);
						break;
					default:
						break;
				}

			}
		}
		catch (Exception e)
		{
			logVector.log("*** error: unable to read line from input file '" + (String) inputFileNameVector.elementAt(pluginContext.getUserNr()) + "' *** ");
			logVector.log(e);
			throw new RuntimeException("unable to read line from input file '" + (String) inputFileNameVector.elementAt(pluginContext.getUserNr()) + "'");
		}

		loopCounter++;	// loop counter per user - ony for debug output
	}


	/**
	 * Return plug-in output parameter.
	 *
	 * output parameter #1: (String) vLineColumn1 / default value = '' / label "Line Column 1"
	 * output parameter #2: (String) vLineColumn2 / default value = '' / label "Line Column 2" / [optional]
	 * output parameter #3: (String) vLineColumn3 / default value = '' / label "Line Column 3" / [optional]
	 * output parameter #4: (String) vLineColumn4 / default value = '' / label "Line Column 4" / [optional]
	 * output parameter #5: (String) vLineColumn4 / default value = '' / label "Line Column 5" / [optional]
	 * output parameter #6: (String) vLineColumn4 / default value = '' / label "Line Column 6" / [optional]
	 * output parameter #7: (String) vLineColumn4 / default value = '' / label "Line Column 7" / [optional]
	 * output parameter #8: (String) vLineColumn4 / default value = '' / label "Line Column 8" / [optional]
	 * output parameter #9: (String) vLineColumn4 / default value = '' / label "Line Column 9" / [optional]
	 * output parameter #10: (String) vLineColumn4 / default value = '' / label "Line Column 10" / [optional]
	 * output parameter #11: (String) vLineColumn4 / default value = '' / label "Line Column 11" / [optional]
	 * output parameter #12: (String) vLineColumn4 / default value = '' / label "Line Column 12" / [optional]
	 * output parameter #13: (String) vLineColumn4 / default value = '' / label "Line Column 13" / [optional]
	 * output parameter #14: (String) vLineColumn4 / default value = '' / label "Line Column 14" / [optional]
	 * output parameter #15: (String) vLineColumn4 / default value = '' / label "Line Column 15" / [optional]
	 * output parameter #16: (String) vLineColumn4 / default value = '' / label "Line Column 16" / [optional]
	 * output parameter #17: (String) vLineColumn4 / default value = '' / label "Line Column 17" / [optional]
	 * output parameter #18: (String) vLineColumn4 / default value = '' / label "Line Column 18" / [optional]
	 * output parameter #19: (String) vLineColumn4 / default value = '' / label "Line Column 19" / [optional]
	 * output parameter #20: (String) vLineColumn4 / default value = '' / label "Line Column 20" / [optional]
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
			case 6:
				return vLineColumn7;
			case 7:
				return vLineColumn8;
			case 8:
				return vLineColumn9;
			case 9:
				return vLineColumn10;
			case 10:
				return vLineColumn11;
			case 11:
				return vLineColumn12;
			case 12:
				return vLineColumn13;
			case 13:
				return vLineColumn14;
			case 14:
				return vLineColumn15;
			case 15:
				return vLineColumn16;
			case 16:
				return vLineColumn17;
			case 17:
				return vLineColumn18;
			case 18:
				return vLineColumn19;
			case 19:
				return vLineColumn20;

			default:
				return null;
		}
	}


	/**
	 * Finalize plug-in at end of user.
	 */
	public void deconstruct(Object context)
	{
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		// close user specific input file
		if (binUser != null)
		{
			try
			{
				binUser.close();
			}
			catch (Exception e)
			{
			}
		}
	}



}	// end of class

