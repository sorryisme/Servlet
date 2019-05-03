package web04.controller;

import java.util.Map;

import web04.bind.DataBinding;
import web04.dao.ProjectDao;

public class ProjectDeleteController implements Controller, DataBinding{
    ProjectDao projectDao;
    
    public ProjectDeleteController setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {"no",Integer.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Integer no = (Integer)model.get("no");
        projectDao.delete(no);
        
        return "redirect:list.do";
    }
    
    
    
}
