/**
 * Paquete server donde se guarda la clase Server.java
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12/08/2022
 * @por Hellen torres
 */
package controller.server;


import controller.controllerApp.ReproductorVideoController;
import controller.controllerApp.UtilitiesImplements;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.InicioApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.controllerApp.HomeController.video;
import static controller.controllerApp.ReproductorVideoController.mpVideo;

public class Server extends Thread{
    final int PUERTO = 12345;
    //puentes de connection entre el cliente y el servidor
    DataInputStream in;// del cliente al servidor
    DataOutputStream out;// del servidor al cliente
    ServerSocket servidor;
    Socket sc;

    ReproductorVideoController reproductorVideoController;
    UtilitiesImplements utilitiesImplements;

    public Server() {
        reproductorVideoController = new ReproductorVideoController();
        utilitiesImplements = new UtilitiesImplements();
    }

    public void serverConnectorJava(){
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor Iniciado");

            while (true) {
                sc = servidor.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(sc.getInputStream());

                out = new DataOutputStream(sc.getOutputStream());

                out.writeUTF(video.getVideoPath());

                String message =  in.readUTF();

                if(message.equals("done")){
                    //reproductorVideoController.reproducir();
                    System.out.println(message);
                    TimeUnit.SECONDS.sleep(1);
                    mpVideo.play();
                }
                if(!sc.isConnected()){
                    sc.close();
                    System.out.println("Cliente desconectado");
                }

                ///respuesta del servidor


                //cerra el cliente, No el servidor

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
