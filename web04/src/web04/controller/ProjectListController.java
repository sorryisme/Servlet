package web04.controller;

import java.util.Map;

import web04.annotation.Component;
import web04.dao.MySqlProjectDao;
@Component("/project/list.do")
public class ProjectListController implements Controller{
    MySqlProjectDao projectDao;
    
    public ProjectListController setProjectDao(MySqlProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }
    
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("projectList", projectDao.selectList());
        
        return "/project/list.jsp";
    }
    
}
