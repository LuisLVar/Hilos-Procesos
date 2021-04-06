/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author javie
 */
public class Practica2_SO2_G4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //NewJFrame window = new NewJFrame();
        //window.setVisible(true);
        
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ConcurrentList list = new ConcurrentList();
        long start_insertion = System.nanoTime();
        
        for(int i = 0; i < 20; i++) {
            int random = (int)(Math.random() * 2 + 1);
            threadPool.execute(new OperationTask(random, list));
        }
        try {
            threadPool.shutdown();
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            
            long finish_insertion = System.nanoTime();
            long delta_insertion_time = finish_insertion - start_insertion;
            System.out.println("Tiempo Insertar: " + delta_insertion_time);
            System.out.println("Numero de cajas: " + list.getSize());
            list.printList();
        } catch(Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
    }
    
}
