/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.uloga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Uloga;
import java.util.ArrayList;
import so.AbstractSO;


public class SOGetAllUloga extends AbstractSO {

    private ArrayList<Uloga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Uloga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Uloga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> uloge = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Uloga>) (ArrayList<?>) uloge;
    }

    public ArrayList<Uloga> getLista() {
        return lista;
    }

}
