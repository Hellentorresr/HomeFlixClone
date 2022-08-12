package controller.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerJava {
    final int PUERTO = 5000;
    //puentes de connection entre el cliente y el servidor
    DataInputStream in;// del cliente al servidor
    DataOutputStream out;// del servidor al cliente
    ServerSocket servidor;
    Socket sc;


}
