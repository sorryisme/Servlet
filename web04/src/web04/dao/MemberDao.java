package web04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import web04.vo.Member;

public class MemberDao {
    
    DataSource ds;
    
    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public List<Member> selectList() throws Exception{
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select mno,mname,email,cre_date from members order by mno asc");
            ArrayList<Member> members = new ArrayList<Member>();

            while(rs.next()) {
                members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL")).setCreateDate(rs.getDate("CRE_DATE")));
            }
            return members;
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(rs != null) rs.close();} catch(Exception e) {}
            try { if(stmt != null) stmt.close();} catch (Exception e) {}
            try { if(connection != null) connection.close();} catch (Exception e) {}
        }
    }

    public int insert(Member member) throws Exception{
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            connection = ds.getConnection();
            stmt = connection.prepareStatement("insert into members(email,pwd,mname,cre_date,mod_date) values(?,?,?,now(),now())");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            int result = stmt.executeUpdate();
            return result;
        }  catch (Exception e) {
            throw e;
        } finally {
            try { if(stmt != null) stmt.close();} catch (Exception e) {}
            try { if(connection != null) connection.close();} catch (Exception e) {}
        }

    }

    public int delete(int no) throws Exception{
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ds.getConnection();
            stmt =connection.prepareStatement("delete from members where mno = ?");
            stmt.setInt(1, no);
            int result = stmt.executeUpdate();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            try{ if(stmt != null ) stmt.close();} catch (Exception e) {}
            try { if(connection != null) connection.close();} catch (Exception e) {}
        }
    }

    public Member selectOne(int no) throws Exception{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Member member = null;
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("select mno,email,mname,cre_date from members where mno = ?");
            stmt.setInt(1, no);
            rs = stmt.executeQuery();

            if (rs.next()) {
                member = new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")).setCreateDate(rs.getDate("cre_date"));
            }
            
            return member;

        } catch (Exception e) {
            throw e;
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close();} catch (Exception e) {}
        }

    }

    public int updateMember(Member member) throws Exception{
        
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("update members set email=?, mname=?,mod_date=now() where mno=?");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getName());
            stmt.setInt(3, member.getNo());
            int result = stmt.executeUpdate();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(stmt!=null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close();} catch (Exception e) {}        }
    }
    public Member exist(String email, String password) throws Exception{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Member member = null;
        try { 
            connection = ds.getConnection();
            stmt = connection.prepareStatement("select mname,email from members where email=? and pwd=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                member = new Member().setEmail(rs.getString("email")).setName(rs.getString("mname"));
            } 
            
            return member;
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(rs !=null)  rs.close();} catch (Exception e) {}
            try { if(connection != null) connection.close();} catch (Exception e) {}
        }
    }
}

