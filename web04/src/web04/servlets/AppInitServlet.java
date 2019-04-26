package web04.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("App init servlet 준비");
        super.init(config);
        try { 
            ServletContext sc =this.getServletContext();
            ServletContext sc1 = config.getServletContext();
            if( sc == sc1)
                System.out.println("동일");
            
            Class.forName(sc.getInitParameter("driver"));
            Connection conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
            sc.setAttribute("conn",conn);
        } catch (Throwable e) {
            throw new ServletException (e);
        }
    }

    @Override
    public void destroy() {
        System.out.println("app init servlet 마무리");
        super.destroy();
        Connection conn = (Connection)this.getServletContext().getAttribute("conn");
        try {
            
            if(conn != null && conn.isClosed() == false) conn.close();
        
        }   catch (Exception e) {}
    }
   
    
    
}
