/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Gledalac extends AbstractDomainObject {
    
    private Long gledalacID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Email: " + email + ")";
    }

    public Gledalac(Long gledalacID, String ime, String prezime, String email, String telefon) {
        this.gledalacID = gledalacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }
    
    @Override
    public String nazivTabele() {
        return " Gledalac ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Gledalac g = new Gledalac(rs.getLong("GledalacID"),
                    rs.getString("g.Ime"), rs.getString("g.Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"));

            lista.add(g);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    
    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Gledalac() {
    }

    public Long getGledalacID() {
        return gledalacID;
    }

    public void setGledalacID(Long gledalacID) {
        this.gledalacID = gledalacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
}
