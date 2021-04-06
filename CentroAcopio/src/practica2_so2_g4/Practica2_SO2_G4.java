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
        long startInsertion = System.nanoTime();
        
        for(int i = 0; i < 1; i++) {
            int random = (int)(Math.random() * 2 + 1);
            System.out.println(random);
            threadPool.execute(new OperationTask(random, list));
        }
        
        threadPool.shutdown();
        
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            long finish_insertion = System.nanoTime();
            long delta_insertion_time = finish_insertion - startInsertion;
            System.out.println("Tiempo Insertar : " + delta_insertion_time);
        } catch(Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
    }
    
}
