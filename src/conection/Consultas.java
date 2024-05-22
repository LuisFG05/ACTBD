package conection;

import Formularios.frmPrincipal;
import FormulariosSencillos.frmPrincipalA;
import conection.Metodos.ForeignKeyConstraintException;
import conection.conectionSQL;
import conection.Metodos.ForeignKeyConstraintException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Date;


public class Consultas {

    public void ConsultarUsuario(String email, String pass) {
    conectionSQL conexion = new conectionSQL();
    try {
        // Establecer la conexión
        conexion.conectar();

        String passCorrecto = null;
        int idrol = -1; // Valor por defecto para el id_rol

        PreparedStatement pst = conexion.conectar().prepareStatement("SELECT email, contrasena, id_rol FROM inmobiliaria.users WHERE email = ?");
        pst.setString(1, email); // Establecer el email como parámetro en la consulta
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String userEmail = rs.getString("email");
            passCorrecto = rs.getString("contrasena");
            idrol = rs.getInt("id_rol"); // Obtener el id_rol del usuario

            if (pass.equals(passCorrecto)) {
                // Verificar el rol del usuario y abrir el formulario correspondiente
                switch (idrol) {
                    case 1: // ID de rol para administrador
                        abrirFormularioAdmin();
                        break;
                    case 2: // ID de rol para usuario normal
                        abrirFormularioUsuarioAgenteInmobiliario();
                        break;
                    // Agrega más casos según los roles que tengas en tu sistema
                    default:
                        JOptionPane.showMessageDialog(null, "Rol no reconocido");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error en la consulta SQL: " + e.getMessage());
    } finally {
        // Cerrar la conexión
        conexion.Desconectar();
    }
}

    
// Método para abrir el formulario del administrador
private void abrirFormularioAdmin() {
    java.awt.EventQueue.invokeLater(() -> {
        frmPrincipal principal = new frmPrincipal(null, true); // Crear una instancia del formulario de administrador
        principal.setLocationRelativeTo(null);
        principal.setVisible(true); // Hacer visible el formulario de administrador
    });
}

// Método para abrir el formulario del usuario normal
private void abrirFormularioUsuarioAgenteInmobiliario() {
    java.awt.EventQueue.invokeLater(() -> {
        frmPrincipalA UserAG = new frmPrincipalA(null, true); // Crear una instancia del formulario de usuario normal
        UserAG.setVisible(true); // Hacer visible el formulario de usuario normal
    });
}

    
     public void insertarCiudad(String nombreCiudad, int id) {
        // Consulta SQL para insertar un registro en la tabla ciudad
        String sql = "INSERT INTO ciudad (nombre_ciudad) VALUES (?)";

        // Crear una instancia de la clase de conexión
        conectionSQL conexion = new conectionSQL();
        Connection conn = null;

        try {
            // Establecer la conexión
            conn = conexion.conectar();
            if (conn != null) {
                // Preparar la declaración SQL
                PreparedStatement pstmt = conn.prepareStatement(sql);
                // Establecer el valor del parámetro en el PreparedStatement
                pstmt.setString(1, nombreCiudad);

                // Ejecutar la inserción
                int affectedRows = pstmt.executeUpdate();

                // Verificar si la inserción fue exitosa
                if (affectedRows > 0) {
                    System.out.println("Registro insertado con éxito en la tabla ciudad.");
                } else {
                    System.out.println("No se pudo insertar el registro en la tabla ciudad.");
                }

                // Cerrar el PreparedStatement
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Desconectar de la base de datos
            if (conn != null) {
                conexion.Desconectar();
            }
        }
    }
public void borrarCiudad(int idCiudad) throws Metodos.ForeignKeyConstraintException, SQLException {
        String checkSql = "SELECT COUNT(*) FROM zona WHERE id_ciudad = ?";
        String deleteSql = "DELETE FROM ciudad WHERE id_ciudad = ?";
        conectionSQL conexion = new conectionSQL();
        Connection conn = null;

        try {
            conn = conexion.conectar();
            if (conn != null) {
                // Verificar si hay zonas asociadas a la ciudad
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setInt(1, idCiudad);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int zonaCount = rs.getInt(1);
                rs.close();
                checkStmt.close();

                if (zonaCount > 0) {
                    throw new Metodos.ForeignKeyConstraintException();
                }

                // Si no hay zonas asociadas, proceder con la eliminación de la ciudad
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setInt(1, idCiudad);
                int affectedRows = deleteStmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Registro borrado con éxito de la tabla ciudad.");
                } else {
                    System.out.println("No se encontró el registro con el ID especificado en la tabla ciudad.");
                }

                deleteStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lanzar la excepción para que pueda ser manejada en otro lugar si es necesario
        } finally {
            if (conn != null) {
                conexion.Desconectar();
            }
        }
}
        
public ResultSet consultarTodasLasCiudades() {
    ResultSet rs = null;
    String sql = "SELECT * FROM ciudad"; // Consulta SQL para obtener todas las ciudades
    
    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();
        
        // Preparar la declaración SQL
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Ejecutar la consulta y obtener el resultado
        rs = pstmt.executeQuery();
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }
    
    // Devolver el ResultSet
    return rs;   
  
}

public void actualizarCiudad(int idCiudad, String nuevoNombreCiudad) {
    // Consulta SQL para actualizar el nombre de una ciudad
    String sql = "UPDATE ciudad SET nombre_ciudad = ? WHERE id_ciudad = ?";
    
    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setString(1, nuevoNombreCiudad);
            pstmt.setInt(2, idCiudad);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla ciudad.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla ciudad.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }

} 
    
}
 public void insertarCliente(String nombreCliente, int id, String telefono) {
    // Verificar longitud del teléfono antes de insertar
    if (telefono.length() > 10) {
        System.out.println("El número de teléfono es demasiado largo. Máximo permitido es 20 caracteres.");
        return;
    }

    // Consulta SQL para insertar un registro en la tabla cliente
    String sql = "INSERT INTO cliente (id_cliente, nombre_cliente, telefono) VALUES (?,?,?)";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, id);
            pstmt.setString(2, nombreCliente);
            pstmt.setString(3, telefono);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro insertado con éxito en la tabla cliente.");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla cliente.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }


}

 
   public void borrarCliente(int idCliente) {
    String sql = "DELETE FROM cliente WHERE id_cliente = ?";
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        conn = conexion.conectar();
        if (conn != null) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCliente);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Registro borrado con éxito de la tabla cliente.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla cliente.");
            }

            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            conexion.Desconectar();
        }
    }


    }
