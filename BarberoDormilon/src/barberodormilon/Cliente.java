package barberodormilon;

public class Cliente implements Runnable{
    
    private Servidor silla;
    private BarberoDormilon barbero;
    
    public Cliente(Servidor silla, BarberoDormilon barbero){
        System.out.println("Nuevo cliente");
        this.barbero = barbero;
        this.silla = silla;
        this.silla.nuevoCliente();
    }
    
    @Override
    public void run() {
        if(barbero.isDurmiendo()){
            despertar();
        }
        while(true){
//            System.out.println("Intentando cortarme el pelo");
           if(!silla.isServidor()){
//               System.out.println("cortarme el pelo");
               silla.cortarPelo();
               break;
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
