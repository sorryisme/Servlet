package web04.controller;

import java.util.Map;

import web04.annotation.Component;
import web04.bind.DataBinding;
import web04.dao.ProjectDao;
import web04.vo.Project;

@Component("/project/add.do")
public class ProjectAddController implements Controller,DataBinding {
    ProjectDao projectDao;
    
    public ProjectAddController setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {"project",web04.vo.Project.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");
        if(project.getTitle() == null) {
            return "/project/ProjectForm.jsp";
        } else {
            projectDao.insert(project);
            return "redirect:list.do";
        }
        
    }
    
}
