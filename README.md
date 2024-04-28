## GM Ecommerce API
- API REST para un e-commerce orientado a la venta de dispositivos de cómputo
- Arquitectura en capas
- Integración con sistemas externos
- Autenticación y autorización con JWT
- Roles: Administrador, cliente e invitado
- Ejemplo: https://computactus.com.pe/web/marcas

### Modelo Entidad - Relación
![ER-diagram](./ecommerce-db.drawio.png)


### Categorías de productos
1. Almacenamiento
   1. HDD
   2. SSD
   3. USB
2. Case
3. Laptop
4. Periféricos
   1. Teclado
   2. Mouse
   3. Monitor
   4. Impresora
   5. Parlante
   6. Micrófono
   7. Webcam
5. Procesador
6. RAM
7. Otros

### Funcionalidades - Cliente
- [x] Buscar productos
- [x] Agregar y eliminar productos del carrito
- [ ] Elegir una modalidad de pago y envío
- [ ] Realizar compra
- [ ] Revisar estado de compra

### Funcionalidades - Administrador
- [x] Agregar, editar y eliminar productos
- [x] Gestionar inventario de productos
- [ ] Gestionar pedidos de clientes

### Tech Stack
- Java 21
- Spring Framework
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Swagger
