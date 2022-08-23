from audioop import add
from re import X
import PySimpleGUI as pg
import socket
import mediaPlayer as MP
import jpysocket

socketCliente = socket.socket()

port = 12345
address = '186.176.68.10'


pg.theme("DarkAmber")

input = pg.Input()
btnOk = pg.Button("OK")
btnCancel = pg.Button("Cancelar")
layout = [
    [pg.Text("Ingrese la direccion ip a conectarse: ")],
    [input],
    [btnOk, btnCancel]
]
portString = str(port)
window = pg.Window("Form", layout)
socketCliente.connect(('localhost', port))




while True: 
    #event, values = window.read()
        msg = socketCliente.recv(port)
        print(msg)
        msg = jpysocket.jpydecode(msg)
        ##recibe info
        if(msg.startswith('C:')):
            mensaje = jpysocket.jpyencode("done")
            socketCliente.send(mensaje)
            MP.runApp(msg)  
            
        if(msg.startswith("pausar")):
            MP.pauseAPP()
        if(msg.startswith("play")):
            MP.playApp
        
        
        

'''if event == "Cancelar": 
        socketCliente.close()
        break
    elif(values[0] == address):'''