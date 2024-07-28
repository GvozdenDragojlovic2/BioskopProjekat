/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.gledalac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Gledalac;
import java.util.ArrayList;
import so.AbstractSO;


public class SOGetAllGledalac extends AbstractSO {

    private ArrayList<Gledalac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Gledalac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Gledalac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> gledaoci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Gledalac>) (ArrayList<?>) gledaoci;
    }

    public ArrayList<Gledalac> getLista() {
        return lista;
    }

}
