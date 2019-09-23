package roundrobinsimulator;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RoundRobinSimulator {
    
    public static void main(String[] args) {
        
       int n;
       int quantum;        
       String name;
       int size;
       int llegada;
       int rafaga;
       int time = 0;
       String opt;
       String opt2;
       String opt3 = null;
        
       Scanner s = new Scanner(System.in);
       Random id = new Random(); 

       Lista listos = new Lista();
       Lista listos_aux = new Lista();
       Lista RAM = new Lista();
       Lista CPU = new Lista();
       
       RoundRobin RR = new RoundRobin();
       
       try{
            System.out.println("SIMULADOR DE PROCESOS, ALGORITMO ROUND ROBIN");
            System.out.print("¿Cantidad de procesos a simular?: ");
            n = s.nextInt();
            System.out.print("Tamaño del quantum del CPU: ");
            quantum = s.nextInt();
            s.nextLine();
            System.out.print("¿Con tiempos de llegada? s/n: ");
            opt = s.nextLine();
            System.out.print("¿Con tamaño personalizado en MB del proceso? s/n: ");
            opt2 = s.nextLine();
            System.out.print("¿La simulación será en tiempo real (segundos)? s/n: ");
            opt3 = s.nextLine();

            int num_proc = n;
            while(n!=0){
                System.out.print("\nNombre del proceso: ");
                name = s.nextLine();
                if("s".equals(opt2)){
                    System.out.print("Tamaño del proceso en MB (máx 1024): ");
                    size = s.nextInt();
                }
                else{
                    size = 16;
                }
                if (size > RAM.getRam()){
                    System.out.println("No puedes ejecutar procesos mayores a 1024 MB.");
                    break;
                }
                System.out.print("Duracion de la rafaga (s): ");
                rafaga = s.nextInt();
                s.nextLine();
                if("n".equals(opt)){
                    llegada = 0;
                }
                else{
                    System.out.print("Tiempo de llegada (s): ");
                    llegada = s.nextInt();   
                    s.nextLine();
                }      
                listos_aux.agregarProcesoDatos(((id.nextInt(100000) % 1000)), name, size, rafaga, llegada, 0);
                n = n-1;
            }

            System.out.print("\n*ROUND ROBIN, ");
            System.out.println("Q = " + quantum + "\n1024MB de RAM disponible.");

            System.out.print("Procesos ingresados por el usuario a simular: ");
            RR.verListaProcesos(listos_aux);

            while(!RR.terminoSimu(listos_aux, listos, RAM, CPU)){
                System.out.println("\n" +time + "s.");
                RR.auxAListos(listos_aux, listos, time);
                if(!listos.esVacia()){
                     RR.listosARAM(listos, RAM);  
                }
                if(!RR.cpuVacio(RAM) || !RR.cpuVacio(CPU)){ 
                     if(RR.cpuVacio(CPU)){             
                         RR.ramACPU(RAM, CPU, time);
                         RR.ejecutandoEnCPU(CPU, listos, quantum,RAM, time);
                    }
                     else{
                        int j = RR.ejecutandoEnCPU(CPU, listos, quantum, RAM, time);
                        if(j==1 && !RAM.esVacia()){ 
                            RR.ramACPU(RAM, CPU, time); 
                            RR.ejecutandoEnCPU_noshow(CPU, listos, quantum,RAM, time); 
                        }
                    }
                }
                    System.out.print("\n  LISTOS->");
                    RR.verListaProcesos(listos);
                    System.out.print("\n  RAM->");
                    RR.verListaProcesos(RAM);
                    System.out.println("\n  " + RAM.getRam() + "MB libres.");
                    time = time + 1;
                    
                    if("s".equals(opt3)){
                        Thread.sleep(1000);
                    }
                }
                System.out.println("\nTiempo promedio de espera = " + ((double)RR.getTiempo_final_espera()/num_proc) + "s."); 
                System.out.println("Tiempo promedio de ejecución = " + ((double)RR.getTiempo_final_ejecución()/num_proc) + "s.");
                System.out.println("Tiempo promedio de respuesta = " + ((double)RR.getTiempo_final_respuesta()/num_proc) + "s.");     
            }
            catch(java.util.InputMismatchException i){
                System.out.println("Entrada inválida.");
            } catch (InterruptedException ex) {
                System.out.println("Simulación interrumpida.");            
            }
    }       
}
