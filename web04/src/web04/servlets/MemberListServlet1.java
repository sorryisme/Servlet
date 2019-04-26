package web04.servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import web04.vo.Member;


/*@WebServlet("/member/list")
*/public class MemberListServlet1 extends GenericServlet{

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        
        Connection conn= null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select mno, mname,email, cre_date from members order by mno asc");
            response.setContentType("text/html; charset=UTF-8");
            ArrayList<Member> members = new ArrayList<Member>();
            while(rs.next()) {
                members.add(new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")).setCreateDate(rs.getDate("cre_date")));
            }
            request.setAttribute("members", members);
            RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
            rd.include(request, response);
            
            
        } catch (Exception e) {
          throw new ServletException(e);
        } finally {
            try { if(rs != null ) rs.close();} catch (Exception e) {}
            try { if(stmt != null) stmt.close();} catch (Exception e) {}
            try { if(conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
