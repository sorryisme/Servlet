package web04.controller;

import java.util.Map;

import web04.dao.MemberDao;
import web04.vo.Member;

public class MemberUpdateController implements Controller{

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        MemberDao memberDao = null;
        if(model.get("member") == null) {
            memberDao =(MemberDao)model.get("memberDao");
            model.put("member", memberDao.selectOne((int)model.get("no")));
            
            return "MemberUpdate.jsp";
        } else {
            
            memberDao = (MemberDao)model.get("memberDao");
            Member member =(Member)model.get("member");
            memberDao.updateMember(member);
            
            return "redirect:list.do";
            
        }
    }

    
}
