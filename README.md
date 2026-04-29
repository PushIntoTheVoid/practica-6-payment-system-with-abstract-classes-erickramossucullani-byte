[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/bcY4nwiu)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=23764341&assignment_repo_type=AssignmentRepo)
# PaymentSystemWithAbstractClasses

Plantilla base para la práctica **SIS-211 G2**.

## 1. Información general

- **Asignatura:** SIS-211
- **Grupo:** G2
- **Práctica:** Sistema de Pagos con Clases Abstractas
- **Lenguaje sugerido:** Java

## 2. Descripción

Este proyecto consiste en modelar un sistema de pagos aplicando Programación Orientada a Objetos, con énfasis en:

- Clases abstractas
- Herencia
- Polimorfismo
- Sobrescritura de métodos

## 3. Objetivos

- Diseñar una jerarquía de clases para distintos métodos de pago.
- Implementar comportamiento común mediante una clase abstracta.
- Especializar la lógica de cada tipo de pago en clases concretas.
- Probar el sistema con diferentes escenarios.

## 4. Requisitos mínimos

- Definir una clase abstracta base (ejemplo: `PaymentMethod`).
- Incluir al menos **3** clases concretas (ejemplo: tarjeta, transferencia, billetera digital).
- Implementar métodos comunes y específicos por tipo de pago.
- Mostrar resultados de ejecución en consola.
- Manejar validaciones básicas de entrada.

## 5. Estructura sugerida

```text
PaymentSystemWithAbstractClasses/
├── src/
│   ├── core/
│   │   ├── PaymentMethod.java
│   ├── models/
│   │   ├── CreditCardPayment.java
│   │   ├── BankTransferPayment.java
│   │   ├── DigitalWalletPayment.java
│   └── Store.java
├── README.md
```

## 6. Instrucciones de ejecución

1. Clonar o descargar el repositorio.
2. Compilar el proyecto.
3. Ejecutar la clase principal (`Store` o equivalente).
4. Verificar la salida en consola con distintos tipos de pago.

> Ajustar estos pasos según el entorno usado (IDE, Maven, Gradle, etc.).

## 7. Criterios de evaluación (referenciales)

- Correcto uso de clases abstractas.
- Calidad del diseño orientado a objetos.
- Claridad y orden del código.
- Funcionamiento de casos de prueba.
- Documentación mínima en este `README`.

## 8. Entregables

- Código fuente completo.
- Evidencias de ejecución (capturas o logs).
- Diagrama de clases (opcional o según consigna).
- README actualizado.

## 9. Autor(es)

- Nombre(s): `________________________`
- Fecha: `____ / ____ / ______`

---
