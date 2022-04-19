package omelcam934.views;

import java.util.Scanner;

public class Main {

    private static final String opciones =
            "1º Listar todos los alumnos\n" +
                    "2º Listar alumno por ID\n" +
                    "3º Insertar nuevo alumno\n" +
                    "4º Modificar alumno ya existente\n" +
                    "5º Eliminar alumno por ID";


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opcion;
        AlumnoViews al = new AlumnoViews();

        do {
            System.out.println(opciones);
            opcion = Integer.parseInt(in.nextLine());

            switch (opcion){
                case 0:
                    break;
                case 1:
                    al.listarAlumnos();
                    break;
                case 2:
                    al.listarAlumnoPorId();
                    break;
                case 3:
                    al.insertarAlumno();
                    break;
                case 4:
                    al.modificarAlumno();
                    break;
                case 5:
                    al.eliminarAlumno();
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }

        }while (opcion!=0);
    }



}
