package listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ex3.SetupDao;

public class ServletListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent arg0) {
    		try{
    			new SetupDao().createSchema();
    		}catch(Exception ex) {
    			System.out.println("Andmebaas on juba olemas");
    			System.out.println("Stack:");
    			System.out.println(ex);
    		}
    }

    

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}
