public class ProxyPattern {
	public static void main(String[] args) {
		
		var builder = new HTTPClientBuilder(true);

		var normalClient = builder.Build();
		var retryClient = builder.AddRetry(5).Build();
		var cacheClient = builder.AddRetry(5).AddCache().Build();
		
		var repository = new SandwichRepository(cacheClient);
		var orderHandler = new OrderHandler(retryClient);
		
		var process = new OrderProcess(repository, orderHandler);
		do {
			process.Execute();
		} while (true);
	}
}