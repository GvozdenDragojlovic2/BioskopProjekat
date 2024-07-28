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


public class SOGetAllFilm extends AbstractSO {

    private ArrayList<Film> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Film)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Film!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> filmovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Film>) (ArrayList<?>) filmovi;
    }

    public ArrayList<Film> getLista() {
        return lista;
    }

}