public void actualizarCliente(int idCliente, String nuevoNombreCliente, String nuevoTelefonoCliente) {
    // Consulta SQL para actualizar el nombre y el teléfono de un cliente
    String sql = "UPDATE cliente SET nombre_cliente = ?, telefono = ? WHERE id_cliente = ?";
    
    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setString(1, nuevoNombreCliente);
            pstmt.setString(2, nuevoTelefonoCliente);
            pstmt.setInt(3, idCliente);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla cliente.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla cliente.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }

}
public ResultSet consultarClientes() {
    ResultSet rs = null;
    String sql = "SELECT * FROM cliente"; // Consulta SQL para obtener todos los clientes
    
    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();
        
        // Preparar la declaración SQL
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Ejecutar la consulta y obtener el resultado
        rs = pstmt.executeQuery();
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }
    
    // Devolver el ResultSet
    return rs;   

}

public void insertarZona(String nombreZona, int id, int idCiudad) {
    // Consulta SQL para insertar un registro en la tabla zona
    String sql = "INSERT INTO zona (id_zona, nombre_zona, id_ciudad) VALUES (?,?,?)";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, id);
            pstmt.setString(2, nombreZona);
            pstmt.setInt(3, idCiudad);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: id_zona=" + id + ", nombre_zona=" + nombreZona + ", id_ciudad=" + idCiudad);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro insertado con éxito en la tabla zona.");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla zona.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

    
      public void borrarZona(int idZona) {
    String sql = "DELETE FROM zona WHERE id_zona = ?";
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        conn = conexion.conectar();
        if (conn != null) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idZona);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Registro borrado con éxito de la tabla zona.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla zona.");
            }

            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            conexion.Desconectar();
        }
    }
    
      } 
      
  public void actualizarZona(int idZona, String nuevoNombreZona, int idCiudad) {
    // Consulta SQL para actualizar el nombre de la zona y su ciudad correspondiente
    String sql = "UPDATE zona SET nombre_zona = ?, id_ciudad = ? WHERE id_zona = ?";
    
    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setString(1, nuevoNombreZona);
            pstmt.setInt(2, idCiudad);
            pstmt.setInt(3, idZona);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla zona.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla zona.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
    }
