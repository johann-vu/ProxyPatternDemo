
public interface ISandwichRepository {
	ISandwich[] GetAllSandwiches() throws Exception;
	ISandwich GetSandwich(String id) throws Exception;
}
