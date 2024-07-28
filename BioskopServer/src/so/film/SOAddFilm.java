/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.film;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Film;
import domain.Uloga;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;


public class SOAddFilm extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Film)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Film!");
        }

        Film noviFilm = (Film) ado;

        if (noviFilm.getUloge().isEmpty()) {
            throw new Exception("Film mora imati barem jednu ulogu!");
        }

        ArrayList<Film> filmovi = (ArrayList<Film>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Film film : filmovi) {
            if (film.getNaziv().equals(noviFilm.getNaziv())) {
                throw new Exception("Film sa tim nazivom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        // vracamo ps sa generisanim kljucem
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        // uzimamo taj kljuc
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long filmID = tableKeys.getLong(1);

        // setujemo ga za nas film
        Film f = (Film) ado;
        f.setFilmID(filmID);

        // dodajemo redom ulogu po ulogu nakon
        // sto setujemo da potice iz naseg filma
        for (Uloga uloga : f.getUloge()) {
            uloga.setFilm(f);
            DBBroker.getInstance().insert(uloga);
        }

    }

}
