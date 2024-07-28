/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Film;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class TableModelFilmovi extends AbstractTableModel implements Runnable {

    private ArrayList<Film> lista;
    private String[] kolone = {"ID", "Naziv", "Zanr", "Reziser"};
    private String parametar = "";

    public TableModelFilmovi() {
        try {
            lista = ClientController.getInstance().getAllFilm();
        } catch (Exception ex) {
            Logger.getLogger(TableModelFilmovi.class.getName()).log(Level.SEVERE, null, ex);
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
        Film f = lista.get(row);

        switch (column) {
            case 0:
                return f.getFilmID();
            case 1:
                return f.getNaziv();
            case 2:
                return f.getZanr();
            case 3:
                return f.getReziser();

            default:
                return null;
        }
    }

    public Film getSelectedFilm(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelFilmovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllFilm();
            if (!parametar.equals("")) {
                ArrayList<Film> novaLista = new ArrayList<>();
                for (Film f : lista) {
                    if (f.getNaziv().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(f);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
