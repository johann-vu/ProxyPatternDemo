
public class HTTPClientBuilder {
	private IHTTPClient client;
	private boolean showDevLog;
	
	public HTTPClientBuilder(boolean pShowDevLog, IHTTPClient pClient) {
		client = pClient;
		showDevLog = pShowDevLog;
	}
	
	public HTTPClientBuilder(boolean pShowDevLog) {
		client = new SlowHTTPClient(showDevLog);
		showDevLog = pShowDevLog;
	}
	
	public IHTTPClient Build() {
		return client;
	}
	
	public HTTPClientBuilder AddCache() {
		return new HTTPClientBuilder(showDevLog, new CacheProxy(client, showDevLog));
	}
	
	public HTTPClientBuilder AddRetry(int maxRetries) {
		return new HTTPClientBuilder(showDevLog, new RetryProxy(client, maxRetries, showDevLog));
	}
}
