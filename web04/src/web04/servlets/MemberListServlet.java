package web04.servlets;

import java.io.IOException;
import java.sql.Connection;
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

import web04.dao.MySqlMemberDao;
import web04.vo.Member;


@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet{

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            ServletContext sc = this.getServletContext();
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
            
            request.setAttribute("members", memberDao.selectList());
            response.setContentType("text/html; charset=UTF-8");
            request.setAttribute("viewUrl", "/member/MemberList.jsp");
            
            
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try { if(rs != null ) rs.close();} catch (Exception e) {}
            try { if(stmt != null) stmt.close();} catch (Exception e) {}
        }
    }
}
