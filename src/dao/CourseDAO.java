package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rahen
 */

import model.Course;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO {
    private Connection connection;

    public CourseDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int create(Course course) {
        try {
            String sql = "INSERT INTO courses (code, name, sks, semester) VALUES (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, course.getCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getSKS());
            stmt.setInt(4, course.getSemester());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Course> getCourse() {
        List<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM courses";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                    rs.getInt("id"), rs.getString("code"), rs.getString("name"),
                    rs.getInt("sks"), rs.getInt("semester")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public int update(Course course) {
        try {
            String sql = "UPDATE courses SET code=?, name=?, sks=?, semester=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, course.getCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getSKS());
            stmt.setInt(4, course.getSemester());
            stmt.setInt(5, course.getId());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(int id) {
        try {
            String sql = "DELETE FROM courses WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public List<Course> getCoursePage(int offset, int limit) {
    List<Course> courses = new ArrayList<>();
    try {
        String sql = "SELECT * FROM courses LIMIT ? OFFSET ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, limit);
        stmt.setInt(2, offset);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            courses.add(new Course(
                rs.getInt("id"), rs.getString("code"), rs.getString("name"),
                rs.getInt("sks"), rs.getInt("semester")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courses;
}

public int getTotalCount() {
    int total = 0;
    try {
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM courses");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
public List<Course> searchByCode(String keyword, int offset, int limit) {
    List<Course> courses = new ArrayList<>();
    try {
        String sql = "SELECT * FROM courses WHERE code LIKE ? LIMIT ? OFFSET ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setInt(2, limit);
        stmt.setInt(3, offset);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            courses.add(new Course(
                rs.getInt("id"), rs.getString("code"), rs.getString("name"),
                rs.getInt("sks"), rs.getInt("semester")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courses;
}

public int getSearchCount(String keyword) {
    int total = 0;
    try {
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM courses WHERE code LIKE ?");
        stmt.setString(1, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
}