package web04.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        PreparedStatement stmt = null;
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            stmt = conn.prepareStatement("insert into members(email,pwd,mname,cre_date,mod_date) values(?,?,?,now(),now())");
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            stmt.setString(3, request.getParameter("name"));
            stmt.executeUpdate();
            RequestDispatcher rd = request.getRequestDispatcher("list");
            rd.include(request, response);
            /*redirect는 html을 출력하지 않는다 
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원등록결과</title>");
            out.println("<meta http-equiv='Refresh' content='1; url=list'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>등록 성공입니다.</p>");
            out.println("</body></html>");
            */
            //response.addHeader("Refresh", "1;url=list");

        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        } finally {
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
        }

    }

}
