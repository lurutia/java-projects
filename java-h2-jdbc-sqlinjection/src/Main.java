import jdbc.H2jdbcCreateDemo;
import jdbc.H2jdbcInsertDemo;
import jdbc.H2jdbcReadDemo;

public class Main {
   public static void main(String[] args) {
	   new H2jdbcCreateDemo().run();
	   new H2jdbcInsertDemo().run();
	   H2jdbcReadDemo readDemo = new H2jdbcReadDemo();
//	   new H2jdbcReadDemo().argRun("Mahnaz");
	   readDemo.run();
	   readDemo.login("Mahnaz", "pw25");
	   readDemo.login("Mahnaz", "1234");
	   readDemo.login("Mahnaz", "1234' or '1'='1");
   }  
}
