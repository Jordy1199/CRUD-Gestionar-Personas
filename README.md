# Sistema de Gestión de Personas - Java POO

## Descripción general
Sistema de consola desarrollado en Java que permite administrar registros de
estudiantes y docentes de una institución educativa. Aplica los principios de
Programación Orientada a Objetos: herencia, encapsulamiento, polimorfismo y
manejo de excepciones, junto con operaciones CRUD completas usando ArrayList.

---

## Estructura del proyecto

El proyecto está compuesto por 5 archivos Java:

- Persona.java → Clase padre con los atributos comunes
- Estudiante.java → Subclase que hereda de Persona, agrega carrera
- Docente.java → Subclase que hereda de Persona, agrega asignatura
- GestionPersonas.java → Lógica CRUD, validaciones y menú interactivo
- Main.java → Punto de entrada del programa

---

## Conceptos aplicados

### Herencia
La herencia permite que una clase hija reciba los atributos y métodos de una
clase padre sin necesidad de repetir el código. En este sistema:

- Persona es la clase padre con los atributos cedula, nombreCompleto y edad
- Estudiante hereda de Persona usando extends y agrega el atributo carrera
- Docente hereda de Persona usando extends y agrega el atributo asignatura

Esto significa que Estudiante y Docente automáticamente tienen cedula,
nombreCompleto y edad sin haberlos declarado en sus propias clases.

Cada subclase llama al constructor del padre usando super() para inicializar
los atributos heredados, y luego inicializa su propio atributo específico.

### Encapsulamiento
Los atributos de las clases están declarados como protected en Persona y como
private en Estudiante y Docente. Esto significa que no se pueden modificar
directamente desde fuera de la clase. Para leerlos o modificarlos se usan
métodos públicos llamados getters y setters, que son el único punto de acceso
controlado a los datos del objeto.

### Polimorfismo
El polimorfismo permite que el mismo método se comporte de forma diferente
según el tipo real del objeto. En este sistema:

- mostrarDatos() está definido en Persona pero es sobrescrito con @Override
  en Estudiante y en Docente
- El ArrayList almacena todos los objetos como tipo Persona, pero cuando se
  llama mostrarDatos() sobre cada uno, Java detecta automáticamente si es un
  Estudiante o un Docente y ejecuta la versión correcta del método

### ArrayList
El ArrayList es una estructura de datos dinámica que almacena objetos y crece
o se reduce automáticamente. Se declaró como ArrayList de tipo Persona para
poder almacenar tanto Estudiantes como Docentes en la misma lista gracias al
polimorfismo. Se usaron los siguientes métodos del ArrayList:

- add() para agregar un nuevo registro al final de la lista
- get(i) para obtener el objeto en la posición i
- set(i, objeto) para reemplazar el objeto en la posición i
- remove(i) para eliminar el objeto en la posición i y reorganizar la lista
- size() para obtener la cantidad total de elementos
- isEmpty() para verificar si la lista no tiene elementos

### Manejo de excepciones con try-catch
El try-catch permite controlar errores en tiempo de ejecución sin que el
programa se cierre. En este sistema se usa para capturar NumberFormatException,
que ocurre cuando se intenta convertir texto a número con Integer.parseInt()
y el texto contiene letras o símbolos. Cuando ocurre ese error, el catch lo
atrapa, muestra un mensaje claro al usuario y el programa continúa ejecutándose
normalmente.

---

## Operaciones CRUD

### CREATE - Registrar persona
El usuario selecciona si desea registrar un Estudiante o un Docente. Luego
ingresa cedula, nombre completo, edad y el campo específico según el tipo
elegido. El registro se agrega al ArrayList con add().

### READ - Mostrar registros
Se recorre el ArrayList con un ciclo for y se llama al método mostrarDatos()
de cada objeto. Java ejecuta automáticamente la versión correcta del método
según el tipo real del objeto. Al final se muestra el conteo total de
estudiantes y docentes registrados.

### UPDATE - Actualizar registro
El usuario selecciona el número del registro a modificar. Se obtiene el objeto
del ArrayList con get(), se actualizan sus campos usando los setters y se
confirma el reemplazo con set(). Se usa instanceof para detectar si el objeto
es Estudiante o Docente y así pedir el campo específico correspondiente.

### DELETE - Eliminar registro
El usuario selecciona el número del registro a eliminar. Se pide confirmación
antes de proceder. Si confirma, se elimina el objeto del ArrayList con remove()
y la lista se reorganiza automáticamente.

---

## Validaciones implementadas

- Opción de menú inválida: si el usuario ingresa letras, try-catch lo captura
  y muestra el mensaje de error. Si ingresa un número fuera de rango, el caso
  default del switch muestra el mensaje de opción inválida
- Campos vacíos: se verifica con isEmpty() después de aplicar trim() en cada
  campo obligatorio
- Ingreso de letras en campos numéricos: controlado con try-catch sobre
  Integer.parseInt() en edad y opciones del menú
- Ingreso de números en campos de texto: controlado con el método soloLetras()
  que usa una expresión regular para aceptar solo letras y espacios en nombre,
  carrera y asignatura
- Formato de cédula: controlado con el método soloDigitos() que acepta solo
  dígitos numéricos
- Edad negativa o cero: se verifica que la edad ingresada sea mayor a cero
- Posición inexistente: se valida que el índice esté dentro del rango del
  ArrayList antes de operar sobre él
- Cédula duplicada: antes de registrar se recorre el ArrayList para verificar
  que no exista otra persona con la misma cédula

---

## Funcionalidades adicionales implementadas

- Búsqueda de persona por cédula: recorre el ArrayList comparando cédulas con
  equals() y muestra los datos si encuentra coincidencia
- Validación de cédula duplicada al registrar: evita que se ingresen dos
  personas con la misma cédula
- Conteo de registros por tipo: al mostrar la lista indica cuántos estudiantes
  y cuántos docentes hay registrados
- Confirmación antes de eliminar: pide al usuario confirmar con s o n antes
  de proceder con la eliminación

---

## Cómo ejecutar el proyecto

1. Colocar los 5 archivos Java en la misma carpeta
2. Abrir una terminal en esa carpeta
3. Compilar con el siguiente comando:

   javac Main.java Persona.java Estudiante.java Docente.java GestionPersonas.java

4. Ejecutar con el siguiente comando:

   java Main

---

## Ejemplo de uso

Al ejecutar el programa aparece el menú principal:

   ===== MENU PRINCIPAL =====
   1. Registrar persona
   2. Mostrar registros
   3. Actualizar registro
   4. Eliminar registro
   5. Salir
   6. Buscar por cedula

Al seleccionar la opción 1 el sistema pide el tipo, luego solicita cada dato
con sus respectivas validaciones y confirma el registro si todo es correcto.

---

## Autor
### Jordy Cajas

---

Práctica integradora de Programación Orientada a Objetos

---

## Asignatura: 
Programación Orientada a Objetos
