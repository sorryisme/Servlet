package web04.controller;

import java.util.Map;

import web04.annotation.Component;
import web04.bind.DataBinding;
import web04.dao.MySqlMemberDao;
import web04.vo.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller,DataBinding{
    MySqlMemberDao memberDao;
    
    public MemberUpdateController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }
    @Override
    public Object[] getDataBinders() {
        return new Object[] {"no",Integer.class,"member",web04.vo.Member.class};
    }



    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        Member member =(Member)model.get("member"); 
        if(member.getEmail()== null) {
            model.put("member", memberDao.selectOne((int)model.get("no")));
            
            return "MemberUpdate.jsp";
        } else {
            
            member =(Member)model.get("member");
            memberDao.updateMember(member);
            
            return "redirect:list.do";
            
        }
    }

    
}
