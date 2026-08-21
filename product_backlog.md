# Product Backlog - MIVELOCIDAD3

## REQ-001 - Cálculo de Velocidad
- **Descripción**: El sistema debe calcular la velocidad a partir de los datos del acelerómetro.
- **Estado**: Aprobado
- **Criterios de Aceptación**: La velocidad calculada debe tener un error menor al 5% respecto a un sensor patrón.

## REQ-002 - Visualización de Estados
- **Descripción**: La interfaz debe mostrar el estado (Parado, Caminando, etc.) y el color asociado.
- **Estado**: Aprobado
- **Criterios de Aceptación**: Al cambiar la velocidad simulada, el color de fondo cambia en menos de 200ms.

## REQ-003 - Filtro de Señal
- **Descripción**: Implementar un filtro paso bajo para reducir el ruido del acelerómetro.
- **Estado**: Aprobado
- **Criterios de Aceptación**: La señal filtrada debe reducir la varianza en al menos un 70%.

## REQ-004 - Historial de Velocidades
- **Descripción**: Guardar un registro de las velocidades calculadas en el almacenamiento interno.
- **Estado**: Propuesto
- **Criterios de Aceptación**: Pendiente de definir.

## REQ-005 - Soporte para Modo Oscuro
- **Descripción**: La aplicación debe adaptar la interfaz al tema oscuro del sistema.
- **Estado**: Sin definir
- **Criterios de Aceptación**: No especificados.
