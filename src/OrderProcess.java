public class OrderProcess {
	IOrderHandler orderHandler;
	ISandwichRepository respository;
	Printer printer;
	
	public OrderProcess(ISandwichRepository pRespository, IOrderHandler pOrderHandler) {
		orderHandler = pOrderHandler;
		respository = pRespository;
		printer = new Printer();
	}
	
	public void Execute() {
		try {
			printer.PrintWelcome();
			var sandwiches = respository.GetAllSandwiches();
			var selection = printer.sandwichSelection(sandwiches);
			printer.PrintOrderStart(selection);
			var orderResponse = orderHandler.PlaceOrder(selection);
			printer.PrintOrderPlaces(orderResponse);
		} catch (Exception e) {
			printer.PrintError();
		}
		
	}
}
