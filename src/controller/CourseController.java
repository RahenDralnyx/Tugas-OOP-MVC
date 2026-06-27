package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rahen
 */

import java.util.List;
import model.Course;
import dao.CourseDAO;

public class CourseController {
    private CourseDAO courseDAO = new CourseDAO();

    public int create(Course course) {
    if (course.getSKS() < 1 || course.getSKS() > 6) return 0;        // aturan akademik: SKS 1-6
    if (course.getSemester() < 1 || course.getSemester() > 10) return 0;
    return courseDAO.create(course);
}

    public List<Course> getCourse() { return courseDAO.getCourse(); }
    
public int update(Course course) {
    if (course.getSKS() < 1 || course.getSKS() > 6) return 0;
    if (course.getSemester() < 1 || course.getSemester() > 10) return 0;
    return courseDAO.update(course);
}    
    public int delete(int id) { return courseDAO.delete(id); }
    public List<Course> getCoursePage(int offset, int limit) { return courseDAO.getCoursePage(offset, limit); }
    public int getTotalCount() { return courseDAO.getTotalCount(); }
    public List<Course> searchByCode(String keyword, int offset, int limit) { return courseDAO.searchByCode(keyword, offset, limit); }
    public int getSearchCount(String keyword) { return courseDAO.getSearchCount(keyword); }
}