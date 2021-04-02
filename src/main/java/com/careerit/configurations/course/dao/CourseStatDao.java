package com.careerit.configurations.course.dao;

import com.careerit.configurations.model.Student;

import java.util.List;

public interface CourseStatDao {

        int insertStudent(List<Student> list);
        List<Student> selectStudentByBatch(String batchName);
        List<Student> selectByPlacementStatus(String placedStatus);
        int selectStudentCountByTrainer(String trainerName);
        int selectCountByBatch(String batchName);

        int removeAll();
}

