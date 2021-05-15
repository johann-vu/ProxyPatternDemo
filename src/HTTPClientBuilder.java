
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
		client = new CacheProxy(client, showDevLog);
		return this;
	}
	
	public HTTPClientBuilder AddRetry(int maxRetries) {
		client = new RetryProxy(client, maxRetries, showDevLog);
		return this;
	}
}
