package com.learn.orm;


import com.learn.pojo.Student;
import com.learn.utils.ConnectionFactory;
import com.learn.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbc {
    @SuppressWarnings("resource")
    public void saveStudent(Student stu) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String selectSQL =
                    "select t_student_seq.nextval from dual";
            pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                stu.setId(rs.getLong(1));
            }
            String insertSQL =
                    "insert into t_student values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setLong(1, stu.getId());
            pstmt.setString(2, stu.getName());
            pstmt.setString(3, stu.getAddress());
            pstmt.setString(4, stu.getGender());
            pstmt.setInt(5, stu.getAge());
            int rows = pstmt.executeUpdate();
            System.out.println("�ɹ�����" + rows + "��ѧ��");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }
    }

    public void deleteStudent(Long id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql =
                    "delete from t_student where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println("�ɹ�ɾ��" + rows + "��ѧ��");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, pstmt, conn);
        }
    }

    public void updateStudent(Student stu) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql =
                    "update t_student " +
                            "set name = ?,address = ?,age = ? " +
                            "where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stu.getName());
            pstmt.setString(2, stu.getAddress());
            pstmt.setInt(3, stu.getAge());
            pstmt.setLong(4, stu.getId());
            int rows = pstmt.executeUpdate();
            System.out.println("�ɹ�����" + rows + "��ѧ��");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, pstmt, conn);
        }
    }

    public Student findStudent(Long id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student stu = new Student();

        try {
            conn = ConnectionFactory.getConnection();
            String sql =
                    "select id,name,address,gender,age " +
                            "from t_student " +
                            "where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                stu.setId(rs.getLong(1));
                stu.setName(rs.getString(2));
                stu.setAddress(rs.getString(3));
                stu.setGender(rs.getString(4));
                stu.setAge(rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }
        return stu;

    }

    public List<Student> findStudents() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<Student>();

        try {
            conn = ConnectionFactory.getConnection();
            String sql =
                    "select id,name,address,gender,age " +
                            "from t_student";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getLong(1));
                stu.setName(rs.getString(2));
                stu.setAddress(rs.getString(3));
                stu.setGender(rs.getString(4));
                stu.setAge(rs.getInt(5));
                list.add(stu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }
        return list;
    }

    //动态查询
    public List<Student> findStudents(List<String> conditions) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<Student>();

        try {
            conn = ConnectionFactory.getConnection();
            String sql =
                    "select id,name,address,gender,age " +
                            "from t_student " +
                            "where 1 = 1";
            for (String condition : conditions) {
                sql += " and ";
                sql += condition;
            }
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getLong(1));
                stu.setName(rs.getString(2));
                stu.setAddress(rs.getString(3));
                stu.setGender(rs.getString(4));
                stu.setAge(rs.getInt(5));
                list.add(stu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }
        return list;

    }

}


