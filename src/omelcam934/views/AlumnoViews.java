package omelcam934.views;

import omelcam934.dao.AlumnoBBDD;
import omelcam934.modelo.Alumno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AlumnoViews {

    private static final Scanner in = new Scanner(System.in);

    private AlumnoBBDD bbdd = null;

    public AlumnoViews(AlumnoBBDD bbdd) {
        this.bbdd = bbdd;
    }

    public AlumnoViews() {
        this.bbdd = new AlumnoBBDD();
    }

    public void listarAlumnos(){
        ArrayList<Alumno> al = bbdd.devAlumnos();
        listarAlumnosPorArray(al);
    }

    public void listarAlumnoPorId(){
        int id;
        System.out.println("Introduce el id");
        id = Integer.parseInt(in.nextLine());
        listarUnAlumnoPorObjeto(bbdd.devAlumno(id));
    }

    public void insertarAlumno(){
        System.out.println("Introduce el nombre");
        String nombre = in.nextLine();
        System.out.println("Introduce el apellido");
        String apellido = in.nextLine();
        System.out.println("Introduce el email (se validara!)");
        String email = in.nextLine();
        System.out.println("Introduce la edad");
        int edad = Integer.parseInt(in.nextLine());
        Alumno alumno = new Alumno(nombre,apellido,email,edad);
        try {
            bbdd.guardarAlumno(alumno);
        } catch (Exception e) {
            System.out.println("El email era invalido!");
        }
    }

    public void modificarAlumno(){
        System.out.println("Introduce la ID del alumno a modificar");
        int id = Integer.parseInt(in.nextLine());
        Alumno alumnopre = bbdd.devAlumno(id);
        if(alumnopre!=null){
            System.out.println("Introduce el nombre");
            String nombre = in.nextLine();
            System.out.println("Introduce el apellido");
            String apellido = in.nextLine();
            System.out.println("Introduce el email (se validara!)");
            String email = in.nextLine();
            System.out.println("Introduce la edad");
            int edad = Integer.parseInt(in.nextLine());
            try {
                bbdd.actualizaAlumno(nombre,apellido,email,edad,id);
            } catch (Exception e) {
                System.out.println("El email era invalido!");
            }
        }else{
            System.out.println("El alumno no existe");
        }

    }

    public void eliminarAlumno(){
        System.out.println("Introduce la ID del alumno a borrar");
        int id = Integer.parseInt(in.nextLine());
        Alumno alumnopre = bbdd.devAlumno(id);
        if(alumnopre!=null){
            bbdd.borrarAlumno(id);
        }else{
            System.out.println("El alumno no existe");
        }
    }

    public static void listarAlumnosPorArray(ArrayList<Alumno> lista){
        if (lista !=null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }

    public static void listarUnAlumnoPorObjeto(Alumno a){
        if (a!=null)
            System.out.println(a);
    }
}
