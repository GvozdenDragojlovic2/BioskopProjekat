/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Karta;
import domain.Termin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;


public class SOAddTermin extends AbstractSO {

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

        if (t.getKarte().isEmpty()) {
            throw new Exception("Termin mora imati barem jednu kartu!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);

        Termin t = (Termin) ado;
        t.setTerminID(terminID);

        for (Karta karta : t.getKarte()) {
            karta.setTermin(t);
            DBBroker.getInstance().insert(karta);
        }

    }

}
