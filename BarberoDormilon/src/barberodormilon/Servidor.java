package barberodormilon;

import java.util.Random;

/**
 *
 * @author luis_
 */
 
public class Servidor implements Runnable{
    
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
    
    public synchronized void cortarPelo(){
        try{
            servidor = true;
            colaEspera--;
            Random r = new Random();
            System.out.println("Barbero en servicio...");
            
            Thread.sleep(r.nextInt(6000-2000) + 2000);
            System.out.println("Servido");
            
            servidor = false;
        
        }catch(InterruptedException ex){
            System.out.println(ex);
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
           colaEspera++;   
    }

    @Override
    public void run() {
        while(true){
            //mantener hilo vivo
        }
    }
    
}
