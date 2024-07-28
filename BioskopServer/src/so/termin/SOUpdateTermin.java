/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Termin;
import java.util.Date;
import so.AbstractSO;


public class SOUpdateTermin extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;

        if (t.getCenaKarte() < 300 || t.getCenaKarte() > 5000) {
            throw new Exception("Cena karte mora biti izmedju 300din i 5000din!");
        }

        if (!t.getDatumVreme().after(new Date())) {
            throw new Exception("Datum i vreme termina moraju biti u buducnosti!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
