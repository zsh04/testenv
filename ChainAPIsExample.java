
// *********************************************************************************************
//
// Automatically Generated Load Test Program
// -----------------------------------------
//
// Source: ChainAPIsExample.java
// Date  : 28 Sep 2021 07:53:49 PST
// Author: Apica ZebraTester V7.0-B / automatically generated
//
// Procedure Copyright by Apica
// All Rights Reserved
//
// https://www.apica.io                                         http://www.zebratester.com
// *********************************************************************************************


import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.*;
import org.apache.commons.lang3.StringUtils;
import dfischer.utils.AbstractInputFileReader;
import dfischer.utils.Base64Decoder;
import dfischer.utils.Base64Encoder;
import dfischer.utils.ConvertToDoubleNumber;
import dfischer.utils.ContentTokenExtractor;
import dfischer.utils.ContentTokenExtractorItem;
import dfischer.utils.Cookie;
import dfischer.utils.CookieHandler;
import dfischer.utils.DigestAuthContext;
import dfischer.utils.DNSCache;
import dfischer.utils.DNSTranslationTable;
import dfischer.utils.DynamicProtobufContentParser;
import dfischer.utils.ExternalParamFile;
import dfischer.utils.FileCache;
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.GetRealTimeUserInputFieldsInterface;
import dfischer.utils.HtmlContentParser;
import dfischer.utils.HtmlHeaderCookieExtractor;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.HttpLoadTestIncrementUserThread;
import dfischer.utils.HttpLoadTestUserContext;
import dfischer.utils.HttpSocketPool;
import dfischer.utils.HttpTestURL;
import dfischer.utils.HttpTestURLDNSContext;
import dfischer.utils.HttpTestURLThreadHandler;
import dfischer.utils.InlineScriptExecutionException;
import dfischer.utils.InlineScriptExecutor;
import dfischer.utils.InnerLoopContext;
import dfischer.utils.JSONHtmlBasedExtractor;
import dfischer.utils.JSONPathBasedExtractor;
import dfischer.utils.JSONPathBasedExtractorItem;
import dfischer.utils.Lib;
import dfischer.utils.LoadtestInlineScriptContext;
import dfischer.utils.LoadtestInlineScriptVar;
import dfischer.utils.LoadtestPluginClassLoader;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.NextProxyConfig;
import dfischer.utils.ParseArgs;
import dfischer.utils.ParseUrl;
import dfischer.utils.PerformanceData;
import dfischer.utils.PerformanceDataTickExtension;
import dfischer.utils.ProtobufFieldAndValueElement;
import dfischer.utils.ProtobufLib;
import dfischer.utils.RealTimeUserInputField;
import dfischer.utils.ScreenshotImage;
import dfischer.utils.SetThreadStepInterface;
import dfischer.utils.SSLInit;
import dfischer.utils.SSLSessionCacheStatistic;
import dfischer.utils.SSLSessionCacheStatisticInterface;
import dfischer.utils.SuspendResumeInterface;
import dfischer.utils.SymmetricEncrypt;
import dfischer.utils.SymmetricEncryptContext;
import dfischer.utils.TextLineTokenExtractor;
import dfischer.utils.TextLineTokenExtractorItem;
import dfischer.utils.ThreadStepInterface;
import dfischer.utils.UserInputField;
import dfischer.utils.UserTransactionContext;
import dfischer.utils.UserTransactionRuntimeHandler;
import dfischer.utils.VarInputFileReader;
import dfischer.utils.VarRandomInputFileReader;
import dfischer.utils.VaryingLoadInterface;
import dfischer.utils.VaryingTestDurationInterface;
import dfischer.utils.XmlContentParser;
import dfischer.utils.XmlDoctypeCommentParser;
import dfischer.utils.ZoneTime;
import dfischer.proxysniffer.ProxySnifferVarSourceInlineScript;
import dfischer.utils.WebSocketPluginInterface;
import dfischer.utils.BoundaryBasedExtractor;
import dfischer.utils.BoundaryBasedExtractorItem;
import dfischer.utils.RegExpBasedExtractor;
import dfischer.utils.RegExpBasedExtractorItem;
import dfischer.utils.XpathBasedExtractor;
import dfischer.utils.XpathBasedExtractorItem;
import dfischer.proxysniffer.WebSocketData;
import dfischer.utils.HttpTestWebsocketContext;
import dfischer.websocket.*;
import dfischer.utils.DataCollectionSequence;
import dfischer.proxysniffer.ProxyDataRecordActionUserDefinedDataPoint;
import replay.ReplaySnapShotsDump;
import dfischer.utils.BrowserCacheStatistic;
import dfischer.generator.ReplaceVarPattern;


/**
 * Automatically generated load test program.
 */
public class ChainAPIsExample extends HttpLoadTest implements Runnable, ThreadStepInterface, SetThreadStepInterface, SSLSessionCacheStatisticInterface, VaryingLoadInterface, VaryingTestDurationInterface, SuspendResumeInterface, GetRealTimeUserInputFieldsInterface
{
	public static final String prxVersion = "V7.0-B";
	public static final int    prxCharEncoding = 3;                         // 1 = OS Default, 2 = ISO-8859-1, 3 = UTF-8
	public static final String testDescription = "example sushi restaurant";

	public static String USER_AGENT_1 = "PostmanRuntime/7.28.4";

	private static final boolean CONTAINS_PARALLEL_EXECUTED_URLS = false;
	private static final int MAX_PARALLEL_THREADS_PER_USER = 6;                       // default value for max. parallel executed URLs per user
	private static int maxParallelThreadsPerUser = MAX_PARALLEL_THREADS_PER_USER;     // configured value for max. parallel executed URLs per user

	private static final boolean CONTAINS_EXTERNAL_RESOURCES = false;       // note: external resources are typically additional Java library files (*.jar files) invoked by self-developed plug-ins. Consider that Input Files and the Main Class of Plug-Ins are NOT external resources in this context because ZebraTester knows already their declarations.

	public static final String USER_INPUT_FIELD_consumerKey = "AQALY29uc3VtZXJLZXkBABRUd2l0dGVyIENvbnN1bWVyIEtleQEAGThJcXlnc2hqcTFmenBlaDh2dWE4d05lQ3g@";		// internal object-string representation of user input field 'consumerKey'
	public static final String USER_INPUT_FIELD_twitterHandler = "AQAOdHdpdHRlckhhbmRsZXIBABBUd2l0dGVyIFVzZXJuYW1lAQAJWnNoTWFsbG9j";		// internal object-string representation of user input field 'twitterHandler'
	public static final String USER_INPUT_FIELD_address = "AQAHYWRkcmVzcwEACU15QWRkcmVzcwEAMTEwODUzJTIwcm9zZSUyMGF2ZSwlMjBsb3MlMjBhbmdlbGVzLCUyMGNhJTIwOTAwMzQ@";		// internal object-string representation of user input field 'address'
	public static final String USER_INPUT_FIELD_ConsumerSecret = "AQAOQ29uc3VtZXJTZWNyZXQBABdUd2l0dGVyIENvbnN1bWVyIFNlY3JldAEAMnFiZkJoa1kwNjFjSm5PNEhsVmRWamRVbTdRdE5nMGhzVkpjV2NWQ2tsdjdZT09LMWJz";		// internal object-string representation of user input field 'ConsumerSecret'
	public static final String USER_INPUT_FIELD_key = "AQADa2V5AQAOR29vZ2xlIEFQSSBLZXkBACdBSXphU3lDUkpQcmxyTThCOFlkaXBRQ1NIMlk3WHpaQkFpbGJOZ0k@";		// internal object-string representation of user input field 'key'

	// --- WebSocket Object ---
	WebSocketData webSocketData = null;                                     // WebSocket Object that contains data of recorded WebSocket frames.
	WebSocketPluginInterface[] webSocketPluginsForURL = null;               // all assiciated web socket plugins for specific URL.

	// --- proxy configuration ---
	private static boolean proxyEnabled = false;                             // if false: do not use a proxy server
	private static boolean proxyCacheDisabled = true;
	private static String  proxyHttpHost = "";
	private static int     proxyHttpPort = -1;
	private static String  proxyHttpsHost = "";
	private static int     proxyHttpsPort = -1;
	private static String  noProxyFor = "";
	private static String  proxyAuthorization = null;
	private static NextProxyConfig commonNextProxyConfig = null;
	private NextProxyConfig userNextProxyConfig = null;
	
	private static int plannedStartupDelay = 200;                           // startup delay between concurrent users in milliseconds, see main argument "-sdelay"
	private static int plannedTestDuration = -1;                            // planned load test duration in seconds, 0 = unlimited, see main argument "-d"
	private static int maxPlannedLoops = 0;  								// maximum planned loops per user, 0 = unlimited, see main argument "-maxloops"
	private static int pacing = 0;  								        // pacing for loops per user, 0 = no pacing, see main argument "-pacing"
	private static int plannedRequestTimeout = 0;                           // planned request timeout in seconds, see main argument "-t"
	private static int plannedConnectTimeout = 0;                           // planned TCP/IP socket connect timeout in seconds (default = use plannedRequestTimeout), see main argument "-tconnect"
	
	private static String defaultTimeZone = "PST";                          // use main argument -tz <timezone> to alter
	private static char defaultNumberGroupingSeparator = ',';               // use main argument -dgs a|c to alter
	private static String sslProtocolVersion = "all";                     // applied ssl protocol version: "all" = v3/tls/tls11/tls12/tls13, use main argument -ssl to set a fixed protocol version
	private static boolean sslcmode = false;                                // support of deficient ssl servers, use main argument -sslcmode to enable
	private static boolean sslECC = true;                                   // elliptic curve cryptography (ECC) is enabled by default, use main argument -noECC to disable
	private static boolean sslSNI = true;                                   // server name indication (SNI) is enabled by default, use main argument -nosni to disable
	private static boolean sslSNICirical = false;                           // the SNI TLS extension is by default set as non-critical, use main argument -snicritical to set as critical
	private static boolean useTlsSessionTickets = false;                    // Session Tickets can only be used with TLS, use main argument -tlssessiontickets to set to use
	private static final long loopErrorDelay = 20;                          // error delay (in milliseconds) applied if a loop of a virtual user has failed
	private static final String THREAD_NAME = "T000000";                    // internal
	private static Integer dumpLock = new Integer(0);                       // internal
	private static Integer inputFileLock = new Integer(0);                  // internal
	private volatile int threadStep = ThreadStepInterface.THREAD_NO_STEP;   // internal
	private boolean urlCallPassed = false;                                  // internal
	private String localIpAddress = null;                                   // internal
	private static long downlinkBandwidth = 0;                              // max. downlink bandwidth per user. 0 = unlimited. use main argument -downlink <kbps> to alter
	private static long uplinkBandwidth = 0;                                // max. uplink bandwidth per user. 0 = unlimited. use main argument -uplink <kbps> to alter
	private static boolean dnsPerLoop = false;								// true if main argument "-dnsperloop" is set = perform new DNS resolves for each executed loop. normally false
	private HttpTestURLDNSContext userDNSContext = null;                    // user specific DNS context - normally null when no special DNS servers are used
	
	private static volatile boolean debugFailedLoops = false;               // true if main argument "-dfl" is set
	private static volatile boolean debugLoops = false;                     // true if main argument "-dl" is set
	private static volatile boolean debugHttp = false;                      // true if main argument "-dh" is set
	private static volatile boolean debugContent = false;                   // true if main argument "-dc" is set
	private static volatile boolean debugCookies = false;                   // true if main argument "-dC" is set
	private static volatile boolean debugKeepAlive = false;                 // true if main argument "-dK" is set
	private static volatile boolean debugSsl = false;                       // true if main argument "-dssl" is set
	
	private static String resultFile = null;                                // name of binary test result file or null
	private static String browserLanguage = null;                           // name of browser language, argument -browserlang <text>
	private static boolean replayMode = false;                              // indicated the replay mode of the load test, use main argument -replay to enable it
	
	private final static String httpProtocolVersion = "1.1";      // applied HTTP protocol version V1.1
	
	private static ExternalParamFile externalParamFile = null;                              // used only for very large parameter values (commonly unused)
	private static final String EXTERNAL_PARAM_FILE = "ChainAPIsExampleExtParams.dat";      // input file name for very large parameter values
	private static FileCache requestFileCache = new FileCache();  // file cache for large XML, SOAP and ASCII requests
	
	private int requestTimeout = -1;                              // thread input data from constructor, timeout per url request in seconds
	private boolean checkLoopCount = false;                       // thread input data from constructor, number of loops per thread (optional)
	private int threadNumber = -1;                                // thread input data from constructor, internal thread number
	private int threadLoopCounter = 0;                            // internal loop counter per thread
	private volatile int userResumeStartWaitDelay = 0;            // internal, user specific delay when the load test execution is resumed
	private volatile boolean decrementEndOfLoopFlag = false;	  // internal flag to decrement the simulated user at runtime
	private volatile boolean incrementUserFlag = false;           // internal flag to increment the simulated user at runtime
	private volatile long incrementUserStartTime = -1;            // internal start time when increment the simulated user at runtime
	private static AtomicInteger totalLoopCounter = new AtomicInteger(0);    // internal overall loop counter

