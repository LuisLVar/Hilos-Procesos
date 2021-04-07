package barberodormilon;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.swing.JLabel;

public class BarberoDormilon implements Runnable{
    
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    ReentrantReadWriteLock candadoLecturaEscritura = new ReentrantReadWriteLock(true);
    private boolean durmiendo;
    
    private Servidor silla;
    
    public boolean activo;
    JLabel lblBarbero;
    
    public BarberoDormilon(Servidor silla){
        this.durmiendo = false;
        this.silla = silla;
        activo = true;
    }
    
    public void setLblBarbero(JLabel lblBarbero){
       this.lblBarbero = lblBarbero;
    }
    
    public void dormir(){
        try {
            System.out.println("Barbero durmiendo...");
            durmiendo = true;
            lblBarbero.setText("Barbero durmiendo");
            silla.setServidor(false);
            synchronized(this){
                wait();
            }
            System.out.println("Barbero Despierta");
            durmiendo = false;
            lblBarbero.setText("Barbero trabajando");
            silla.setServidor(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isDurmiendo() {
        return durmiendo;
    }

    public void setDurmiendo(boolean durmiendo) {
        this.durmiendo = durmiendo;
    }
    
    @Override
    public void run() {
        while(activo){
//            //mantener hilo vivo
            try {
//                System.out.println(silla.isServidor());
//                System.out.println(silla.getColaEspera());
//                System.out.println(durmiendo);
                this.candadoLecturaEscritura.writeLock().lock();
                if(!silla.isServidor() && silla.getColaEspera() == 0 && !durmiendo){
                    System.out.println("Inicia a dormir");
                    dormir();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                this.candadoLecturaEscritura.writeLock().unlock();
            }
        }
    }

}
