/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Uloga extends AbstractDomainObject {

    private Film film;
    private int rb;
    private String naziv;
    private String opis;
    private Glumac glumac;

    public Uloga(Film film, int rb, String naziv, String opis, Glumac glumac) {
        this.film = film;
        this.rb = rb;
        this.naziv = naziv;
        this.opis = opis;
        this.glumac = glumac;
    }

    public Uloga() {
    }

    @Override
    public String nazivTabele() {
        return " Uloga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return " JOIN GLUMAC GL ON (GL.GLUMACID = U.GLUMACID) "
                + "JOIN FILM F ON (F.FILMID = U.FILMID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Glumac gl = new Glumac(rs.getLong("GlumacID"),
                    rs.getString("gl.Ime"), rs.getString("gl.Prezime"),
                    rs.getDate("DatumRodjenja"));

            Film f = new Film(rs.getLong("FilmID"),
                    rs.getString("Naziv"), rs.getString("Zanr"),
                    rs.getString("Opis"), rs.getString("Reziser"), null);

            Uloga u = new Uloga(f, rs.getInt("rb"), rs.getString("naziv"),
                    rs.getString("opis"), gl);

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (FilmID, rb, naziv, opis, glumacID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + film.getFilmID() + ", " + rb + ", "
                + "'" + naziv + "', '" + opis + "', " + glumac.getGlumacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " FilmID = " + film.getFilmID();
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE F.FILMID = " + film.getFilmID();
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

}
