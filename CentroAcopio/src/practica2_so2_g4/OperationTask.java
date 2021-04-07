/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author javier
 */
public class OperationTask implements Runnable {
   
    private final int operationType;
    private final ConcurrentList concurrentList;
    private final JPanel[] label;
    private final JLabel giveLabel, takeLabel;
    
    public OperationTask(int operationType, ConcurrentList concurrentList, JPanel[] label, JLabel giveQueue, JLabel takeQueue) {
        this.operationType = operationType;
        this.concurrentList = concurrentList;
        this.label = label;
        this.giveLabel = giveQueue;
        this.takeLabel = takeQueue;
    }

    @Override
    public void run() {
        try {
            switch(this.operationType) {
                case 1:
                    this.concurrentList.insert(this.label, this.giveLabel, this.takeLabel);
                    break;
                case 2:
                    this.concurrentList.remove(this.label, this.giveLabel, this.takeLabel);
                    break;
            }
        } catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        }  
    }
}
