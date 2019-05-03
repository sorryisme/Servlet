package web04.controller;

import java.util.Map;

import web04.annotation.Component;
import web04.bind.DataBinding;
import web04.dao.ProjectDao;
import web04.vo.Project;

@Component("/project/update.do")
    public class ProjectUpdateController implements Controller, DataBinding{
        ProjectDao projectDao;
        
        public ProjectUpdateController setProjectDao(ProjectDao projectDao) {
            this.projectDao = projectDao;
            return this;
        }

        @Override
        public Object[] getDataBinders() {
            return new Object[] {
                    "no",Integer.class,
                    "project", web04.vo.Project.class
                    };
        }

        @Override
        public String execute(Map<String, Object> model) throws Exception {
            Project project = (Project)model.get("project");
            
            if(project.getTitle() == null) {
                Integer no = (Integer)model.get("no");
                Project detailInfo = projectDao.selectOne(no);
                model.put("project", detailInfo);
            return "/project/ProjectUpdateForm.jsp";
        } else {
            projectDao.update(project);
            return "redirect:list.do";
        }
    }
}
