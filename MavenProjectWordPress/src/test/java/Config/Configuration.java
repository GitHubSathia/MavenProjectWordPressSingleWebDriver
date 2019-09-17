package Config;

public class Configuration {
	//creating inner classes so that we can call it from different test cases
	//we are making it static because we have to call it from different test cases
	public static class URL{
		
		public static String appURL="http://localhost/WordPress5/wp-login.php";
	}
	public static class Browser{
		
		public static String browser="ff";
	}
	
	public static class Credentials{
		public static String username="WordPress5";
		public static String password="WordPress5";
		//public static String invalidusername="WordPress56";
		
	}
	

}
