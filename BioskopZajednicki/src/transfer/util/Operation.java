/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;


public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_FILM = 2;
    public static final int DELETE_FILM = 3;
    public static final int UPDATE_FILM = 4;
    public static final int GET_ALL_FILM = 5;

    public static final int GET_ALL_ULOGA = 6;

    public static final int ADD_TERMIN = 7;
    public static final int DELETE_TERMIN = 8;
    public static final int UPDATE_TERMIN = 9;
    public static final int GET_ALL_TERMIN = 10;

    public static final int ADD_KARTA = 11;
    public static final int DELETE_KARTA = 12;
    public static final int GET_ALL_KARTA = 13;

    public static final int GET_ALL_SALA = 14;

    public static final int GET_ALL_GLEDALAC = 15;

    public static final int GET_ALL_GLUMAC = 16;

}
