import java.util.HashMap;
import java.util.Map;

public class SlowHTTPClient implements IHTTPClient {
	
	private double errorRate = 0.25;
	private int delay = 8;
	private Map<String, String> sandwiches;
	private int orderCount = 0;
	private boolean showDevLog;
	
	public SlowHTTPClient(boolean pShowDevLog) {
		showDevLog = pShowDevLog;
		this.sandwiches = new HashMap<String, String>();
		this.sandwiches.put("1", "1;Ham     ;3 €;B;Gluten;");
		this.sandwiches.put("2", "2;Cheese  ;3 €;B;Gluten, Laktose;");
		this.sandwiches.put("3", "3;Ham and Cheese;4 €;B;Gluten, Laktose;");
		this.sandwiches.put("4", "4;Thunfisch;4 €;C;Gluten;");
		this.sandwiches.put("5", "5;Vegan Patty;3 €;A;Gluten, Nüsse;");
		this.sandwiches.put("6", "6;Chicken;3 €;B;Gluten;");
	}

	public String Do(String path) {
		Printer.PrintDeveloperInfos("HTTPClient is handling request", showDevLog);
		delay();
		
		if (hasError()) {
			return "SERVER_ERROR";
		}
		
		var response = routing(path);
		Printer.PrintDeveloperInfos("HTTPClient returns response", showDevLog);
		return response;
	}
	
	private String routing(String path) {
		if (path == "/sandwiches") {
			return handleSandwiches();
		} else if (path.startsWith("/sandwiches/")) {
			return handleSandwich(path);
		} else if (path.startsWith("/order/")) {
			return handleOrder(path);
		}
		
		return "NOT_FOUND";
	}
	
	private String handleSandwiches() {
		var returnValue = "";
		for (String sandwich : this.sandwiches.values()) {
			returnValue = returnValue + sandwich +  "&";
		}
		return returnValue.substring(0, returnValue.length() - 1);
	}
	
	private String handleSandwich(String path) {
		var id = path.replace("/sandwiches/", "");
		return this.sandwiches.getOrDefault(id, "BAD_REQUEST");
	}
	
	private String handleOrder(String path) {
		var id = path.replace("/order/", "");
		if (!this.sandwiches.containsKey(id)) {
			return "BAD_REQUEST";
		}
		orderCount++;
		return "#" + orderCount;
	}
	
	private void delay() {
		try {
			Thread.sleep(delay*1000);
		} catch (InterruptedException e) {
			// Nothing happens
		}
	}
	
	private boolean hasError() {
		return Math.random() < errorRate;
	}

}
