package web04.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web04.dao.MySqlMemberDao;
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
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
            
            request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
            request.setAttribute("viewUrl", "MemberUpdate.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            
            ServletContext sc = this.getServletContext();
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
            
            Member member1  =(Member)request.getAttribute("member");
            System.out.println(member1.getEmail());
            System.out.println(member1.getName());
            memberDao.updateMember((Member)request.getAttribute("member"));
            request.setAttribute("viewUrl", "redirect:list.do");
        } catch (Exception e) {
            throw new ServletException (e);
        }
    }
    
}
