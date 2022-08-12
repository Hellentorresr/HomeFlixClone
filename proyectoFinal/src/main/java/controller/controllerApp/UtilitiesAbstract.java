/**
 * Paquete controlador que controla la interfaz
 *  @autor por Hellen Torres
 *  @FechaCreacion 12/08/2022
 *  @Ultima_Modificacion 12/08/2022
 *  @por Hellen torres
 */
package controller.controllerApp;

/**
 * clase UtilitiesAbstract para abstraer código repetitivo
 */
public class UtilitiesAbstract {
    /**
     * Atributos de la clase UtilitiesAbstract
     */
    //quiero usar un metodo que valide los numeros y no se caiga el sistema
    //variable String para recibir path
    private int num;
    private String pathInterfazGrafica;

    /**
     * Constructor
     */
    public UtilitiesAbstract() {
    }

    /**
     * Constructor dos si solo ocupo el path
     *
     * @param pathInterfazGrafica recibe un string
     */

    public UtilitiesAbstract(String pathInterfazGrafica) {
        this.pathInterfazGrafica = pathInterfazGrafica;
    }

    //getters y setters
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPathInterfazGrafica() {
        return pathInterfazGrafica;
    }

    public void setPathInterfazGrafica(String pathInterfazGrafica) {
        this.pathInterfazGrafica = pathInterfazGrafica;
    }
}