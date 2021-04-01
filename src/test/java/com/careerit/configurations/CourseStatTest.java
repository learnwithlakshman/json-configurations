package com.careerit.configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.careerit.configurations.model.Student;

public class CourseStatTest {
	
	private static final ConnectionUtil util = new ConnectionUtil();
	
	@BeforeAll
	public static void init() throws IOException {
		final String fileName = "src/test/resources/coursedata.csv";
	   	List<Student> studentList = Files.readAllLines(Paths.get(fileName)).stream().skip(1)
				.map(line -> {
					String[] arr = line.split(",");
					int c = 0;
				    return Student.builder().name(arr[c++]).batch(arr[c++]).completed(arr[c++]).placed(arr[c++])
							.qualification(arr[c++]).score(Float.parseFloat(arr[c++])).build();
				
				}).collect(Collectors.toList());
			Connection con = null;
			PreparedStatement pst = null;
			try {
				con = util.getConnection();
				pst = con.prepareStatement("insert into student(name,batch,completed,placed,qualification,score) values(?,?,?,?,?,?)");
				for(Student student:studentList) {
					pst.setString(1, student.getName());
					pst.setString(2, student.getBatch());
					pst.setString(3, student.getCompleted());
					pst.setString(4, student.getPlaced());
					pst.setString(5, student.getQualification());
					pst.setFloat(6, student.getScore());
					pst.executeUpdate();
				}
			}catch (SQLException e) {
				System.out.println("While adding student :"+e);
			}finally {
				util.close(pst, con);
			}
		
	}
	
	@AfterAll
	public static void destory() {
		
	}
	
	@Test
	void doesNothing() {
		Stream.of("Tanvi","Haadya","Dhatri","Swathi","Anusha").skip(1).forEach(System.out::println);
	}
}