public ResultSet consultarZona() {
    ResultSet rs = null;
    String sql = "SELECT z.id_zona, z.nombre_zona, z.id_ciudad, c.nombre_ciudad " +
                 "FROM zona z " +
                 "INNER JOIN ciudad c ON z.id_ciudad = c.id_ciudad"; 
    
    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();
        
        // Verificar si la conexión es exitosa
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Ejecutar la consulta y obtener el resultado
            rs = pstmt.executeQuery();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }
    
    // Devolver el ResultSet
    return rs;
}

public void insertarOficina(int idOficina, boolean hay_llave, int idZona) {
    
    // Consulta SQL para insertar un registro en la tabla oficina
    String sql = "INSERT INTO oficina (id_oficina, hay_llave, id_zona) VALUES (?,?,?)";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, idOficina);
            pstmt.setBoolean(2, hay_llave); // Aquí se establece el valor booleano correctamente
            pstmt.setInt(3, idZona);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: id_oficina=" + idOficina + ", hay_llave=" + hay_llave + ", id_zona=" + idZona);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro insertado con éxito en la tabla oficina.");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla oficina.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

public void actualizarOficina(int idOficina, boolean hay_llave, int idZona) {
    // Consulta SQL para actualizar el valor booleano de hay_llave en la tabla oficina
    String sql = "UPDATE oficina SET hay_llave = ? WHERE id_oficina = ?";
    
    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setBoolean(1, hay_llave);
            pstmt.setInt(2, idOficina);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla oficina.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla oficina.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

   public void borrarOficina(int idZona) {
    String sql = "DELETE FROM oficina WHERE id_oficina = ?";
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        conn = conexion.conectar();
        if (conn != null) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idZona);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Registro borrado con éxito de la tabla oficina.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla oficina.");
            }

            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            conexion.Desconectar();
        }
    }
    
      } 
public ResultSet consultarOficina() {
    ResultSet rs = null;
    String sql = "SELECT o.id_oficina, o.hay_llave, o.id_zona, z.nombre_zona " +
                 "FROM oficina o " +
                 "INNER JOIN zona z ON o.id_zona = z.id_zona"; // Corrección en la condición de unión
    
    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();
        
        // Verificar si la conexión es exitosa
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Ejecutar la consulta y obtener el resultado
            rs = pstmt.executeQuery();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }
    
    // Devolver el ResultSet
    return rs;
}

public void CrearUsuario(int idUsuario, String Nombre, String Email, String Contrasenia, String Fecha, int idRol){
    String sql = "INSERT INTO users (id_users, nombre_user, email, contrasena, fecha, id_rol) VALUES (?,?,?,?,?,?)";
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, Nombre);
            pstmt.setString(3, Email);
            pstmt.setString(4,Contrasenia);
            pstmt.setString(5,Fecha);
            pstmt.setInt(6,idRol);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: id_users=" + idUsuario + ", nombre_user=" + Nombre + ", email=" + Email + ", contrasena=" + Contrasenia + ", fecha="
            +Fecha+ ", id_rol=" +idRol);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro insertado con éxito en la tabla users.");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla users.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

  public void borrarUsuario(int idUsuario) {
    String sql = "DELETE FROM users WHERE id_users = ?";
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        conn = conexion.conectar();
        if (conn != null) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Registro borrado con éxito de la tabla users.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla users.");
            }

            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            conexion.Desconectar();
        }
    }
    
      }
  
public void actualizarUsuario(int idUsuario, String Nombre, String Email, String Contrasenia, Date Fecha, int idRol) {
    // Consulta SQL para actualizar un registro en la tabla users
    String sql = "UPDATE users SET nombre_user=?, email=?, contrasena=?, fecha=?, id_rol=? WHERE id_users=?";
    
    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setString(1, Nombre);
            pstmt.setString(2, Email);
            pstmt.setString(3, Contrasenia);
            pstmt.setDate(4, (java.sql.Date) Fecha);
            pstmt.setInt(5, idRol);
            pstmt.setInt(6, idUsuario);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla users.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado en la tabla users.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

public ResultSet consultarUsuario() {
    ResultSet rs = null;
    String sql = "SELECT u.id_users, u.nombre_user, u.email, u.contrasena, u.fecha, u.id_rol, r.nombre_rol " +
                 "FROM users u " +
                 "INNER JOIN rol r ON u.id_rol = r.id_rol";
    
    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();
        
        // Verificar si la conexión es exitosa
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Ejecutar la consulta y obtener el resultado
            rs = pstmt.executeQuery();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }
    
    // Devolver el ResultSet
    return rs;
}

