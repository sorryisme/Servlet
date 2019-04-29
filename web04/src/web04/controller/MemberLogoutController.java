package web04.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import web04.annotation.Component;
import web04.dao.MySqlMemberDao;

@Component("/auth/logout.do")
public class MemberLogoutController implements Controller{
    MySqlMemberDao memberDao;

    public MemberLogoutController getMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        HttpSession session = (HttpSession)model.get("session");
        session.invalidate();
        return "redirect:/web04/member/list.do";
    }

    
}
