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
            this.padlockReadWrite.readLock().lock();
            this.padlockReadWrite.writeLock().lock();
            
            System.out.println("boxes: " + String.valueOf(this.list.size()));
            System.out.println("give: " + String.valueOf(this.giveQueue));
            System.out.println("take: " + String.valueOf(this.takeQueue));
            
            if(this.list.size() == 20) {
                this.giveQueue++;
            } else {
                this.list.add(1);
                if(this.giveQueue > 0) {
                    this.giveQueue--;
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            this.padlockReadWrite.readLock().unlock();
            this.padlockReadWrite.writeLock().unlock();
        }
    }
    
    public void remove() {
        try {
            this.padlockReadWrite.readLock().lock();
            this.padlockReadWrite.writeLock().lock();
            
            System.out.println("boxes: " + String.valueOf(this.list.size()));
            System.out.println("give: " + String.valueOf(this.giveQueue));
            System.out.println("take: " + String.valueOf(this.takeQueue));
            
            if(this.list.isEmpty()) {
                this.takeQueue++;
            } else {
                this.list.remove();
                if(this.takeQueue > 0) {
                    this.takeQueue--;
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            this.padlockReadWrite.readLock().unlock();
            this.padlockReadWrite.writeLock().unlock();
        }
    }
    
    public int getSize() {
        int size = -1;
        try {
            this.padlockReadWrite.readLock().lock();
            size = this.list.size();
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
             this.padlockReadWrite.readLock().unlock();
        }
        return size;
    }
}
