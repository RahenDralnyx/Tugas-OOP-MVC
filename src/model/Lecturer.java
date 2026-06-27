/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Person;

public class Lecturer extends Person {

    private int id;
    private String nidn;
    private String expertise;

    public Lecturer(String idCard, String name, String nidn, String expertise) {
        super(idCard, name);
        this.nidn = nidn;
        this.expertise = expertise;
    }

    public Lecturer(int id, String idCard, String name, String nidn, String expertise) {
        super(idCard, name);
        this.id = id;
        this.nidn = nidn;
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return this.name;
    }
    public String getRole() {
    return "Dosen";
    }

    public int getId() { return id; }
    public String getIdCard() { return idCard; }
    public String getName() { return name; }
    public String getNidn() { return nidn; }
    public String getExpertise() { return expertise; }
}