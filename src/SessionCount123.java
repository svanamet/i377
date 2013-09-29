

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCount
 *
 */
public class SessionCount123 extends HttpServlet implements HttpSessionListener {
	private static final long serialVersionUID = 1L;

	private static int totalActiveSessions = 0;
	
    public void sessionCreated(HttpSessionEvent arg0) {
        totalActiveSessions++;
    }

    public void sessionDestroyed(HttpSessionEvent arg0) {
        totalActiveSessions--;
    }
    
    public static int getTotalActiveSessions() {
    	return totalActiveSessions;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().println("Session count: " + totalActiveSessions);
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
