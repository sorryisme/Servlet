package web04.controller;

import java.util.Map;

import web04.dao.MemberDao;

public class MemberDeleteController implements Controller{

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao)model.get("memberDao");
        memberDao.delete((int)model.get("no"));
        
        return "redirect:list.do";
    }

    
    
}
