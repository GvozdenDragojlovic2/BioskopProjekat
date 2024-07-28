/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Film;
import domain.Karta;
import domain.Termin;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;


public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                // primi zahtev
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                // switch
                Response response = handleRequest(request);
                // posalji odgovor
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_FILM:
                    ServerController.getInstance().addFilm((Film) request.getData());
                    break;
                case Operation.ADD_TERMIN:
                    ServerController.getInstance().addTermin((Termin) request.getData());
                    break;
                case Operation.ADD_KARTA:
                    ServerController.getInstance().addKarta((Karta) request.getData());
                    break;
                case Operation.DELETE_FILM:
                    ServerController.getInstance().deleteFilm((Film) request.getData());
                    break;
                case Operation.DELETE_TERMIN:
                    ServerController.getInstance().deleteTermin((Termin) request.getData());
                    break;
                case Operation.DELETE_KARTA:
                    ServerController.getInstance().deleteKarta((Karta) request.getData());
                    break;
                case Operation.UPDATE_FILM:
                    ServerController.getInstance().updateFilm((Film) request.getData());
                    break;
                case Operation.UPDATE_TERMIN:
                    ServerController.getInstance().updateTermin((Termin) request.getData());
                    break;
                case Operation.GET_ALL_FILM:
                    response.setData(ServerController.getInstance().getAllFilm());
                    break;
                case Operation.GET_ALL_GLEDALAC:
                    response.setData(ServerController.getInstance().getAllGledalac());
                    break;
                case Operation.GET_ALL_GLUMAC:
                    response.setData(ServerController.getInstance().getAllGlumac());
                    break;
                case Operation.GET_ALL_KARTA:
                    response.setData(ServerController.getInstance().getAllKarta((Termin) request.getData()));
                    break;
                case Operation.GET_ALL_SALA:
                    response.setData(ServerController.getInstance().getAllSala());
                    break;
                case Operation.GET_ALL_TERMIN:
                    response.setData(ServerController.getInstance().getAllTermin());
                    break;
                case Operation.GET_ALL_ULOGA:
                    response.setData(ServerController.getInstance().getAllUloga((Film) request.getData()));
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator admin = ServerController.getInstance().login(administrator);
                    response.setData(admin);
                    break;
                case Operation.LOGOUT:
                    Administrator ulogovani = (Administrator) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
