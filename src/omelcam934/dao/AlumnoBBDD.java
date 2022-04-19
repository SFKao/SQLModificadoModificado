package omelcam934.dao;

import omelcam934.conexion.Conex;
import omelcam934.modelo.Alumno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AlumnoBBDD {

    private Conex connection = null;

    public AlumnoBBDD(Conex connection) {
        this.connection = connection;
    }

    public AlumnoBBDD() {
        this.connection = new Conex();
    }

    public void guardarAlumno(Alumno alumno) throws Exception {
        if(!Pattern.matches("[\\w\\d-]+(?:\\.[\\w\\d-]+)*?@[\\w\\d-]+(?:\\.[\\w\\d-]+)*\\.[\\w]{2,}$",alumno.getEmail()))
            throw new Exception("Email invalido");
        try {
            PreparedStatement st = connection.getConnect().prepareStatement("insert into tblAlumno (nombre, apellidos, email, edad) values (?,?,?,?)");
            st.setString(1, alumno.getNombre());
            st.setString(2, alumno.getApellidos());
            st.setString(3,alumno.getEmail());
            st.setInt(4,alumno.getEdad());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Alumno> devAlumnos()  {

        int num = 0;
        ArrayList<Alumno> dev = null;
        try {
            Statement st = connection.getConnect().createStatement();
            String sql = "SELECT * FROM tblAlumno;";
            ResultSet rs = st.executeQuery(sql);
            dev = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                int edad = rs.getInt("edad");
                dev.add(new Alumno(id, nombre, apellidos,email,edad));
                num++;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return ( (num>0)? dev: null );
    }
    public Alumno devAlumno (int id){
        Alumno alum = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            String sql = "select id, nombre, apellidos, email, edad from tblAlumno where id = ?";
            st = connection.getConnect().prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                int edad = rs.getInt("edad");
                alum = new Alumno(id, nombre, apellidos,email,edad);
                return alum;

            }

        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return null;
    }


    public void actualizaAlumno(String nombre, String apellidos,String email, int edad, int id) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        if(!Pattern.matches("[\\w\\d-]+(?:\\.[\\w\\d-]+)*?@[\\w\\d-]+(?:\\.[\\w\\d-]+)*\\.[\\w]{2,}$",email))
            throw new Exception("Email invalido");
        try {
            String sql = "update tblAlumno set nombre= ?, apellidos = ?, email = ?, edad = ? where id = " + id;
            st = connection.getConnect().prepareStatement(sql);
            st.setString(1, nombre);
            st.setString(2, apellidos);
            st.setString(3,email);
            st.setInt(4,edad);
            st.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();

        }
    }


    public void borrarAlumno(int id){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "delete from tblAlumno  where id = ?";
            st = connection.getConnect().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

}
