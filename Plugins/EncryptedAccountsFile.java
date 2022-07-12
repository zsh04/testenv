// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Apica AB, Sweden.
// Copyright for manual written code belongs to Apica AB, Sweden.

import dfischer.utils.*;

import java.io.*;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Load test add-on module.
 */
public class EncryptedAccountsFile implements LoadtestPluginInterface
{
    public static final String ENC_FILE_NAME = "EncryptedAccounts.txt"; // name of encrypted file

    public static final char LINE_COMMENT_CHAR = '#';       // ignore lines starting with this char. supported only in plain-input file - but not in encrypted file
    public static final char FIELD_DELIMITER_CHAR = ';';    // field delimiter char between username and password

    public static final byte[] SYMMETRIC_KEY = calcBinaryKey("-replace-this-txt-by-your-own-key");      // change this string to any value before using the plug-in
    public static final String ENCRYPTION_ALGORITHM = "AES/ECB/PKCS5Padding";       // 128-Bit AES encryption

    private String vUsername = "";		// output parameter #1 - label "Username"
    private String vPassword = "";		// output parameter #2 - label "Password"

    private BufferedReader binEnc = null;   // used to read the encrypted file during load test execution
    private Cipher cEnc = null;             // performs the decryption
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
        return "Encrypted Accounts File";
    }


    public String getPluginDescription()
    {
        return "Read a file (named " + ENC_FILE_NAME + ") that contains on each encrypted line a user name and a password.\n\n" +
                "Before using this plug-in please modify first the source code and set a new value for SYMMETRIC_KEY. Then recompile the plug-in.\n\n" +
                "You have also to add " + ENC_FILE_NAME + " as an external resource to your recorded session - don't add it as standard input file!\n\n" +
                "To debug user names and passwords at runtime enable debug loops when starting the load test.\n\n" +
                "Note: You can call this class as a stand-alone utility to create " + ENC_FILE_NAME + " from any input file " +
                "that contains per line a user name and a password, separated by a '" + FIELD_DELIMITER_CHAR + "' char.";
    }


    public int getAllowedConstructScope()
    {
        return LoadtestPluginInterface.CONSTRUCT_SCOPE_GLOBAL;
    }


    public int getAllowedExecScope()
    {
        return LoadtestPluginInterface.EXEC_SCOPE_LOOP;     // new account per loop - can be changed to EXEC_SCOPE_USER if needed (new account per user)
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
        String[] labels = new String[2];
        labels[0] = "Username";
        labels[1] = "Password";
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
        try
        {
            binEnc = new BufferedReader(new FileReader(ENC_FILE_NAME));
        }
        catch (Exception e)
        {
            throw new RuntimeException("unable to open file " + ENC_FILE_NAME, e);
        }
        try
        {
            cEnc = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            SecretKeySpec k = new SecretKeySpec(SYMMETRIC_KEY, "AES");
            cEnc.init(Cipher.DECRYPT_MODE, k);
        }
        catch (Exception e)
        {
            throw new RuntimeException("unable initialize decryption algorithm", e);
        }
        catch (Error err)
        {
            throw new RuntimeException("unable initialize decryption algorithm", err);
        }
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
     * Execute plug-in at start of loop (per loop).
     *
     * Intrinsic plug-in implementation.
     */
    public void execute(Object context)
    {
        logVector = new LogVector();

        try
        {
            // read next encrypted line
            String encLine = "";
            boolean wasAlreadyReopen = false;
            while (encLine.length() == 0)
            {
                encLine = binEnc.readLine();

                // re-open file?
                if (encLine == null)
                {
                    if (wasAlreadyReopen)
                        throw new RuntimeException("invalid data content of " + ENC_FILE_NAME + ". no valid lines");

                    binEnc.close();
                    binEnc = new BufferedReader(new FileReader(ENC_FILE_NAME));
                    wasAlreadyReopen = true;
                    encLine = "";
                    continue;
                }

                encLine = encLine.trim();
            }

            // convert encrypted base 64 line to encrypted byte array
            byte[] encryptedLine = Base64Decoder.decodeToBytes(encLine);

            // decrypt data
            byte[] decryptedData = cEnc.doFinal(encryptedLine);
            String decryptedLine = new String(decryptedData, "UTF-8");

            // extract username and password from line
            StringTokenizer strtok = new StringTokenizer(decryptedLine, "" + FIELD_DELIMITER_CHAR);
            if (strtok.hasMoreTokens())
                vUsername = strtok.nextToken().trim();
            if (strtok.hasMoreTokens())
                vPassword = strtok.nextToken().trim();
        }
        catch (Exception e)
        {
            throw new RuntimeException("*** internal error in plug-in ***", e);
        }
    }


    /**
     * Return plug-in output parameter.
     *
     * output parameter #1: (String) vUsername / default value = '' / label "Username"
     * output parameter #2: (String) vPassword / default value = '' / label "Password"
     *
     * Note: all output parameters are always converted to strings.
     */
    public Object getOutputParameter(int parameterNumber)
    {
        switch (parameterNumber)
        {
            case 0:
                return vUsername;
            case 1:
                return vPassword;
            default:
                return null;
        }
    }


    /**
     * Finalize plug-in at end of load test.
     */
    public void deconstruct(Object context)
    {
        try
        {
            binEnc.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("unable to close file " + ENC_FILE_NAME, e);
        }
    }



    // ----------------------------------------------------------------------------
    // PART 3: stand-alone utility that creates the encrypted file.
    //
    // Argument[0] = "encrypt"
    // Argument[1] = input file (unencrypted/plain data)
    //
    // or, for test purposes only:
    //
    // Argument[0] = "decrypt"
    // Argument[1] = "<key-string>" (pass the same value as SYMMETRIC_KEY has)
    // ----------------------------------------------------------------------------
    public static void main(String[] args)
    {
        try
        {
            if (args.length != 2)
                throw new IllegalArgumentException("invalid number or arguments passed");

            if (args[0].equalsIgnoreCase("encrypt"))
            {
                // encrypt the file
                // ----------------
                Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);
                SecretKeySpec k = new SecretKeySpec(SYMMETRIC_KEY, "AES");
                c.init(Cipher.ENCRYPT_MODE, k);

                BufferedReader bin = new BufferedReader(new FileReader(args[1]));
                PrintWriter pout = new PrintWriter(new FileWriter(ENC_FILE_NAME));
                String line = bin.readLine();
                while (line != null)
                {
                    line = line.trim();

                    if ((line.length() > 0) && (!line.startsWith("" + LINE_COMMENT_CHAR)))
                    {
                        // encrypt line
                        byte[] encryptedLine = c.doFinal(line.getBytes("UTF-8"));

                        // convert encrypted line to base 64 format
                        String encryptedLineB64 = Base64Encoder.encode(encryptedLine);

                        // write encrypted base 64 line to output file
                        pout.println(encryptedLineB64);
                    }
                    line = bin.readLine();
                }
                bin.close();
                pout.close();
                return;
            }

            if (args[0].equalsIgnoreCase("decrypt"))
            {
                // decrypt the file
                // ----------------

                // note: if you pass as argument[1] a wrong key you get javax.crypto.BadPaddingException: Given final block not properly padded

                byte[] symmetricEnteredKey = calcBinaryKey(args[1]);

                Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);
                SecretKeySpec k = new SecretKeySpec(symmetricEnteredKey, "AES");
                c.init(Cipher.DECRYPT_MODE, k);

                BufferedReader bin = new BufferedReader(new FileReader(ENC_FILE_NAME));
                String line = bin.readLine();
                while (line != null)
                {
                    line = line.trim();

                    if (line.length() > 0)
                    {
                        // convert encrypted base 64 line to encrypted byte array
                        byte[] encryptedLine = Base64Decoder.decodeToBytes(line);

                        // decrypt encrypted byte array
                        byte[] decryptedData = c.doFinal(encryptedLine);

                        // write decrypted data stdout
                        System.out.println(new String(decryptedData, "UTF-8"));
                    }
                    line = bin.readLine();
                }
                bin.close();
                return;
            }

            throw new IllegalArgumentException("first argument must be encrypt or decrypt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Calculates from any text string a binary key by using the MD5 algorithm.
     *
     * @param key   key input string
     *
     * @return      the binary key as a byte[16] array.
     */
    private static byte[] calcBinaryKey(String key)
    {
        MD5 md5 = new MD5(key);
        md5.calc();
        int[] regs = md5.getregs();

        byte[] binaryKey = new byte[16];

        // convert the MD5 integer array to a byte array
        int byteOffset = 0;
        for (int i = 0; i < 4; i++)
        {
            binaryKey[byteOffset] = (byte)(regs[i]);
            byteOffset++;
            binaryKey[byteOffset] = (byte)(regs[i] >> 8);
            byteOffset++;
            binaryKey[byteOffset] = (byte)(regs[i] >> 16);
            byteOffset++;
            binaryKey[byteOffset] = (byte)(regs[i] >> 24);
            byteOffset++;
        }

        return binaryKey;
    }


}	// end of class

