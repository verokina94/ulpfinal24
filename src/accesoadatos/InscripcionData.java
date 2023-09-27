
package accesoadatos;

import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
    private Connection conex;
    private AlumnoData aluData;
    private MateriaData matData;
    
    public InscripcionData() {
        this.conex = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc) {
      //            int nota cambiar a double 
        try {
            String sql = "INSERT INTO inscripcion (idAlumno, idMateria, nota) "
                    + "VALUES (?, ?, ?)";
            
            PreparedStatement ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setInt(3, insc.getNota());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();//tabla de inscripcion

            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripción guardada.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de inscripción. " + ex.getMessage());
        }
    }
    
    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion";

            PreparedStatement ps = conex.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción. " + ex.getMessage());
        }
        return inscripciones;
    }
    
    /*
        Este método debe obtener las inscripciones del alumno
        pasandole el id del alumno por parametro
    */
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id) {
        List<Inscripcion> inscripciones = new ArrayList();
       // Materias materia;
        try {
            // método modificado para funcionar tabla cargar notas
            /*
            String listar = "SELECT inscripcion.idInscripto, inscripcion.idMateria, nota " // Se recupera de la tabla inscripción
                          + "FROM inscripcion JOIN alumno "
                          + "ON (inscripcion.idAlumno = alumno.idAlumno) "
                          + "WHERE inscripcion.idAlumno = ?";
            */
            String listar = "SELECT inscripcion.idInscripto, materia.nombre AS nombreMateria, inscripcion.nota " +
                            "FROM inscripcion " +
                            "JOIN materia ON inscripcion.idMateria = materia.idMateria " +
                            "WHERE inscripcion.idAlumno = ?";
            
            PreparedStatement ps = conex.prepareStatement(listar);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Materias materia = new Materias();
                MateriaData md = new MateriaData(); 
                materia=md.buscarMateria(id);
                Inscripcion inscripcion = new Inscripcion();
                
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setMateria(materia);
                rs.getString("nombreMateria");
                inscripcion.setNota(rs.getInt("nota"));
                
                inscripciones.add(inscripcion);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas inscripcion y/o materia. " + ex.getMessage());
        }
        return inscripciones;
    }
    
    // Este método recibe el id de un alumno.
    public List<Materias> obtenerMateriasCursadas(int id) {
        List<Materias> materias = new ArrayList();
        try {
            /*
            En esta consulta utilizamos una <subconsulta> para buscar en la tabla inscripcion las 
            materias NO inscriptas
            */
            String listar = "SELECT materia.idMateria, materia.nombre, materia.año " +
                        "FROM materia " + 
                        "WHERE materia.idMateria " + // aca traigo a la materia y todos sus datos
                        "NOT IN (SELECT inscripcion.idMateria " + // subconsulta donde determinamos que NO
                        "FROM inscripcion " +                     //  hay un inscripcion.idMateria
                        "WHERE inscripcion.idAlumno = ?)";        // segun el alumno por su ID         
            PreparedStatement ps = conex.prepareStatement(listar);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
                        
            while (rs.next()) {
                Materias materia = new Materias();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materias.add(materia);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas inscripcion y/o materia. " + ex.getMessage());
        }
        return materias;
    }
    
    public List<Materias> obtenerMateriasNoCursadas(int id) {
        List<Materias> materias = new ArrayList<>();
        try {
            String listar = "SELECT materia.idMateria, nombre, año "
                          + "FROM inscripcion JOIN materia "
                          + "ON NOT (inscripcion.idMateria = materia.idMateria) "
                          + "WHERE inscripcion.idAlumno = ?";
            
            PreparedStatement ps = conex.prepareStatement(listar);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Materias materia = new Materias();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materias.add(materia);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas inscripcion y/o materia. " + ex.getMessage());
        }
        return materias;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        try {
            String borrado = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conex.prepareStatement(borrado);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La inscripción se eliminó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al borrar la inscripción.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion. " + ex.getMessage());
        }
    }
    
    public void actualizarNota(int idAlumno, int idMateria, int nota) {
        try {
            String borrado = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conex.prepareStatement(borrado);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La nota del alumno se modificó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al modificar la nota.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción. " + ex.getMessage());
        }
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria) {
        List<Alumno> alumnos = new ArrayList();
        try {
            String obtencion = "SELECT alumno.idAlumno, dni, apellido, nombre FROM alumno "
                             + "JOIN inscripcion ON (inscripcion.idAlumno = alumno.idAlumno) "
                             + "WHERE inscripcion.idMateria = ?";
            PreparedStatement ps = conex.prepareStatement(obtencion);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumnos.add(alumno);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas alumno y/o inscripción. " + ex.getMessage());
        }
        return alumnos;
    }
    
}