package roundrobinsimulator;

public class RoundRobin {
    public int tiempo_final_ejecución; 
    public int tiempo_final_respuesta;
    public int tiempo_final_espera;
    
    public void RoundRobin(int i){
        this.tiempo_final_ejecución = 0;
        this.tiempo_final_respuesta = 0;
    }
   
    public void setTiempo_final_respuesta(int i){
        this.tiempo_final_respuesta = i;
    }
    
    public int getTiempo_final_respuesta(){
        return tiempo_final_respuesta;
    }
    
    public void setTiempo_final_ejecución(int i){
        this.tiempo_final_ejecución = i;
    }
    
    public int getTiempo_final_ejecución(){
        return tiempo_final_ejecución;
    }
    
    public void setTiempo_final_espera(int i){
        this.tiempo_final_espera = i;
    }
    
    public int getTiempo_final_espera(){
        return tiempo_final_espera;
    }
    
    
    public boolean cpuVacio(Lista listaCPU){
        return listaCPU.esVacia();
    }

    public boolean ramVacio(Lista listaRAM){
        return listaRAM.esVacia();
    }
    
    public void resetCola(Lista lista){
        lista.setInicio(null);
    }
    
    void auxAListos(Lista listos_aux, Lista listos, int time) {
       
        if(!listos_aux.esVacia()){ 
            Proceso copia = listos_aux.getInicio(); 
            while(copia!=null){
                if(copia.getT_llegada()==time){ 
                    listos.agregarProceso(copia);
                    listos_aux.setContador_aux(listos_aux.getContador_aux() + 1);
                    System.out.print(" *" + copia.getNombre() + " llegó a los " + time + "s... LISTOS->");
                    this.verListaProcesos(listos);
                }
                copia = copia.getSiguiente(); 
            }
        }
        else{}
        if(listos_aux.getContador_aux() == listos_aux.getTamanio()){ 
            this.resetCola(listos_aux); 
        }

    }
    
    public int listosARAM(Lista listos, Lista RAM){
        if(RAM.esVacia()){
            listos.getInicio();      
            String proc_listo = listos.getInicio().getNombre();
            
            if(listos.getInicio().getTamanio_proceso()<=RAM.getRam()){
                RAM.agregarProceso(listos.getInicio());
                System.out.print("\n *" + listos.getInicio().getNombre() + " (" + listos.getInicio().getTamanio_proceso() + "MB) subió a la RAM->");
                this.verListaProcesos(RAM);

                RAM.setRam(RAM.getRam() - listos.getInicio().getTamanio_proceso());
                System.out.print(", " +RAM.getRam() + " MB restantes.");

                listos.removerProceso(); 
                System.out.print("\n *" + proc_listo + " sale de LISTOS->");
                this.verListaProcesos(listos);
            }
            else{
                System.out.print("\n *No hay RAM suficiente para subir a " + listos.getInicio().getNombre() + " (" + listos.getInicio().getTamanio_proceso() + "MB)\n");
                return 1;
            }
        }
        else{ 
            
            Proceso copia = new Proceso(); 
            copia.setResponde(listos.getInicio().getResponde());
            copia.setlast_time_in_cpu(listos.getInicio().getlast_time_in_cpu());
            String proc_listo = listos.getInicio().getNombre();
            copia.Proceso(listos.getInicio().getId(), listos.getInicio().getNombre(), listos.getInicio().getTamanio_proceso(), listos.getInicio().getRafaga(), listos.getInicio().getT_llegada(), listos.getInicio().getRafaga_acumulada(), listos.getInicio().getT_respuesta());
            
            if(copia.getTamanio_proceso()<=RAM.getRam()){
                Proceso copia_recorre = RAM.getInicio(); 
                while(copia_recorre.getSiguiente()!= null){
                    copia_recorre = copia_recorre.getSiguiente();
                    }      
                copia_recorre.setSiguiente(copia);
                System.out.print("\n *" + copia.getNombre() + " (" + copia.getTamanio_proceso() + "MB) subió a la RAM->");
                this.verListaProcesos(RAM);
                RAM.setRam(RAM.getRam() - listos.getInicio().getTamanio_proceso());
                System.out.print(", "+ RAM.getRam() + " MB restantes.");
                
                listos.removerProceso(); 
                System.out.print("\n *" + proc_listo + " sale de LISTOS->");
                this.verListaProcesos(listos);
            }
            else{
                System.out.print("\n *No hay RAM suficiente para subir a " + listos.getInicio().getNombre() + " (" + listos.getInicio().getTamanio_proceso() + "MB)");
                return 1;
            }
        }
        return 0;
    }
  
    public void verListaProcesos(Lista lista){
        if(!lista.esVacia()){
            Proceso copia = lista.getInicio();
            System.out.print("[");
            while(copia != null){
                System.out.print(copia.getNombre() + " ");
                copia = copia.getSiguiente();
            }
            System.out.print("\b]");
        }
        else{
            System.out.print("[ ]");
        }
    }    

