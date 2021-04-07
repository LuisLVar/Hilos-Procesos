/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.border.Border; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author javier
 */
public class Interface extends JFrame{
    JPanel jpanel;
    JPanel[] label;
    JLabel giveQueue, takeQueue;
    Border border;
    
    public Interface() {
        border = BorderFactory.createLineBorder(Color.black, 1);
        jpanel = (JPanel) this.getContentPane();
        jpanel.setLayout(null);
        jpanel.setBackground(Color.lightGray);
        label = new JPanel[20];
        for(int i = 0; i < label.length; i++) {
            label[i] = new JPanel();
            if(i < 10) {
                label[i].setBounds(new Rectangle(15, (i+1)*30, 40, 25));
            } else {
                label[i].setBounds(new Rectangle(70, (i-9)*30, 40, 25));
            }
            label[i].setBorder(border);
            label[i].setBackground(Color.white);
            jpanel.add(label[i], null);
        }
        giveQueue = new JLabel();
        giveQueue.setFont(new Font(giveQueue.getName(), Font.PLAIN, 18));
        giveQueue.setBounds(new Rectangle(145, (2)*30, 200, 50));
        giveQueue.setText("Cola dejar caja: ");
        jpanel.add(giveQueue, null);
        takeQueue = new JLabel();
        takeQueue.setFont(new Font(giveQueue.getName(), Font.PLAIN, 18));
        takeQueue.setBounds(new Rectangle(145, (6)*30, 200, 50));
        takeQueue.setText("Cola traer caja: ");
        jpanel.add(takeQueue, null);
        JButton b =new JButton("Terminar");  
        b.setBounds(270,315,95,30);  
        b.addActionListener((ActionEvent e) -> {
            System.exit(0);  
        }); 
        jpanel.add(b);
        setSize(400,400);
        setTitle("Centro de Acopio");
        setVisible(true);
    }
    
    public void startExecution() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ConcurrentList list = new ConcurrentList();
        for(int i = 0; i < 500; i++) {
            int random = (int)(Math.random() * 2 + 1);
            threadPool.execute(new OperationTask(random, list, this.label, this.giveQueue, this.takeQueue));
        }
        try {
            threadPool.shutdown();
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch(InterruptedException ex) {
            System.out.println("error " + ex.getMessage());
        }
    }
}
