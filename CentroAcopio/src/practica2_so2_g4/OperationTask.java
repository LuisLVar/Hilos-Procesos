/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author javie
 */
public class OperationTask implements Runnable {
   
    private final int operationType;
    private final ConcurrentList concurrentList;
    
    public OperationTask(int operationType, ConcurrentList concurrentList) {
        this.operationType = operationType;
        this.concurrentList = concurrentList;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(OperationTask.class.getName()).log(Level.SEVERE, null, ex);
//        }
        switch(this.operationType) {
            case 1:
                this.concurrentList.insert();
                break;
            case 2:
                this.concurrentList.remove();
                break;
        }
    }
}
