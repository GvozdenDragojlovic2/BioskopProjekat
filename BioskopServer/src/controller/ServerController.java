/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Film;
import domain.Gledalac;
import domain.Glumac;
import domain.Karta;
import domain.Sala;
import domain.Termin;
import domain.Uloga;
import java.util.ArrayList;
import so.film.SOAddFilm;
import so.film.SODeleteFilm;
import so.film.SOGetAllFilm;
import so.film.SOUpdateFilm;
import so.gledalac.SOGetAllGledalac;
import so.glumac.SOGetAllGlumac;
import so.karta.SOAddKarta;
import so.karta.SODeleteKarta;
import so.karta.SOGetAllKarta;
import so.login.SOLogin;
import so.sala.SOGetAllSala;
import so.termin.SOAddTermin;
import so.termin.SODeleteTermin;
import so.termin.SOGetAllTermin;
import so.termin.SOUpdateTermin;
import so.uloga.SOGetAllUloga;


public class ServerController {

    private static ServerController instance;
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }

    public void setUlogovaniAdministratori(ArrayList<Administrator> ulogovaniAdministratori) {
        this.ulogovaniAdministratori = ulogovaniAdministratori;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addFilm(Film film) throws Exception {
        (new SOAddFilm()).templateExecute(film);
    }

    public void addTermin(Termin termin) throws Exception {
        (new SOAddTermin()).templateExecute(termin);
    }

    public void addKarta(Karta karta) throws Exception {
        (new SOAddKarta()).templateExecute(karta);
    }

    public void deleteFilm(Film film) throws Exception {
        (new SODeleteFilm()).templateExecute(film);
    }

    public void deleteTermin(Termin termin) throws Exception {
        (new SODeleteTermin()).templateExecute(termin);
    }

    public void deleteKarta(Karta karta) throws Exception {
        (new SODeleteKarta()).templateExecute(karta);
    }

    public void updateFilm(Film film) throws Exception {
        (new SOUpdateFilm()).templateExecute(film);
    }

    public void updateTermin(Termin termin) throws Exception {
        (new SOUpdateTermin()).templateExecute(termin);
    }

    public ArrayList<Film> getAllFilm() throws Exception {
        SOGetAllFilm so = new SOGetAllFilm();
        so.templateExecute(new Film());
        return so.getLista();
    }

    public ArrayList<Glumac> getAllGlumac() throws Exception {
        SOGetAllGlumac so = new SOGetAllGlumac();
        so.templateExecute(new Glumac());
        return so.getLista();
    }

    public ArrayList<Termin> getAllTermin() throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();
        so.templateExecute(new Termin());
        return so.getLista();
    }

    public ArrayList<Uloga> getAllUloga(Film film) throws Exception {
        SOGetAllUloga so = new SOGetAllUloga();

        // ovo je jako bitno!
        // setovali smo da nasa uloga ima odredjeni film
        // i time vratili sve uloge samo tog filma!
        // idi u DBBrokera i udji u select, pa za
        // uslovaZaSelect() je dodao 
        // WHERE F.FILMID = nasID
        Uloga uloga = new Uloga();
        uloga.setFilm(film);

        so.templateExecute(uloga);
        return so.getLista();
    }

    public ArrayList<Karta> getAllKarta(Termin termin) throws Exception {
        SOGetAllKarta so = new SOGetAllKarta();

        Karta karta = new Karta();
        karta.setTermin(termin);

        so.templateExecute(karta);
        return so.getLista();
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        SOGetAllSala so = new SOGetAllSala();
        so.templateExecute(new Sala());
        return so.getLista();
    }

    public ArrayList<Gledalac> getAllGledalac() throws Exception {
        SOGetAllGledalac so = new SOGetAllGledalac();
        so.templateExecute(new Gledalac());
        return so.getLista();
    }

    public void logout(Administrator ulogovani) {
        ulogovaniAdministratori.remove(ulogovani);
    }

}