public void insertarTipoInmueble(int idTipoInmueble, boolean Diafono, boolean Acondicionado, String Entrada, boolean Blindada, boolean Gas, String Urbanizacion, String Tamanio) {
    
    // Consulta SQL para insertar un registro en la tabla tipo_inmueble
    String sql = "INSERT INTO tipo_inmueble (id_tipo_inmueble, diafono, acondicionado, puertas_entrada, puerta_blindada, gas_ciudad, urbanizacion, tamaño_parcela) VALUES (?,?,?,?,?,?,?,?)";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, idTipoInmueble);
            pstmt.setBoolean(2, Diafono);
            pstmt.setBoolean(3, Acondicionado);
            pstmt.setString(4, Entrada);
            pstmt.setBoolean(5, Blindada);
            pstmt.setBoolean(6, Gas);
            pstmt.setString(7, Urbanizacion);
            pstmt.setString(8, Tamanio);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: id_tipo_inmueble=" + idTipoInmueble + ", diafono=" + Diafono + ", acondicionado=" + Acondicionado + ", puertas_entrada=" + Entrada + ", puerta_blindada=" + Blindada + ", gas_ciudad=" + Gas + ", urbanizacion=" + Urbanizacion + ", tamaño_parcela=" + Tamanio);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro insertado con éxito en la tabla tipo_inmueble.");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla tipo_inmueble.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}
public void eliminarTipoInmueble(int idTipoInmueble) {
    // Consulta SQL para eliminar un registro de la tabla tipo_inmueble
    String sql = "DELETE FROM tipo_inmueble WHERE id_tipo_inmueble = ?";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer el valor del parámetro en el PreparedStatement
            pstmt.setInt(1, idTipoInmueble);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetro: id_tipo_inmueble=" + idTipoInmueble);

            // Ejecutar la eliminación
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la eliminación fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro eliminado con éxito de la tabla tipo_inmueble.");
            } else {
                System.out.println("No se encontró ningún registro con el id_tipo_inmueble proporcionado.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

public void actualizarTipoInmueble(int idTipoInmueble, boolean Diafono, boolean Acondicionado, String Entrada, boolean Blindada, boolean Gas, String Urbanizacion, String Tamanio) {
    // Consulta SQL para actualizar un registro en la tabla tipo_inmueble
    String sql = "UPDATE tipo_inmueble SET diafono = ?, acondicionado = ?, puertas_entrada = ?, puerta_blindada = ?, gas_ciudad = ?, urbanizacion = ?, tamaño_parcela = ? WHERE id_tipo_inmueble = ?";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setBoolean(1, Diafono);
            pstmt.setBoolean(2, Acondicionado);
            pstmt.setString(3, Entrada);
            pstmt.setBoolean(4, Blindada);
            pstmt.setBoolean(5, Gas);
            pstmt.setString(6, Urbanizacion);
            pstmt.setString(7, Tamanio);
            pstmt.setInt(8, idTipoInmueble);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: diafono=" + Diafono + ", acondicionado=" + Acondicionado + ", puertas_entrada=" + Entrada + ", puerta_blindada=" + Blindada + ", gas_ciudad=" + Gas + ", urbanizacion=" + Urbanizacion + ", tamaño_parcela=" + Tamanio + ", id_tipo_inmueble=" + idTipoInmueble);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla tipo_inmueble.");
            } else {
                System.out.println("No se encontró ningún registro con el id_tipo_inmueble proporcionado.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

public ResultSet consultarTiposInmuebles() {
    ResultSet rs = null;
    String sql = "SELECT * FROM tipo_inmueble"; // Consulta SQL para obtener todos los clientes
    
    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();
        
        // Preparar la declaración SQL
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Ejecutar la consulta y obtener el resultado
        rs = pstmt.executeQuery();
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }
    
    // Devolver el ResultSet
    return rs;   

}

public void insertarEstancia(int idEstancia, String NombreEstancia, int Cantidad, int IdTipoInmueble) {
    // Consulta SQL para insertar un registro en la tabla estancia
    String sql = "INSERT INTO estancia (id_estancia, nombre_estancia, cantidad, id_tipo_inmueble) VALUES (?,?,?,?)";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, idEstancia);
            pstmt.setString(2, NombreEstancia);
            pstmt.setInt(3, Cantidad);
            pstmt.setInt(4, IdTipoInmueble);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: id_estancia=" + idEstancia + ", nombre_estancia=" + NombreEstancia + ", cantidad=" + Cantidad + ", id_tipo_inmueble=" + IdTipoInmueble);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro insertado con éxito en la tabla estancia.");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla estancia.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}
 

