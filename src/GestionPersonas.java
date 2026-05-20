import java.util.ArrayList;
import java.util.Scanner;

// Clase que contiene toda la logica CRUD y el menu interactivo
public class GestionPersonas {

    // ArrayList que almacena objetos de tipo Persona (estudiantes y docentes)
    private ArrayList<Persona> listaPersonas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // ─── METODO AUXILIAR: Validar que un texto solo tenga letras y espacios ──
    private boolean soloLetras(String texto) {
        return texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    // ─── METODO AUXILIAR: Validar que la cedula solo tenga digitos ───────────
    private boolean soloDigitos(String texto) {
        return texto.matches("[0-9]+");
    }

    // ─── METODO: Registrar persona ───────────────────────────────────────────
    public void registrarPersona() {
        System.out.println("\nSeleccione tipo:");
        System.out.println("1. Estudiante");
        System.out.println("2. Docente");
        System.out.print("Opcion: ");

        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar solo numeros.");
            return;
        }

        if (tipo != 1 && tipo != 2) {
            System.out.println("Error: opcion invalida. Intente nuevamente.");
            return;
        }

        // Validar cedula: no vacia y solo digitos
        System.out.print("Ingrese cedula: ");
        String cedula = scanner.nextLine().trim();
        if (cedula.isEmpty()) {
            System.out.println("Campo obligatorio.");
            return;
        }
        if (!soloDigitos(cedula)) {
            System.out.println("Error: la cedula debe contener solo numeros.");
            return;
        }

        // Plus: evitar cedulas duplicadas
        for (Persona p : listaPersonas) {
            if (p.getCedula().equals(cedula)) {
                System.out.println("Error: ya existe una persona registrada con esa cedula.");
                return;
            }
        }

        // Validar nombre: no vacio y solo letras
        System.out.print("Ingrese nombre completo: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Campo obligatorio.");
            return;
        }
        if (!soloLetras(nombre)) {
            System.out.println("Error: el nombre solo debe contener letras.");
            return;
        }

        System.out.print("Ingrese edad: ");
        int edad;
        try {
            edad = Integer.parseInt(scanner.nextLine().trim());
            if (edad <= 0) {
                System.out.println("Error: la edad debe ser un numero positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar solo numeros.");
            return;
        }

        if (tipo == 1) {
            System.out.print("Ingrese carrera: ");
            String carrera = scanner.nextLine().trim();
            if (carrera.isEmpty()) {
                System.out.println("Campo obligatorio.");
                return;
            }
            if (!soloLetras(carrera)) {
                System.out.println("Error: la carrera solo debe contener letras.");
                return;
            }
            listaPersonas.add(new Estudiante(cedula, nombre, edad, carrera));

        } else {
            System.out.print("Ingrese asignatura: ");
            String asignatura = scanner.nextLine().trim();
            if (asignatura.isEmpty()) {
                System.out.println("Campo obligatorio.");
                return;
            }
            if (!soloLetras(asignatura)) {
                System.out.println("Error: la asignatura solo debe contener letras.");
                return;
            }
            listaPersonas.add(new Docente(cedula, nombre, edad, asignatura));
        }

        System.out.println("Registro agregado correctamente.");
    }

    public void mostrarRegistros() {
        if (listaPersonas.isEmpty()) {
            System.out.println("\nNo hay registros almacenados.");
            return;
        }

        System.out.println("\n===== LISTA DE PERSONAS =====");

        for (int i = 0; i < listaPersonas.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            listaPersonas.get(i).mostrarDatos();
        }

        int totalEstudiantes = 0;
        int totalDocentes = 0;
        for (Persona p : listaPersonas) {
            if (p instanceof Estudiante) {
                totalEstudiantes++;
            } else if (p instanceof Docente) {
                totalDocentes++;
            }
        }
        System.out.println("\nTotal estudiantes: " + totalEstudiantes);
        System.out.println("Total docentes: " + totalDocentes);
        System.out.println("==============================");
    }

