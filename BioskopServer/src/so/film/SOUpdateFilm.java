/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.film;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Film;
import java.util.ArrayList;
import so.AbstractSO;


public class SOUpdateFilm extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Film)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Film!");
        }

        Film izmenjeniFilm = (Film) ado;

        ArrayList<Film> filmovi = (ArrayList<Film>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Film film : filmovi) {
            if (!film.getFilmID().equals(izmenjeniFilm.getFilmID())) {
                if (film.getNaziv().equals(izmenjeniFilm.getNaziv())) {
                    throw new Exception("Film sa tim nazivom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