    public void verProcesoCPU(Lista lista){
        if(!lista.esVacia()){
            Proceso copia = lista.getInicio();
            System.out.print("[");
            System.out.print(copia.getNombre() + "]... (" + copia.getRafaga_acumulada() + "/" +copia.getRafaga() + ")s | Q = " + copia.getContador() + "s.\n");
           }
        else{
        System.out.print("[ ]");
        }
        
    }    
    
    public void ramACPU(Lista ram, Lista cpu, int time){ 
        Proceso copia = ram.getInicio();
        int nom_ultimo_ram = copia.getTamanio_proceso();
  
        System.out.print("\n *" + copia.getNombre() + " sale de RAM->");
        ram.removerProcesoRAM(); 
        this.verListaProcesos(ram);
        System.out.print(" y libera " + nom_ultimo_ram + "MB, " + ram.getRam() + " MB restantes.\n");
        cpu.agregarProceso(copia);
        System.out.print(" *" + cpu.getInicio().getNombre() + " sube a CPU->");
        this.verProcesoCPU(cpu);
    }
    
    public int ejecutandoEnCPU(Lista CPU, Lista listos, int quantum, Lista RAM, int time){
    if(CPU.getInicio().getContador() == 0){
        CPU.getInicio().setlast_time_in_cpu(time);
    }
    else{}   
        
    if(CPU.getInicio().getResponde()==false){
        CPU.getInicio().setResponde(true);
        CPU.getInicio().setT_respuesta(time);
        this.tiempo_final_respuesta = this.tiempo_final_respuesta + (CPU.getInicio().getT_respuesta() - CPU.getInicio().getT_llegada());
    }
    else{}
        if(CPU.getInicio().getRafaga_acumulada() == CPU.getInicio().getRafaga()){ 
                int sum = (time - CPU.getInicio().getlast_time_in_cpu()); 
                this.setTiempo_final_espera(this.getTiempo_final_espera()+ (((time - sum) - (CPU.getInicio().getRafaga() - sum)) - CPU.getInicio().getT_llegada()));
                System.out.print(" *CPU->");
                this.verProcesoCPU(CPU);
                System.out.print(" *¡Terminó su ejecución " + CPU.getInicio().getNombre() + "!, CPU ->[ ]");  
                this.setTiempo_final_ejecución((time - CPU.getInicio().getT_llegada()) + this.getTiempo_final_ejecución());
                CPU.removerProceso(); 
                return 1;
        }
        else{
            System.out.print("\n  CPU->");
            this.verProcesoCPU(CPU);
            
            if(CPU.getInicio().getContador() == quantum){
                System.out.println(" *Finalizó su quantum de ejecución " + CPU.getInicio().getNombre() + " y sale de la CPU ->[ ]");   
                CPU.setContador_aux(0); 
                this.cpuAlista(CPU, listos);
                this.listosARAM(listos, RAM);
                return 1;
            }
            else{
            CPU.getInicio().setRafaga_acumulada(CPU.getInicio().getRafaga_acumulada() + 1);
            CPU.getInicio().setContador(CPU.getInicio().getContador() + 1);     
            }
        }
        return 0;
    }
    
    public int ejecutandoEnCPU_noshow(Lista CPU, Lista listos, int quantum, Lista RAM, int time){
        if(CPU.getInicio().getContador() == 0){
            CPU.getInicio().setlast_time_in_cpu(time);
        }
        else{}
        
        if(CPU.getInicio().getResponde()==false){ 
            CPU.getInicio().setResponde(true); 
            CPU.getInicio().setT_respuesta(time);
            this.tiempo_final_respuesta = this.tiempo_final_respuesta + (CPU.getInicio().getT_respuesta() - CPU.getInicio().getT_llegada());
        }

        if(CPU.getInicio().getRafaga_acumulada() == CPU.getInicio().getRafaga()){ 
                CPU.removerProceso(); 
                return 1;
        }
        else{
            if(CPU.getInicio().getContador() == quantum){                 
                CPU.setContador_aux(0); 
                this.cpuAlista(CPU, listos); 
                this.listosARAM(listos, RAM);
                return 1;
            }
            else{
                CPU.getInicio().setRafaga_acumulada(CPU.getInicio().getRafaga_acumulada() + 1);
                CPU.getInicio().setContador(CPU.getInicio().getContador() + 1);
            }
        }
        return 0;
    }    

    public void cpuAlista(Lista cpu,Lista listos){
        System.out.print(" *" + cpu.getInicio().getNombre() + " se forma en LISTOS->" );       
        listos.agregarProceso(cpu.getInicio());   
        this.verListaProcesos(listos);
        cpu.quitar();
    }    
    
    public boolean terminoSimu(Lista aux, Lista listos, Lista RAM, Lista CPU){
        Proceso a = aux.getInicio();
        Proceso b = listos.getInicio();
        Proceso c = RAM.getInicio();
        Proceso d = CPU.getInicio();
        return a == null && b == null && c == null && d==null;
    }
    
}
