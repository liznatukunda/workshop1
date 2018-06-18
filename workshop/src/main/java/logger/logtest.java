package logger;



//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logtest {
	
  public static void main(String[] args) {

	
	 
	  Logger logger = LoggerFactory.getLogger(logtest.class);
    
	  //Logger logger =Logger.getLogger(Logger.class.getName());
	// final Logger logger = LogManager.getLogger(logtest.class);
	  logger.warn("Goodbye World");
  }
}