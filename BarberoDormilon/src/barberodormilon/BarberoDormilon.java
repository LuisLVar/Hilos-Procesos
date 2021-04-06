package barberodormilon;

public class BarberoDormilon implements Runnable{
    
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    
    private boolean durmiendo;
    
    private Servidor silla;
    
    public boolean activo;
    
    public BarberoDormilon(Servidor silla){
        this.durmiendo = false;
        this.silla = silla;
        activo = true;
    }
    
    public void dormir(){
        try {
            System.out.println("Barbero durmiendo...");
            durmiendo = true;
            silla.setServidor(false);
            synchronized(this){
                wait();
            }
            System.out.println("Barbero Despierta");
            durmiendo = false;
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
            //mantener hilo vivo
            System.out.println(silla.isServidor());
            System.out.println(silla.getColaEspera());
            System.out.println(durmiendo);
            if(!silla.isServidor() && silla.getColaEspera() == 0 && !durmiendo){
                System.out.println("Inicia a dormir");
                dormir();
            }
        }
    }

}
