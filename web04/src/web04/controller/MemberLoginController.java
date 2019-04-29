package web04.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import web04.annotation.Component;
import web04.bind.DataBinding;
import web04.dao.MySqlMemberDao;
import web04.vo.Member;

@Component("/auth/login.do")
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
        Member member = (Member) model.get("loginInfo");
        // 데이터 바인딩 선언시 loginInfo로 이름을 지정했다.
        // 바인딩 자체는 타입으로 자동으로 매칭하기 때문에 Member 타입으로 저장되었다.
        if(member.getEmail()== null) {
            return "redirect:/web04/auth/LoginForm.jsp";
        } else {
            member = memberDao.exist(member.getEmail(), member.getPassword());
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
