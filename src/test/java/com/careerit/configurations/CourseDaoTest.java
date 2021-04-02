package com.careerit.configurations;

import com.careerit.configurations.course.dao.CourseDaoImpl;
import com.careerit.configurations.course.dao.CourseStatDao;
import com.careerit.configurations.model.Student;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDaoTest {
    
      private static List<Student> studentList = new ArrayList<>();
      private CourseStatDao courseStatDao;

      @BeforeAll
      static void initAll() throws IOException {
            final String fileName = "src/test/resources/coursedata.csv";
            studentList = Files.readAllLines(Paths.get(fileName)).stream().skip(1)
                    .map(line -> {
                          String[] arr = line.split(",");
                          int c = 0;
                          return Student.builder().name(arr[c++]).batch(arr[c++]).completed(arr[c++]).placed(arr[c++])
                                  .qualification(arr[c++]).score(Float.parseFloat(arr[c++])).build();
                    }).collect(Collectors.toList());
      }


      @BeforeEach
      void init() {

          courseStatDao = new CourseDaoImpl();
          courseStatDao.removeAll();
          courseStatDao.insertStudent(studentList);
      }
      @AfterEach
      void destory(){
          courseStatDao.removeAll();
      }

      @Test
      @DisplayName("Select student by batch name")
      void selectStudentByBatchTest(){
          List<Student> list = courseStatDao.selectStudentByBatch("BCJ1");
          assertEquals(2, list.size());
      }
      @Test
      void selectByPlacementStatusTest(){
          List<Student> list = courseStatDao.selectByPlacementStatus("Y");
          assertEquals(12, list.size());
      }

      @Test
      void selectCountByBatchTest(){
          int actual = courseStatDao.selectCountByBatch("BCJ1");
          int expected = 2;
          assertEquals(expected, actual);

      }
      @Test
      void selectStudentCountByTrainerTest(){
          int actual = courseStatDao.selectStudentCountByTrainer("BCJ1");
          int expected = 0;
          assertEquals(expected, actual);
      }


}
