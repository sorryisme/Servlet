package web04.controller;

import java.util.Map;

import web04.annotation.Component;
import web04.bind.DataBinding;
import web04.dao.MySqlMemberDao;
import web04.vo.Member;

@Component("/member/add.do")
public class MemberAddController implements Controller,DataBinding{
    MySqlMemberDao memberDao;
    
    public MemberAddController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {"member",web04.vo.Member.class};
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member)model.get("member");
        
        if(member.getEmail() == null) {
            return "/member/MemberForm.jsp";
        } else {
            memberDao.insert(member);
            return "redirect:list.do";
        }
    }
    
}
