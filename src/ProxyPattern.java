public class ProxyPattern {
	public static void main(String[] args) {
		var normalClient = new HTTPClientBuilder(true).Build();
		var retryClient = new HTTPClientBuilder(true).AddRetry(5).Build();
		var cacheClient = new HTTPClientBuilder(true).AddRetry(5).AddCache().Build();
		
		var repository = new SandwichRepository(cacheClient);
		var orderHandler = new OrderHandler(retryClient);
		
		var process = new OrderProcess(repository, orderHandler);
		do {
			process.Execute();
		} while (true);
	}
}