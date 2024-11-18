package ejercicioFinalUD1.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
Clase Gestor que gestiona el CRUD (Crear, Leer, Actualizar y Eliminar) de alumnos.
Permite trabajar con ficheros de texto, binarios y XML para almacenar y cargar la información de alumnos.
Tambien permite la creación y eliminación de alumnado.

@author Héctor López Blanco
*/

public class Gestor {
    
    /**
    ArrayList donde se almacenaran los alumnos.
    */

    private ArrayList<Alumno> registroAlumnos;

    /**
    Objeto Scanner para leer la entrada.
    */

    Scanner teclado = new Scanner(System.in);

    /**
    Constructor que inicializa la lista de alumnos.
    */

    public Gestor(){
        this.registroAlumnos = new ArrayList<Alumno>();
    }

    /**
    Método que solicita la opción del menú al usuario.
    Controla que la opción ingresada sea válida.

    @return La opción seleccionada por el usuario.
    */

    public int pedirOpcionMenu(){
        Boolean opcionValida = false;
        int opcion = 0;

        while(!opcionValida){
            try {
                System.out.print("\n" + "Elige una opcion: ");
                opcion = teclado.nextInt();

                if(opcion >= 0 && opcion <= 5){
                    opcionValida = true;
                }else{
                    System.out.println("Opcion no valida");
                }

            } catch (InputMismatchException e) {  
                System.out.println("Opcion no valida");
                teclado.nextLine();
            }
        }
        return opcion;
    }

    /**
    Método que crea un nuevo alumno pidiendo los datos al usuario y lo agrega a la lista.
    */

    public void crearAlumno(){

        System.out.println("\n" + "--CREACION DE ALUMNO--");       
        registroAlumnos.add(new Alumno(pedirNombre(), pedirEdad(), pedirNotaMedia(), pedirBilingue()));
        System.out.println("\n" + "ALUMNO CREADO");

    }

    /**
    Método que solicita al usuario que introduzca un nombre hasta que este sea válido.

    @return El nombre del alumno.
    */

