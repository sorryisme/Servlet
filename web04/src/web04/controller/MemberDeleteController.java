package web04.controller;

import java.util.Map;

import web04.annotation.Component;
import web04.bind.DataBinding;
import web04.dao.MySqlMemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding{
    MySqlMemberDao memberDao;
    
    public MemberDeleteController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }
    
    @Override
    public Object[] getDataBinders() {
        return new Object[]{"no",Integer.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        memberDao.delete((int)model.get("no"));
        return "redirect:list.do";
    }

    
    
}
