/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.film;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Film;
import so.AbstractSO;


public class SODeleteFilm extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Film)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Film!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
