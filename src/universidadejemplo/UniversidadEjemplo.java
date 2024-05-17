
package universidadejemplo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class UniversidadEjemplo{

    public static void main(String[] args)  throws ClassNotFoundException, SQLException{
        Connection con;
        
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/universidadulp", "root", "");
            JOptionPane.showMessageDialog(null, "Conexión establecida");
            
            //Acá insertamos 3 alumnos
            
            PreparedStatement ps;
//            ps= con.prepareStatement("INSERT INTO `alumno`(`dni`, `apellido`, `nombre`, "
//                    + "`fechaNacimiento`, `estado`) VALUES (38749316, 'Castro', 'Florencia',"
//                    + "'1995-02-21', true), (37908002, 'Coronel', 'Adan', '1994-01-20',true),"
//                    + "(42553123, 'Wotozsyn','Tadeo','2003-10-30', false)");
//            ps.execute();
            
            //Acá insertamos 4 materias
//            ps=con.prepareStatement("INSERT INTO `materia` (`nombre`,`año`,`estado`) VALUES("
//                    + "'Laboratorio 1', 1, true), ('Matematica introductoria',1, false),"
//                    + "('Redes', 3, true), ('Base de datos 2', 2, true)");
//
//            ps.execute();
            
            //Inscribimos alumnos
//            ps=con.prepareStatement("INSERT INTO `inscripcion`( `nota`, `idAlumno`, `idMateria`) VALUES ("
//                    + "9,1,1),(10,1,4)");
//            ps.execute();
//            ps = con.prepareStatement("INSERT INTO inscripcion( `nota`, `idAlumno`, `idMateria`) "
//                    + "VALUES (10,1,1),"
//                    + "(10,1,2)");
//            
//            ps.execute();
//            
//            ps = con.prepareStatement("INSERT INTO inscripcion( `nota`, `idAlumno`, `idMateria`) "
//                    + "VALUES (10,3,4),"
//                    + "(10,3,3)");
//            ps.execute();
//            
//            ps = con.prepareStatement("INSERT INTO inscripcion(`nota`, `idAlumno`, `idMateria`) "
//                    + "VALUES (7,2,1),"
//                    + "(8,2,1)");
//            ps.execute();
                ps=con.prepareStatement("SELECT Distinct alumno.idAlumno, nombre, apellido, fechaNacimiento, estado FROM alumno "
                        + "join inscripcion on alumno.idAlumno=inscripcion.idAlumno where nota>8");
                ResultSet rs= ps.executeQuery();
            System.out.println("Alumnos con notas mayores a 8");
            while(rs.next()){
                int idAlumno = rs.getInt("idAlumno");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                boolean estado = rs.getBoolean("estado");
                System.out.println("Alumno con id:" + idAlumno);
                System.out.print(apellido + ", " + nombre + " nacido/a en " + fechaNacimiento + ". ");
                if(estado)
                    System.out.println("Activo.");
                else
                    System.out.println("Inactivo.");
            }
            
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Conexión fallida, error SQL"+ex);
        }
        catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Conexión fallida, error clase no encontrada"+ex);
        }
        
    }
    
}
