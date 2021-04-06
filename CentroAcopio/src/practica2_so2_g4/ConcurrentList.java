/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author javie
 */
public class ConcurrentList {
    
    private final LinkedList<Integer> list;
    private int giveQueue, takeQueue;
    ReentrantLock padlock;
    ReentrantReadWriteLock padlockReadWrite;
    
    public ConcurrentList() {
        this.list = new LinkedList<>();
        this.padlock = new ReentrantLock();
        this.padlockReadWrite = new ReentrantReadWriteLock(true);
        this.giveQueue = 0;
        this.takeQueue = 0;
    }
    
    public void insert() {
        try {
            this.padlockReadWrite.writeLock().lock();
            if(this.list.size() == 20) {
                this.giveQueue++;
            } else {
                if(this.takeQueue > 0) {
                    this.takeQueue--;
                } else {
                    this.list.add(1);
                    if(this.giveQueue > 0) {
                        this.giveQueue--;
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("DEJAR CAJA");
            System.out.println("cajas: " + String.valueOf(this.list.size()));
            System.out.println("cola dejar: " + String.valueOf(this.giveQueue));
            System.out.println("cola recoger: " + String.valueOf(this.takeQueue));
            System.out.println("----------------------------------------");
            this.padlockReadWrite.writeLock().unlock();
        }
    }
    
    public void remove() {
        try {
            this.padlockReadWrite.writeLock().lock();
            if(this.list.isEmpty()) {
                this.takeQueue++;
            } else {
                if(this.giveQueue > 0) {
                    this.giveQueue--;
                } else {
                    this.list.remove();
                    if(this.takeQueue > 0) {
                        this.takeQueue--;
                    }
                }
                
            }
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("RECOGER CAJA");
            System.out.println("cajas: " + String.valueOf(this.list.size()));
            System.out.println("cola dejar: " + String.valueOf(this.giveQueue));
            System.out.println("cola recoger: " + String.valueOf(this.takeQueue));
            System.out.println("----------------------------------------");
            this.padlockReadWrite.writeLock().unlock();
        }
    }
    
    public int getSize() {
        int size = -1;
        try {
            //this.padlockReadWrite.readLock().lock();
            size = this.list.size();
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
             //this.padlockReadWrite.readLock().unlock();
        }
        return size;
    }
    
    public void printList() {
        this.list.forEach((element) -> {
            System.out.println(element + ", ");
        });
        System.out.println("\n");
    }
}
