package web04.dao;

import java.util.List;

import web04.vo.Project;

public interface ProjectDao{
    List<Project> selectList() throws Exception;
    int insert(Project project) throws Exception;
    Project selectOne(int no) throws Exception;
    int update(Project project) throws Exception;
    int delete(int no) throws Exception;
}
