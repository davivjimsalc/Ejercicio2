package com.project.controlaccesoparque;

import java.util.concurrent.Semaphore;


public class Atraccion {
    private final String nombre; // nombre de la atraccion
    private final Semaphore semaphore; // controla el acceso segun la capacidad

    // constructor que recibe el nombre y la capacidad maxima
    public Atraccion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.semaphore = new Semaphore(capacidad); // inicializa el semaforo con la capacidad dada
    }

    // metodo para que un visitante acceda a la atraccion
    public void acceder(Visitante visitante) {
        try {
            semaphore.acquire(); // espera hasta que haya un espacio disponible
            System.out.println("visitante " + visitante.getId() + " accediendo a la atraccion " + nombre);
            Thread.sleep(2000); // simula el tiempo que pasa en la atraccion
            System.out.println("visitante " + visitante.getId() + " ha salido de la atraccion " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restablece el estado de interrupcion del hilo
        } finally {
            semaphore.release(); // libera un espacio para otro visitante
        }
    }
}


