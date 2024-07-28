/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sala;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Sala;
import java.util.ArrayList;
import so.AbstractSO;


public class SOGetAllSala extends AbstractSO {

    private ArrayList<Sala> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sala)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sala!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sale = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sala>) (ArrayList<?>) sale;
    }

    public ArrayList<Sala> getLista() {
        return lista;
    }

}