    public String pedirNombre(){
        Boolean nombreValido = false;
        String nombre = "";

        teclado.nextLine();

        // Bucle para validar el nombre.
        while(!nombreValido){
            try {
                System.out.print( "\n" + "Introduce el nombre del alumno: ");
                nombre = teclado.nextLine();

                if (nombre.matches("[a-zA-Z]+")) {
                    nombreValido = true;
                }else{
                    System.out.println("Nombre no valido");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Nombre no valido");
                teclado.nextLine();
            } 
        }
        return nombre;
    }

    /**
    Método que solicita al usuario que introduzca la edad hasta que este sea válido.

    @return La edad del alumno.
    */

    public int pedirEdad(){
        Boolean edadValida = false;
        int edad = 0; 

        // Bucle para validar la edad.
        while(!edadValida){
            try {
                System.out.print("\n" +"Introduce la edad del alumno: ");
                edad = teclado.nextInt();
    
                if (edad < 0) {
                    System.out.println("Edad no valida");
                }else{
                    edadValida = true;
                }
    
            } catch (InputMismatchException e) {  
                System.out.println("Edad no valida");
                teclado.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Edad no valida");
            } 
        }
        return edad;
    }

    /**
    Método que solicita al usuario que introduzca la nota media hasta que este sea válido.

    @return La nota media del alumno.
    */

    public float pedirNotaMedia(){
        Boolean notaMediaValida = false;
        float notaMedia = 0; 
        
        // Bucle para validar la nota media.
        while(!notaMediaValida){
            try {
                System.out.print("\n" +"Introduce la nota media del alumno: ");
                notaMedia = teclado.nextFloat();

                if (notaMedia < 0 || notaMedia > 10) {
                    System.out.println("Nota media no valida");
                }else{
                    notaMediaValida = true;
                }
    
            } catch (InputMismatchException e) {  
                System.out.println("Nota media no valida");
                teclado.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Nota media no valida");
            }
        }
        return notaMedia;
    }

    /**
    Método que solicita al usuario que introduzca si el alumno es bilingue(true) o no(false)
    hasta que este sea válido.

    @return Si el alumno es o no bilingue.
    */

    public boolean pedirBilingue(){
        Boolean bilingueValidado = false;
        Boolean bilingue = false;

        teclado.nextLine();

        // Bucle para validar la respuesta es válida.
        while(!bilingueValidado){
            try {
                System.out.print("\n" +"Introduce si el alumno es o no bilingue: ");
                String opcion = teclado.nextLine();
    
                if (opcion.equalsIgnoreCase("si")) {
                    bilingue = true;
                    bilingueValidado = true;
                }else if(opcion.equalsIgnoreCase("no")){
                    bilingue = false;
                    bilingueValidado = true;
                }else{
                    System.out.println("Opcion no valida");
                }
            
            } catch (IllegalArgumentException e) {
                System.out.println("Opcion no valida");
            }
        }
        return bilingue;
    }

    /**
    Método que busca un alumno por nombre en el registro.

    @return El objeto Alumno si se encuentra, null en caso contrario.
    */

    public Alumno buscarAlumno(String nombre){
        for(Alumno a : registroAlumnos){
            if(a.getNombre().equals(nombre)){
                System.out.println(a);
                return a;
            }
        }
        return null;
    }

    /**
    Método que elimina un alumno del registro a partir de su nombre.
    */

    public void eliminarAlumno(){
        System.out.println("\n" + "--ELIMINACION DE ALUMNO--");
        System.out.print("\n" + "Introduce el nombre del alumno: ");

        teclado.nextLine();
        String nombre = teclado.nextLine();

        if(buscarAlumno(nombre) == null){
            System.out.println("\n" + "Alumno no encontrado");
        }else{
            registroAlumnos.remove(buscarAlumno(nombre));
            System.out.println("\n" + "ALUMNO BORRADO");
        }
    }

    /**
    Método que imprime un menu con las opciones de guardado y solicita al usuario 
    el formato en el que desea guardar los datos hasta que la opción es válida.

    @return Opción de guardado seleccionada.
    */

    public int pedirOpcionGuardar(){
        System.out.println("\n" + "--OPCIONES DE GUARDARDO--" + "\n");
        System.out.println("1. Fichero de texto");
        System.out.println("2. Fichero binario");
        System.out.println("3. Fichero XML");

        Boolean opcionValida = false;
        int opcion = 0;

        // Bucle que valida la opcion de guardado
        while(!opcionValida){
            try {
                System.out.println("\n" + "Elige una opcion: ");
                opcion = teclado.nextInt();

                if(opcion >= 1 && opcion <= 3){
                    opcionValida = true;
                }else{
                    System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
                teclado.nextLine();
            } catch (NoSuchElementException ignored){
                teclado.nextLine();
            }
        }
        return opcion;

    }

    /**
    Método que recibe la opcion de guardado y guarda el registro de alumnos en el formato elegido.
    */

    public void guardar(int opcion) {
        switch (opcion) {
            case 1:
                guardarTexto();                
                break;
            case 2:
                guardarBinario();
                break;
            case 3:
                guardarXML();
                break;
        }
    }

    /**
    Método que guarda los datos del registro de alumnos en un archivo de texto.

    @param archivo Archivo tipo txt donde de guarda el registro.
    */

    public void guardarTexto(){
        String archivo = "src/ejercicioFinalUD1/src/archivoTexto.txt";
            try {
                BufferedWriter bfw = new BufferedWriter(new FileWriter(archivo));
                    for(Alumno a : registroAlumnos){
                        bfw.write(a.getNombre());
                        bfw.write("-" + a.getEdad());
                        bfw.write("-" + a.getNotaMedia());
                        bfw.write("-" + a.isBilingue() + "\n");
                    }
                bfw.close();
            } catch (Exception e) {
                System.out.println("Error al guardar");
            } 
        
    }
    
    /**
    Método que guarda los datos del registro de alumnos en un archivo binario.

    @param archivo Archivo tipo dat donde de guarda el registro.
    */

    public void guardarBinario(){
        String archivo = "src/ejercicioFinalUD1/src/archivoBinario.dat";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            for(Alumno a : registroAlumnos){
                oos.writeObject(a);
            }
            oos.close();
        } catch (Exception e) { 
            System.out.println("Error al guardar");
        }
    }

    /**
    Método que guarda los datos del registro de alumnos en un archivo XML.

    @param archivo Archivo tipo xml donde de guarda el registro
    */

    public void guardarXML(){
        String archivo = "src/ejercicioFinalUD1/src/archivoXML.xml";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element raiz = doc.createElement("clase");
            doc.appendChild(raiz);

            for(Alumno a: registroAlumnos){
                Element alumno = doc.createElement("alumno");
                
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(a.getNombre() ));
                alumno.appendChild(nombre);

                Element edad = doc.createElement("edad");
                edad.appendChild(doc.createTextNode( "" + a.getEdad() ));
                alumno.appendChild(edad);

                Element nota = doc.createElement("nota");
                nota.appendChild(doc.createTextNode( "" + a.getNotaMedia() ));
                alumno.appendChild(nota);

                Attr attr = doc.createAttribute("bilingue");
                attr.setValue(String.valueOf(a.isBilingue()));

                alumno.setAttributeNode(attr);
                raiz.appendChild(alumno);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(archivo));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println("Error al guardar");
        } 
    }

    /**
    Método que imprime un menu con las opciones de carga y solicita al usuario 
    el formato desde el que desea cargar los datos hasta que la opción es válida.

    @return Opción de carga seleccionada.
    */

    public int pedirOpcionCargar(){
        System.out.println("\n" + "--OPCIONES DE CARGA--" + "\n");
        System.out.println("1. Fichero de texto");
        System.out.println("2. Fichero binario");
        System.out.println("3. Fichero XML");

        // Bucle que valida la opcion de guardado
        Boolean opcionValida = false;
        int opcion = 0;
        while(!opcionValida){
            try {
                System.out.println("\n" + "Elige una opcion: ");
                opcion = teclado.nextInt();

                if(opcion >= 1 && opcion <= 3){
                    opcionValida = true;
                }else{
                    System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
                teclado.nextLine();
            }
        }
        return opcion;

    }

    /**
    Método que recibe la opcion de carga y carga los alumnos en el formato elegido y 
    los guarda en el registro.
    */

    public void cargar(int opcion) {
        switch (opcion) {
            case 1:
                cargarTexto();
                break;
            case 2:
                cargarBinario();
                break;
            case 3:
                cargarXML();
                break;
        }
    }

    /**
    Método que carga los alumnos desde un archivo de texto y los guarda los alumnos en el registro.

    @param archivo Archivo tipo txt desde donde se carga los alumnos.
    */

    public void cargarTexto(){
        String nombreArchivo = "src/ejercicioFinalUD1/src/archivoTexto.txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(nombreArchivo));

            String linea;
            while((linea = bfr.readLine())!=null){
                String[] variables = linea.split("-");

                String nombre = variables[0];
                int edad = Integer.parseInt(variables[1]);
                Float notaMedia = Float.parseFloat(variables[2]);
                Boolean bilingue = Boolean.parseBoolean(variables[3]);

                registroAlumnos.add(new Alumno(nombre,edad,notaMedia,bilingue));
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar: Archivo no encontrado");
        } catch(IOException e){
            System.out.println("Error al cargar");
        } catch(IllegalArgumentException e){
            System.out.println("Error al cargar");
        }
    }

    /**
    Método que carga los alumnos desde un archivo binario y los guarda los alumnos en el registro.

    @param archivo Archivo tipo dat desde donde se carga los alumnos.
    */

    public void cargarBinario(){
        String archivo = "src/ejercicioFinalUD1/src/archivoBinario.dat";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));

            try{
                while(true){
                Alumno a = (Alumno)ois.readObject();
                registroAlumnos.add(a);
                }
            }
            catch (EOFException e) {
                // Fin del archivo, salir del bucle
            }
            ois.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar: Archivo no encontrado");
        } catch(IllegalArgumentException e){
            System.out.println("Error al cargar");
        } catch(ClassNotFoundException e){
        } catch(IOException e){
        }
    }

    /**
    Método que carga los alumnos desde un archivo XML y los guarda los alumnos en el registro.

    @param archivo Archivo tipo XML desde donde se carga los alumnos.
    */

    public void cargarXML(){
        String archivo = "src/ejercicioFinalUD1/src/archivoXML.xml";

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);

            doc.getDocumentElement().normalize();

            NodeList listaNodosAlumnos = doc.getElementsByTagName("alumno");

            for (int i = 0; i < listaNodosAlumnos.getLength(); i++) {
                Element e = (Element)listaNodosAlumnos.item(i);
            String nombre =  e.getElementsByTagName("nombre").item(0).getTextContent();
            int edad =  Integer.parseInt(e.getElementsByTagName("edad").item(0).getTextContent());
            float nota = Float.parseFloat(e.getElementsByTagName("nota").item(0).getTextContent());
            Boolean bilingue = Boolean.parseBoolean(e.getAttribute("bilingue"));

            registroAlumnos.add(new Alumno(nombre, edad, nota, bilingue));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar: Archivo no encontrado");
        } catch(IllegalArgumentException e){
            System.out.println("Error al cargar");
        } catch(SAXException e){
        } catch(IOException e){
        } catch(ParserConfigurationException e){
        }

           
    }

    /**
    Método que devuelve una cadena de texto de todos los alumnos en el registro.

    @return Una cadena con la información de todos los alumnos, o un mensaje de que el registro 
    está vacio si no hay alumnos en el registro.
    */
    
    @Override
    public String toString() {
        String resultado = "";
        if(registroAlumnos.isEmpty()){
            return "\n" + "Registro vacio";
        }else{
            for(Alumno a: registroAlumnos){
                resultado += a.toString()+"\n";
            }
            return resultado;
        }
    }
}