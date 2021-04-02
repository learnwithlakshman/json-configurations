package com.careerit.configurations.course.dao;

import com.careerit.configurations.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseStatDao{

    private static final String ADD_STUDENT = "insert into student(name,batch,completed,placed,qualification,score) values(?,?,?,?,?,?)";
    private static final String DELETE_STUDENTS = "delete from student";
    private static final String SELECT_STUDENT_BY_BATCH = "select name,batch,completed,placed,qualification,score from student where batch= ?";
    private static final String SELECT_STUDENT_COUNT_BY_BATCH = "select count(*) as count from student where batch = ?";
    private static final String SELECT_STUDENT_BY_PLACEMENT_STATUS = "select name,batch,completed,placed,qualification,score from student where placed=?";
    private static final String COUNT_STUDENT_BY_TRAINER = "CALL count_of_students(?,?)";
    private ConnectionUtil conUtil = ConnectionUtil.obj;

    @Override
    public int insertStudent(List<Student> list) {

        Connection con = null;
        PreparedStatement pst = null;
        int count = 0;
        try {
            con = conUtil.getConnection();
            pst = con.prepareStatement(ADD_STUDENT);
            for(Student student:list) {
                pst.setString(1, student.getName());
                pst.setString(2, student.getBatch());
                pst.setString(3, student.getCompleted());
                pst.setString(4, student.getPlaced());
                pst.setString(5, student.getQualification());
                pst.setFloat(6, student.getScore());
                pst.executeUpdate();
                count++;
            }
        }catch (SQLException e) {
            System.out.println("While adding student :"+e);
        }finally {
            conUtil.close(pst, con);
        }
        return count;
    }

    @Override
    public List<Student> selectStudentByBatch(String batchName) {
        List<Student> studentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = conUtil.getConnection();
            pst = con.prepareStatement(SELECT_STUDENT_BY_BATCH);
            pst.setString(1,batchName);
            rs = pst.executeQuery();
            while(rs.next()){
                Student student = rowToStudent(rs);
                studentList.add(student);
            }
        }catch (SQLException e){
            System.out.println("While selecting students :"+e);
        }finally {
            conUtil.close(pst, con);
        }
        return studentList;
    }
    @Override
    public List<Student> selectByPlacementStatus(String placedStatus) {
        List<Student> studentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = conUtil.getConnection();
            pst = con.prepareStatement(SELECT_STUDENT_BY_PLACEMENT_STATUS);
            pst.setString(1,placedStatus);
            rs = pst.executeQuery();
            while(rs.next()){
                Student student = rowToStudent(rs);
                studentList.add(student);
            }
        }catch (SQLException e){
            System.out.println("While selecting students :"+e);
        }finally {
            conUtil.close(rs,pst, con);
        }
        return studentList;
    }

    @Override
    public int selectStudentCountByTrainer(String trainerName) {
        Connection con = null;
        CallableStatement cst = null;
        int count = 0;
        try{
            con = conUtil.getConnection();
            cst = con.prepareCall(COUNT_STUDENT_BY_TRAINER);
            cst.setString(1,trainerName);
            cst.registerOutParameter(2,Types.INTEGER);
            cst.execute();
            count = cst.getInt(2);
        }catch (SQLException e){
            System.out.println("While getting student count :"+e);
        }
        return count;
    }

    @Override
    public int selectCountByBatch(String batchName) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = conUtil.getConnection();
            pst = con.prepareStatement(SELECT_STUDENT_COUNT_BY_BATCH);
            pst.setString(1,batchName);
            rs = pst.executeQuery();
            if(rs.next()){
                return rs.getInt("count");
            }
        }catch (SQLException e){
            System.out.println("While selecting students :"+e);
        }finally {
            conUtil.close(rs,pst, con);
        }
        return 0;
    }

    @Override
    public int removeAll() {
        Connection con = null;
        Statement pst = null;
        int count = 0;
        try{
            con = conUtil.getConnection();
            pst = con.createStatement();
            count = pst.executeUpdate(DELETE_STUDENTS);

        }catch (SQLException e){
            System.out.println("While deleting students :"+e);
        }finally {
            conUtil.close(pst, con);
        }
        return count;
    }

    private Student rowToStudent(ResultSet rs) throws  SQLException {
        String name = rs.getString("name");
        String batch = rs.getString("batch");
        String placed = rs.getString("placed");
        String completed = rs.getString("completed");
        String qualification = rs.getString("qualification");
        float score = rs.getFloat("score");
        return Student.builder().name(name).batch(batch).placed(placed).completed(completed).score(score).
                qualification(qualification).build();
    }
}
