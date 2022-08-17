from audioop import add
from re import X
import PySimpleGUI as pg
import socket


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

while True: 
    event, values = window.read()
    
    if event == "Cancelar": 
        socketCliente.close()
        break
    elif(values[0] == address):
        print("hola")
        socketCliente.connect(('localhost', port))
        import mediaPlayer as MP

window.close()
