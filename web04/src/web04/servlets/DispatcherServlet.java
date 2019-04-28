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

import web04.bind.DataBinding;
import web04.bind.ServletRequestDataBinder;
import web04.controller.Controller;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();

        try {
            ServletContext sc = this.getServletContext();
            HashMap<String, Object> model = new HashMap<>();
            model.put("session",request.getSession());
            Controller pageController = (Controller)sc.getAttribute(servletPath);

            if(pageController instanceof DataBinding) {
                prepareRequestData(request,model,(DataBinding)pageController);
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

    private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model,
            DataBinding dataBinding) throws Exception{
        Object[] dataBinders = dataBinding.getDataBinders();
        String dataName = null;
        Class<?> dataType = null;
        Object dataObj =null;
        for (int i=0; i< dataBinders.length; i+=2) {
            dataName = (String)dataBinders[i];
            dataType = (Class<?>)dataBinders[i+1];
            dataObj = ServletRequestDataBinder.bind(request,dataType, dataName);
            model.put(dataName, dataObj);
        }
        
    }
}