public void eliminarEstancia(int idEstancia) {
    // Consulta SQL para eliminar un registro de la tabla tipo_inmueble
    String sql = "DELETE FROM estancia WHERE id_estancia = ?";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer el valor del parámetro en el PreparedStatement
            pstmt.setInt(1, idEstancia);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetro: id_estancia=" + idEstancia);

            // Ejecutar la eliminación
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la eliminación fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro eliminado con éxito de la tabla estancia.");
            } else {
                System.out.println("No se encontró ningún registro con el id_estancia proporcionado.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

public void actualizarEstancia(int idEstancia, String NombreEstancia, int Cantidad, int IdTipoInmueble) {
    // Consulta SQL para actualizar un registro en la tabla estancia
    String sql = "UPDATE estancia SET nombre_estancia = ?, cantidad = ?, id_tipo_inmueble = ? WHERE id_estancia = ?";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setString(1, NombreEstancia);
            pstmt.setInt(2, Cantidad);
            pstmt.setInt(3, IdTipoInmueble);
            pstmt.setInt(4, idEstancia);

            // Mostrar mensaje de depuración
            System.out.println("Ejecutando consulta: " + sql);
            System.out.println("Parámetros: nombre_estancia=" + NombreEstancia + ", cantidad=" + Cantidad + ", id_tipo_inmueble=" + IdTipoInmueble + ", id_estancia=" + idEstancia);

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (affectedRows > 0) {
                System.out.println("Registro actualizado con éxito en la tabla estancia.");
            } else {
                System.out.println("No se encontró ningún registro con el id_estancia proporcionado.");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

public ResultSet consultarEstancia() {
    ResultSet rs = null;
    String sql = "SELECT e.id_estancia, e.nombre_estancia, e.cantidad, e.id_tipo_inmueble, t.nombre_inmueble FROM estancia e  JOIN tipo_inmueble t ON e.id_tipo_inmueble = t.id_tipo_inmueble";

    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();

        // Verificar si la conexión es exitosa
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Ejecutar la consulta y obtener el resultado
            rs = pstmt.executeQuery();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }

    // Devolver el ResultSet
    return rs;
}

public void insertarVisitas(int idVisita, int idInmueble, int idCliente,String comentario,String fecha,String hora) {
    // Consulta SQL para insertar un registro en la tabla estancia
    String sql = "INSERT INTO visita(id_visita, id_inmueble, id_cliente, comentario,fecha,hora) VALUES (?,?,?,?,?,?)";

    // Crear una instancia de la clase de conexión
    conectionSQL conexion = new conectionSQL();
    Connection conn = null;

    try {
        // Establecer la conexión
        conn = conexion.conectar();
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros en el PreparedStatement
            pstmt.setInt(1, idVisita);
            pstmt.setInt(2, idInmueble);
            pstmt.setInt(3,idCliente);
            pstmt.setString(4, comentario);
            pstmt.setString(5, fecha);
            pstmt.setString(6, hora);

            // Ejecutar la inserción
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Registro insertado con Exito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al hacer el registor");
            }

            // Cerrar el PreparedStatement
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        if (conn != null) {
            conexion.Desconectar();
        }
    }
}

 public ResultSet consultarVisitas(){
     ResultSet rs = null;
    String sql = "";

    try {
        // Crear una conexión
        Connection conn = new conectionSQL().conectar();

        // Verificar si la conexión es exitosa
        if (conn != null) {
            // Preparar la declaración SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Ejecutar la consulta y obtener el resultado
            rs = pstmt.executeQuery();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        e.printStackTrace();
    }

    // Devolver el ResultSet
    return rs;
 }

 

}


