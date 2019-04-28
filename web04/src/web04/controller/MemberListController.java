package web04.controller;

import java.util.Map;

import web04.dao.MemberDao;

public class MemberListController implements Controller {

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao)model.get("memberDao");
        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
    
    
}
