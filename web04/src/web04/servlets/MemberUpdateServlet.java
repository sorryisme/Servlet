package web04.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web04.vo.Member;

@WebServlet( 
            urlPatterns = {"/member/update"},
            initParams= {
                    @WebInitParam(name="driver", value="com.mysql.jdbc.Driver"),
                    @WebInitParam(name="url", value="jdbc:mysql://localhost/studydb"),
                    @WebInitParam(name="username",value="root"),
                    @WebInitParam(name="password",value="1111")})
public class MemberUpdateServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        response.setContentType("text/html; charset=UTF-8");
        try {
            ServletContext sc  = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select mno,email,mname,cre_date from members"
                    + " where mno =" + request.getParameter("no"));
            System.out.println(rs.next());
            request.setAttribute("member", new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")));
            RequestDispatcher rd = request.getRequestDispatcher("MemberUpdate.jsp");
            rd.include(request, response);
            
            
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try{ if (rs != null) rs.close();} catch (Exception e) {}
            try { if (stmt != null) stmt.close();} catch (Exception e) {}
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            stmt = conn.prepareStatement("update members set email=?, mname=?,mod_date=now() where mno=?");
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("name"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
            stmt.executeUpdate();
            response.sendRedirect("list");
            
        } catch (Exception e) {
            throw new ServletException (e);
        } finally {
            try { if(stmt != null)stmt.close();} catch (Exception e) {}
        }
    }
    
}
