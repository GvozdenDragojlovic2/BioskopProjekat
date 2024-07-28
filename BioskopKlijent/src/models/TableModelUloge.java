/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Film;
import domain.Uloga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class TableModelUloge extends AbstractTableModel {

    private ArrayList<Uloga> lista;
    private String[] kolone = {"Rb", "Naziv uloge", "Glumac"};
    private int rb = 0;

    public TableModelUloge() {
        lista = new ArrayList<>();
    }

    public TableModelUloge(Film f) {
        try {
            lista = ClientController.getInstance().getAllUloga(f);
        } catch (Exception ex) {
            Logger.getLogger(TableModelUloge.class.getName()).log(Level.SEVERE, null, ex);
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
        Uloga u = lista.get(row);

        switch (column) {
            case 0:
                return u.getRb();
            case 1:
                return u.getNaziv();
            case 2:
                return u.getGlumac();

            default:
                return null;
        }
    }

    public void dodajUlogu(Uloga u) {
        rb = lista.size();
        u.setRb(++rb);
        lista.add(u);
        fireTableDataChanged();
    }

    public void obrisiUlogu(int row) {
        lista.remove(row);

        rb = 0;
        for (Uloga uloga : lista) {
            uloga.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public boolean postojiNaziv(String nazivUloge) {
        for (Uloga uloga : lista) {
            if (uloga.getNaziv().equals(nazivUloge)) {
                return true;
            }
        }
        return false;
    }

    public boolean postojiOpis(String opisUloge) {
        for (Uloga uloga : lista) {
            if (uloga.getOpis().equals(opisUloge)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Uloga> getLista() {
        return lista;
    }

}
