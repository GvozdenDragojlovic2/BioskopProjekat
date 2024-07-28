/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.karta;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Karta;
import so.AbstractSO;


public class SODeleteKarta extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Karta)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Karta!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
