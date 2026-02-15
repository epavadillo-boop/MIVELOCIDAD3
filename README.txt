
MIVELOCIDAD3 - Sensor Virtual de Velocidad para Android


Autor: ELISA PAREJO VADILLO
Fecha: 15/02/2026
Repositorio: https://github.com/epavadillo-boop/MIVELOCIDAD3


1. DESCRIPCION GENERAL


MIVELOCIDAD es un sensor virtual que utiliza el acelerometro del dispositivo 
Android para determinar la velocidad y clasificarla en 7 estados diferentes.

Rangos de velocidad y estados:

---------------------------------------------------------------------
| Rango (km/h) | Estado                | Color                      |
---------------------------------------------------------------------
| 0 - 1        | PARADO                | Rojo (#FF0000)             |
| 1 - 4        | CAMINANDO             | Amarillo (#FFFF00)         |
| 4 - 6        | MARCHANDO             | Verde claro (#ADFF2F)      |
| 6 - 12       | CORRIENDO             | Verde (#00FF00)            |
| 12 - 25      | SPRINT                | Naranja (#FFA500)          |
| 25 - 170     | VEHICULO TERRESTRE    | Morado (#800080)           |
| > 170        | VEHICULO AEREO        | Gris (#808080)             |
---------------------------------------------------------------------



2. ARQUITECTURA DE LA SOLUCION


Patrones de Diseño Implementados:


2.1 PATRON STATE (Maquina de Estados)

- Archivos: patrones.state.* (7 clases)
- Descripcion: Cada estado de velocidad es una clase independiente que implementa la interfaz Estado
- Estados implementados: Parado, Caminando, Marchando, Corriendo, Sprint, VehiculoTerrestre, VehiculoAereo
- Ventaja: Añadir nuevos estados no requiere modificar el código existente


2.2 PATRON OBSERVER (Actualizacion en tiempo real)

- Archivos: patrones.observer.* (4 clases)
- Descripcion: El sensor notifica automaticamente a la interfaz de usuario cuando cambia la velocidad o el estado
- Componentes: Observable (clase abstracta), Observador (interfaz), PantallaUI, Logger


2.3 PATRON STRATEGY (Filtrado de datos)

- Archivos: patrones.strategy.* (4 clases)
- Descripcion: Diferentes algoritmos para filtrar el ruido del acelerometro
- Estrategias disponibles: FiltroPasaBajos, FiltroKalman, FiltroMediana


2.4 SENSOR ANDROID DIRECTO

- Archivo: SensorAndroid.java
- Descripcion: Implementacion directa del sensor usando SensorManager de Android
- Ventaja: Codigo mas simple y facil de mantener, sin complejidad multiplataforma



3. BANDAS MUERTAS IMPLEMENTADAS


Para evitar cambios de estado inestables, se han implementado bandas muertas:

---------------------------------------------------------------------
| Transicion                 | Tiempo minimo | Histeresis | Archivo |
---------------------------------------------------------------------
| CAMINANDO -> MARCHANDO     | 1000 ms       | 0.3 km/h   | EstadoCaminando.java |
| MARCHANDO -> CORRIENDO     | 1000 ms       | 0.3 km/h   | EstadoMarchando.java |
| CORRIENDO -> SPRINT        | 1500 ms       | 0.5 km/h   | EstadoCorriendo.java |
| SPRINT -> CORRIENDO        | 500 ms        | 0.5 km/h   | EstadoSprint.java |
| VEHICULO TERRESTRE -> SPRINT | 2000 ms     | 1.0 km/h   | EstadoVehiculoTerrestre.java |
---------------------------------------------------------------------



4. INTERFAZ DE USUARIO


Componentes de la interfaz:

- MainActivity.java: Actividad principal que gestiona la UI y el bucle de actualizacion
- activity_main.xml: Layout con los siguientes elementos:
  * TextView grande para mostrar el estado actual (cambia de color segun el estado)
  * TextView para mostrar la velocidad numerica
  * Button para reiniciar el sensor

Colores asociados a cada estado:

-------------------------
| Estado            | Color |
-------------------------
| PARADO            | Rojo |
| CAMINANDO         | Amarillo |
| MARCHANDO         | Verde claro |
| CORRIENDO         | Verde |
| SPRINT            | Naranja |
| VEHICULO TERRESTRE| Morado |
| VEHICULO AEREO    | Gris |
-------------------------



5. FUNCIONAMIENTO TECNICO


SensorAndroid.java:
- Clase que encapsula toda la logica de comunicacion con el acelerometro nativo de Android
- Implementa SensorEventListener para recibir eventos del sensor
- Proporciona metodos para leer los ejes X, Y, Z
- Gestiona el ciclo de vida del sensor (iniciar/detener)

Ciclo de actualizacion:
1. Un Handler ejecuta un Runnable cada 100ms
2. El Runnable llama al metodo actualizar() del sensor principal
3. Se leen los ejes X, Y, Z del acelerometro mediante SensorAndroid
4. Se aplica el filtro seleccionado (Strategy)
5. Se calcula la velocidad mediante integracion
6. La maquina de estados (State) determina el nuevo estado
7. Se notifica a los observadores (Observer)
8. La UI se actualiza automaticamente

Calculo de velocidad:
- Se elimina el efecto de la gravedad (9.81 m/s²)
- Se integra la aceleracion para obtener velocidad
- Se convierte de m/s a km/h



6. ESTRUCTURA DEL PROYECTO


MIVELOCIDAD/
├── app/
│   ├── src/main/
│   │   ├── java/com.elisaparejo.mivelocidad/
│   │   │   ├── MainActivity.java
│   │   │   ├── MIVELOCIDAD.java
│   │   │   ├── SensorAndroid.java
│   │   │   └── patrones/
│   │   │       ├── state/                 # 8 archivos
│   │   │       │   ├── Estado.java
│   │   │       │   ├── EstadoParado.java
│   │   │       │   ├── EstadoCaminando.java
│   │   │       │   ├── EstadoMarchando.java
│   │   │       │   ├── EstadoCorriendo.java
│   │   │       │   ├── EstadoSprint.java
│   │   │       │   ├── EstadoVehiculoTerrestre.java
│   │   │       │   └── EstadoVehiculoAereo.java
│   │   │       │
│   │   │       ├── observer/               # 4 archivos
│   │   │       │   ├── Observable.java
│   │   │       │   ├── Observador.java
│   │   │       │   ├── PantallaUI.java
│   │   │       │   └── Logger.java
│   │   │       │
│   │   │       └── strategy/               # 4 archivos
│   │   │           ├── EstrategiaFiltro.java
│   │   │           ├── FiltroPasaBajos.java
│   │   │           ├── FiltroKalman.java
│   │   │           └── FiltroMediana.java
│   │   │
│   │   └── res/layout/
│   │       └── activity_main.xml
│   └── build.gradle.kts
├── gradle/
└── README.md

Total de archivos Java: 17 clases


7. CARACTERISTICAS IMPLEMENTADAS


------------------------------------------------
| Caracteristica                 | Estado       |
------------------------------------------------
| 7 estados de velocidad         | Implementado |
| Patron State                   | Implementado |
| Patron Observer                | Implementado |
| Patron Strategy                | Implementado |
| Sensor Android nativo          | Implementado |
| Bandas muertas (5 transiciones)| Implementado |
| Boton de reinicio              | Implementado |
| Sistema de logs                | Implementado |
| Interfaz grafica con colores   | Implementado |
| Actualizacion en tiempo real   | Implementado |
------------------------------------------------



8. PRUEBAS REALIZADAS


- Funciona en dispositivos Android reales
- Cambios de estado correctos en los umbrales de velocidad
- Bandas muertas funcionando según especificacion
- Actualizacion en tiempo real cada 100ms
- Boton de reinicio funcional
- Sin fallos de memoria (Handler detenido en onDestroy)



9. REQUISITOS TECNICOS


- Android minimo: API 24 (Android 7.0)
- Lenguaje: Java
- IDE: Android Studio
- Hardware requerido: Acelerometro en el dispositivo



10. NOTAS


El proyecto demuestra el uso correcto de tres patrones de diseño:

1. STATE - Maquina de estados con 7 estados
2. OBSERVER - Comunicacion sensor-UI en tiempo real
3. STRATEGY - Filtros intercambiables para el sensor

El proyecto se ha realizado para Android, utilizando una clase SensorAndroid que accede directamente al SensorManager nativo de Android.

Las bandas muertas son configurables y estan documentadas. La aplicacion es completamente funcional en dispositivos Android reales con acelerometro.

================================================
11. COMO EJECUTAR
================================================

1. Abrir el proyecto en Android Studio
2. Conectar un dispositivo Android con depuracion USB activada
3. Hacer clic en Run (triangulo verde)
4. La app se instalara automaticamente en el dispositivo

================================================

Alumno: [Tu nombre]
Email: [tu email]
GitHub: [URL de tu repositorio]
Fecha de entrega: [fecha]


================================================
FIN DEL DOCUMENTO
================================================