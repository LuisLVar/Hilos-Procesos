package barberodormilon;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.swing.JLabel;

public class Cliente implements Runnable{
    
    ReentrantReadWriteLock candadoLecturaEscritura = new ReentrantReadWriteLock(true);
    private Servidor silla;
    private BarberoDormilon barbero;
    JLabel lblEspera;
    JLabel lblSilla;
    
    public Cliente(Servidor silla, BarberoDormilon barbero, JLabel lblEspera, JLabel lblSilla){
        System.out.println("Nuevo cliente");
        this.barbero = barbero;
        this.silla = silla;
        this.silla.nuevoCliente();
        this.lblEspera = lblEspera;
        this.lblSilla = lblSilla;
        lblEspera.setText("Sala de espera: "+this.silla.getColaEspera());
    }
    
    @Override
    public void run() {
        try {
            this.candadoLecturaEscritura.readLock().lock();
            if(barbero.isDurmiendo()){
                despertar();  
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{   
            this.candadoLecturaEscritura.readLock().unlock();
        }
        while(true){
//            System.out.println("Intentando cortarme el pelo...");

            try {
                this.candadoLecturaEscritura.readLock().lock();
//                System.out.println(silla.isServidor());
                if(!silla.isServidor()){
     //               System.out.println("----------  cortarme el pelo -----------");

                    silla.cortarPelo(lblEspera, lblSilla);
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }finally{
                this.candadoLecturaEscritura.readLock().unlock();
            }
        }
        
        

        
        

    }
    
    public void despertar(){
        System.out.println("Despertando barbero");
        synchronized(barbero){
            barbero.notify();
        }
    }
    
}
