package web04.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web04.dao.MemberDao;
import web04.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("MemberForm.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        Connection conn = null;
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            memberDao.insert(new Member().setEmail(request.getParameter("email")).setName(request.getParameter("name")).setPassword(request.getParameter("password")));
            RequestDispatcher rd = request.getRequestDispatcher("list");
            rd.include(request, response);

        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }
        
    }

}
