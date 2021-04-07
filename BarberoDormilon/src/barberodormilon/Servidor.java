package barberodormilon;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.swing.JLabel;

/**
 *
 * @author luis_
 */
 
public class Servidor implements Runnable{
    
    ReentrantReadWriteLock candadoLecturaEscritura = new ReentrantReadWriteLock(true);
    private int nSillas;
    
    // Silla del barbero
    private boolean servidor;
    
    private int colaEspera;
    
    public boolean activo;
    
    Servidor(){
        nSillas = 1;
        servidor = false;
        colaEspera = 0;
        activo = true;    }
    
    public synchronized void cortarPelo(JLabel lblEspera, JLabel lblSilla){
        try{
//            this.candadoLecturaEscritura.writeLock().lock();
            servidor = true;
            lblSilla.setText("Estado: Trabajando");
            
            colaEspera--;
            lblEspera.setText("Sala de espera: "+this.getColaEspera());
//            this.candadoLecturaEscritura.writeLock().unlock();
            Random r = new Random();
            System.out.println("Barbero en servicio...");
            
            Thread.sleep(r.nextInt(6000-2000) + 2000);
            System.out.println("Servido");
            
     
            servidor = false; 
            lblSilla.setText("Estado: ocio");  
            Thread.sleep(500);
            
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        finally{
            
        }
    
    }
    
    public int getColaEspera(){
        return colaEspera;
    }
    
    public boolean isServidor() {
        return servidor;
    }

    public void setServidor(boolean servidor) {
        this.servidor = servidor;
    }
    
    public void nuevoCliente(){
        try {
            this.candadoLecturaEscritura.writeLock().lock();
            colaEspera++; 
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            this.candadoLecturaEscritura.writeLock().unlock();
        }
             
    }

    @Override
    public void run() {
        while(true){
            //mantener hilo vivo
        }
    }
    
}
