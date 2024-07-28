/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Karta extends AbstractDomainObject {

    private Long kartaID;
    private Termin termin;
    private Gledalac gledalac;
    private String sediste;

    public Karta(Long kartaID, Termin termin, Gledalac gledalac, String sediste) {
        this.kartaID = kartaID;
        this.termin = termin;
        this.gledalac = gledalac;
        this.sediste = sediste;
    }

    public Karta() {
    }

    @Override
    public String nazivTabele() {
        return " Karta ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "JOIN GLEDALAC G ON (G.GLEDALACID = K.GLEDALACID) "
                + "JOIN TERMIN T ON (T.TERMINID = K.TERMINID) "
                + "JOIN FILM F ON (F.FILMID = T.FILMID) "
                + "JOIN SALA S ON (S.SALAID = T.SALAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = T.ADMINISTRATORID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Sala s = new Sala(rs.getLong("SalaID"),
                    rs.getString("s.Naziv"));

            Film f = new Film(rs.getLong("FilmID"),
                    rs.getString("f.Naziv"), rs.getString("Zanr"),
                    rs.getString("Opis"), rs.getString("Reziser"), null);

            Termin t = new Termin(rs.getLong("terminID"), rs.getTimestamp("datumVreme"),
                    rs.getDouble("cenaKarte"), rs.getString("projekcija"),
                    f, s, a, null);
            
            Gledalac g = new Gledalac(rs.getLong("GledalacID"),
                    rs.getString("g.Ime"), rs.getString("g.Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"));
            
            Karta k = new Karta(rs.getLong("kartaID"), t, g, rs.getString("sediste"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (terminID, GledalacID, sediste) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + termin.getTerminID() + ", " + gledalac.getGledalacID() + ", "
                + "'" + sediste + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " kartaID = " + kartaID;
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }

    public Long getKartaID() {
        return kartaID;
    }

    public void setKartaID(Long kartaID) {
        this.kartaID = kartaID;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public Gledalac getGledalac() {
        return gledalac;
    }

    public void setGledalac(Gledalac gledalac) {
        this.gledalac = gledalac;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

}
