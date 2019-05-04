package web04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import web04.annotation.Component;
import web04.vo.Project;

@Component("ProjectDao")
public class MySqlProjectDao implements ProjectDao{
    SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Project> selectList() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectList("web04.dao.ProjectDao.selectList");
        } finally {
            sqlSession.close();
        }

    }

    @Override
    public int insert(Project project) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int count = sqlSession.insert("web04.dao.ProjectDao.insert",project);
            sqlSession.commit();
            return count;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Project selectOne(int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectOne("web04.dao.ProjectDao.selectOne", no);
        } finally {
            sqlSession.close();
        }
    }


    @Override
    public int update(Project project) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {

            int count = sqlSession.update("web.dao.ProjectDao.update",project);
            return count;

        } finally {
            sqlSession.close();
        }
    }

    @Override
    public int delete(int no) throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            int count = sqlSession.delete("web04.dao.ProjectDao.delete",no);
            return count;
        } finally {
            sqlSession.close();
        }
    }
}