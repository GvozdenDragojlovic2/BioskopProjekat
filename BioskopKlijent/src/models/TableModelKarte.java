/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Karta;
import domain.Termin;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class TableModelKarte extends AbstractTableModel {

    private ArrayList<Karta> lista;
    private String[] kolone = {"Rb", "Naziv uloge", "Glumac"};

    public TableModelKarte() {
        lista = new ArrayList<>();
    }

    public TableModelKarte(Termin t) {
        try {
            lista = ClientController.getInstance().getAllKarta(t);
        } catch (Exception ex) {
            Logger.getLogger(TableModelKarte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Karta k = lista.get(row);

        switch (column) {
            case 0:
                return k.getGledalac();
            case 1:
                return k.getSediste();

            default:
                return null;
        }
    }

    public void dodajKartu(Karta k) {
        lista.add(k);
        fireTableDataChanged();
    }

    public void obrisiKartu(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public boolean postojiSediste(String sediste) {
        for (Karta karta : lista) {
            if (sediste.equals(karta.getSediste())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Karta> getLista() {
        return lista;
    }

    public Karta getSelectedKarta(int row) {
        return lista.get(row);
    }

}
