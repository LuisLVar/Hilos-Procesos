/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class ConcurrentList {
    
    private final int[] list;
    private int giveQueue, takeQueue;
    ReentrantLock padlock;
    ReentrantReadWriteLock padlockReadWrite;
    
    public ConcurrentList() {
        this.list = new int[20];
        this.padlock = new ReentrantLock();
        this.padlockReadWrite = new ReentrantReadWriteLock(true);
        this.giveQueue = 0;
        this.takeQueue = 0;
    }
    
    public void insert(JPanel[] label, JLabel giveQueue, JLabel takeQueue) throws InterruptedException {
        try {
            this.padlockReadWrite.writeLock().lock();
            if(this.isFull()) {
                this.giveQueue++;
            } else {
                if(this.takeQueue > 0) {
                    this.takeQueue--;
                } else {
                    for(int i = 0; i < 20; i++) {
                        if(this.list[i] == 0) {
                            this.list[i] = 1;
                            label[i].setBackground(Color.orange);
                            if(this.giveQueue > 0) {
                                this.giveQueue--;
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            giveQueue.setText("Cola dejar caja: " + String.valueOf(this.giveQueue));
            takeQueue.setText("Cola llevar caja: " + String.valueOf(this.takeQueue));
            Thread.sleep(150);
            this.padlockReadWrite.writeLock().unlock();
        }
    }
    
    private boolean isFull() {
        for(int space : this.list) {
            if(space == 0) {
                return false;
            }
        }
        return true;
    }
    
    public void remove(JPanel[] label, JLabel giveQueue, JLabel takeQueue) throws InterruptedException {
        try {
            this.padlockReadWrite.writeLock().lock();
            if(this.isEmpty()) {
                this.takeQueue++;
            } else {
                if(this.giveQueue > 0) {
                    this.giveQueue--;
                } else {
                    for(int i = 0; i < 20; i++) {
                        if(this.list[i] == 1) {
                            this.list[i] = 0;
                            label[i].setBackground(Color.white);
                            if(this.takeQueue > 0) {
                                this.takeQueue--;
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            giveQueue.setText("Cola dejar caja: " + String.valueOf(this.giveQueue));
            takeQueue.setText("Cola llevar caja: " + String.valueOf(this.takeQueue));
            Thread.sleep(150);
            this.padlockReadWrite.writeLock().unlock();
        }
    }
    
    private boolean isEmpty() {
        for(int space : this.list) {
            if(space == 1) {
                return false;
            }
        }
        return true;
    }
}
