package com.careerit.configurations.report;

import com.careerit.configurations.course.dao.CourseDaoImpl;
import com.careerit.configurations.course.dao.CourseStatDao;
import com.careerit.configurations.model.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReportService {

        private CourseStatDao courseStatDao;

        public ReportService(){
                courseStatDao = new CourseDaoImpl();
        }

        public void downloadExcelReport(String batch)throws IOException {
               List<Student> list= courseStatDao.selectStudentByBatch(batch);
               final String WORKBOOK_NAME = "student_batch_details.xlsx";

                if (!Files.exists(Paths.get(WORKBOOK_NAME))) {

                        try (FileOutputStream fos = new FileOutputStream(new File("emp_workbook.xlsx"))) {
                                Workbook workbook = new XSSFWorkbook();
                                Sheet sheet = workbook.createSheet("student_details");

                                int rowcount = 0;
                                Row row = sheet.createRow(rowcount++);

                                Object[] colHeading = getColumnHeadings();
                                int i = 0;
                                for (Object obj : colHeading) {
                                        Cell cell = row.createCell(i++);
                                        cell.setCellValue((String) obj);
                                }

                                for (Student student:list) {
                                        i = 0;
                                        row = sheet.createRow(rowcount++);
                                        Cell cell = row.createCell(i++);
                                        cell.setCellValue((String) student.getName());
                                        cell = row.createCell(i++);
                                        cell.setCellValue((String) student.getBatch());
                                        cell = row.createCell(i++);
                                        cell.setCellValue((String) student.getCompleted());
                                        cell = row.createCell(i++);
                                        cell.setCellValue((String) student.getPlaced());
                                        cell = row.createCell(i++);
                                        cell.setCellValue((String) student.getQualification());
                                        cell = row.createCell(i++);
                                        cell.setCellValue(student.getScore());
                                }
                                workbook.write(fos);
                                System.out.println("Work book is created successfully with name :" + WORKBOOK_NAME);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                } else {
                        System.out.println("File already exists with the name :" + WORKBOOK_NAME);
                }
        }

        private static Object[] getColumnHeadings() {
                return new Object[] { "NAME", "BATCH", "COMPLETED", "PLACED","QUALIFICATION","SCORE" };
        }


}

