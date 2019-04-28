package web04.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web04.controller.Controller;
import web04.controller.MemberAddController;
import web04.controller.MemberDeleteController;
import web04.controller.MemberListController;
import web04.controller.MemberLoginController;
import web04.controller.MemberLogoutController;
import web04.controller.MemberUpdateController;
import web04.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();
        
        try {
            ServletContext sc = this.getServletContext();
            HashMap<String, Object> model = new HashMap<>();
            model.put("memberDao", sc.getAttribute("memberDao"));
            String pageControllerPath = null;
            Controller pageController = null;
            if("/member/list.do".equals(servletPath)) {
                pageController = new MemberListController();
            } else if ("/member/add.do".equals(servletPath)) {
                pageController = new MemberAddController();
                if(request.getParameter("email") != null) {
                    model.put("member", new Member().setEmail(request.getParameter("email")).setName(request.getParameter("name")).setPassword(request.getParameter("password")));
                }
            } else if("/member/update.do".equals(servletPath)) {
                pageController = new MemberUpdateController();
                if(request.getParameter("email") != null) {
                    model.put("member", new Member().setNo(Integer.parseInt(request.getParameter("no"))).setEmail(request.getParameter("email")).setName(request.getParameter("name")));
                } else {
                    model.put("no",Integer.parseInt(request.getParameter("no")));
                }

            } else if("/member/delete.do".equals(servletPath)) {
                pageController = new MemberDeleteController();
                model.put("no",Integer.parseInt(request.getParameter("no")));
            } else if("/auth/login.do".equals(servletPath)) {
                pageController =new MemberLoginController();
               
                if(request.getParameter("email") != null) {
                    model.put("email",request.getParameter("email"));
                    model.put("password",request.getParameter("password"));
                    model.put("session", request.getSession());
                }
                
            } else if("/auth/logout.do".equals(servletPath)) {
                pageController =new MemberLogoutController();
                model.put("session",request.getSession());
            }

           

            // 요청 페이지로 작업을 완료 한후 request 저장소에 viewUrl이 저장된다.
            
            String viewURL = pageController.execute(model);
            for (String key : model.keySet()) {
                request.setAttribute(key, model.get(key));
            }
            if(viewURL.startsWith("redirect:")) {
                response.sendRedirect(viewURL.substring(9));
                return;
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(viewURL);
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
