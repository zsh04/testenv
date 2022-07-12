/******************************************************
 * File: PrxGenericOutputFileV10.java
 */

import java.io.*;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.PerformanceData;
import dfischer.utils.SymmetricEncrypt;
import dfischer.utils.SymmetricEncryptContext;


public class PrxGenericOutputFileV10 implements LoadtestPluginInterface
{
	private final static int MAX_OUTPUT_PARAMS = 6;
	
	private LogVector logVector = new LogVector();			// internal log vector
	String[] outputValue = new String[MAX_OUTPUT_PARAMS];
	PrintWriter outputWriter = null;
	

	public int getPluginType()
	{
		return GenericPluginInterface.TYPE_LOADTEST_EXEC;
	}
	
	
	public String getPluginName()
	{
		return "Generic Output File (V1.0)";
	}
	
	
	public String getPluginDescription()
	{
		return "This plug-in provides a generic output file to which up to " + MAX_OUTPUT_PARAMS + " variables " +
		       "can be written per line.\n" +
		       "The output-values within a line are separated by semicolons (;).\n\n" +
			   "The execution scope of the plug-in is arbitrary selectable. However it is recommended to " +
			   "bind this plug-in to a loop or to an URL call so that variables with a scope of [loop] or [inner loop] can be written to the file."; 
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
		return LoadtestPluginInterface.EXEC_ORDER_AFTER;
	}
	
	
	public boolean allowMultipleUsage()
	{
		return false;
	}
	
	
	public String[] getInputParameterLabels()
	{
		String[] labels = new String[MAX_OUTPUT_PARAMS];
		for (int x = 0; x < MAX_OUTPUT_PARAMS; x++)
			labels[x] = "Output Value " + (x + 1);
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
		return new String[0];
	}
	
	
	public int allowOptionalOutputParameter()
	{
		return -1;
	}
	
	
	public void construct(Object context)
	{
		for (int x = 0; x < MAX_OUTPUT_PARAMS; x++)
			outputValue[x] = null;	
		
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		PerformanceData performanceData = pluginContext.getPerformanceData();
		
		// apply the same name as the *.prxres file for the output file name, but use *.log
		String outputFileName = performanceData.getResultFileName();
		int pointIndex = outputFileName.lastIndexOf(".");
		if (pointIndex != -1)
			outputFileName = outputFileName.substring(0, pointIndex);
		outputFileName = outputFileName + ".log";
		
		try
		{
            SymmetricEncryptContext encryptContext = pluginContext.getHttpLoadTest().getSymmetricEncryptContext();
            if (encryptContext != null)
            {
                outputWriter = new PrintWriter(new OutputStreamWriter(SymmetricEncrypt.getCipherOutputStream(new FileOutputStream(outputFileName), encryptContext)));
            }
            else
            {
			    outputWriter = new PrintWriter(new FileWriter(outputFileName));
            }
		}
		catch (Throwable tr)
		{
			tr.printStackTrace();
			throw new RuntimeException("unable to open output file " + outputFileName, tr);
		}
		
	}
	
	
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		outputValue[parameterNumber] = (String) parameterValue;
	}
	
	
	public void execute(Object context)
	{
		logVector = new LogVector();		// log currently not made
		
		// write output values to file
		int count = 0;
		for (int x = 0; x < MAX_OUTPUT_PARAMS; x++)
		{
			if (outputValue[x] != null)
			{
				if (count > 0)
					outputWriter.print(";");	
				
				outputWriter.print(outputValue[x]);
				count++;
			}
		}
		outputWriter.println();
		
		// clear output values
		for (int x = 0; x < MAX_OUTPUT_PARAMS; x++)
			outputValue[x] = null;	
	}
	
	
	public Object getOutputParameter(int parameterNumber)
	{
		return null;
	}
	
	
	public LogVector getLogVector()
	{
		return logVector;
	}
	
	
	public void deconstruct(Object context)
	{
		try
		{
			outputWriter.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("unable to close output file ");
		}
	}

}



	
