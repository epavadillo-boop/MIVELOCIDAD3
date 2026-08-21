# Requisitos del Proyecto MIVELOCIDAD3

| ID | Descripción | Estado | Criterios de Aceptación |
|----|-------------|--------|--------------------------|
| REQ-001 | El sistema debe calcular la velocidad a partir de los datos del acelerómetro. | Aprobado | La velocidad calculada debe tener un error menor al 5% respecto a un sensor patrón. |
| REQ-002 | La interfaz debe mostrar el estado (Parado, Caminando, etc.) y el color asociado. | Aprobado | Al cambiar la velocidad simulada, el color de fondo cambia en menos de 200ms. |
| REQ-003 | Implementar un filtro paso bajo para reducir el ruido del acelerómetro. | Aprobado | La señal filtrada debe reducir la varianza en al menos un 70%. |
| REQ-004 | Guardar un registro de las velocidades calculadas en el almacenamiento interno. | Propuesto | Pendiente de definir |
| REQ-005 | La aplicación debe adaptar la interfaz al tema oscuro del sistema. | Sin definir | No especificados |
