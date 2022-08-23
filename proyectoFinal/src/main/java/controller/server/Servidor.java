/**
 * Paquete server donde se guarda la clase Server.java
 *
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 22/08/2022
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

/**
 * Esta Clase servidor se encarga solo de gestionar cliente, Para que siempre este a la escucha
 * Y luego tengamos un hilo para cada cliente
 */
public class Servidor extends Thread {
    public  String resp;

    public void sever() {
        try {
            ServerSocket server = new ServerSocket(12345);
            Socket sc;//será el cliente que se conectara
            System.out.println("Servidor iniciado");
            while (true) {//indicando que siempre estará escuchado
                sc = server.accept();//que si acepta peticiones
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());

                //aqui le indicamos al cliente que queremos

                out.writeUTF("Escribe tu nombre: ");
                String nombreCliente = in.readUTF();

                ServidorHilo hilo = new ServidorHilo(sc, in, out, nombreCliente);
                hilo.start();//como que inicie un hilo aparte, ya teniendo la information del cliente, tu hijo gestiónalo
                resp = nombreCliente;
                System.out.println("Creada la conexion con el cliente: " + nombreCliente);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String devolverN() {
        return this.resp;
    }
}


