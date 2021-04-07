# Space Invaders
Se deberá desarrollar un video juego similar al clásico “Space Invaders” de 1978, pero en
esta ocasión será para 2 jugadores, se aconseja ver videos, referencias o directamente
jugar un poco con el videojuego en internet para tener una mejor idea de lo que se
solicita.
Básicamente se tendrá una pantalla en la cual en la parte superior irán apareciendo naves
enemigas que irán descendiendo por la pantalla, cada una de ellas con “2 puntos de vida”,
estas deberán ir apareciendo de forma aleatoria en la parte superior de la pantalla y cada
25 segundos irán apareciendo con más frecuencia. En la parte inferior se tendrán 2 naves,
las cuales son aliadas y controladas por los jugadores, estas naves solamente se podrán
mover de forma horizontal. Los comandos serán las siguientes teclas, para la primera
nave: “A” - Izquierda , “S” - Disparar, “D” - Derecha; para la segunda nave: “J” - Izquierda,
“K” - Disparar , “L” - Derecha.

![alt-text](https://github.com/LuisLVar/Hilos-Procesos.git/blob/master/SpaceInvaders/img/space.png)

## Código Concurrente y Paralelo
El código concurrente se ejecuta en cada actualización de la pantalla

```java
    collisionDetection.Detect();
    eventResolution.Resolve();
    vfxManager.Update();
    Update();
```

Variables compartidas entre procesos de alienigenas y naves espaciales
```java
    public boolean IsGameOver = false; // Indica el final del juego 
    public boolean fin1 = false; // fin de juego del jugador 1
    public boolean fin2 = false; // fin de juego del jugador 2
```

## Comunicación y Sincronización entre procesos
Se cuenta con un metodo que se llama al inicio, es para que el juego pueda compartir todos los recursos como son las vidas, el tiempo, cantidad de enemigos y que los subprocesos accedan sin resultados erroneos. Donde Activa el manejo de Hilo.

```java
    public synchronized void start() {
        this.running = true;
        new Thread(this).start();
    }
```
La situación en DeadLocks se complico cuando en los procesos paralelos que verifican un choche de diferente objeto, se topan con con el hecho de tener bloquear una nave para que solo la otra continue con todos los recuersos
```java
    Detect(){
        for (InvaderProjectile invaderProjectile : game.allInvaderProjectiles){
            if(IsShapeOutsideWindow(invaderProjectile))
                eventResolution.Push(new RemoveInvaderProjectileOutOfWindow(invaderProjectile));
            else if(areTwoShapesInCollision(game.heroShip, invaderProjectile))
                if(game.heroShip.vida1 == null){
                    game.heroShip.vida1 = invaderProjectile;
                }else{
                    if(!game.heroShip.vida1.equals(invaderProjectile)){
                        
                        if(game.heroShip.vida2 == null){
                            game.heroShip.vida2 = invaderProjectile;
                        }else{
                            if(!game.heroShip.vida2.equals(invaderProjectile)){

                                if(game.heroShip.vida3 == null){
                                    game.heroShip.vida3 = invaderProjectile;
                                }else{
                                    eventResolution.Push(new EndGame(false));
                                    fin1 = true;
                                }
                                
                            }
                        }
                        
                    }
                }
                    
        }
    }
```
   