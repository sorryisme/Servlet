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

import web04.dao.MemberDao;
import web04.vo.Member;


@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet{

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        
        Connection conn= null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            ServletContext sc = this.getServletContext();
            // app init Servlet에서 setAttribute로 처리했으니 getAttribute로 처리
            conn = (Connection)sc.getAttribute("conn");
            
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            request.setAttribute("members", memberDao.selectList());
            response.setContentType("text/html; charset=UTF-8");

            
           
            RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
            rd.include(request, response);
            
            
            //jsp 페이지에서 <jsp:include page="/Tail.jsp"/>와 동일
            
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        } finally {
            try { if(rs != null ) rs.close();} catch (Exception e) {}
            try { if(stmt != null) stmt.close();} catch (Exception e) {}
        }
    }
}