	private LoadtestPluginContext userPluginContext = null;       // plug-in context per user
	private LoadtestInlineScriptContext inlineScriptContext = null;          // re-used, scratch, the returned context of an inline script that runs at item or URL exec scope

	private Object sslSessionCache = null;                        // internal ssl session cache per thread
	private SSLSessionCacheStatistic sslStatistic = null;         // internal ssl session cache statistic per thread
	private static int sslSessionCacheTimeout = 300;              // timeout of ssl session cache in seconds, 0 = session cache disabled
	private static int sslHandshakeRandomGeneratorType = -1;      // invalid initial value, use the default secure random generator for SSL handshakes
	private HttpSocketPool socketPool = null;                     // re-used, scratch, internal socket pool per thread and loop
	private CookieHandler cookieHandler = null;                   // re-used, scratch, internal cookie handler per thread and loop

	private HttpTestURL testURL = null;                           // re-used, scratch, http request and response
	private HttpTestURLThreadHandler pageThreadHandler = null;    // re-used, scratch, support for parallel processing of http request within a page
	private Map<String,HttpTestURLThreadHandler> httpTestURLThreadHandlerMap = new HashMap<>();
	private int httpStatus = -1;                                  // re-used, scratch, http response status code from webserver
	private HtmlContentParser htmlContentParser = null;           // re-used, scratch, used to extract vars from http response
	private XmlContentParser xmlContentParser = null;             // re-used, scratch, used to extract vars from http response
	private JSONHtmlBasedExtractor jsonHtmlBasedExtractor = null; // re-used, scratch, used to extract vars from http response
	private DynamicProtobufContentParser protobufContentParser = null;  // re-used, scratch, used to extract vars from http response
	private TextLineTokenExtractor textLineTokenExtractor = null; // re-used, scratch, used to extract vars from http response
	private ContentTokenExtractor contentTokenExtractor = null;   // re-used, scratch, used to extract vars from http response
	private BoundaryBasedExtractor boundaryBasedExtractor = null;   // re-used, scratch, used to extract vars from http response
	private RegExpBasedExtractor regExpBasedExtractor = null;   // re-used, scratch, used to extract vars from http response
	private XpathBasedExtractor xpathBasedExtractor = null;   // re-used, scratch, used to extract vars from http response
	private JSONPathBasedExtractor jsonPathBasedExtractor = null;   // re-used, scratch, used to extract vars from http response
	
	private static String location = "";                                         // var declaration from web admin var handler: scope = global
	private static String address = null;                                        // var declaration from web admin var handler: scope = global
	private static String key = null;                                            // var declaration from web admin var handler: scope = global
	private static String ConsumerSecret = null;                                 // var declaration from web admin var handler: scope = global
	private static String consumerKey = null;                                    // var declaration from web admin var handler: scope = global
	private static String twitterHandler = null;                                 // var declaration from web admin var handler: scope = global

	private volatile UserTransactionRuntimeHandler transactionHandler = new UserTransactionRuntimeHandler();		// re-used, support to manage user-defined transactions
	private Map<String,String> transactionContextMap = new HashMap<>();		// used to correctly measure the user-defined transactions

	private Map<String,String> loopVarMap = new HashMap<>();		// used for loop variables
	private String lng = null;                           // var declaration from web admin var handler: scope = per loop
	private String lat = null;                           // var declaration from web admin var handler: scope = per loop
	
	/**
	 * constructor: called from load test plug-ins (scope = global).
	 */
	public ChainAPIsExample()
	{
		super();
	}


