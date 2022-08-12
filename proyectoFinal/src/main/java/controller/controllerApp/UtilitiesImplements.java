/**
 * @controller.controllerApp paquete que controla la interfaz grafica
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12/08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Clase UtilitiesImplements, para implementar los metodos abstractos de la clase UtilitiesAbstract
 */
public class UtilitiesImplements extends UtilitiesAbstract {
    @Override
    public int validarNumero(int numero) {
        return 0;
    }

    @Override
    public String pathInterfazGrafica(String path) throws IOException {
        return null;
    }

    @Override
    public String pathInterfazGrafica(String path, Button button) throws IOException {
        return null;
    }
}
