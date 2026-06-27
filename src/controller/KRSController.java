/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author rahen
 */

import java.util.List;
import dao.KRSDAO;

public class KRSController {
    private KRSDAO krsDAO = new KRSDAO();

public int create(String nim, int courseId, int lecturerId, int semester,
                   int sikap, int uts, int uas, double score, String grade) {
    if (sikap < 0 || sikap > 100 || uts < 0 || uts > 100 || uas < 0 || uas > 100) {
        return 0; // nilai di luar rentang 0-100, tolak
    }
    return krsDAO.create(nim, courseId, lecturerId, semester, sikap, uts, uas, score, grade);
}


public int update(int id, String nim, int courseId, int lecturerId, int semester,
                   int sikap, int uts, int uas, double score, String grade) {
    if (sikap < 0 || sikap > 100 || uts < 0 || uts > 100 || uas < 0 || uas > 100) {
        return 0;
    }
    return krsDAO.update(id, nim, courseId, lecturerId, semester, sikap, uts, uas, score, grade);
}

    public int delete(int id) { return krsDAO.delete(id); }
    public List<Object[]> getAllKRS() { return krsDAO.getAllKRS(); }
    public List<Object[]> getKRSPage(int offset, int limit) { return krsDAO.getKRSPage(offset, limit); }
    public int getTotalCount() { return krsDAO.getTotalCount(); }
    public List<Object[]> searchByNim(String keyword, int offset, int limit) { return krsDAO.searchByNim(keyword, offset, limit); }
    public int getSearchCount(String keyword) { return krsDAO.getSearchCount(keyword); }
}