    public void actualizarRegistro() {
        if (listaPersonas.isEmpty()) {
            System.out.println("\nNo hay registros para actualizar.");
            return;
        }

        mostrarRegistros();

        System.out.print("\nIngrese el numero del registro a actualizar: ");
        int indice;
        try {
            indice = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar solo numeros.");
            return;
        }

        if (indice < 0 || indice >= listaPersonas.size()) {
            System.out.println("Registro no encontrado.");
            return;
        }

        Persona personaActual = listaPersonas.get(indice);

        System.out.print("Ingrese nuevo nombre completo: ");
        String nuevoNombre = scanner.nextLine().trim();
        if (nuevoNombre.isEmpty()) {
            System.out.println("Campo obligatorio.");
            return;
        }
        if (!soloLetras(nuevoNombre)) {
            System.out.println("Error: el nombre solo debe contener letras.");
            return;
        }

        System.out.print("Ingrese nueva edad: ");
        int nuevaEdad;
        try {
            nuevaEdad = Integer.parseInt(scanner.nextLine().trim());
            if (nuevaEdad <= 0) {
                System.out.println("Error: la edad debe ser un numero positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar solo numeros.");
            return;
        }

        personaActual.setNombreCompleto(nuevoNombre);
        personaActual.setEdad(nuevaEdad);

        if (personaActual instanceof Estudiante) {
            System.out.print("Ingrese nueva carrera: ");
            String nuevaCarrera = scanner.nextLine().trim();
            if (nuevaCarrera.isEmpty()) {
                System.out.println("Campo obligatorio.");
                return;
            }
            if (!soloLetras(nuevaCarrera)) {
                System.out.println("Error: la carrera solo debe contener letras.");
                return;
            }
            ((Estudiante) personaActual).setCarrera(nuevaCarrera);

        } else if (personaActual instanceof Docente) {
            System.out.print("Ingrese nueva asignatura: ");
            String nuevaAsignatura = scanner.nextLine().trim();
            if (nuevaAsignatura.isEmpty()) {
                System.out.println("Campo obligatorio.");
                return;
            }
            if (!soloLetras(nuevaAsignatura)) {
                System.out.println("Error: la asignatura solo debe contener letras.");
                return;
            }
            ((Docente) personaActual).setAsignatura(nuevaAsignatura);
        }

        listaPersonas.set(indice, personaActual);
        System.out.println("Registro actualizado correctamente.");
    }

    public void eliminarRegistro() {
        if (listaPersonas.isEmpty()) {
            System.out.println("\nNo hay registros para eliminar.");
            return;
        }

        mostrarRegistros();

        System.out.print("\nIngrese el numero del registro a eliminar: ");
        int indice;
        try {
            indice = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar solo numeros.");
            return;
        }

        if (indice < 0 || indice >= listaPersonas.size()) {
            System.out.println("Registro no encontrado.");
            return;
        }

        System.out.print("¿Esta seguro que desea eliminar este registro? (s/n): ");
        String confirmacion = scanner.nextLine().trim();
        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Eliminacion cancelada.");
            return;
        }

        listaPersonas.remove(indice);
        System.out.println("Registro eliminado correctamente.");
    }

    public void buscarPorCedula() {
        System.out.print("\nIngrese la cedula a buscar: ");
        String cedula = scanner.nextLine().trim();
        if (cedula.isEmpty()) {
            System.out.println("Campo obligatorio.");
            return;
        }
        if (!soloDigitos(cedula)) {
            System.out.println("Error: la cedula debe contener solo numeros.");
            return;
        }

        boolean encontrado = false;
        for (Persona p : listaPersonas) {
            if (p.getCedula().equals(cedula)) {
                System.out.println("\nPersona encontrada:");
                p.mostrarDatos();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Registro no encontrado.");
        }
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Registrar persona");
            System.out.println("2. Mostrar registros");
            System.out.println("3. Actualizar registro");
            System.out.println("4. Eliminar registro");
            System.out.println("5. Salir");
            System.out.println("6. Buscar por cedula");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar solo numeros.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarPersona();
                    break;
                case 2:
                    mostrarRegistros();
                    break;
                case 3:
                    actualizarRegistro();
                    break;
                case 4:
                    eliminarRegistro();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                case 6:
                    buscarPorCedula();
                    break;
                default:
                    System.out.println("Error: opcion invalida. Intente nuevamente.");
            }
        }
    }
}