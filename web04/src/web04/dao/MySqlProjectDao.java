package web04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import web04.annotation.Component;
import web04.vo.Project;

@Component("ProjectDao")
public class MySqlProjectDao implements ProjectDao{
    DataSource ds;

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Project> selectList() throws Exception{
        Connection conn =null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<Project> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement("select * from projects");
            rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(new Project().setNo(rs.getInt("pno")).setTitle(rs.getString("pname")).setContent(rs.getString("content")).setStartDate(rs.getDate("sta_date")).setEndDate(rs.getDate("end_date")).setState(rs.getInt("state")).setCreatedDate(rs.getDate("cre_date")).setTags(rs.getString("tags")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs!=null)rs.close();}catch (Exception e) {}
            try { if(stmt!=null)stmt.close();}catch(Exception e) {}
            try {if(conn!=null)conn.close();} catch (Exception e) {}
        }


        return null;
    }

    @Override
    public int insert(Project project) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(
                    "INSERT INTO PROJECTS (PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS) VALUES(?,?,?,?,0,NOW(),?)");
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getContent());
            stmt.setDate(3, new java.sql.Date(project.getStartDate().getDate()));
            stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setString(5, project.getTags());
            return stmt.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally {
            try {if(stmt != null) stmt.close();} catch (Exception e) {}
            try {if(connection != null)connection.close();} catch (Exception e) {}
        }
    }

    @Override
    public Project selectOne(int no) throws Exception {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT PNO,PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS FROM PROJECTS WHERE PNO="+no);
            if(rs.next()) {
                return new Project().setNo(rs.getInt("pno")).setTitle(rs.getString("pname")).setContent(rs.getString("content")).setStartDate(rs.getDate("STA_DATE")).setEndDate(rs.getDate("END_DATE")).setState(rs.getInt("STATE")).setCreatedDate(rs.getDate("CRE_DATE")).setTags(rs.getString("TAGS"));

            } else {
                throw new Exception("해당 번호의 프로젝트를 찾을 수 없습니다.");
            } 

        } catch (Exception e) {
            throw e;
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e) {}
            try { if(stmt != null) rs.close();} catch (Exception e) {}
            try { if(connection != null) rs.close();} catch (Exception e) {}
        }
    }


    @Override
    public int update(Project project) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(
                    "UPDATE PROJECTS SET PNAME=?, CONTENT=?, STA_DATE=?,END_DATE=?,STATE=?,TAGS=? WHERE PNO=?"
                    );
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getContent());
            stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setInt(5, project.getState());
            stmt.setString(6, project.getTags());
            stmt.setInt(7, project.getNo());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {if(stmt != null) stmt.close();} catch (Exception e) {}
            try {if(connection != null) connection.close();} catch (Exception e) {}
        }
    }

    @Override
    public int delete(int no) throws Exception {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            return stmt.executeUpdate("Delete from projects where pno="+no);
        } catch (Exception e) {
            throw e;
        } finally {
            try {if(stmt != null) stmt.close();} catch (Exception e) {}
            try {if(connection != null) connection.close();} catch (Exception e) {}
        }
    }
}