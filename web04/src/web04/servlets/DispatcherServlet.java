package web04.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web04.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();
        try {
            String pageControllerPath = null;
            if("/member/list.do".equals(servletPath)) {
                pageControllerPath = "/member/list";
            } else if ("/member/add.do".equals(servletPath)) {
                pageControllerPath = "/member/add";
                if(request.getParameter("email") != null) {
                    request.setAttribute("member", new Member().setEmail(request.getParameter("email")).setName(request.getParameter("name")).setPassword(request.getParameter("password")));
                }
            } else if("/member/update.do".equals(servletPath)) {
                pageControllerPath = "/member/update";
                if(request.getParameter("email") != null) {
                    request.setAttribute("member", new Member().setNo(Integer.parseInt(request.getParameter("no"))).setEmail(request.getParameter("email")).setName(request.getParameter("name")));
                }

            } else if("/member/delete.do".equals(servletPath)) {
                pageControllerPath ="/member/delete";
            } else if("/auth/login.do".equals(servletPath)) {
                pageControllerPath ="/auth/login";
            } else if("/auth/logout.do".equals(servletPath)) {
                pageControllerPath ="/auth/logout";
            }

            RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);

            rd.include(request, response);
            // 요청 페이지로 작업을 완료 한후 request 저장소에 viewUrl이 저장된다.
            
            String viewURL = (String) request.getAttribute("viewUrl");
            if(viewURL.startsWith("redirect:")) {
                response.sendRedirect(viewURL.substring(9));
                return;
            } else {
                rd = request.getRequestDispatcher(viewURL);
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }

    }
}
