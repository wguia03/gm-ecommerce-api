## GM Ecommerce API
### Notas
- API REST desarrollada para un Ecommerce orientado a la venta de productos de cómputo
- Arquitectura en capas: Presentación, dominio y acceso a datos
- Ejemplo: https://computactus.com.pe/web/marcas

### Modelo Entidad - Relación
![ER-diagram](./ecommerce-db.drawio.png)

### Requerimientos
- Diferentes modalidades de pago (tarjeta de crédito, débito, paypal)
- Diferentes modalidades de envío (a domicilio, punto de entrega, retiro en sucursal)
- Carrito de compras
- Registro de historial de compras
- Categorias de productos:
    - Almacenamiento
    - Sonido
    - Cámaras
    - Case
    - Fuente de poder
    - Periféricos
    - Impresoras
    - Laptops
    - Memorias
    - Monitores
    - Placa madre
    - Procesadores
    - Otros

### Tech Stack
- Java 17
- Spring Framework 6
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL