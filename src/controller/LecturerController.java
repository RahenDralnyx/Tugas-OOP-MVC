/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rahen
 */

package controller;

import java.util.List;
import model.Lecturer;
import dao.LecturerDAO;

public class LecturerController {
    private LecturerDAO lecturerDAO = new LecturerDAO();

    public int create(Lecturer lecturer) {
        if (lecturer.getName() == null || lecturer.getName().trim().isEmpty()) return 0;
        if (lecturer.getNidn() == null || !lecturer.getNidn().matches("\\d+")) return 0; // NIDN wajib angka
        return lecturerDAO.create(lecturer);
}
    public List<Lecturer> getLecturer() { return lecturerDAO.getLecturer(); }
    
    public int update(Lecturer lecturer) {
    if (lecturer.getNidn() == null || !lecturer.getNidn().matches("\\d+")) return 0;
    return lecturerDAO.update(lecturer);
}
    
    public int delete(int id) { return lecturerDAO.delete(id); }
    public List<Lecturer> getLecturerPage(int offset, int limit) { return lecturerDAO.getLecturerPage(offset, limit); }
    public int getTotalCount() { return lecturerDAO.getTotalCount(); }
    public List<Lecturer> searchByNidn(String keyword, int offset, int limit) { return lecturerDAO.searchByNidn(keyword, offset, limit); }
    public int getSearchCount(String keyword) { return lecturerDAO.getSearchCount(keyword); }
}