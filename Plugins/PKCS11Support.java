// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.HttpSocketPool;
import dfischer.utils.SSLInit;
import dfischer.utils.PKCS11Config;
import dfischer.utils.PKCS11ConfigCertificateItem;

import java.io.File;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;


/**
 * Load test add-on module.
 */
public class PKCS11Support implements LoadtestPluginInterface
{
	private String vPIN = "";		// input parameter #1 - label "PIN"
	private int vCertIndex = 0;		// input parameter #2 - label "Cert. Index (0..n-1)"
	private int vSlotNo = 0;		// input parameter #3 - label "Slot No. (0..n-1)"

	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	private static boolean           pluginIsInitialized = false;
	private static X509Certificate[] pluginX509CertificateChain = null;
	private static PrivateKey        pluginPrivateKey = null;

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
		return "PKCS#11 Security Device";
	}


	public String getPluginDescription()
	{
		return "Provide Support for PKCS#11 Security Devices which contain a SSL Client Certificate used for authentication against web servers.\n\n" +
		       "Notes: after generating the load test program you have to ZIP the compiled class of the load test program together with the following files:\n" +
		       "- PKCS11Support.class (this plug-in)\n" +
		       "- pkcs11wrapper.dll or libpkcs11wrapper.so (depending on your operating system))\n" +
		       "- the manufacturer specific DLL-library or SO-library of your PKCS#11 security device (for example: siecap11.dll)\n" +
		       "\n" +
		       "Plug-In Input Parameter 1: the PIN (password) of the PKCS#11 security device.\n" +
		       "Plug-In Input Parameter 2: the index of the certificate [0..n-1] (usually = 0)\n" +
		       "Plug-In Input Parameter 3: the slot number of the PKCS#11 security device [0..n-1] (usually = 0).\n\n" +
		       "WARNING: after 3 attempts of using a wrong PIN many security devices will switch to an inoperable state and cannot longer be used (damaged). " +
		       "Therefore it its strongly recommended that you verify the value of the PIN twice before you start the load test. " +
		       "It is also recommended to simulate at the first time only one user which executes only one loop.";
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
		String[] labels = new String[3];
		labels[0] = "PIN";
		labels[1] = "Cert. Index [0..n-1]";
		labels[2] = "Slot No. [0..n-1]";
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
	 * input parameter #1: (String) vPIN / default value = '' / label "PIN"
	 * input parameter #2: (int) vCertIndex / default value = '0' / label "Cert. Index (0..n-1)" / [optional]
	 * input parameter #3: (int) vSlotNo / default value = '0' / label "Slot No.  (0..n-1)" / [optional]
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vPIN = (String) parameterValue;
				break;
			case 1:
				vCertIndex = Integer.valueOf((String) parameterValue).intValue();
				break;
			case 2:
				vSlotNo = Integer.valueOf((String) parameterValue).intValue();
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

		if (!pluginIsInitialized)
		{
			initializePKCS11Device(vPIN, vCertIndex, vSlotNo);
			pluginIsInitialized = true;
			logVector.log("PKCS#11 device initialized");
		}

		// add client certificate to socket pool
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		HttpSocketPool socketPool = pluginContext.getSocketPool();
		socketPool.setClientCredentials(pluginX509CertificateChain, pluginPrivateKey);
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
	 * Initializes the PKCS#11 device and extracts the client certificate and a reference to the private key.
	 */
	private static void initializePKCS11Device(String pin, int certIndex, int slotNumber)
	{
		String pkcs11WrapperFile = null;
		String pkcs11NativeDeviceLibrary = null;  // this depends on the manufacturer of the PKCS#11 device

		// search the native device library in the current directory
		File f = new File(System.getProperty("user.dir"));
		File[] dirEntries = f.listFiles();
		for (int x = 0; x < dirEntries.length; x++)
		{
			if (!dirEntries[x].isDirectory())
			{
				String fileName = dirEntries[x].getName();
				if (fileName.toLowerCase().endsWith(".dll") || fileName.toLowerCase().endsWith(".so"))
				{
					if (fileName.equalsIgnoreCase("pkcs11wrapper.dll"))
					{
						pkcs11WrapperFile = fileName;
						continue;
					}
					if (fileName.equalsIgnoreCase("libpkcs11wrapper.so"))
					{
						pkcs11WrapperFile = fileName;
						continue;
					}

					// ok: native device library found
					pkcs11NativeDeviceLibrary = fileName;
				}
			}

			if ((pkcs11WrapperFile != null) && (pkcs11NativeDeviceLibrary != null))
				break;
		}

		if (pkcs11WrapperFile == null)
			throw new RuntimeException("error: generic PKCS#11 wrapper library not found in current directory");
		if (pkcs11NativeDeviceLibrary == null)
			throw new RuntimeException("error: native library for PKCS#11 device not found in current directory");
		System.out.println("generic PKCS#11 wrapper library = " + pkcs11WrapperFile);
		System.out.println("native library for PKCS#11 device = " + pkcs11NativeDeviceLibrary);

		// initialize the PKCS#11 device
		PKCS11Config pkcs11Config = new PKCS11Config();
		pkcs11Config.setActive(true);
		pkcs11Config.setOsLibraryFileName(pkcs11WrapperFile);
		pkcs11Config.setDeviceLibraryFileName(pkcs11NativeDeviceLibrary);
		pkcs11Config.setPin(pin);
		pkcs11Config.setSelectCertIndex(certIndex);
		pkcs11Config.setSlotNumber(slotNumber);

		try
		{
			pkcs11Config.acquireCertificateItems();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("--- PKCS#11 device misconfigured ---");
		}

		// check if certificates have been extracted
		PKCS11ConfigCertificateItem[] certItems = pkcs11Config.getCertificateItems();
		if (certItems == null)
			throw new RuntimeException("--- PKCS#11 device not available ---");
		if (certItems.length == 0)
			throw new RuntimeException("--- no certificate(s) available in PKCS#11 device (empty array) ---");

		// select certificate
		if ((pkcs11Config.getSelectCertIndex() < certItems.length) && (pkcs11Config.getSelectCertIndex() >= 0))
		{
			PKCS11ConfigCertificateItem pkcs11ConfigCertificateItem = certItems[pkcs11Config.getSelectCertIndex()];

			String serialNoStr = "" + pkcs11ConfigCertificateItem.getX509CertificateChain()[0].getSerialNumber();
			while (serialNoStr.length() < 24)
				serialNoStr = serialNoStr + " ";

			System.out.println("+--------------------------------------+");
			System.out.println("| Active PKCS#11 Client Certificate    |");
			System.out.println("| Serial No. = " + serialNoStr + "|");
			System.out.println("+--------------------------------------+");

			pluginX509CertificateChain = pkcs11ConfigCertificateItem.getX509CertificateChain();
            pluginPrivateKey = pkcs11ConfigCertificateItem.getPrivateKey();
            return;
		}
		else
		{
			throw new RuntimeException("error: invalid certificate index configured for PKCS#11 device ---");
		}
	}



	// ----------------------------------------------------------------------------
	// PART 3: stand-alone test utility - optional - used for plug-in development
	// ----------------------------------------------------------------------------

	public static void main(String[] args)
	{
		try
		{
			SSLInit.execute();
			initializePKCS11Device("754268", 1, 0);
			System.out.println("" + pluginX509CertificateChain[0]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}	// end of class

