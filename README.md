# TF-Java
Tarea Final de Programación en Java

Implemente la siguiente variante del modelo del Productor-Consumidor. Se tienen n procesos productores, m procesos consumidores y un proceso llamado Intermedio, ejecutándose en a lo sumo m+n+1 computadoras.  Los mismos harán uso compartido de un buffer controlado por el proceso Intermedio (el buffer es un objeto del proceso Intermedio). Dicho buffer estará compuesto  por un arreglo de 5 enteros y una variable de estado, la cual puede tomar los valores VACIO, DESORD o ORD (vacío, desordenado, ordenado). El proceso Intermedio, a través del empleo del puerto público 7777, actuará como intermediario entre los productores y los consumidores, estableciendo una comunicaciones orientadas a conexión. En este entorno, el proceso Intermedio ante las solicitudes de comunicación entre productores y consumidores, creará un hilo por cada productor y un hilo por cada consumidor. A su vez, creará un hilo para el ordenamiento del buffer.

El proceso Intermediario brindará los siguientes servicios:

* Producir (solicitado por un productor): Recibe 5 enteros desde el productor para el llenado del buffer. Si el buffer está en estado VACIO, lo llena con valores recibidos y pone el buffer en estado DESORD. De lo contrario el hilo se queda en espera.

* Ordenar (llamado por el hilo para el ordenamiento del buffer): Si el buffer está en estado DESORD, ordena el arreglo ascendentemente y pone el buffer en estado ORD. De lo contrario el hilo se queda en espera.

* Consumir (solicitado por un consumidor): Envía los 5 enteros del buffer al consumidor de la siguiente forma. Si el buffer está en estado ORD, envía sus valores al Consumidor y pone dicho buffer en estado VACIO. De lo contrario el hilo se queda en espera.
    
Para la comunicación entre los productores y los consumidores, el proceso Intermedio debe estar en ejecución, escuchando solicitudes de conexión por el puerto mencionado. Mediante el empleo de ciclos infinitos (con demoras de 500 ms en cada iteración), los hilos antes mencionados harán uso del buffer de la siguiente forma: Los productores mostrarán el mensaje “Producido:”, seguido de los elementos que serán enviados al proceso Intermedio y luego enviarán dichos elementos al proceso Intermedio. El Intermedio invocará al método ordenar y no mostrará ningún mensaje. Los consumidores solicitarán el servicio Consumir al Intermedio y mostrarán el mensaje “Consumido:”, seguido de los elementos obtenidos.
