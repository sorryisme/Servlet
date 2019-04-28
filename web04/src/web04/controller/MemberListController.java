package web04.controller;

import java.util.Map;

import web04.dao.MySqlMemberDao;

public class MemberListController implements Controller {
    MySqlMemberDao memberDao;
    
    public MemberListController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }
    
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
    
    
}