	/**
	 * constructor: called when a user is created (per user).
	 */
	public ChainAPIsExample(int maxLoops, int requestTimeout, int threadNumber)
	{
		super();
		this.requestTimeout = requestTimeout;
		this.remainingLoops = maxLoops;
		this.checkLoopCount = (maxLoops > 0);
		this.threadNumber = threadNumber;
		this.sslSessionCache = SSLInit.getNewSslSessionCache(sslSessionCacheTimeout);
		this.sslStatistic = new SSLSessionCacheStatistic();
		if (isMultihomed() && (!ipPerLoop()))
			this.localIpAddress = getNextMultihomedIpAddress();
		if (dnsCache != null)
			userDNSContext = new HttpTestURLDNSContext(dnsCache, threadNumber);
		
		applyNextProxySettingsFromFileIfExists();
		
		if (commonNextProxyConfig != null)
			// set proxy configuration data per user 
			userNextProxyConfig = new NextProxyConfig(commonNextProxyConfig);
		
		// initialize context for plug-ins which are executed per user
		userPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber);
		if(replayMode)
		{
			replaySnapShotsDump = new ReplaySnapShotsDump();
		}
	}


	/**
	 * internal method: called when a user starts a loop.
	 * contains the recorded session which is called by users x loops.
	 * 
	 * @param totalLoopCounter total number of loops (0..n-1) counted overall threads. This value is unique per loop.
	 * 
	 * @return  true:  loop successful completed.
	 *          false: loop failed.
	 */
	private boolean execute(int totalLoopCounter) throws Exception
	{
		markStartOfLoop();
		threadStep = 0;                 // internal - start loop at thread step 0
		
		// enable DNS resolves per loop?
		if (dnsPerLoop)
			userDNSContext = new HttpTestURLDNSContext(dnsCache.clone(false), threadNumber);
		
		// debug http headers?
		Object httpLogVectorObject = null;
		if (debugHttp)
			httpLogVectorObject = this;
		
		// create socket pool per loop
		sslSessionCache = SSLInit.getNewSslSessionCache(sslSessionCacheTimeout);		// reset the SSL session cache to get new SSL session IDs for this loop
		socketPool = new HttpSocketPool(this, sslProtocolVersion, sslSessionCache, sslStatistic, sslcmode);
		if (plannedConnectTimeout > 0)
			socketPool.setConnectTimeout(plannedConnectTimeout);
		if (sslHandshakeRandomGeneratorType != -1)
			socketPool.setSslHandshakeRandomGeneratorType(sslHandshakeRandomGeneratorType);
		socketPool.setSupportEllipticCurves(sslECC);
		socketPool.setHintUseSNI(sslSNI);
		socketPool.setSniCritical(sslSNICirical);
		socketPool.setUseTlsSessionTickets(useTlsSessionTickets);
		if (debugKeepAlive)
		{
			socketPool.setlogVectorObject(this);
			if (debugSsl)
				socketPool.enableSslLog();
		}
		
		if (downlinkBandwidth > 0)
			socketPool.setDownlinkBandwidth(downlinkBandwidth);
		if (uplinkBandwidth > 0)
			socketPool.setUplinkBandwidth(uplinkBandwidth);
		
		if (isMultihomed())
		{
			if (ipPerLoop())
				localIpAddress = getNextMultihomedIpAddress();
			socketPool.setClientIpAddress(localIpAddress);
			log();
			log("multihomed client ip address = " + localIpAddress);
		}
		
		// initialize context for plug-ins which are executed per loop
		LoadtestPluginContext loopPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber, socketPool, cookieHandler);
		
		if(isNonModularMode())
		{
			// setup cookie handler per loop
			cookieHandler = new CookieHandler();
			if (debugCookies)
				cookieHandler.setLogVectorInterface(this);
		}
		
		// customised vars from web admin var handler: scope = per loop
		htmlContentParser = null;
		xmlContentParser = null;
		jsonHtmlBasedExtractor = null;
		protobufContentParser = null;
		if(isNonModularMode())
			lng = null;
		if(isNonModularMode())
			lat = null;
		log();
		log("<<< lng = " + lng);
			loopVarMap.put("lng", lng);
		log("<<< lat = " + lat);
			loopVarMap.put("lat", lat);
		log("<<< consumerKey = " + consumerKey);
		log("<<< twitterHandler = " + twitterHandler);
		log("<<< address = " + address);
		log("<<< ConsumerSecret = " + ConsumerSecret);
		log("<<< key = " + key);
		



		// --- VIRTUAL PAGE #0 ---
		if (!executePage_0(totalLoopCounter, loopPluginContext, new InnerLoopContext(), 0,0, httpLogVectorObject))
			return false;



		// --- PAGE BREAK: Test [0] ---
		log();
		log();
		log("# Page #1: Get GeoLoc");
		log("# -------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #1: Get GeoLoc", 0, 0, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(),0,0, httpLogVectorObject))
		{
			// execution of page 1 failed
			synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			if(replayMode)
			{
				dumpLog(System.out);
				clearLog();
			}
			return false;
		}
		if (!synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, null, httpLogVectorObject))
		{
			return false;		// execution of page 1 failed
		}
		
		// page 1 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);
		httpTestURLThreadHandlerMap.put("1", pageThreadHandler);
		if(replayMode)
		{
			dumpLog(System.out);
			clearLog();
		}



		// --- PAGE BREAK: Test [2] ---
		log();
		log();
		log("# Page #2: Get Sushi Restaurants");
		log("# ------------------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #2: Get Sushi Restaurants", 0, 0, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(),0,0, httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			if(replayMode)
			{
				dumpLog(System.out);
				clearLog();
			}
			return false;
		}
		if (!synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, null, httpLogVectorObject))
		{
			return false;		// execution of page 2 failed
		}
		
		// page 2 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);
		httpTestURLThreadHandlerMap.put("2", pageThreadHandler);
		if(replayMode)
		{
			dumpLog(System.out);
			clearLog();
		}



		// --- PAGE BREAK: Test [4] ---
		log();
		log();
		log("# Page #3: Get Twitter ID");
		log("# -----------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #3: Get Twitter ID", 0, 0, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(),0,0, httpLogVectorObject))
		{
			// execution of page 3 failed
			synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			if(replayMode)
			{
				dumpLog(System.out);
				clearLog();
			}
			return false;
		}
		if (!synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, null, httpLogVectorObject))
		{
			return false;		// execution of page 3 failed
		}
		
		// page 3 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);
		httpTestURLThreadHandlerMap.put("3", pageThreadHandler);
		if(replayMode)
		{
			dumpLog(System.out);
			clearLog();
		}



		// --- PAGE BREAK: Test [6] ---
		log();
		log();
		log("# Page #4: POST Direct Twitter Message");
		log("# ------------------------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #4: POST Direct Twitter Message", 0, 0, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(),0,0, httpLogVectorObject))
		{
			// execution of page 4 failed
			synchResponsesParallelRequestsPage_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			if(replayMode)
			{
				dumpLog(System.out);
				clearLog();
			}
			return false;
		}
		if (!synchResponsesParallelRequestsPage_4(totalLoopCounter, loopPluginContext, null, httpLogVectorObject))
		{
			return false;		// execution of page 4 failed
		}
		
		// page 4 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);
		httpTestURLThreadHandlerMap.put("4", pageThreadHandler);
		if(replayMode)
		{
			dumpLog(System.out);
			clearLog();
		}
		
		
		// loop successful done
		// --------------------
		
		markEndOfPage(performanceData);
		
		endOfExecuteLoop(true, null, -1, loopPluginContext);
		log();
		return true;
	}		// end of execute()
		
		
		
	/**
	 * internal method: called when a user has completed a loop.
	 */
	private void endOfExecuteLoop(boolean loopPassed, HttpTestURL testURL, int threadStep, LoadtestPluginContext loopPluginContext) throws Exception
	{
		// log URL if last call has failed
		if ((!loopPassed) && (testURL != null))
			log(testURL);
		
		// create browser cache per loop if simulate user per loop is enabled in the browser cache settings
		createBrowserCachePerLoop();
		
		// update plugin context
		loopPluginContext.setLoopPassed(loopPassed);
		if ((testURL != null) && (loopPluginContext.getHttpTestURL() == null))
			loopPluginContext.setHttpTestURL(testURL);
		if ((threadStep != -1) && (loopPluginContext.getThreadStep() == -1))
			loopPluginContext.setThreadStep(threadStep);
	}


	/**
	 * Recorded http requests of page #0.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_0(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext,int innerLoopCounter, int totalInnerLoopCount, Object httpLogVectorObject) throws Exception
	{

		// all http requests of page #0 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext,int innerLoopCounter, int totalInnerLoopCount, Object httpLogVectorObject) throws Exception
	{
		// # Page #1: Get GeoLoc
		// # -------------------



		// --- HTTP REQUEST: Test [1] <- WEB ADMIN Index 1 ---
		String requestProt0001 = "https";
		String requestHost0001 = "maps.googleapis.com";
		int    requestPort0001 = 443;
		String requestFile0001 = "/maps/api/geocode/json" +
				"?address=" + address +		// recorded value = {$address}
				"&key=" + key;		// recorded value = AIzaSyCRJPrlrM8B8YdipQCSH2Y7XzZBAilbNgI
		String requestHeader0001 = "GET " + requestFile0001 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Postman-Token: a5d2c630-c91c-481b-aa12-f2bb67f79344\r\n" + 
				"Host: maps.googleapis.com\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0001, requestHost0001, requestPort0001, requestHeader0001, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		if (userNextProxyConfig != null)
			testURL.setProxy(userNextProxyConfig);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		// Check for browse cache
		if(enableBrowserCache)
		{
			testURL = isInCache(testURL, threadStep);
			if(!testURL.isCached())
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
			else if(conditionalGet)
			{
				testURL.execute(performanceData);
				testURL = getFromCache(testURL, performanceData, threadStep);
			}
			else
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
		}
		else
		{
			testURL.execute(performanceData);
		}
		requestHeader0001 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/JSON", header text fragment = [verification disabled], recorded content size = 594
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/JSON", null, null);		// Test [1] <- Index 1
		if(replayMode)
		{
			long proxyDataRecordId =1632771983921L;
			createReplaySnapShotRecord(threadLoopCounter, proxyDataRecordId, testURL, replaySnapShotsDump);
		}
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// extract variable(s) from response
		try
		{
			jsonHtmlBasedExtractor = new JSONHtmlBasedExtractor(testURL.getContentString(), this);
		}
		catch (Exception e)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: malformed XML or JSON data - unable to parse received response content";
			log(errorText);
			threadStep = performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_USER_SPECIFIC_TEST_FAILED, errorText, testURL, this);
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		try
		{
			// extract var 'lng'
			lng = jsonHtmlBasedExtractor.getNodeValue("$.results[0].geometry.location.lng");		// recorded value = -118.4174551
			log("<<< lng = " + lng);
			loopVarMap.put("lng", lng);
			// Replace the value of a variable with a user given value in case of a blank text extracted during the load test
			if (false && lng!= null && lng.trim().length() == 0)
			{
				lng = "";
				log("runtime note: the extraction of a new value for the variable 'lng' ends up with an empty string - a substitute value was set");
				log("<<< lng = " + lng);
				loopVarMap.put("lng", lng);
			}
		}
		catch (Exception e) { lng = null; log(e); }
		if (lng == null)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: unable to extract var 'lng' from json response data";
			log(errorText);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			threadStep = performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_USER_SPECIFIC_TEST_FAILED, errorText, testURL, this);
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		try
		{
			// extract var 'lat'
			lat = jsonHtmlBasedExtractor.getNodeValue("$.results[0].geometry.location.lat");		// recorded value = 34.0250133
			log("<<< lat = " + lat);
			loopVarMap.put("lat", lat);
			// Replace the value of a variable with a user given value in case of a blank text extracted during the load test
			if (false && lat!= null && lat.trim().length() == 0)
			{
				lat = "";
				log("runtime note: the extraction of a new value for the variable 'lat' ends up with an empty string - a substitute value was set");
				log("<<< lat = " + lat);
				loopVarMap.put("lat", lat);
			}
		}
		catch (Exception e) { lat = null; log(e); }
		if (lat == null)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: unable to extract var 'lat' from json response data";
			log(errorText);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			threadStep = performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_USER_SPECIFIC_TEST_FAILED, errorText, testURL, this);
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated
		
		// execute inline script "join_lat_lng"
		inlineScriptContext = executeInlineScript_1632772668836(testURL, urlCallPassed, threadStep - 1, totalLoopCounter, innerLoopContext);
		if (inlineScriptContext.isScriptAbort())
		{
			// special case: url already executed but inline script aborted - set URL as failed and abort current loop
			log("*** INLINE SCRIPT \"" + inlineScriptContext.getScriptTitle() + "\" ABORTED / CURRENT LOOP ABORTED ***");
			log("Abort Message = " + inlineScriptContext.getScriptAbortMessage());
			testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_HANDLE_AS_ERROR);
			testURL.setStatusType(HttpTestURL.STATUS_TYPE_ABORTED_BY_INLINE_SCRIPT);
			threadStep--;
			if (urlCallPassed)
				modPassed(performanceData, threadStep, -1);
			else
				modFailed(performanceData, threadStep, -1);
			performanceData.getPerformanceDataRecord(threadStep).modifyFailureActionType(HttpTestURL.FAILURE_ACTION_HANDLE_AS_ERROR);
			performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_ABORTED_BY_INLINE_SCRIPT, inlineScriptContext.getScriptAbortMessage(), testURL, this);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}




		// all http requests of page #1 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext,int innerLoopCounter, int totalInnerLoopCount, Object httpLogVectorObject) throws Exception
	{
		// # Page #2: Get Sushi Restaurants
		// # ------------------------------



		// --- HTTP REQUEST: Test [3] <- WEB ADMIN Index 3 ---
		String requestProt0003 = "https";
		String requestHost0003 = "maps.googleapis.com";
		int    requestPort0003 = 443;
		String requestFile0003 = "/maps/api/place/nearbysearch/json" +
				"?key=" + key +		// recorded value = AIzaSyCRJPrlrM8B8YdipQCSH2Y7XzZBAilbNgI
				"&location=" + location +		// recorded value = 34.0250133,-118.4174551
				"&radius=500" +
				"&type=restaurant" +
				"&keyword=sushi";
		String requestHeader0003 = "GET " + requestFile0003 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Postman-Token: f53b4914-0006-4403-8504-704ebcb63063\r\n" + 
				"Host: maps.googleapis.com\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0003, requestHost0003, requestPort0003, requestHeader0003, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		if (userNextProxyConfig != null)
			testURL.setProxy(userNextProxyConfig);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		// Check for browse cache
		if(enableBrowserCache)
		{
			testURL = isInCache(testURL, threadStep);
			if(!testURL.isCached())
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
			else if(conditionalGet)
			{
				testURL.execute(performanceData);
				testURL = getFromCache(testURL, performanceData, threadStep);
			}
			else
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
		}
		else
		{
			testURL.execute(performanceData);
		}
		requestHeader0003 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/JSON", header text fragment = [verification disabled], recorded content size = 1205
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/JSON", null, null);		// Test [3] <- Index 3
		if(replayMode)
		{
			long proxyDataRecordId =1632771984680L;
			createReplaySnapShotRecord(threadLoopCounter, proxyDataRecordId, testURL, replaySnapShotsDump);
		}
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated




		// all http requests of page #2 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #3.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_3(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext,int innerLoopCounter, int totalInnerLoopCount, Object httpLogVectorObject) throws Exception
	{
		// # Page #3: Get Twitter ID
		// # -----------------------



		// --- HTTP REQUEST: Test [5] <- WEB ADMIN Index 5 ---
		String requestProt0005 = "https";
		String requestHost0005 = "api.twitter.com";
		int    requestPort0005 = 443;
		String requestFile0005 = "/1.1/users/show.json" +
				"?screen_name=" + twitterHandler;		// recorded value = ZshMalloc
		String requestHeader0005 = "GET " + requestFile0005 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Authorization: OAuth oauth_consumer_key=\"8Iqygshjq1fzpeh8vua8wNeCx\",oauth_token=\"1417620085-TmTLWQWyfuPoZ3tWvFNhC2G1l8fm9NC1yF8mzIu\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1632771985\",oauth_nonce=\"CfOo17UmIni\",oauth_version=\"1.0\",oauth_signature=\"viBoVsFhRiD2D0bgpjQ0yEK1XuI%3D\"\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Postman-Token: cdcb2426-f171-4b96-ad43-9abd053dbf68\r\n" + 
				"Host: api.twitter.com\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0005, requestHost0005, requestPort0005, requestHeader0005, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		if (userNextProxyConfig != null)
			testURL.setProxy(userNextProxyConfig);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		// Check for browse cache
		if(enableBrowserCache)
		{
			testURL = isInCache(testURL, threadStep);
			if(!testURL.isCached())
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
			else if(conditionalGet)
			{
				testURL.execute(performanceData);
				testURL = getFromCache(testURL, performanceData, threadStep);
			}
			else
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
		}
		else
		{
			testURL.execute(performanceData);
		}
		requestHeader0005 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/JSON", header text fragment = [verification disabled], recorded content size = 970
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/JSON", null, null);		// Test [5] <- Index 5
		if(replayMode)
		{
			long proxyDataRecordId =1632771986752L;
			createReplaySnapShotRecord(threadLoopCounter, proxyDataRecordId, testURL, replaySnapShotsDump);
		}
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated




		// all http requests of page #3 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #4.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_4(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext,int innerLoopCounter, int totalInnerLoopCount, Object httpLogVectorObject) throws Exception
	{
		// # Page #4: POST Direct Twitter Message
		// # ------------------------------------



		// --- HTTP REQUEST: Test [7] <- WEB ADMIN Index 7 ---
		String requestProt0007 = "https";
		String requestHost0007 = "api.twitter.com";
		int    requestPort0007 = 443;
		String requestFile0007 = "/1.1/direct_messages/events/new.json";
		String requestContent0007 = 
				"{" + "\r\n" + 
				"  \"event\": {" + "\r\n" + 
				"    \"type\": \"message_create\"," + "\r\n" + 
				"    \"message_create\": {" + "\r\n" + 
				"      \"target\": {" + "\r\n" + 
				"        \"recipient_id\": \"1417620085\"" + "\r\n" + 
				"      }," + "\r\n" + 
				"      \"message_data\": {" + "\r\n" + 
				"        \"text\": \"You should try Niko Niko Sushi at 10839 National Blvd, Los Angeles rated 4.5.\"" + "\r\n" + 
				"      }" + "\r\n" + 
				"    }" + "\r\n" + 
				"  }" + "\r\n" + 
				"}";
		String requestHeader0007 = "POST " + requestFile0007 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Content-Type: application/json\r\n" + 
				"Authorization: OAuth oauth_consumer_key=\"8Iqygshjq1fzpeh8vua8wNeCx\",oauth_token=\"1417620085-TmTLWQWyfuPoZ3tWvFNhC2G1l8fm9NC1yF8mzIu\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1632771986\",oauth_nonce=\"px49MUPncuM\",oauth_version=\"1.0\",oauth_signature=\"x6xv7PJNjjbNqOLcJerTaAUOYW4%3D\"\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Postman-Token: 73b16e8f-3f9a-4c28-ace9-61dc7b9ad7b2\r\n" + 
				"Host: api.twitter.com\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Content-Length: " + requestContent0007.getBytes().length + "\r\n" + 		// Content-Length: 272
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0007, requestHost0007, requestPort0007, requestHeader0007, requestContent0007.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		if (userNextProxyConfig != null)
			testURL.setProxy(userNextProxyConfig);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		// Check for browse cache
		if(enableBrowserCache)
		{
			testURL = isInCache(testURL, threadStep);
			if(!testURL.isCached())
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
			else if(conditionalGet)
			{
				testURL.execute(performanceData);
				testURL = getFromCache(testURL, performanceData, threadStep);
			}
			else
			{
				testURL.execute(performanceData);
				storeToCache(testURL, performanceData);
			}
		}
		else
		{
			testURL.execute(performanceData);
		}
		requestHeader0007 = null;		// support garbage collector to reduce memory
		requestContent0007 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/JSON", header text fragment = [verification disabled], recorded content size = 257
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/JSON", null, null);		// Test [7] <- Index 7
		if(replayMode)
		{
			long proxyDataRecordId =1632771986982L;
			createReplaySnapShotRecord(threadLoopCounter, proxyDataRecordId, testURL, replaySnapShotsDump);
		}
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated




		// all http requests of page #4 successful done
		return true;
	}



	boolean synchResponsesParallelRequestsPage_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 1 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		if (!writeHttpAsyncResponseTabOutput_1(loopPluginContext,lastThreadStepInMainThread, innerLoopContext, totalLoopCounter))
			return false;
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}
	
	
	private boolean writeHttpAsyncResponseTabOutput_1 (LoadtestPluginContext loopPluginContext, int lastThreadStepInMainThread, InnerLoopContext innerLoopContext, int totalLoopCounter) throws Exception
	{
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_2(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 2 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		if (!writeHttpAsyncResponseTabOutput_2(loopPluginContext,lastThreadStepInMainThread, innerLoopContext, totalLoopCounter))
			return false;
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}
	
	
	private boolean writeHttpAsyncResponseTabOutput_2 (LoadtestPluginContext loopPluginContext, int lastThreadStepInMainThread, InnerLoopContext innerLoopContext, int totalLoopCounter) throws Exception
	{
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_3(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 3 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		if (!writeHttpAsyncResponseTabOutput_3(loopPluginContext,lastThreadStepInMainThread, innerLoopContext, totalLoopCounter))
			return false;
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}
	
	
	private boolean writeHttpAsyncResponseTabOutput_3 (LoadtestPluginContext loopPluginContext, int lastThreadStepInMainThread, InnerLoopContext innerLoopContext, int totalLoopCounter) throws Exception
	{
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_4(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 4 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		if (!writeHttpAsyncResponseTabOutput_4(loopPluginContext,lastThreadStepInMainThread, innerLoopContext, totalLoopCounter))
			return false;
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}
	
	
	private boolean writeHttpAsyncResponseTabOutput_4 (LoadtestPluginContext loopPluginContext, int lastThreadStepInMainThread, InnerLoopContext innerLoopContext, int totalLoopCounter) throws Exception
	{
		return true;		// end of asynch response checks for this page
	}



	/**
	 * implement GetRealTimeUserInputFieldsInterface: get the definition and the value of all real-time user input fields.
	 */
	public ArrayList<RealTimeUserInputField> getRealTimeUserInputFields()
	{
		try
		{
			ArrayList<RealTimeUserInputField> realTimeUserInputFieldList = new ArrayList<RealTimeUserInputField>();
			return realTimeUserInputFieldList;
		}
		catch (Exception e)
		{
			log(e);
			return null;
		}
	}



	/**
	 * implement ThreadStepInterface: get the current execution step (current URL call or page break) of a simulated user.
	 */
	public int getExecutionStep()
	{
		return threadStep;
	}



	/**
	 * implement SetThreadStepInterface: set the current execution step (current URL call or page break) of a simulated user.
	 */
	public void setExecutionStep(int threadStep)
	{
		this.threadStep = threadStep;
	}



	/**
	 * implement SSLSessionCacheStatisticInterface: get statistic data about the SSL session cache behavior of a simulated user.
	 */
	public SSLSessionCacheStatistic getSSLSessionCacheStatistic()
	{
		return sslStatistic;
	}



	/**
	 * implement VaryingTestDurationInterface: support to extend or to reduce the planned test duration.
	 */
	public int getPlannedTestDuration()
	{
		return plannedTestDuration;
	}
	
	public void setExtendTestDuration(int deltaSeconds)
	{
		if (plannedTestDuration == 0)
		{
			plannedTestDuration = deltaSeconds;		// the old planned test duration was unlimited but is now limited
			return;
		}
		if (deltaSeconds == 0)
		{
			plannedTestDuration = 0;		// the new planned test duration is now unlimited
			return;
		}
		plannedTestDuration = plannedTestDuration + deltaSeconds;
	}



	/**
	 * implement SuspendResumeInterface: support to suspend and to resume the test execution.
	 */
	public int getPlannedStartupDelay()
	{
		return plannedStartupDelay;
	}
	
	public void setUserResumeStartWaitDelay(int millis)
	{
		userResumeStartWaitDelay = millis;
	}



	/**
	 * implement VaryingLoadInterface: support to decrement the number of simulated users at runtime.
	 */
	public void setDecrementEndOfLoopFlag(boolean decrementEndOfLoopFlag)
	{
		this.decrementEndOfLoopFlag = decrementEndOfLoopFlag;
	}

	public boolean isDecrementEndOfLoopFlag()
	{
		return decrementEndOfLoopFlag;
	}



	/**
	 * internal method: execute the loops for one simulated user as a thread.
	 * controls the thread and displays the (thread-)log if one loop has been completed.
	 */
	public void run()
	{
		// user terminated by inline script, at start of user ?
		if (isTerminateUser())
		{
			System.out.println("# --- thread " + Thread.currentThread().getName() + " aborted --- " + ZoneTime.dateToShortString() + " ---");
			return;
		}
		try
		{
			while (true)
			{
				threadStep = ThreadStepInterface.THREAD_NO_STEP;
				clearLog();
				
				// execute loop
				log("--- loop started --- " + ZoneTime.dateToShortString() + " ---");
				long loopStartTime = System.nanoTime();
				if(replayMode)
				{
					createReplaySnapShot(threadLoopCounter, replaySnapShotsDump);
				}
				boolean loopPassed = this.execute(totalLoopCounter.getAndIncrement());
				if(isNonModularMode())
				{
					performanceData.addUserTransactionMeasuredSamples(transactionHandler.getPendingTransactions());
					ArrayList<UserTransactionContext> nonExecutedTransactions = transactionHandler.getNotExecutedTransactions();

					for (UserTransactionContext nonExecutedTransaction : new ArrayList<>(nonExecutedTransactions))
					{
						if(transactionContextMap.containsKey(nonExecutedTransaction.getTransactionId()+"") && transactionContextMap.get(nonExecutedTransaction.getTransactionId()+"") == null)
						{
							transactionContextMap.remove(nonExecutedTransaction.getTransactionId()+"");
							nonExecutedTransactions.remove(nonExecutedTransaction);
						}
					}

					performanceData.addUserTransactionNotExecutedSamples(nonExecutedTransactions);
				}
				performanceData.addSocktPoolStatistic(socketPool);
				socketPool.closePool();
				performanceData.addSSLCacheStatistic(sslStatistic.getSSLSessionCacheStatisticResult());
				sslStatistic.reset();
				
				// eof of input file or out of values for an unique number ?
				if (abortedByEofInputFile() || abortedByUniqueNumberVar())
					return;		// endOfRun() not called in such a case
				
				// check loop result
				String remainingLoopInfo = "";
				if (checkLoopCount)
					remainingLoopInfo = " [remaining loops = " + (remainingLoops - 1) + "]";
				if (loopPassed)
				{
					performanceData.addPassedLoop();
					log("--- loop passed ---  " + ZoneTime.dateToShortString() + " ---" + remainingLoopInfo);
				}
				else
				{
					performanceData.addFailedLoop();
					log("--- loop failed ---  " + ZoneTime.dateToShortString() + " ---" + remainingLoopInfo);
				}
					if (!loopVarMap.isEmpty())
					{
						log();
						log("+----------------------------------------------------------------+");
						log("Loop Variables Values");
						log("----------------------------------------------------");
						for(Map.Entry<String, String> entry : loopVarMap.entrySet())
						{
							log("|" + entry.getKey() + " = " + entry.getValue()+"|");
							log("");
						}
						log("+----------------------------------------------------------------+");
					}
				threadStep = ThreadStepInterface.THREAD_NO_STEP;
				
				// display thread log to standard output
				if (debugLoops || (debugFailedLoops && (!loopPassed)))
				{
					synchronized (dumpLock)
					{
						dumpLog(System.out);				// full log
					}
				}
				
				// check if max loops reached
				if (checkLoopCount)
				{
					// all done ?
					remainingLoops--;
					if (remainingLoops <= 0)
					{
						endOfRun();
						return;
					}
				}
				
				// check if simulated user must be decremented
				if (decrementEndOfLoopFlag && (getOwnLoadTestUserContext().getDecrementEndOfLoopPointInTime() <= System.currentTimeMillis()))
				{
					return;
				}
				
				// check if max test duration reached
				if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
				{
					endOfRun();
					return;
				}
				
				// check if this specific user is terminated earlier than planned by a HTTP content verification or by a plug-in
				if (isTerminateUser())
				{
					endOfRun();
					System.out.println("# --- thread " + Thread.currentThread().getName() + " terminate failed user --- " + ZoneTime.dateToShortString() + " ---");
					return;
				}
				
				// check if load test execution is temporary suspended
				boolean wasSuspended = false;
				while (isSuspend())
				{
					wasSuspended = true;
					try { Thread.currentThread().sleep(20); } catch (InterruptedException ie) {}
					if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
					{
						endOfRun();
						return;
					}
					
					// check if max test duration reached during suspend
					if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
					{
						endOfRun();
						return;
					}
				}
				// check if load test execution is resumed
				if (wasSuspended)
				{
					try { sleepRemoteInterruptable(userResumeStartWaitDelay); } catch (InterruptedException ie) {}
				}
				
				// wait 20 milliseconds if loop has failed
				if (!loopPassed)
					try { Thread.currentThread().sleep(loopErrorDelay); } catch (InterruptedException ie) {}
				
				// enable pacing for the loop
				if(pacing > 0)
				{
					long loopElaspedTime = (System.nanoTime() - loopStartTime) / 1000000l;
					long loopPacingValue = Long.valueOf(pacing).longValue() * 1000l;
					if (loopElaspedTime < loopPacingValue)
					{
						threadStep = threadStep - 1;
						log(" - loop pacing: sleeping for "+ (loopPacingValue - loopElaspedTime)+" ms ...");
						Thread.currentThread().sleep(loopPacingValue - loopElaspedTime);
						threadStep++;
					}
				}
				// execute next loop
				threadLoopCounter++;
			}	// end: while (true)
		}
		catch (Throwable tex)
		{
			log("*** INTERNAL ERROR / LOAD TEST ABORTED ***");
			log(tex);
			log();
			synchronized (dumpLock)
			{
				if(replayMode)
				{
					saveReplaySnapShots("ZTReplay.snapshot");
				}
				dumpLog(System.out);				// full log
				System.err.println("*** INTERNAL ERROR / LOAD TEST ABORTED *** Please verify the variables/user input fields/user input files/inline scripts");
				tex.printStackTrace(System.err);
				System.exit(-2);
			}
		}
		finally
		{
			// remove the reference to this load test instance and the reference to the thread that runs this instance
			try
			{
				if (!decrementEndOfLoopFlag)
				{
					getUserContextTable().getWriteLock().lock();
					getOwnLoadTestUserContext().setLoadTestUserFinallyEnded();
					getUserContextTable().getWriteLock().unlock();
				}
				else
					decrementEndOfLoopFlag = false;
			}
			catch (Throwable texFinal)
			{
				System.err.println("*** INTERNAL FINALLY ERROR / LOAD TEST ABORTED ***");
				texFinal.printStackTrace(System.err);
				System.exit(-2);
			}
		}
	}



	/**
	 * internal method: called when a user has completed the test-run.
	 */
	public void endOfRun()
	{
		clearLog();
		
		if (debugLoops && (getLog().size() > 0))
		{
			synchronized (dumpLock)
			{
				dumpLog(System.out);		// dump log of inline scripts and load test plug-ins which are executed at end of user
			}
		}
	}



	/**
	 * Main program. Starts the test and waits until all have been done.
	 */
	public static void main(String[] args)
	{
		// check command line argument -h or -help
		if ((ParseArgs.hasOption(args, "-h")) || (ParseArgs.hasOption(args, "-help")))
		{
			System.out.println();
			System.out.println("Help - Proxy Sniffer Load Test Program:");
			System.out.println();
			System.out.println("-consumerKey <user-input-field>          ->> required argument: Twitter Consumer Key");
			System.out.println("-twitterHandler <user-input-field>       ->> required argument: Twitter Username");
			System.out.println("-address <user-input-field>              ->> required argument: MyAddress");
			System.out.println("-ConsumerSecret <user-input-field>       ->> required argument: Twitter Consumer Secret");
			System.out.println("-key <user-input-field>                  ->> required argument: Google API Key");
			System.out.println();
			System.out.println("-u <number>              ->> required argument: number of concurrent users");
			System.out.println("-d <seconds>             ->> required argument: planned test duration in seconds (default: 30, 0 = unlimited)");
			System.out.println("-t <seconds>             ->> required argument: request timeout per url in seconds");
			System.out.println();
			System.out.println("-tconnect <seconds>      ->> TCP/IP socket connect timeout in seconds (default: use value of -t <seconds>)");
			System.out.println();
			System.out.println("-sdelay <milliseconds>   ->> startup delay time between concurrent users in milliseconds (default: 200)");
			System.out.println("-mtpu <number>           ->> maximum number of parallel threads per user (default: " + MAX_PARALLEL_THREADS_PER_USER + ")");
			System.out.println("-maxloops <number>       ->> maximum number of loops per user (default: 0 = unlimited)");
			System.out.println("-pacing <seconds>        ->> sets a minimum time for all in the loop executed page breaks and url calls which must be elapsed before the next loop starts (default: 0 = no pacing)");
			System.out.println("-downlink <kbps>         ->> maximum downlink network bandwidth per user in kilobits per second (default: 0 = unlimited)");
			System.out.println("-uplink <kbps>           ->> maximum uplink network bandwidth per user in kilobits per second (default: 0 = unlimited)");
			System.out.println("-multihomed <filename>   ->> use serveral client ip addresses - file format: <addr1>, <addr2>, ... (all on the same line)");
			System.out.println("-sampling <seconds>      ->> statistic sampling interval in seconds (default: 15)");
			System.out.println("-percpage <percent>      ->> additional sampling rate per web page call in percent (default: 100)");
			System.out.println("-percurl <percent>       ->> additional sampling rate per url call in percent (default: 0)");
			System.out.println("-percurlopt <level>      ->> extended sampling level per url call, see application reference manual (default: 0 = disabled)");
			System.out.println("-maxerrsnap <number>     ->> maximum number of error snapshots per url (default: 0 = unlimited)");
			System.out.println("-maxerrmem <megabytes>   ->> maximum size of memory in megabytes which can be used for error snapshots (default: 20, -1 = unlimited)");
			System.out.println("-nosdelayCluster         ->> apply startup delay time between concurrent users per exec agent, but not per cluster job (default: apply per cluster job)");
			System.out.println("-setuseragent \"<text>\"   ->> replace the recorded value of the HTTP request header field User-Agent with a new value");
			System.out.println("-collect <host>[:<port>] ->> collect additional data from external measuring agents (data collectors)");
			System.out.println("-res <filename>          ->> overrides the default name of the binary output file");
			System.out.println("-nores                   ->> disables to create the binary output file");
			System.out.println();
			System.out.println("-ssl <version>           ->> set SSL version: possible options are \"all\" (default), \"v3\", \"tls\", \"tls11\", \"tls12\" or \"tls13\"");
			System.out.println("-sslcache <seconds>      ->> timeout of user-related SSL session cache (default: 300, 0 = cache disabled)");
			System.out.println("-sslrandom <type>        ->> set the type of the random generator used for SSL handshakes: possible options are \"fast\", \"iaik\" (default) or \"java\"");
			System.out.println("-sslcmode                ->> apply SSL/HTTPS compatibility workarounds for deficient SSL servers");
			System.out.println("-noECC                   ->> disable support of elliptic curve cryptography (ECC)");
			System.out.println("-nosni                   ->> disable support of server name indication (SNI)");
			System.out.println("-snicritical             ->> set the TLS SNI extension as critical (default: non-critical)");
			System.out.println("-tlssessiontickets       ->> set the TLS to use Session Tickets (non-critical)");
			System.out.println("-iaikLast                ->> adds the IAIK security provider at the last position (instead of default: IAIK at first position)");
			System.out.println();
			System.out.println("-dnssrv <IP-1>[,IP-N])   ->> use specific DNS server(s) to resolve DNS host names (default: use OS to resolve host names)");
			System.out.println("-dnshosts <filename>     ->> use specific DNS hosts file (default: use OS to resolve host names)");
			System.out.println("-dnstranslation <filename> ->> use a DNS translation file that converts DNS names. It might be needed to disable TLS SNI if this option is used");
			System.out.println("-dnsenattl               ->> enable consideration of DNS TTL by using the received TTL-values from the DNS Server(s) (default: TTL disabled)");
			System.out.println("-dnsfixttl <seconds>     ->> enable DNS TTL by using a fixed TTL-value of seconds for all DNS resolves");
			System.out.println("-dnsperloop              ->> perform new DNS resolves for each executed loop. All resolves are stable within the same loop (no consideration of DNS TTL within a loop)");
			System.out.println("-dnsstatistic            ->> collect statistical data about DNS resolutions. Note: use this option only if not any other, more specific DNS option is enabled");
			System.out.println("-dnsdebug                ->> debug DNS resolves and the DNS cache");
			System.out.println("-enableIPv6 <networkinterface-name>  ->> enable only IPv6 support for recording, also can provide the IPv6 network interface of the load generator");
			System.out.println("-enableIPv6v4 <networkinterface-name> ->> enable both IPv6 and IPv4 (first will try with IPv6 ,if fails will try with IPv4 support for recording),also can provide the IPv6 network interface of the load generator");
			System.out.println();
			System.out.println("-dfl                     ->> debug execution steps of all failed loops to standard output");
			System.out.println("-dl                      ->> debug execution steps of all loops to standard output");
			System.out.println("-dh                      ->> debug HTTP protocol headers to standard output, includes the -dl option");
			System.out.println("-dc                      ->> debug HTTP content data to standard output, includes the -dl option");
			System.out.println("-dhc                     ->> debug HTTP protocol headers and HTTP content data to standard output, includes the -dl option");
			System.out.println("-dC                      ->> debug cookies to standard output, includes the -dl option");
			System.out.println("-dK                      ->> debug keep-alive (socket pool) to standard output, includes the -dl option");
			System.out.println("-dssl                    ->> debug SSL handshake (https) to standard output, includes the -dl and the -dK option");
			System.out.println();
			System.out.println("-tz <timezone>           ->> set time zone (see Application Reference Manual: supported time zones)");
			System.out.println("-dgs a|c                 ->> set number format (decimal group separator) a = '  c = ,");
			System.out.println("-browserlang <text>      ->> set the browser language for the test");
			System.out.println("-enablebrowcache         ->> set the browser cache mode on");
			System.out.println("-checknewpageversion     ->> set the conditional GET mode on");
			System.out.println("-cachehtml               ->> set the cache html resources flag");
			System.out.println("-newvuperloop            ->> simulate new VU per loop");
			System.out.println("-replay                  ->> Indicates the replay mode");
			System.out.println("-annotation <text>       ->> adds an annotation for this test run");
			System.out.println();
			System.out.println("-execAgentHost <ip address or dns name>      ->> set the ip address or the dns name of the exec agent from which the license is used (default: 127.0.0.1)");
			System.out.println("-execAgentPort <port>                        ->> set the tcp/ip port of the exec agent (default: 7993)");
			System.out.println("-execAgentProtocol <plain | http | https>    ->> set the protocol of the exec agent (default: plain)");
			System.out.println("-execAgentUsername <string>                  ->> set the auth. username for the exec agent (default: [no username])");
			System.out.println("-execAgentPassword <string>                  ->> set the auth. password for the exec agent (default: [no password])");
			System.out.println();
			System.out.println("-h                       ->> display this help text");
			System.out.println("-help                    ->> display this help text");
			System.out.println();
			System.exit(-1);
		}
		
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("| Welcome to the ZebraTester Load Test Program.                  |");
		System.out.println("| Additional help available with program argument \"-help\"        |");
		System.out.println("| Procedure Copyright by Apica. |");
		System.out.println("| All rights reserved.             |");
		System.out.println("+----------------------------------------------------------------+");
		
		
		if (commonNextProxyConfig != null)
		{
			System.out.println();
			System.out.println("*** Warning: Loadtest over HTTP(S) Proxy ***");
		}
		
		// check command line argument -tz <timezone>
		String timeZoneString = ParseArgs.getString(args, "-tz");
		if (timeZoneString != null)
			defaultTimeZone = timeZoneString; // set time zone?
		ZoneTime.setDefaultTimeZone(defaultTimeZone);
		
		// check command line argument -noECC
		sslECC = (!ParseArgs.hasOption(args, "-noECC"));       // disable ssl ecc ?
		
		// initialize ssl/https support
		SSLInit.execute(!ParseArgs.hasOption(args, "-iaikLast"), true);
		if (sslECC)
			SSLInit.enableECC();
		
		// check command line argument -nosni
		if (ParseArgs.hasOption(args, "-nosni"))            // disable ssl sni ?
			sslSNI = false;
		
		// check command line argument -snicritical
		if (ParseArgs.hasOption(args, "-snicritical"))      // set ssl sni as critical tls extension ?
			sslSNICirical = true;
		
		// check command line argument -tlssessiontickets
		if (ParseArgs.hasOption(args, "-tlssessiontickets"))      // set tls extension session tickets?
			useTlsSessionTickets = true;
		
		// set default character set for response content tests
		setCharEncoding(prxCharEncoding);
		
		// check command line argument -dgs a|c
		String dgs = ParseArgs.getString(args, "-dgs");
		if (dgs != null)
		{
			if (dgs.equalsIgnoreCase("a"))
				defaultNumberGroupingSeparator = '\'';
			if (dgs.equalsIgnoreCase("c"))
				defaultNumberGroupingSeparator = ',';
		}
		Lib.setDefaultNumberGroupingSeparator(defaultNumberGroupingSeparator);
		
		// check command line argument -browserlang <text>
		browserLanguage = ParseArgs.getString(args, "-browserlang");
		
		// check command line argument -enablebrowcache
		if (ParseArgs.hasOption(args, "-enablebrowcache"))
		{
			enableBrowserCache = true;
			browserCacheStatistic = new BrowserCacheStatistic();
		}
		
		// check command line argument -checknewpageversion
		if (ParseArgs.hasOption(args, "-checknewpageversion"))
		{
			conditionalGet = true;
		}
		
		// check command line argument -replay
		if (ParseArgs.hasOption(args, "-replay"))
		{
			replayMode = true;
		}
		
		// check command line argument -annotation <text>
		String testRunAnnotation = ParseArgs.getString(args, "-annotation");
		if (testRunAnnotation != null)
		{
			if (testRunAnnotation.startsWith("\"") && testRunAnnotation.endsWith("\""))
				testRunAnnotation = testRunAnnotation.substring(1, testRunAnnotation.length() - 1);
		}
		
		// check command line argument -ssl
		String newSslProtocolVersion = ParseArgs.getString(args, "-ssl");
		if (newSslProtocolVersion != null)
		{
			if (newSslProtocolVersion.equalsIgnoreCase("v2"))
				sslProtocolVersion = "v2";
			if (newSslProtocolVersion.equalsIgnoreCase("v3"))
				sslProtocolVersion = "v3";
			if (newSslProtocolVersion.equalsIgnoreCase("tls"))
				sslProtocolVersion = "tls";
			if (newSslProtocolVersion.equalsIgnoreCase("tls11"))
				sslProtocolVersion = "tls11";
			if (newSslProtocolVersion.equalsIgnoreCase("tls12"))
				sslProtocolVersion = "tls12";
			if (newSslProtocolVersion.equalsIgnoreCase("tls13"))
				sslProtocolVersion = "tls13";
		}
		
		// check command line argument -sslcache <seconds>
		Integer newSslSessionCacheTimeout = ParseArgs.getInteger(args, "-sslcache");
		if (newSslSessionCacheTimeout != null)
			sslSessionCacheTimeout = newSslSessionCacheTimeout.intValue();
		
		// check command line argument -sslrandom
		String sslHandshakeRandomGeneratorTypeStr = ParseArgs.getString(args, "-sslrandom");
		if (sslHandshakeRandomGeneratorTypeStr != null)
		{
			if (sslHandshakeRandomGeneratorTypeStr.equalsIgnoreCase("java"))
				sslHandshakeRandomGeneratorType = HttpSocketPool.SSL_HANDSHAKE_RANDOM_GENERATOR_TYPE_JAVA_DEFAULT;
			if (sslHandshakeRandomGeneratorTypeStr.equalsIgnoreCase("iaik"))
				sslHandshakeRandomGeneratorType = HttpSocketPool.SSL_HANDSHAKE_RANDOM_GENERATOR_TYPE_IAIK_DEFAULT;
			if (sslHandshakeRandomGeneratorTypeStr.equalsIgnoreCase("fast"))
				sslHandshakeRandomGeneratorType = HttpSocketPool.SSL_HANDSHAKE_RANDOM_GENERATOR_TYPE_FAST;
		}
		
		// check command line argument -sslcmode
		sslcmode = ParseArgs.hasOption(args, "-sslcmode");  // enable ssl/https compatibility workarounds?
		
		// check command line argument -dfl
		debugFailedLoops = ParseArgs.hasOption(args, "-dfl"); // debug failed loops?
		
		// check command line argument -dl
		debugLoops = ParseArgs.hasOption(args, "-dl");      // debug loops?
		
		// check command line argument -dh
		debugHttp = ParseArgs.hasOption(args, "-dh");       // debug http?
		if (debugHttp)
			debugLoops = true;
		
		// check command line argument -dc
		debugContent = ParseArgs.hasOption(args, "-dc");    // debug content?
		if (debugContent)
			debugLoops = true;
		
		// check command line argument -dhc
		if (ParseArgs.hasOption(args, "-dhc"))				// debug http and content
		{
			debugLoops = true;
			debugHttp = true;
			debugContent = true;
		}
		
		// check command line argument -dC
		debugCookies = ParseArgs.hasOption(args, "-dC");    // debug cookies?
		if (debugCookies)
			debugLoops = true;
		
		// check command line argument -dK
		debugKeepAlive = ParseArgs.hasOption(args, "-dK");  // debug keep.alive (socket pool)?
		if (debugKeepAlive)
			debugLoops = true;
		
		// check command line argument -dssl
		debugSsl = ParseArgs.hasOption(args, "-dssl");      // debug keep.alive (socket pool)?
		if (debugSsl)
		{
			debugLoops = true;
			debugKeepAlive = true;
		}
		
		// get startup delay
		Integer newStartupDelay = ParseArgs.getInteger(args, "-sdelay");
		if (newStartupDelay != null)
			plannedStartupDelay = newStartupDelay.intValue();
		
		// get max. parallel threads per user - note: only valid if CONTAINS_PARALLEL_EXECUTED_URLS = true
		Integer newMaxParallelThreadsPerUser = ParseArgs.getInteger(args, "-mtpu");
		if (newMaxParallelThreadsPerUser != null)
		{
			if (newMaxParallelThreadsPerUser.intValue() > 0)
				maxParallelThreadsPerUser = newMaxParallelThreadsPerUser.intValue();
		}
		// get maximum number of loops per user
		maxPlannedLoops = 0;  // maximum loops per user, 0 = unlimited
		Integer newLoops = ParseArgs.getInteger(args, "-maxloops");
		if (newLoops != null)
			maxPlannedLoops = newLoops.intValue();
		
		// pacing of loops per user
		Integer pacingObj = ParseArgs.getInteger(args, "-pacing");
		if (pacingObj != null)
			pacing = pacingObj.intValue();
		
		// get maximum downlink network bandwidth per user (default = unlimited)
		Integer newDownlinkBandwidth = ParseArgs.getInteger(args, "-downlink");
		if (newDownlinkBandwidth != null)
			downlinkBandwidth = newDownlinkBandwidth.intValue();
		
		// get maximum uplink network bandwidth per user (default = unlimited)
		Integer newUplinkBandwidth = ParseArgs.getInteger(args, "-uplink");
		if (newUplinkBandwidth != null)
			uplinkBandwidth = newUplinkBandwidth.intValue();
		
		// get statistic sampling interval
		int samplingInterval = 15;  // statistic sampling interval in seconds
		Integer newSamplingInterval = ParseArgs.getInteger(args, "-sampling");
		if (newSamplingInterval != null)
			samplingInterval = newSamplingInterval.intValue();
		
		// get additional sampling rate per web page call
		int percentilePageSampling = 100;  // additional sampling rate per web page call in percent
		Integer newPercentilePageSampling = ParseArgs.getInteger(args, "-percpage");
		if (newPercentilePageSampling != null)
			percentilePageSampling = newPercentilePageSampling.intValue();
		
		// get additional sampling rate per url call
		int percentileUrlSampling = 0;  // additional sampling rate per url call in percent
		Integer newPercentileUrlSampling = ParseArgs.getInteger(args, "-percurl");
		if (newPercentileUrlSampling != null)
			percentileUrlSampling = newPercentileUrlSampling.intValue();
		
		// get extended sampling level per url call
		int percentileUrlSamplingAddOption = 0;  // extended sampling level per url call, 0 = disabled
		Integer newPercentileUrlSamplingAddOption = ParseArgs.getInteger(args, "-percurlopt");
		if (newPercentileUrlSamplingAddOption != null)
			percentileUrlSamplingAddOption = newPercentileUrlSamplingAddOption.intValue();
		
		// get maximum number of error snapshots per URL (0 = unlimited)
		int maxErrorSnapshots = 0;
		Integer newMaxErrorSnapshots = ParseArgs.getInteger(args, "-maxerrsnap");
		if (newMaxErrorSnapshots != null)
			maxErrorSnapshots = newMaxErrorSnapshots.intValue();
		
		// get maximum memory in megabytes which can be used for error snapshots (-1 = unlimited, default = 10)
		long maxErrorSnapshotMemory = 20;
		Long newMaxErrorSnapshotMemory = ParseArgs.getLong(args, "-maxerrmem");
		if (newMaxErrorSnapshotMemory != null)
			maxErrorSnapshotMemory = newMaxErrorSnapshotMemory.longValue();
		
		// single user mode ?
		boolean singleUserMode = ParseArgs.hasOption(args, "-singleuser");
		
		// override value for http user agent field ?
		String setUserAgentStr = ParseArgs.getIgnoreCaseString(args, "-setuseragent");
		if (setUserAgentStr != null)
		{
			USER_AGENT_1 = setUserAgentStr;
		}
		// get required input arguments
		int concurrentUsers = 0;     // number of concurrent users
		plannedRequestTimeout = 0;   // request timeout in seconds
		
		System.out.println();
		
		// parse -consumerKey argument or ask --> <user-input-field>: proposed value = "8Iqygshjq1fzpeh8vua8wNeCx"
		try
		{
			consumerKey = UserInputField.readFromObjectString(USER_INPUT_FIELD_consumerKey).readUserInputValue(args);
			if (consumerKey.startsWith("\"") && consumerKey.endsWith("\""))
				consumerKey = consumerKey.substring(1, consumerKey.length() - 1);
		}
		catch (IOException ie)
		{
			System.out.println("*** INTERNAL ERROR: invalid user input field object-string declaration for 'consumerKey' ***");
			ie.printStackTrace();
			System.exit(-2);
		}
		
		// parse -twitterHandler argument or ask --> <user-input-field>: proposed value = "ZshMalloc"
		try
		{
			twitterHandler = UserInputField.readFromObjectString(USER_INPUT_FIELD_twitterHandler).readUserInputValue(args);
			if (twitterHandler.startsWith("\"") && twitterHandler.endsWith("\""))
				twitterHandler = twitterHandler.substring(1, twitterHandler.length() - 1);
		}
		catch (IOException ie)
		{
			System.out.println("*** INTERNAL ERROR: invalid user input field object-string declaration for 'twitterHandler' ***");
			ie.printStackTrace();
			System.exit(-2);
		}
		
		// parse -address argument or ask --> <user-input-field>: proposed value = "10853%20rose%20ave,%20los%20angeles,%20ca%2090034"
		try
		{
			address = UserInputField.readFromObjectString(USER_INPUT_FIELD_address).readUserInputValue(args);
			if (address.startsWith("\"") && address.endsWith("\""))
				address = address.substring(1, address.length() - 1);
		}
		catch (IOException ie)
		{
			System.out.println("*** INTERNAL ERROR: invalid user input field object-string declaration for 'address' ***");
			ie.printStackTrace();
			System.exit(-2);
		}
		
		// parse -ConsumerSecret argument or ask --> <user-input-field>: proposed value = "qbfBhkY061cJnO4HlVdVjdUm7QtNg0hsVJcWcVCklv7YOOK1bs"
		try
		{
			ConsumerSecret = UserInputField.readFromObjectString(USER_INPUT_FIELD_ConsumerSecret).readUserInputValue(args);
			if (ConsumerSecret.startsWith("\"") && ConsumerSecret.endsWith("\""))
				ConsumerSecret = ConsumerSecret.substring(1, ConsumerSecret.length() - 1);
		}
		catch (IOException ie)
		{
			System.out.println("*** INTERNAL ERROR: invalid user input field object-string declaration for 'ConsumerSecret' ***");
			ie.printStackTrace();
			System.exit(-2);
		}
		
		// parse -key argument or ask --> <user-input-field>: proposed value = "AIzaSyCRJPrlrM8B8YdipQCSH2Y7XzZBAilbNgI"
		try
		{
			key = UserInputField.readFromObjectString(USER_INPUT_FIELD_key).readUserInputValue(args);
			if (key.startsWith("\"") && key.endsWith("\""))
				key = key.substring(1, key.length() - 1);
		}
		catch (IOException ie)
		{
			System.out.println("*** INTERNAL ERROR: invalid user input field object-string declaration for 'key' ***");
			ie.printStackTrace();
			System.exit(-2);
		}
		
		// parse -u argument or ask --> <number of concurrent users>
		Integer i = ParseArgs.getIntegerOrAsk(args, "-u", "Concurrent Users, <RETURN>=1 : ", new Integer(1));
		if (i == null)
			System.exit(-1);
		else
			concurrentUsers = i.intValue();
		
		// parse -d argument or ask --> <test duration in seconds>
		i = ParseArgs.getIntegerOrAsk(args, "-d", "Test Duration in Seconds, <RETURN>=30 : ", new Integer(30));
		if (i == null)
			System.exit(-1);
		else
			plannedTestDuration = i.intValue();
		
		// parse -t argument or ask --> <request timeout in seconds>
		i = ParseArgs.getIntegerOrAsk(args, "-t", "HTTP Request Timeout per URL in Seconds, <RETURN>=60 : ", new Integer(60));
		if (i == null)
			System.exit(-1);
		else
			plannedRequestTimeout = i.intValue();
		
		// parse optional -tconnect argument (TCP/IP socket connect timeout in seconds)
		i = ParseArgs.getInteger(args, "-tconnect");
		if (i != null)
			plannedConnectTimeout = i.intValue();
		
		String genericFileName = PerformanceData.proposeFileName("ChainAPIsExample", concurrentUsers, ParseArgs.getString(args, "-executionPlan"));
		
		// auto-configure binary result file
		if (!ParseArgs.hasOption(args, "-nores"))
		{
			String newResultFile = ParseArgs.getString(args, "-res");
			if (newResultFile == null)
				resultFile = genericFileName + ".prxres";
			else
				resultFile = newResultFile;
			System.out.println("Result File : " + resultFile);
		}
		
		// display common arguments at console output
		System.out.println();
		System.out.println("# concurrent users = " + concurrentUsers);
		System.out.println("# max. parallel threads per user = [serial execution order for all URLs]");
		System.out.print("# planned test duration = ");
		if (plannedTestDuration == 0 || plannedTestDuration == -1)
			System.out.println("unlimited");
		else
			System.out.println("" + plannedTestDuration + " seconds");
		System.out.println("# http request timeout = " + plannedRequestTimeout + " seconds");
		if (plannedConnectTimeout != 0)
			System.out.println("# tcp/ip socket connect timeout = " + plannedConnectTimeout + " seconds");
		System.out.println("# startup delay = " + plannedStartupDelay + " milliseconds");
		System.out.println("# statistic sampling interval = " + samplingInterval + " seconds");
		System.out.println("# additional sampling rate per web page call = " + percentilePageSampling + " %");
		System.out.println("# additional sampling rate per url call = " + percentileUrlSampling + " %");
		System.out.println("# extended sampling per url call = " + PerformanceDataTickExtension.extTypeToString(percentileUrlSamplingAddOption).toLowerCase());
		System.out.print("# max loops per user = ");
		if (maxPlannedLoops == 0)
			System.out.println("unlimited");
		else
			System.out.println("" + maxPlannedLoops + " loops");
		System.out.print("# pacing for loops = ");
		if (pacing == 0)
			System.out.println("no pacing");
		else
			System.out.println("" + pacing + " seconds");
		if (downlinkBandwidth > 0)
			System.out.println("# max downlink bandwidth per user = " + downlinkBandwidth + " kbps");
		if (uplinkBandwidth > 0)
			System.out.println("# max uplink bandwidth per user = " + uplinkBandwidth + " kbps");
		System.out.println("# http protocol version = v" + httpProtocolVersion);
		System.out.println("# ssl protocol version = " + sslProtocolVersion);
		if (sslSessionCacheTimeout != 0)
			System.out.println("# ssl session cache timeout = " + sslSessionCacheTimeout + " seconds");
		else
			System.out.println("# ssl session cache disabled");
		if (resultFile != null)
			System.out.println("# result file = " + resultFile);
		else
			System.out.println("# no result file");
		if (debugFailedLoops)
			System.out.println("# debug failed loops");
		if (debugLoops)
			System.out.println("# debug loops");
		if (debugHttp)
			System.out.println("# debug http protocol headers");
		if (debugContent)
			System.out.println("# debug http content data");
		if (debugCookies)
			System.out.println("# debug cookies");
		if (debugKeepAlive)
			System.out.println("# debug keep-alive (socket pool)");
		if (debugSsl)
			System.out.println("# debug ssl (https)");
		if (enableBrowserCache)
		{
			if (conditionalGet)
			System.out.println("# browser cache - conditional GET is enabled");
		}
		
		
		// ----------------------------
		// *** initialize load test ***
		// ----------------------------
		
		
		// initialize performance data
		// ---------------------------
		final int PAGE_BREAKS = 4;  // number of page breaks in execute()  - modify this value if you add or delete some page breaks
		final int MAX_THREAD_STEPS = 4;  // number of URL requests in execute() - modify this value if you add or delete some requests
		performanceData = new PerformanceData(PAGE_BREAKS, MAX_THREAD_STEPS, concurrentUsers, -1, plannedTestDuration, maxPlannedLoops, httpProtocolVersion, plannedStartupDelay, plannedRequestTimeout, samplingInterval, percentilePageSampling, percentileUrlSampling, percentileUrlSamplingAddOption);
		performanceData.setInfoText("ChainAPIsExample");
		performanceData.setExecutorsAnnotation(testRunAnnotation);
		if (commonNextProxyConfig != null)
		{
			performanceData.setProxyInfo(proxyHttpHost, proxyHttpPort, proxyHttpsHost, proxyHttpsPort);
			performanceData.addTestDescription("*** Warning: Load test executed via outbound proxy ***");
		}
		performanceData.setResultFileName(resultFile);
		performanceData.setDumpStream(System.out, "# ");
		performanceData.setMaxErrorSnapshots(maxErrorSnapshots);		// 0 = unlimited
		if (maxErrorSnapshotMemory >= 0)
			performanceData.setMaxErrorSnapshotsMemory(maxErrorSnapshotMemory * 1048576l);		// value in bytes
		
		if (sslECC)
			performanceData.addTestDescription("*** SSL/TLS ECC is enabled ***");
		
		if ((downlinkBandwidth != 0) || (uplinkBandwidth != 0))
		{
			String downlinkBandwidthStr = "unlimited";
			String uplinkBandwidthStr = "unlimited";
			
			if (downlinkBandwidth != 0)
				downlinkBandwidthStr = "" + downlinkBandwidth + " kbps";
			if (uplinkBandwidth != 0)
				uplinkBandwidthStr = "" + uplinkBandwidth + " kbps";
			
			performanceData.addTestDescription("*** Warning: Max. network bandwidth per user limited. downlink = " + downlinkBandwidthStr + ", uplink = " + uplinkBandwidthStr + " ***");
		}
		
		if ((percentileUrlSamplingAddOption != PerformanceDataTickExtension.EXT_TYPE_NOTYPE) && (percentileUrlSampling > 0))
			performanceData.addTestDescription("*** Warning: Option \"" + PerformanceDataTickExtension.extTypeToString(percentileUrlSamplingAddOption).toLowerCase() + "\" enabled for additional sampling rate per URL call ***");
		if (debugFailedLoops)
			performanceData.addTestDescription("*** Warning: Debug failed loops enabled ***");
		if (debugLoops)
			performanceData.addTestDescription("*** Warning: Debug loops enabled ***");
		if (debugHttp)
			performanceData.addTestDescription("*** Warning: Debug HTTP protocol headers enabled ***");
		if (debugContent)
			performanceData.addTestDescription("*** Warning: Debug HTTP content data enabled ***");
		if (debugCookies)
			performanceData.addTestDescription("*** Warning: Debug cookies enabled ***");
		if (debugKeepAlive)
			performanceData.addTestDescription("*** Warning: Debug keep-alive enabled ***");
		if (debugSsl)
			performanceData.addTestDescription("*** Warning: Debug SSL/TLS enabled ***");
		if (!sslProtocolVersion.equalsIgnoreCase("all"))
			performanceData.addTestDescription("*** Warning: SSL/TLS protocol version fixed to " + sslProtocolVersion.toUpperCase() + " ***");
		if (sslSessionCacheTimeout == 0)
			performanceData.addTestDescription("*** Warning: SSL/TLS session cache disabled ***");
		if (singleUserMode)
		{
			String singleUserModeWarning = "*** Warning: Single user mode enabled ***";
			System.out.println(singleUserModeWarning);
			performanceData.addTestDescription(singleUserModeWarning);
		}
		if (setUserAgentStr != null)
		{
			String overrideUserAgentWarning = "*** Warning: User agent set to \"" + setUserAgentStr + "\" ***";
			System.out.println(overrideUserAgentWarning);
			performanceData.addTestDescription(overrideUserAgentWarning);
		}
		performanceData.addUserInputField("Twitter Consumer Key", consumerKey);		// add user input field name and value to performance data
		performanceData.addUserInputField("Twitter Username", twitterHandler);		// add user input field name and value to performance data
		performanceData.addUserInputField("MyAddress", address);		// add user input field name and value to performance data
		performanceData.addUserInputField("Twitter Consumer Secret", ConsumerSecret);		// add user input field name and value to performance data
		performanceData.addUserInputField("Google API Key", key);		// add user input field name and value to performance data
		
		
		// ready to start load test...
		initNull(concurrentUsers);
		performanceData.setUserContextTable(getUserContextTable());
		
		// ... but init first remote interface ...
		initRemote(args);
		
		// ... and init plug-in class loader ...
		try
		{
			if (getPluginClassLoader() == null)
				setPluginClassLoader(new LoadtestPluginClassLoader(getSymmetricEncryptContext(), getEncryptedClasspathList()));
		}
		catch (Throwable pluginClassLoaderThrowable)
		{
			System.out.println("*** ERROR: Unable to load encrypted files ***");
			pluginClassLoaderThrowable.printStackTrace();
			System.exit(-1);
		}
		
		// ... and check multihomed option ...
		initMultihomed(args);
		
		// ... and add dynaTrace session ID to load test result - if available ...
		initDynaTrace();
		
		
		// use test-specific DNS hosts file (optional) ?
		String dnsHostsFile = ParseArgs.getIgnoreCaseString(args, "-dnshosts");
		if (dnsHostsFile != null)
		{
			try
			{
				dnsCache = new DNSCache(getSymmetricEncryptContext(), dnsHostsFile);
			}
			catch (IOException ie)
			{
				System.out.println("*** ERROR: unable to read DNS hosts file " + dnsHostsFile + " ***");
				System.out.println("*** Hint: you have to ZIP " + dnsHostsFile + " together with the compiled class of the load test program ***");
				ie.printStackTrace();
				System.exit(-1);
			}
		}
		
		// use test-specific DNS translation table (optional) ?
		String dnsTranslationTableFile = ParseArgs.getIgnoreCaseString(args, "-dnstranslation");
		if (dnsTranslationTableFile != null)
		{
			try
			{
				DNSTranslationTable dnsTranslationTable = new DNSTranslationTable(getSymmetricEncryptContext(), new File(dnsTranslationTableFile));
				if (dnsCache == null)
					dnsCache = new DNSCache();
				dnsCache.setDNSTranslationTable(dnsTranslationTable);
			}
			catch (IOException ie)
			{
				System.out.println("*** ERROR: unable to read DNS translation table file " + dnsTranslationTableFile + " ***");
				System.out.println("*** Hint: you have to ZIP " + dnsTranslationTableFile + " together with the compiled class of the load test program ***");
				ie.printStackTrace();
				System.exit(-1);
			}
		}
		
		// use test-specific DNS servers (optional) ?
		String dnsSrvStr = ParseArgs.getIgnoreCaseString(args, "-dnssrv");
		if (dnsSrvStr != null)
		{
			ArrayList<String> dnsSrvList = new ArrayList<String>();
			StringTokenizer dnsTok = new StringTokenizer(dnsSrvStr, ",;");
			while (dnsTok.hasMoreTokens())
				dnsSrvList.add(dnsTok.nextToken());
			if (dnsCache == null)
				dnsCache = new DNSCache(dnsSrvList);
			else
				dnsCache.setDnsServers(dnsSrvList);
		}
		
		// enable DNS TTL ?
		if (ParseArgs.hasOption(args, "-dnsenattl"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsCache.enableTTL();
		}
		
		// set fixed DNS TTL ?
		Integer dnsFixTTL = ParseArgs.getInteger(args, "-dnsfixttl");
		{
			if (dnsFixTTL != null)
			{
				if (dnsCache == null)
					dnsCache = new DNSCache();
				dnsCache.setFixedTTL(dnsFixTTL.intValue());
			}
		}
		
		// enable DNS resolves per loop ?
		if (ParseArgs.hasOption(args, "-dnsperloop"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsPerLoop = true;
		}
		
		// enable DNS statistic ?   // note: use this option only if not any other, more specific DNS option is enabled.
		if (ParseArgs.hasOption(args, "-dnsstatistic"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
		}
		
		// debug DNS resolver ?
		if (ParseArgs.hasOption(args, "-dnsdebug"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsCache.setDebugToStdout(true);
		}
		
		// enable IPv6 
		if (ParseArgs.hasIgnoreCaseOption(args, "-enableIPv6"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsCache.setEnableIPv6(true);
			String networkInterfaceName = ParseArgs.getString(args, "-enableIPv6");
			if(null != networkInterfaceName && !networkInterfaceName.startsWith("-"))
				dnsCache.setNetworkInterfaceName(networkInterfaceName);
		}
		
		// enable IPv6 and V4 
		if (ParseArgs.hasIgnoreCaseOption(args, "-enableIPv6v4"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsCache.setEnableIPv6v4(true);
			String networkInterfaceName = ParseArgs.getString(args, "-enableIPv6v4");
			if(null != networkInterfaceName && !networkInterfaceName.startsWith("-"))
				dnsCache.setNetworkInterfaceName(networkInterfaceName);
		}
		
		if (dnsCache != null)
		{
			// update performance data with DNS settings
			performanceData.addDNSCacheStatistic(dnsCache.getCacheStatistic());
			performanceData.addTestDescription("*** Warning: OS-independent DNS access enabled. " + dnsCache.getConfigInfoText() + " ***");
			if (dnsPerLoop)
				performanceData.addTestDescription("*** Warning: DNS option -dnsperloop enabled ***");
			
			// log DNS settings
			System.out.println("# OS-independent DNS access enabled. " + dnsCache.getConfigInfoText());
			if (dnsPerLoop)
				System.out.println("# DNS option -dnsperloop enabled");
		}
		
		// log the initial value of all global vars
		if (debugLoops || debugFailedLoops)
		{
			System.out.println("global var <<< ConsumerSecret = " + ConsumerSecret);
			System.out.println("global var <<< address = " + address);
			System.out.println("global var <<< consumerKey = " + consumerKey);
			System.out.println("global var <<< key = " + key);
			System.out.println("global var <<< location = " + location);
			System.out.println("global var <<< twitterHandler = " + twitterHandler);
			System.out.println();
		}
		
		// calculate sampling offset and virtual user startup offset for cluster jobs (time shift per cluster member)
		int samplingTimeshift = 0;	// value in seconds
		if (!ParseArgs.hasOption(args, "-nosdelayCluster"))
		{
			if ((getClusterTotalMemberCount() > 1) && (getClusterMemberLocalId() > 0))
			{
				samplingTimeshift = Math.round(((float) samplingInterval / (float) getClusterTotalMemberCount()) * (float) getClusterMemberLocalId());
				System.out.println("# samplingTimeshift = " + samplingTimeshift + " seconds");
				
				long startupDelayOffset = (plannedStartupDelay / ((long) getClusterTotalMemberCount())) * ((long) getClusterMemberLocalId());
				System.out.println("# startupDelayTimeshift = " + startupDelayOffset + " milliseconds");
				System.out.println();
				if (startupDelayOffset > 0)
				{
					try { sleepRemoteInterruptable(startupDelayOffset); } catch (InterruptedException ie) {}
				}
			}
		}
		
		
		// Add user defined data point sequences to performance data
		// -----------------------
		initializeDataCollectionSequences();
		
		
		// update performance data
		// -----------------------
		testDurationStart = System.currentTimeMillis();
		performanceData.setStartDate();
		performanceData.addSnapshot(getCpuUsagePercent());
		performanceData.setSnapshotsTimeshift(samplingTimeshift);
		performanceData.setEndDate();
		if(browserCacheStatistic != null)
		{
			performanceData.setBrowserCacheStatistic(browserCacheStatistic);
		}
		
		// initialize global context for plug-ins  
		LoadtestPluginContext globalPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, new ChainAPIsExample());
		
		
		// --------------------------
		// *** start of load test ***
		// ---------------------------
		
		
		// special case for execution plans - start test with zero virtual users
		// ---------------------------------------------------------------------
		if (hasExecutionPlan())
		{
			if (concurrentUsers != 0)
			{
				System.out.println("*** Fatal Error: Load test with execution plan must start with zero number of virtual users - Load test program aborted ***");
				System.exit(1);
			}
			try
			{
				getUserContextTable().getWriteLock().lock();
				
				ChainAPIsExample simulatedUser = new ChainAPIsExample(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
				
				HttpLoadTestUserContext userContext = new HttpLoadTestUserContext(simulatedUser, null);
				userContext.setLoadTestUserExecutionPlanPlaceholder();
				getUserContextTable().getUserContextList().add(userContext);
				simulatedUser.setOwnLoadTestUserContext(userContext);
			}
			finally
			{
				getUserContextTable().getWriteLock().unlock();
			}
		}
		
		
		// start virtual users as threads (normal case)
		// --------------------------------------------
		usersToBeIncreasedAtStartupOffset.set(concurrentUsers);
		for (int x = 0; x < concurrentUsers; x++)
		{
			if (cancelIncrementDecrementUser || cancelInitialIncrementUser)
			{
				usersToBeIncreasedAtStartupOffset.set(0);
				break;
			}
			
			// start load test thread
			// ----------------------
			Thread t = null;
			try
			{
				getUserContextTable().getWriteLock().lock();
				
				ChainAPIsExample simulatedUser = new ChainAPIsExample(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
				t = new Thread(simulatedUser);
				String threadName = "" + getUserContextTable().getUserContextList().size();
				threadName = THREAD_NAME.substring(0, 7 - threadName.length()) + threadName;
				t.setName(threadName);
				if (debugLoops && (simulatedUser.getLog().size() > 0))
				{
					synchronized (dumpLock)
					{
						simulatedUser.dumpLog(threadName + " ", System.out);		// dump log of constructor
					}
				}
				HttpLoadTestUserContext userContext = new HttpLoadTestUserContext(simulatedUser, t);
				getUserContextTable().getUserContextList().add(userContext);
				simulatedUser.setOwnLoadTestUserContext(userContext);
			}
			finally
			{
				getUserContextTable().getWriteLock().unlock();
			}
			t.start();
			usersToBeIncreasedAtStartupOffset.decrementAndGet();
			System.out.println("# --- thread " + t.getName() + " created --- " + ZoneTime.dateToShortString() + " ---");
			
			while (isSuspend())
			{
				try { Thread.currentThread().sleep(100); } catch (InterruptedException ie) { break; }
				if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
					break;
				
				// check if max test duration reached during suspend
				if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
					break;
				
				// display and sample temporary performance data all "sampling interval" seconds - also during suspend
				if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
				{
					performanceData.addSnapshot(getCpuUsagePercent());
					performanceData.setEndDate();
				}
			}
			
			if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
				break;
			
			if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
				break;
			
			// startup delay for next thread
			// -----------------------------
			try
			{
				if (!singleUserMode)   // create concurrent users
				{
					if (plannedStartupDelay <= 3000)
						sleepRemoteInterruptable(plannedStartupDelay);
					else
					{
						long startupDelayStartTime = System.currentTimeMillis();
						while (true)
						{
							if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
								break;
							if ((System.currentTimeMillis() - startupDelayStartTime) >= plannedStartupDelay)
								break;
							
							Thread.currentThread().sleep(100);
							
							// display and sample temporary performance data all "sampling interval" seconds
							if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
							{
								performanceData.addSnapshot(getCpuUsagePercent());
								performanceData.setEndDate();
							}
						}
						while (isSuspend())
						{
							try { Thread.currentThread().sleep(100); } catch (InterruptedException ie) { break; }
							if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
								break;
							
							// check if max test duration reached during suspend
							if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
								break;
							
							// display and sample temporary performance data all "sampling interval" seconds - also during suspend
							if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
							{
								performanceData.addSnapshot(getCpuUsagePercent());
								performanceData.setEndDate();
							}
						}
						
					}
				}
				else
					t.join();   // single user mode
			}
			catch (InterruptedException ie) { break; }
			if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
				break;
			
			
			// display and sample temporary performance data all "sampling interval" seconds
			if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
			{
				performanceData.addSnapshot(getCpuUsagePercent());
				performanceData.setEndDate();
				if (!isRemote())
				{
					synchronized (dumpLock)
					{
						performanceData.dump(false);
					}
				}
			}
		}
		usersToBeIncreasedAtStartupOffset.set(0);	// just in case that a thread is not started for any reason
		
		
		// wait for test-end in a loop
		// ---------------------------
		boolean allDone = false;
		while (!allDone)
		{
			// test aborted ?
			if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
			{
				nearRemoteEnd();
				String abort = "";
				if (abortedByRemote())
					abort = "*** test aborted by remote command ***";
				if (abortedByEofInputFile())
				{
					abort = "*** test aborted at eof of input file ***";
					performanceData.addTestDescription(abort);
				}
				if (abortedByUniqueNumberVar())
				{
					abort = "*** test aborted as an unique number is out of values ***";
					performanceData.addTestDescription(abort);
				}
				synchronized (dumpLock)
				{
					addSSLCacheStatistic();
					performanceData.addSnapshot(getCpuUsagePercent());
					performanceData.setEndDate();
					
					if (!isRemote())
						performanceData.dump(false);
					if (resultFile != null)
						try { performanceData.writeObjectToFile(getSymmetricEncryptContext(), resultFile); } catch (IOException ie) { ie.printStackTrace(); }
					
					if(replayMode)
					{
						saveReplaySnapShots("ZTReplay.snapshot");
					}
					
					System.out.println();
					System.out.println(abort);
					System.out.println();
					System.out.flush();
					try { Thread.currentThread().sleep(10000); } catch (InterruptedException ie) {}
					System.exit(0);
				}
			}
			
			// display and sample temporary performance data all "sampling interval" seconds
			if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
			{
				performanceData.addSnapshot(getCpuUsagePercent());
				performanceData.setEndDate();
				if (!isRemote())
				{
					synchronized (dumpLock)
					{
						performanceData.dump(false);
					}
				}
			}
			
			// check if test has been done in case of decrement users to zero
			if ((decrementUser) && (plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
				decrementUser = false;
			// check if test has been done
			if (!hasExecutionPlan())
				allDone = isTestDone();
			else
			{
				if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
				{
					performanceData.addTestDescription("*** Warning: Load test aborted. Execution plan time limit exceeded after " + plannedTestDuration + " seconds ***");
					triggerAbort();
				}
			}
			
			// if not all is done: sleep one second
			if (!allDone)
				try { sleepRemoteInterruptable(1000); } catch (InterruptedException ie) { break; }
		} // end of wait loop
		
		
		// mark near end of test - only if a remote interface has been used
		nearRemoteEnd();
		
		
		
		// final test result - test completed
		// ----------------------------------
		performanceData.addSnapshot(getCpuUsagePercent());
		performanceData.setEndDate();
		if(replayMode)
		{
			saveReplaySnapShots("ZTReplay.snapshot");
		}
		
		// save test result
		performanceData.dump(true);
		if (resultFile != null)
			try { performanceData.writeObjectToFile(getSymmetricEncryptContext(), resultFile); } catch (IOException ie) { ie.printStackTrace(); }
		
		// all done
		System.out.flush();
		
		// wait for official end of test - only if a remote interface has been used
		waitRemoteEnd();
		
		System.out.println();
		System.out.println("Result File: " + resultFile);
		
		// now dead
		System.exit(0);
	}


	/**
	 * implement VaryingLoadInterface: support to increment the number of simulated users at runtime.
	 */
	public void setIncrementUser(long startTime)
	{
		incrementUserStartTime = startTime;
		incrementUserFlag = true;
	}

	public boolean isIncrementUser()
	{
		return incrementUserFlag;
	}

	public long getIncrementUserStartTime()
	{
		return incrementUserStartTime;
	}

	public void setIncrementUserStartTime(long startTime)
	{
		incrementUserStartTime = startTime;
	}

	public void clearIncrementUser()
	{
		incrementUserFlag = false;
	}

	public int incrementUsers(int numAddUsers, long startupDelay)
	{
		if (startupDelay < 0)
			startupDelay = plannedStartupDelay;
		return incrementUsersImplementation(numAddUsers, startupDelay, this);
	}

	static int incrementUsersImplementation(int numAddUsers, long startupDelay, HttpLoadTest httpLoadTest)
	{
		long startupDelayOffset = 0;
		if ((getClusterTotalMemberCount() > 1) && (getClusterMemberLocalId() > 0))
			startupDelayOffset = (startupDelay / ((long) getClusterTotalMemberCount())) * ((long) getClusterMemberLocalId());
		
		ArrayList<HttpLoadTestUserContext> addUserContextList = new ArrayList<HttpLoadTestUserContext>(numAddUsers);
		for (int x = 0; x < numAddUsers; x++)
		{
			if (abortedByRemote() || abortedByEofInputFile() || abortedByUniqueNumberVar())
				return x;
			
			// initialize load test thread
			// ---------------------------
			try
			{
				getUserContextTable().getWriteLock().lock();
				
				ChainAPIsExample simulatedUser = new ChainAPIsExample(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
				HttpLoadTestUserContext userContext = prepareIncrementUser(simulatedUser, System.currentTimeMillis() + startupDelayOffset + (x * startupDelay));
				addUserContextList.add(userContext);
				simulatedUser.setOwnLoadTestUserContext(userContext);
				if (debugLoops && (simulatedUser.getLog().size() > 0))
				{
					synchronized (dumpLock)
					{
						simulatedUser.dumpLog(userContext.getLoadTestThread().getName() + " ", System.out);		// dump log of constructor
					}
				}
			}
			finally
			{
				getUserContextTable().getWriteLock().unlock();
			}
		}
		
		// start load test threads by a thread
		// -----------------------------------
		HttpLoadTestIncrementUserThread incrementUserThread = new HttpLoadTestIncrementUserThread(httpLoadTest, addUserContextList);
		incrementUserThread.start();
		return numAddUsers;
	}


	/**
	 * Internal method used to load and apply next proxy settings from ZBAProxySettings.properties file.
	 * Settings are applied only if file exists and nextProxyIsActive is set to true in the file.
	 * Note that settings stored with the session, will be ineffective.
	 *
	 * @since V5.5-D
	 */
	private void applyNextProxySettingsFromFileIfExists()
	{
		
		String filename = "ZBAProxySettings.properties";
		BufferedReader bin = null;
		SymmetricEncryptContext decryptContext = getSymmetricEncryptContext();
		File file = new File(filename);
		
		if (file.exists())
		// open input file
		try
		{
			if (decryptContext == null)
			{
				bin = new BufferedReader(new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")));
			}
			else
			{
				// create a reader stream that decrypts the data on the fly
				bin = new BufferedReader(new InputStreamReader(SymmetricEncrypt.getCipherInputStream(new FileInputStream(filename), decryptContext), Charset.forName("UTF-8")));
			}
			
			if (bin != null)
			{
				Properties nextProxyConfigProperties = new Properties();
				nextProxyConfigProperties.load(bin);
				NextProxyConfig nextProxyConfig = new NextProxyConfig();
				nextProxyConfig.setActive(Boolean.valueOf(nextProxyConfigProperties.getProperty("nextProxyIsActive")));
				proxyEnabled = nextProxyConfig.isActive();                             // if false: do not use a proxy server
				
				if (proxyEnabled)
				{
					nextProxyConfig.disableHttpProxyCache(Boolean.valueOf(nextProxyConfigProperties.getProperty("nextProxyHttpCacheDisabled")));
					proxyCacheDisabled = nextProxyConfig.isDisabledHttpProxyCache();
					nextProxyConfig.setHttpHost(nextProxyConfigProperties.getProperty("nextProxyHttpHost"));
					proxyHttpHost = nextProxyConfig.getHttpHost();
					nextProxyConfig.setHttpPort(Integer.valueOf(nextProxyConfigProperties.getProperty("nextProxyHttpPort")));
					proxyHttpPort = nextProxyConfig.getHttpPort();
					nextProxyConfig.setHttpsHost(nextProxyConfigProperties.getProperty("nextProxyHttpsHost"));
					proxyHttpsHost = nextProxyConfig.getHttpsHost();
					nextProxyConfig.setHttpsPort(Integer.valueOf(nextProxyConfigProperties.getProperty("nextProxyHttpsPort")));
					proxyHttpsPort = nextProxyConfig.getHttpsPort();
					nextProxyConfig.setAuthUsername(nextProxyConfigProperties.getProperty("nextProxyAuthUsername"));
					nextProxyConfig.setAuthPassword(nextProxyConfigProperties.getProperty("nextProxyAuthPassword"));
					nextProxyConfig.setNoNextProxy(nextProxyConfigProperties.getProperty("noNextProxy"));
					noProxyFor = nextProxyConfig.getNoNextProxy();
					
					String proxyAuth = "";
					
					if (nextProxyConfig.getAuthUsername() != null)
					{
						proxyAuth = nextProxyConfig.getAuthUsername() + ":";
						if (nextProxyConfig.getAuthPassword() != null)
							proxyAuth = proxyAuth + nextProxyConfig.getAuthPassword();
					}
					
					String  proxyAuthorization = null;
					
					if (proxyAuth.length() > 1)
						proxyAuthorization = Base64Encoder.encode(proxyAuth);		// Base 64 encoded - used for basic authentication only
					
					commonNextProxyConfig = new NextProxyConfig(proxyEnabled, proxyCacheDisabled, proxyHttpHost, proxyHttpPort, proxyHttpsHost, proxyHttpsPort, proxyAuthorization, noProxyFor);
					
					log("*** Warning: Applying proxy configuration from file " + filename + " ***");
				}
				
			}
		}
		catch (Throwable tr)
		{
			log("*** Unable to load proxy configuration from properties file " + filename + " ***");
			tr.printStackTrace();
		}
		finally
		{
			if (bin != null)
			{
				try { bin.close(); } catch (Exception e) {}
			}
		}
		
	}
		
	
	// source code of inline script "join_lat_lng"
	public static String getInlineScriptCode_1632772668836()
	{
		return "location = lat+\",\"+lng";
	}
	
	
	/**
	* Execute the inline script "join_lat_lng"
	* 
	* @param testURL 	the context of the executed URL call
	* @param urlCallPassed 	a flag that signals (when set to false) if a 'yellow' error did occur after the URL call (triggered by response verification settings)
	* @param threadStep 	the current execution step of the simulated user (page break or URL call)
	* @param totalLoopCounter 	the total number of executed loops - counted overall simulated users
	* @param innerLoopContext 	the context of the current inner loop, or null if the inline script in not executed inside an inner loop
	*
	* @return the context of the inline script
	*/
	public LoadtestInlineScriptContext executeInlineScript_1632772668836(HttpTestURL testURL, boolean urlCallPassed, int threadStep, int totalLoopCounter, InnerLoopContext innerLoopContext)
	{
		LoadtestInlineScriptContext inlineScriptContext = new LoadtestInlineScriptContext("join_lat_lng", ProxySnifferVarSourceInlineScript.EXEC_SCOPE_URL_END, "", this, LoadtestInlineScriptContext.RESULT_TYPE_SET_OUTPUT_VARS, 15, threadNumber, threadLoopCounter, socketPool, cookieHandler, testURL, threadStep);
		inlineScriptContext.setUrlCallPassed(urlCallPassed);
		InlineScriptExecutor inlineScriptExecutor = new InlineScriptExecutor(getInlineScriptCode_1632772668836(), inlineScriptContext);
		log("Executing inline script \"" + inlineScriptContext.getScriptTitle() + "\"");
		LoadtestInlineScriptVar inputVar1 = new LoadtestInlineScriptVar("lat", Lib.nullToBlank(lat), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addInputVar(inputVar1);
		LoadtestInlineScriptVar inputVar2 = new LoadtestInlineScriptVar("lng", Lib.nullToBlank(lng), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addInputVar(inputVar2);
		LoadtestInlineScriptVar outputVar1 = new LoadtestInlineScriptVar("location", Lib.nullToBlank(location), 3);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar1);
		inlineScriptExecutor.execute();		// execute inline script
		location = inlineScriptContext.getOutputVar("location").getVarValue();
		log("<<< location = " + location);
		loopVarMap.put("lng", lng);
		loopVarMap.put("lat", lat);
		for (String stdoutLine : inlineScriptContext.getOutputStreamData())
			log(inlineScriptContext.getScriptTitle() + ": " + stdoutLine);
		for (String stderrLine : inlineScriptContext.getErrorStreamData())
			System.err.println(inlineScriptContext.getScriptTitle() + ": " + stderrLine);
		if (!inlineScriptExecutor.wasSuccessFulExecution())
		{
			if (!inlineScriptContext.isScriptAbort())
				throw new InlineScriptExecutionException("*** Execution of inline script \"" + inlineScriptContext.getScriptTitle() + "\" failed ***");
		}
		return inlineScriptContext;
	}
	/**
	 * Internal method used to initialize all data collection sequences for user defined data points.
	 * @since V6.0-A
	 */
	private static void initializeDataCollectionSequences()
	{
		int newSequenceId = performanceData.getDataCollectionSet().getNewSequenceId();
		
		
	}
		

}	// end of class

