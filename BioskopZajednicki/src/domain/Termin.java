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


public class Termin extends AbstractDomainObject {

    private Long terminID;
    private Date datumVreme;
    private double cenaKarte;
    private String projekcija;
    private Film film;
    private Sala sala;
    private Administrator administrator;
    private ArrayList<Karta> karte;

    public Termin(Long terminID, Date datumVreme, double cenaKarte, String projekcija, Film film, Sala sala, Administrator administrator, ArrayList<Karta> karte) {
        this.terminID = terminID;
        this.datumVreme = datumVreme;
        this.cenaKarte = cenaKarte;
        this.projekcija = projekcija;
        this.film = film;
        this.sala = sala;
        this.administrator = administrator;
        this.karte = karte;
    }

    public Termin() {
    }

    @Override
    public String nazivTabele() {
        return " Termin ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN FILM F ON (F.FILMID = T.FILMID) "
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, cenaKarte, projekcija, filmID, salaID, administratorID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Timestamp(datumVreme.getTime()) + "', " + cenaKarte + ", "
                + "'" + projekcija + "', " + film.getFilmID() + ", "
                + sala.getSalaID() + ", "
                + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVreme = '" + new java.sql.Timestamp(datumVreme.getTime()) + "', "
                + "cenaKarte = " + cenaKarte + ", "
                + "salaID = " + sala.getSalaID() + " ";
    }

    @Override
    public String uslov() {
        return " terminID = " + terminID;
    }

    @Override
    public String uslovZaSelect() {
//        if(film == null){
            return "";
//        }
//        return " WHERE F.FILMID = " + film.getFilmID();
    }

    public Long getTerminID() {
        return terminID;
    }

    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getCenaKarte() {
        return cenaKarte;
    }

    public void setCenaKarte(double cenaKarte) {
        this.cenaKarte = cenaKarte;
    }

    public String getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(String projekcija) {
        this.projekcija = projekcija;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Karta> getKarte() {
        return karte;
    }

    public void setKarte(ArrayList<Karta> karte) {
        this.karte = karte;
    }

}
