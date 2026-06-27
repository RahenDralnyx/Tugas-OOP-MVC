/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Student;
import dao.StudentDAO;
import dao.StudentDAO;

/**
 *
 * @author rahen
 */
public class StudentController {
    private StudentDAO studentDAO = new StudentDAO();

public int create(Student student) {
    if (student.getName() == null || student.getName().trim().isEmpty()) return 0;
    if (student.getNim() == null || student.getNim().trim().isEmpty()) return 0;

    // cek NIM duplikat sebelum simpan
    for (Student s : studentDAO.getStudent()) {
        if (s.getNim().equalsIgnoreCase(student.getNim())) {
            return 0; // NIM sudah terdaftar
        }
    }
    return studentDAO.create(student);
}

    public List<Student> getStudent() {
        return studentDAO.getStudent();
    }
    public int update(Student student, String nim) {
        if (student.getName() == null || student.getName().trim().isEmpty()) return 0;
            return studentDAO.update(student, nim);
}

    public int delete(String nim) {
        return studentDAO.delete(nim);
    }
    public List<Student> getStudentPage(int offset, int limit) { return studentDAO.getStudentPage(offset, limit); }
    public int getTotalCount() { return studentDAO.getTotalCount(); } 
    public List<Student> searchByNim(String keyword, int offset, int limit) { return studentDAO.searchByNim(keyword, offset, limit); }
    public int getSearchCount(String keyword) { return studentDAO.getSearchCount(keyword); }
}
