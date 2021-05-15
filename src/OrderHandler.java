
public class OrderHandler implements IOrderHandler {
	IHTTPClient client;
	
	public OrderHandler(IHTTPClient pClient) {
		client = pClient;
	}

	@Override
	public String PlaceOrder(String id) throws Exception{
		var result = this.client.Do("/order/"+id);
		if (result == "SERVER_ERROR") {
			throw new Exception();		
		}
		if (result == "BAD_REQUEST") {
			return "";	
		}
		return result;
	}

}
