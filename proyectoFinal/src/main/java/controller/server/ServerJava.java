/**
 * Paquete server donde se guarda la clase Server.java
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12/08/2022
 * @por Hellen torres
 */
package controller.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerJava {
    final int PUERTO = 12345;
    //puentes de connection entre el cliente y el servidor
    DataInputStream in;// del cliente al servidor
    DataOutputStream out;// del servidor al cliente
    ServerSocket servidor;
    Socket sc;

    public void serverConnectorJava() {
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor Iniciado");

            while (true) {
                sc = servidor.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(sc.getInputStream());

                out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF("conectar");
                String massage = in.readUTF();
                System.out.println(massage);

                ///respuesta del servidor


                //cerra el cliente, No el servidor
                sc.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
