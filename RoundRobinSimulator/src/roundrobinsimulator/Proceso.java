package roundrobinsimulator;

/**
 *
 * @author eriq
 */
public class Proceso {
    private int id; 
    private String nombre; 
    private int tamanio_proceso;
    private int rafaga; 
    private int t_llegada; 
    private Proceso siguiente; 
    private int rafaga_acumulada = 0; 
    private int contador =0; 
    private boolean responde = false;
    private int t_respuesta = 0;
    private int last_time_in_cpu = 0;

    public Proceso(){}

    void Proceso(int id, String name, int tamanio, int rafaga, int t_llegada, int acumulada, int resp){
        this.id = id;
        this.nombre = name;
        this.tamanio_proceso = tamanio;
        this.rafaga = rafaga;
        this.t_llegada = t_llegada;
        this.rafaga_acumulada = acumulada;
        this.t_respuesta = resp;
    }     
    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    String getNombre() {
        return nombre;
    }
    void setNombre(String nombre){
        this.nombre = nombre;
    }
    int getT_respuesta(){
        return t_respuesta;
    }
    void setT_respuesta(int i){
        this.t_respuesta = i;
    }     
    void setResponde(boolean i){
        this.responde = i;
    }
    boolean getResponde(){
        return this.responde;
    }
    int getTamanio_proceso() {
        return tamanio_proceso;
    }
    void setTamanio_proceso(int tamanio) {
        this.tamanio_proceso = tamanio;
    }
    int getRafaga() {
        return rafaga;
    }
    void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    } 
    int getT_llegada() {
        return t_llegada;
    }
    void setT_llegada(int t_llegada) {
        this.t_llegada = t_llegada;
    }
    Proceso getSiguiente() {
        return siguiente;
    }
    void setSiguiente(Proceso siguiente) {
        this.siguiente = siguiente;
    }
    int getRafaga_acumulada() {
        return rafaga_acumulada;
    }
    void setRafaga_acumulada(int rafaga_acumulada) {
        this.rafaga_acumulada = rafaga_acumulada;
    }
    int getContador() {
        return contador;
    }
    void setContador(int contador) {
        this.contador = contador;
    }
    int getlast_time_in_cpu(){
       return this.last_time_in_cpu;
    } 
    void setlast_time_in_cpu(int i){
       this.last_time_in_cpu = i;
    }
}