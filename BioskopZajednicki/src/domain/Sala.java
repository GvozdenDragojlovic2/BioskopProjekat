/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Sala extends AbstractDomainObject {

    private Long salaID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Sala(Long salaID, String naziv) {
        this.salaID = salaID;
        this.naziv = naziv;
    }

    public Sala() {
    }

    @Override
    public String nazivTabele() {
        return " Sala ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sala s = new Sala(rs.getLong("SalaID"),
                    rs.getString("s.Naziv"));

            lista.add(s);
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

    public Long getSalaID() {
        return salaID;
    }

    public void setSalaID(Long salaID) {
        this.salaID = salaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
