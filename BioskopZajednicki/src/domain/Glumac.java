/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Glumac extends AbstractDomainObject {
    
    private Long glumacID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Glumac(Long glumacID, String ime, String prezime, Date datumRodjenja) {
        this.glumacID = glumacID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
    }

    public Glumac() {
    }
    
    @Override
    public String nazivTabele() {
        return " Glumac ";
    }

    @Override
    public String alijas() {
        return " gl ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Glumac gl = new Glumac(rs.getLong("GlumacID"),
                    rs.getString("gl.Ime"), rs.getString("gl.Prezime"),
                    rs.getDate("DatumRodjenja"));

            lista.add(gl);
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

    public Long getGlumacID() {
        return glumacID;
    }

    public void setGlumacID(Long glumacID) {
        this.glumacID = glumacID;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
    
}
