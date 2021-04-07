# Manual Tecnico Centro de Acopio

## Concurrencia y Paralelismo
- Varias personas llegando a dejar cajas al mismo tiempo.
- Varias personas llegando a traer cajas al mismo tiempo.

```java
for(int i = 0; i < 500; i++) {
    int random = (int)(Math.random() * 2 + 1);
    threadPool.execute(new OperationTask(random, list, this.label, this.giveQueue, this.takeQueue));
}
```

## Comunicacion
Para la comunicación entre procesos se manejan dos contadores los cuales representan la cola de llegada (cola para dejar cajas) y la cola de salida (cola para llevar cajas) y un arreglo de enteros el cual representa los 20 espacios disponibles para colocar las cajas.

Al momento de alguien llegar a dejar una caja hace uso tanto de los contadores como del arreglo para saber si hay algún espacio disponible, si hay cola o si debe de esperar a que haya espacios, al igual que para llegar a traer una caja, solo que para traer una caja es para saber si hay cajas disponibles, si hay cola o si debe de esperar a que hayan cajas.

```java
private final int[] list;
private int giveQueue, takeQueue;
```

## Sincronizacion
Para la sincronizacion se usa un bloqueo de lectura y escritura ```this.padlockReadWrite.writeLock().lock();``` al momento de iniciar las operaciones de una persona tanto de llegada como de salida y este bloqueo se libera al finalizar todas las operaciones respectivas.

```java
this.padlockReadWrite.writeLock().lock();
this.padlockReadWrite.writeLock().unlock();
```


## Posibles situaciones erroneas
Estas situaciones erroneas se han solucionado gracias al bloqueo realizado durante la transaccion.

- Falsa lectura al querer saber si un espacio esta ocupado o no.
- Falsa lectura al momento de querer saber cuantas personas hay en cada cola.
- Sobreescritura de algun posible espacio.
- Soobrescritura de algun contador de las colas.
  
## Variables compartidas

```java
private final int[] list;
private int giveQueue, takeQueue;
```
