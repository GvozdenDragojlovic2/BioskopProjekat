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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;


public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void logout(Administrator ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addFilm(Film film) throws Exception {
        sendRequest(Operation.ADD_FILM, film);
    }

    public void addTermin(Termin termin) throws Exception {
        sendRequest(Operation.ADD_TERMIN, termin);
    }

    public void addKarta(Karta karta) throws Exception {
        sendRequest(Operation.ADD_KARTA, karta);
    }

    public void deleteFilm(Film film) throws Exception {
        sendRequest(Operation.DELETE_FILM, film);
    }

    public void deleteTermin(Termin termin) throws Exception {
        sendRequest(Operation.DELETE_TERMIN, termin);
    }

    public void deleteKarta(Karta karta) throws Exception {
        sendRequest(Operation.DELETE_KARTA, karta);
    }

    public void updateFilm(Film film) throws Exception {
        sendRequest(Operation.UPDATE_FILM, film);
    }

    public void updateTermin(Termin termin) throws Exception {
        sendRequest(Operation.UPDATE_TERMIN, termin);
    }

    public ArrayList<Film> getAllFilm() throws Exception {
        return (ArrayList<Film>) sendRequest(Operation.GET_ALL_FILM, null);
    }

    public ArrayList<Termin> getAllTermin() throws Exception {
        return (ArrayList<Termin>) sendRequest(Operation.GET_ALL_TERMIN, null);
    }

    public ArrayList<Glumac> getAllGlumac() throws Exception {
        return (ArrayList<Glumac>) sendRequest(Operation.GET_ALL_GLUMAC, null);
    }

    public ArrayList<Uloga> getAllUloga(Film f) throws Exception {
        return (ArrayList<Uloga>) sendRequest(Operation.GET_ALL_ULOGA, f);
    }

    public ArrayList<Gledalac> getAllGledalac() throws Exception {
        return (ArrayList<Gledalac>) sendRequest(Operation.GET_ALL_GLEDALAC, null);
    }

    public ArrayList<Karta> getAllKarta(Termin t) throws Exception {
        return (ArrayList<Karta>) sendRequest(Operation.GET_ALL_KARTA, t);
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        return (ArrayList<Sala>) sendRequest(Operation.GET_ALL_SALA, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
