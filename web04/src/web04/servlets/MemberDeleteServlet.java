package web04.servlets;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web04.dao.MySqlMemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        try {
            ServletContext sc = this.getServletContext();
            
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
            
            memberDao.delete(Integer.parseInt(request.getParameter("no")));
            
            request.setAttribute("viewUrl", "redirect:list.do");
            
        } catch (Exception e) {
            throw new ServletException(e);
            
        }
    }
}
