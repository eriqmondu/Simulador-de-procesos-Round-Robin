![java](java.png)

# Simulador de procesos: algoritmo "Round Robin"

**Implementado por: Erik Valdez Mondragón**

Facultad de Ingeniería, UNAM

Sistemas Operativos

Simulador de procesos que utiliza el algoritmo de Round Robin para administrarlos dentro de un SO. Incluye visualización de colas de procesos y el cómo se van procesando por la CPU según su tiempo asignado (quantum). Útil para estudiar el comportamiento paso a paso de dicho algoritmo. 

## Instrucciones de instalación

Clonar este repositorio

	https://github.com/eriqmondu/Simulador-de-procesos-Round-Robin.git


Ejecutar el jar desde Ubuntu con:

	java -jar RoundRobinSimulator.jar

El simulador soporta las siguientes características:

	Cantidad personalizada de procesos a simular
	Elección del tamaño del Quantum de la CPU
	Tiempos de llegada por proceso
	Tamaño personalizado de memoria para cada proceso a simular
	Memoria RAM simulada de 1024MB

Usar Java 1.8 o mayor. Se incluye la documentación del proyecto en PDF.
