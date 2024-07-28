/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Film extends AbstractDomainObject {
    
    private Long filmID;
    private String naziv;
    private String zanr;
    private String opis;
    private String reziser;
    private ArrayList<Uloga> uloge;

    @Override
    public String toString() {
        return naziv + " (Zanr: " + zanr + ")";
    }

    public Film(Long filmID, String naziv, String zanr, String opis, String reziser, ArrayList<Uloga> uloge) {
        this.filmID = filmID;
        this.naziv = naziv;
        this.zanr = zanr;
        this.opis = opis;
        this.reziser = reziser;
        this.uloge = uloge;
    }

    public Film() {
    }
    
    @Override
    public String nazivTabele() {
        return " Film ";
    }

    @Override
    public String alijas() {
        return " f ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Film f = new Film(rs.getLong("FilmID"),
                    rs.getString("f.Naziv"), rs.getString("Zanr"),
                    rs.getString("Opis"), rs.getString("Reziser"), null);

            lista.add(f);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Zanr, Opis, Reziser) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + zanr + "', "
                + "'" + opis + "', '" + reziser + "'";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return " opis = '" + opis + "', naziv = '" + naziv + "', "
                + "zanr = '" + zanr + "' ";
    }
    
    @Override
    public String uslov() {
        return " FilmID = " + filmID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getFilmID() {
        return filmID;
    }

    public void setFilmID(Long filmID) {
        this.filmID = filmID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public ArrayList<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(ArrayList<Uloga> uloge) {
        this.uloge = uloge;
    }
    
}
