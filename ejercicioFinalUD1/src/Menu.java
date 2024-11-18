package ejercicioFinalUD1.src;

import java.util.Scanner;

/**
Clase Menu que despliega un menú de opciones para gestionar los alumnos (crear, eliminar,
listar, guardar y cargar). Usa un objeto de la clase Gestor para llevar a cabo las 
operaciones correspondientes.

@author Héctor López Blanco
*/

public class Menu {

    /**
    @param opcion Opcion seleccionada.
    */
    private int opcion;

    /**
    Constructor de la clase Menu. Llama al método principal para mostrar el menú.
    */

    public Menu(){
        menu();
    }

    /**
    Metodo principal que despliega un menu y las multiples opciones que tiene.
    */

    public void menu(){
        System.out.println("\n" + "--MENU--" + "\n");
        System.out.println("0. Salir");
        System.out.println("1. Crear un alumno");
        System.out.println("2. Eliminar un alumno");
        System.out.println("3. Listar alumnos");
        System.out.println("4. Guardar en fichero de texto, binario o XML");
        System.out.println("5. Cargar desde un fichero de texto, binario o XML");
        opcionMenu();
    }
 
    /**
    Objeto Gestor que realiza las distintas operaciones.
    */
    
    Gestor gestor = new Gestor();
    
    /**
    Objeto Scanner para leer la entrada.
    */

    Scanner teclado = new Scanner(System.in);

    /**
    Metodo que pide opcion y despues ejecuta una acción depenciendo de la opcion seleccionada.
    */
    
    public void opcionMenu(){

        /**
        Metodo para pedir y recoger la opcion elegida.
        
        @return opcion elegida.
        */

        opcion = gestor.pedirOpcionMenu();
        
        /**
        Switch para determinar qué acción realizar dependiendo de la opcion seleccionada.
        El menú se vuelve a mostrar después de ejecutar cada acción, excepto cuando se 
        selecciona la opción 0.
        */

        switch (opcion) {
            case 0:
            
                // Salir
                break;

            case 1:

                // Crear un alumno
                gestor.crearAlumno();
                
                // Volver al menú después de la acción
                menu();

                // Salir
                break;


            case 2:

                // Eliminar un alumno
                gestor.eliminarAlumno();
                menu();

                // Salir
                break;


            case 3:

                // Listar alumnos
                System.out.println(gestor);
                menu();
                
                // Salir
                break;


            case 4:

                // Guardar en fichero
                gestor.guardar(gestor.pedirOpcionGuardar());
                menu();
                break;


            case 5:

                // Cargar desde fichero
                gestor.cargar(gestor.pedirOpcionCargar());
                menu();
                break;

        }

        // Cierro Scanner
        teclado.close();
    }
}