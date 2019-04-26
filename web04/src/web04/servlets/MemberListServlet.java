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


@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet{

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        
        Connection conn= null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            // conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
            
            // app init Servlet에서 처리한 Conn을 사용하자
            // app init Servlet에서 setAttribute로 처리했으니 getAttribute로 처리
            conn = (Connection)sc.getAttribute("conn");
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select mno, mname,email, cre_date from members order by mno asc");
            response.setContentType("text/html; charset=UTF-8");
            ArrayList<Member> members = new ArrayList<Member>();
            while(rs.next()) {
                members.add(new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")).setCreateDate(rs.getDate("cre_date")));
            }
            request.setAttribute("members", members);
            RequestDispatcher rd = request.getRequestDispatcher("/Header.jsp");
            rd.include(request, response);
           
            rd = request.getRequestDispatcher("/member/MemberList.jsp");
            rd.include(request, response);
            
            rd = request.getRequestDispatcher("/Tail.jsp");
            rd.include(request, response);
            
            //jsp 페이지에서 <jsp:include page="/Tail.jsp"/>와 동일
            
            
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        } finally {
            try { if(rs != null ) rs.close();} catch (Exception e) {}
            try { if(stmt != null) stmt.close();} catch (Exception e) {}
        }
    }
}
