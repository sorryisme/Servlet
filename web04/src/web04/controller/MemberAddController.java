package web04.controller;

import java.util.Map;

import web04.dao.MemberDao;
import web04.vo.Member;

public class MemberAddController implements Controller{

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if(model.get("member") == null) {
            return "/member/MemberForm.jsp";
        } else {
            MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member member = (Member)model.get("member");
            memberDao.insert(member);
            return "redirect:list.do";
        }
    }
    
}
