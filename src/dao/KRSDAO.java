/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author rahen
 */

import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KRSDAO {
    private Connection connection;

    public KRSDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(KRSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int create(String nim, int courseId, int lecturerId, int semester,
                       int sikap, int uts, int uas, double score, String grade) {
        try {
            String sql = "INSERT INTO krs (student_nim, course_id, lecturer_id, semester, nilai_sikap, nilai_uts, nilai_uas, score, grade) " +
                         "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nim);
            stmt.setInt(2, courseId);
            stmt.setInt(3, lecturerId);
            stmt.setInt(4, semester);
            stmt.setInt(5, sikap);
            stmt.setInt(6, uts);
            stmt.setInt(7, uas);
            stmt.setDouble(8, score);
            stmt.setString(9, grade);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(int id, String nim, int courseId, int lecturerId, int semester,
                       int sikap, int uts, int uas, double score, String grade) {
        try {
            String sql = "UPDATE krs SET student_nim=?, course_id=?, lecturer_id=?, semester=?, nilai_sikap=?, nilai_uts=?, nilai_uas=?, score=?, grade=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nim);
            stmt.setInt(2, courseId);
            stmt.setInt(3, lecturerId);
            stmt.setInt(4, semester);
            stmt.setInt(5, sikap);
            stmt.setInt(6, uts);
            stmt.setInt(7, uas);
            stmt.setDouble(8, score);
            stmt.setString(9, grade);
            stmt.setInt(10, id);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(int id) {
        try {
            String sql = "DELETE FROM krs WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Object[]> getAllKRS() {
        List<Object[]> rows = new ArrayList<>();
        try {
            String sql = "SELECT k.id, k.student_nim, s.name AS student_name, c.name AS course_name, " +
                         "c.sks, k.semester, k.grade, l.name AS lecturer_name " +
                         "FROM krs k " +
                         "JOIN students s ON k.student_nim = s.NIM " +
                         "JOIN courses c ON k.course_id = c.id " +
                         "JOIN lecturers l ON k.lecturer_id = l.id";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getInt("id"), rs.getString("student_nim"), rs.getString("student_name"),
                    rs.getString("course_name"), rs.getInt("sks"), rs.getInt("semester"),
                    rs.getString("grade"), rs.getString("lecturer_name")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
    public List<Object[]> getKRSPage(int offset, int limit) {
    List<Object[]> rows = new ArrayList<>();
    try {
        String sql = "SELECT k.id, k.student_nim, s.name AS student_name, c.name AS course_name, " +
                     "c.sks, k.semester, k.grade, l.name AS lecturer_name " +
                     "FROM krs k " +
                     "JOIN students s ON k.student_nim = s.NIM " +
                     "JOIN courses c ON k.course_id = c.id " +
                     "JOIN lecturers l ON k.lecturer_id = l.id " +
                     "LIMIT ? OFFSET ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, limit);
        stmt.setInt(2, offset);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            rows.add(new Object[]{
                rs.getInt("id"), rs.getString("student_nim"), rs.getString("student_name"),
                rs.getString("course_name"), rs.getInt("sks"), rs.getInt("semester"),
                rs.getString("grade"), rs.getString("lecturer_name")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rows;
}

public int getTotalCount() {
    int total = 0;
    try {
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM krs");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
public List<Object[]> searchByNim(String keyword, int offset, int limit) {
    List<Object[]> rows = new ArrayList<>();
    try {
        String sql = "SELECT k.id, k.student_nim, s.name AS student_name, c.name AS course_name, " +
                     "c.sks, k.semester, k.grade, l.name AS lecturer_name " +
                     "FROM krs k " +
                     "JOIN students s ON k.student_nim = s.NIM " +
                     "JOIN courses c ON k.course_id = c.id " +
                     "JOIN lecturers l ON k.lecturer_id = l.id " +
                     "WHERE k.student_nim LIKE ? LIMIT ? OFFSET ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setInt(2, limit);
        stmt.setInt(3, offset);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            rows.add(new Object[]{
                rs.getInt("id"), rs.getString("student_nim"), rs.getString("student_name"),
                rs.getString("course_name"), rs.getInt("sks"), rs.getInt("semester"),
                rs.getString("grade"), rs.getString("lecturer_name")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rows;
}

public int getSearchCount(String keyword) {
    int total = 0;
    try {
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM krs WHERE student_nim LIKE ?");
        stmt.setString(1, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
}