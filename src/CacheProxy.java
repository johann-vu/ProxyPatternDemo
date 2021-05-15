import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements IHTTPClient {
	private Map<String, String> cache;
	private IHTTPClient	innerClient;
	private boolean showDevLog;
	
	public CacheProxy(IHTTPClient pInnerClient, boolean pShowDevLog) {
		innerClient = pInnerClient;
		showDevLog = pShowDevLog;
		cache = new HashMap<String, String>();
	}

	public String Do(String path) {
		Printer.PrintDeveloperInfos("CacheProxy is handling request", showDevLog);
		if (cache.containsKey(path)) {
			Printer.PrintDeveloperInfos("CacheProxy is using cached value", showDevLog);
			Printer.PrintDeveloperInfos("CacheProxy returns response", showDevLog);
			return cache.get(path);
		}
		var response = innerClient.Do(path);
		Printer.PrintDeveloperInfos("CacheProxy is saving response", showDevLog);
		cache.put(path, response);
		Printer.PrintDeveloperInfos("CacheProxy returns response", showDevLog);
		return response;
	}

}
