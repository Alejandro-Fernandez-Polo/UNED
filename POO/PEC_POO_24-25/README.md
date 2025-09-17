# Proyecto POO - Curso 2024-2025

## Autor
- **Nombre:** Alejandro Fernandez Polo

## Descripción
Este proyecto es un sistema de gestión de movilidad para una flota de vehículos eléctricos, desarrollado como parte de la Práctica de Programación Orientada a Objetos (POO) de la UNED. Permite la gestión de usuarios, vehículos, tarifas, mantenimientos y asignaciones, aplicando principios de POO como herencia, polimorfismo y encapsulamiento.

## Estructura de Clases
- **Persona (abstracta):** Base para Usuario y Empleado.
- **Usuario (abstracta):** Hereda de Persona. Subclases: UsuarioEstandar, UsuarioPremium.
- **Empleado (abstracta):** Hereda de Persona. Subclases: Administrador, Mecánico, Mantenimiento.
- **Vehiculo (abstracta):** Subclases: Bicicleta, Patinete, Moto.
- **Base:** Representa una estación de vehículos.
- **Alquiler:** Registra los alquileres de vehículos.
- **Factura:** Registra operaciones de mantenimiento.
- **Tarifa:** Define precios y descuentos.
- **SistemaGestion:** Singleton que gestiona el estado global.
- **Gestores:** Clases para gestionar usuarios, administradores, mecánicos y mantenimientos.
- **Movilidad:** Clase principal que inicia la aplicación.

## Principales Funcionalidades
- Gestión de usuarios y empleados.
- Gestión y asignación de vehículos eléctricos.
- Validación de entradas y manejo de excepciones.
- Registro de alquileres y cálculo de importes.
- Promoción de usuarios estándar a premium.
- Menús interactivos para cada tipo de usuario.

## Decisiones de Diseño
- Uso de Singleton para la gestión global.
- Separación de responsabilidades mediante gestores.
- Colecciones dinámicas con ArrayList.
- Modularidad y encapsulamiento.
- Validaciones estrictas en constructores y setters.

## Notas
- El sistema está diseñado para ser modular y mantenible.
- Por defecto, la ciudad tiene límites de coordenadas configurables.
- Solo existe un administrador en la aplicación.
- Las bicicletas y patinetes no tienen coordenadas propias, dependen de la base.

## Documentación
Para más detalles, consulta el archivo `Memoria_AlejandroFernandezPolo.pdf` incluido en esta carpeta.
