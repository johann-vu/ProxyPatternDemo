public class SandwichRepository implements ISandwichRepository {
	
	private IHTTPClient client;
	
	public SandwichRepository(IHTTPClient pClient) {
		this.client = pClient;
	}

	@Override
	public ISandwich[] GetAllSandwiches() throws Exception {
		var result = this.client.Do("/sandwiches");
		if (result == "SERVER_ERROR") {
			throw new Exception();		
		}
		var sandwichesRaw = result.split("&");
		var sandwiches = new ISandwich[sandwichesRaw.length];
		
		for (int i = 0; i < sandwichesRaw.length; i++) {
			sandwiches[i] = parseSandwich(sandwichesRaw[i]);
		}
		
		return sandwiches;
	}

	@Override
	public ISandwich GetSandwich(String id) throws Exception {
		var result = this.client.Do("/sandwiches/"+id);
		if (result == "SERVER_ERROR") {
			throw new Exception();		
		}
		if (result == "BAD_REQUEST") {
			return null;
		}
		return parseSandwich(result);
	}
	
	private ISandwich parseSandwich(String input) {
		var values = input.split(";");
		return new Sandwich(values[0], values[1], values[2], values[3], values[4]);
	}

}
