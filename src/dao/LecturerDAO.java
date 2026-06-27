
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rahen
 */


package dao;

import model.Lecturer;
import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturerDAO {
    private Connection connection;

    public LecturerDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int create(Lecturer lecturer) {
        try {
            String sql = "INSERT INTO lecturers (id_card, name, nidn, expertise) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, lecturer.getIdCard());
            stmt.setString(2, lecturer.getName());
            stmt.setString(3, lecturer.getNidn());
            stmt.setString(4, lecturer.getExpertise());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Lecturer> getLecturer() {
        List<Lecturer> lecturers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lecturers";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lecturers.add(new Lecturer(
                    rs.getInt("id"),
                    rs.getString("id_card"),
                    rs.getString("name"),
                    rs.getString("nidn"),
                    rs.getString("expertise")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }

    public int update(Lecturer lecturer) {
        try {
            String sql = "UPDATE lecturers SET id_card=?, name=?, nidn=?, expertise=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, lecturer.getIdCard());
            stmt.setString(2, lecturer.getName());
            stmt.setString(3, lecturer.getNidn());
            stmt.setString(4, lecturer.getExpertise());
            stmt.setInt(5, lecturer.getId());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(int id) {
        try {
            String sql = "DELETE FROM lecturers WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Lecturer> getLecturerPage(int offset, int limit) {
        List<Lecturer> lecturers = new ArrayList<>();
    try {
        String sql = "SELECT * FROM lecturers LIMIT ? OFFSET ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, limit);
        stmt.setInt(2, offset);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            lecturers.add(new Lecturer(
                rs.getInt("id"), rs.getString("id_card"), rs.getString("name"),
                rs.getString("nidn"), rs.getString("expertise")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lecturers;
}

    public int getTotalCount() {
      int total = 0;
        try {
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM lecturers");
        ResultSet rs = stmt.executeQuery();
          if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
    public List<Lecturer> searchByNidn(String keyword, int offset, int limit) {
    List<Lecturer> lecturers = new ArrayList<>();
    try {
        String sql = "SELECT * FROM lecturers WHERE nidn LIKE ? LIMIT ? OFFSET ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setInt(2, limit);
        stmt.setInt(3, offset);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            lecturers.add(new Lecturer(
                rs.getInt("id"), rs.getString("id_card"), rs.getString("name"),
                rs.getString("nidn"), rs.getString("expertise")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lecturers;
}

public int getSearchCount(String keyword) {
    int total = 0;
    try {
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM lecturers WHERE nidn LIKE ?");
        stmt.setString(1, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
}