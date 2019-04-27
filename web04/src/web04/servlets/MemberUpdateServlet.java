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

import web04.dao.MemberDao;
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
        response.setContentType("text/html; charset=UTF-8");
        try {
            ServletContext sc  = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            
            request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
            RequestDispatcher rd = request.getRequestDispatcher("MemberUpdate.jsp");
            rd.include(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            
            Member member = new Member().setEmail(request.getParameter("email")).setName(request.getParameter("name")).setNo(Integer.parseInt(request.getParameter("no")));
            memberDao.updateMember(member);
            
            response.sendRedirect("list");
            
        } catch (Exception e) {
            throw new ServletException (e);
        }
    }
    
}
