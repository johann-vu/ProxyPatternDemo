
public class RetryProxy implements IHTTPClient {
	
	private int maxRetries;
	private IHTTPClient innerClient;
	private boolean showDevLog;

	public RetryProxy(IHTTPClient pInnerClient, int pMaxRetries) {
		new RetryProxy(pInnerClient, pMaxRetries, false);
	}
	
	public RetryProxy(IHTTPClient pInnerClient, int pMaxRetries, boolean pShowDevLog) {
		innerClient = pInnerClient;
		maxRetries = pMaxRetries;
		showDevLog = pShowDevLog;
	}

	public String Do(String path) {
		Printer.PrintDeveloperInfos("RetryProxy is handling request", showDevLog);
		var response = "";
		for (int i = 0; i < maxRetries; i++) {
			Printer.PrintDeveloperInfos("RetryProxy makes request #" + (i+1), showDevLog);
			response = innerClient.Do(path);
			if (response != "SERVER_ERROR") {
				break;
			}
		}
		Printer.PrintDeveloperInfos("CacheProxy returns response", showDevLog);
		return response;
	}
}
