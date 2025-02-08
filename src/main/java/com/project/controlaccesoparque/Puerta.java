package com.project.controlaccesoparque;

import java.util.concurrent.Semaphore;


public class Puerta {
    private final int id; // identificador de la puerta
    private final Semaphore semaphore; // controla el acceso, solo un visitante a la vez

    // constructor que inicializa la puerta con su id y el semaforo
    public Puerta(int id) {
        this.id = id;
        this.semaphore = new Semaphore(1); // permite solo un visitante a la vez
    }

    // metodo para que un visitante intente acceder
    public boolean acceder(Visitante visitante) {
        if (semaphore.tryAcquire()) { // intenta adquirir acceso sin bloquear
            try {
                System.out.println("visitante " + visitante.getId() + " accediendo a traves de la puerta " + id);
                Thread.sleep(1000); // simula el tiempo de acceso
                System.out.println("visitante " + visitante.getId() + " ha accedido a la puerta " + id);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restablece el estado de interrupcion del hilo
            } finally {
                semaphore.release(); // libera el acceso para otro visitante
            }
            return true;
        }
        return false; // no pudo acceder porque la puerta estaba ocupada
    }
}

