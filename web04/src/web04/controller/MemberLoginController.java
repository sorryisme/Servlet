package web04.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import web04.bind.DataBinding;
import web04.dao.MySqlMemberDao;
import web04.vo.Member;

public class MemberLoginController implements Controller,DataBinding{
    MySqlMemberDao memberDao;
    
    public MemberLoginController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }
    
    @Override
    public Object[] getDataBinders() {
        return new Object[] {"loginInfo",web04.vo.Member.class};
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        if(model.get("email") == null) {
            return "redirect:/web04/auth/LoginForm.jsp";
        } else {
            Member member = memberDao.exist((String)model.get("email"), (String)model.get("password"));
            if(member != null) {
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:/web04/member/list.do";
            } else {
                return "redirect:/web04/auth/LoginFail.jsp";
            }
        }
    }

    
}
