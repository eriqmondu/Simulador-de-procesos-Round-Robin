package roundrobinsimulator;

public class Lista{
    
    private Proceso inicio; 
    private int tamanio;
    private int ram = 1024; 
    private int rafaga_acumulada;
    private int contador_aux = 0; 
     
    public int getT_llegadaProceso(){
        return this.getInicio().getT_llegada();
    }   
    public void setContador_aux(int i){
        this.contador_aux = i;
    } 
    public int getContador_aux(){
        return contador_aux;
    }
    public Proceso getInicio() { 
        return inicio;
    }
    public void setInicio(Proceso inicio) {
        this.inicio = inicio;
    }
    public int getTamanio() { 
        return tamanio;
    }
    public void setTamanio(int tamanio) { 
        this.tamanio = tamanio;
    }
    public void setRam(int ram) {
        this.ram = ram;
    }
    public int getRafaga_acumulada() {
        return rafaga_acumulada;
    }
    public void setRafaga_acumulada(int rafaga_acumulada) {
        this.rafaga_acumulada = rafaga_acumulada;
    }   
    public void quitar(){
        this.inicio = inicio.getSiguiente();
    }   
    public void Lista(){ 
        inicio = null; 
        tamanio = 0;
        contador_aux = 0;
    }
    public boolean esVacia(){
        return inicio == null;
    }        
    public void agregarProcesoDatos(int id, String nombre, int tamanio, int rafaga, int t_llegada, int respuesta){
        Proceso nuevo = new Proceso();
        nuevo.Proceso(id, nombre, tamanio, rafaga, t_llegada, 0, respuesta);

        if(esVacia()){
            inicio = nuevo;
        }
        else{ 
            Proceso copia = inicio;
            while(copia.getSiguiente()!= null){
                 copia = copia.getSiguiente();
            }
            copia.setSiguiente(nuevo);
        }
        this.tamanio++;
    }
    public void agregarProceso(Proceso proc){
        Proceso copy = new Proceso();
        copy.Proceso(proc.getId(), proc.getNombre(), proc.getTamanio_proceso(), proc.getRafaga(), proc.getT_llegada(), proc.getRafaga_acumulada(), proc.getT_respuesta());
        copy.setResponde(proc.getResponde());
        copy.setlast_time_in_cpu(proc.getlast_time_in_cpu());
        
        if(esVacia()){
            inicio = copy;
        }
        else{ 
            Proceso copia = inicio;
            while(copia.getSiguiente()!= null){
                copia = copia.getSiguiente();
            }              
            copia.setSiguiente(copy);
        }
        this.tamanio++;
    }    
    public void removerProceso(){
        inicio = inicio.getSiguiente();
        tamanio--;
    }    
    public void removerProcesoRAM(){ //sacar el proceso de la ram y liberar memoria
        ram = ram + inicio.getTamanio_proceso(); //ram liberada
        inicio = inicio.getSiguiente();
        tamanio--;
    }   
    public int getRam(){
        return this.ram;
    }
        
}