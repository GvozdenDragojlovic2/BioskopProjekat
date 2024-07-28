/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.karta;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Karta;
import java.util.ArrayList;
import so.AbstractSO;


public class SOGetAllKarta extends AbstractSO {

    private ArrayList<Karta> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Karta)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Karta!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> karte = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Karta>) (ArrayList<?>) karte;
    }

    public ArrayList<Karta> getLista() {
        return lista;
    }

}
