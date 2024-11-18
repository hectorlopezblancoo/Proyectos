package ejercicioFinalUD1.src;

import java.io.Serializable;

/**
Clase Alumno que representa a un estudiante con atributos como nombre, edad, nota media y 
si es bilingüe o no. Implementa la interfaz Serializable para poder ser serializada.

@author Héctor López Blanco
*/

public class Alumno implements Serializable{

    /**
    @param nombre El nombre del alumno.
    @param edad La edad del alumno.
    @param notaMedia La nota media del alumno.
    @param bilingue Indica si el alumno es bilingüe (true si es bilingüe, false si no lo es).
    */

    private String nombre;
    private int edad;
    private float notaMedia;
    private boolean bilingue;

    /**
    Constructor para inicializar un objeto Alumno con todos los atributos.
    */

    public Alumno( String nombre,int edad, float notaMedia, boolean bilingue) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.bilingue = bilingue;
    }

    /**
    Obtiene el nombre del alumno.

    @return El nombre del alumno.
    */

    public String getNombre() {
        return nombre;
    }

    /**
    Establece el nombre del alumno.
    */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
    Obtiene la edad del alumno.
    
    @return La edad del alumno.
    */

    public int getEdad() {
        return edad;
    }

    /**
    Establece la edad del alumno.
    */

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
    Obtiene la nota media del alumno.
 
    @return La nota media del alumno.
    */

    public float getNotaMedia() {
        return notaMedia;
    }

    /**
    Establece la nota media del alumno.
    */

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    /**
    Obtiene si el alumno es bilingüe o no.
     
    @return true si el alumno es bilingüe, false si no lo es.
    */

    public boolean isBilingue() {
        return bilingue;
    }

    /**
    Establece si el alumno es bilingüe.
    */ 
     
    public void setBilingue(boolean bilingue) {
        this.bilingue = bilingue;
    }

    /**
    Obtiene los atributos del alumno.
    */

    @Override
    public String toString() {
        return "\n" + "--ALUMNO--" + "\n" + 
        "\n" + "Nombre: " + nombre + 
        "\n" + "Edad: " + edad + 
        "\n" + "Nota media: " + notaMedia + 
        "\n" + "Bilingue: " + bilingue;
    }

}