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

import java.util.concurrent.locks.ReentrantReadWriteLock;



/**
 * Load test add-on module.
 */
public class MassivePeakLoad extends Thread implements LoadtestPluginInterface
{
	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data
	private static volatile LoadtestPluginContext pluginContext = null;
	private static volatile ReentrantReadWriteLock lock = null;
	private static volatile boolean lockCreated = false;


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
		return "Massive Peak Load";
	}


	public String getPluginDescription()
	{
		return "Allows you to generate a massive peak of load. This plug-in blocks all simulated users at the start of the first loop. Later, at real time, you can manually change the value of the user input field called \"Start Peak Load\" from false to true which effects that all of the simulated users are instantly executing their loops.\n\nIn order that this plug-in works, you have to define by yourself a user input field with the following settings:\n\no Var Name: vStartPeakLoad\n\no Var Label Text: Start Peak Load\n\no Default Value: false\n\no Enable the checkbox: [x] Enable Value Changes in Real-Time - During Test Execution\n\n";
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
		return LoadtestPluginInterface.EXEC_ORDER_BEFORE;
	}


	public boolean allowMultipleUsage()
	{
		return false;
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
		pluginContext = (LoadtestPluginContext) context;

		// create and start the internal thread
		MassivePeakLoad internalThread = new MassivePeakLoad();
		internalThread.start();

		// wait until the internal thread has created the lock
		try
		{
			while (!lockCreated)
				Thread.currentThread().sleep(1L);
		}
		catch (InterruptedException ie) {}
	}


	/**
	 * Transfer input parameter before execute() is called.
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
	 * Internal thread that detects when the value of the real time user input field becomes true
	 */
	public void run()
	{
		try
		{
			// create an acquire the write lock
			lock = new ReentrantReadWriteLock();
			lock.writeLock().lock();
			lockCreated = true;

			while (true)
			{
				if (pluginContext.getHttpLoadTest().abortedByRemote())
				{
					if (lock.isWriteLocked())
						lock.writeLock().unlock();
					return;
				}

				String vStartPeakLoad = System.getProperty("vStartPeakLoad");
				if (vStartPeakLoad != null)
				{
					if (vStartPeakLoad.equalsIgnoreCase("true"))
					{
						if (lock.isWriteLocked())
							lock.writeLock().unlock();
					}
					else
					{
						if (!lock.isWriteLocked())
							lock.writeLock().lock();
					}
				}
				Thread.currentThread().sleep(1L);	// sleep a millisecond
			}
		}
		catch (Throwable tr)
		{
			tr.printStackTrace();
		}
	}


	/**
	 * Execute plug-in at start of loop (per loop).
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		// logVector = new LogVector();
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;

		// acquire the read lock for a short time
		lock.readLock().lock();
		lock.readLock().unlock();
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
     * Internal test program to verify the overhead for sleeping on minute in 1 millisecond intervals.
     * Result: No overhead.
      */
    public static void main(String[] args)
    {
        try
        {
            for (int x = 0; x < (60 * 1000); x++)
                Thread.currentThread().sleep(1L);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}	// end of class

