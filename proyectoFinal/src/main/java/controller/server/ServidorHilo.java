package controller.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ServidorHilo extends Thread {
    Scanner sn = new Scanner(System.in);
    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;
    private Socket sc;


    public ServidorHilo(Socket sc, DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
        this.sc = sc;

    }

    @Override
    public void run() {//del hilo
        String mensaje;
    }
}
