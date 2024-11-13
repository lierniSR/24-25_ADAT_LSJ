package dao;

import java.sql.*;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import model.ModeloDeportista;

/**
 * Clase que gestiona las operaciones relacionadas con la tabla Deportista en la base de datos.
 * Proporciona métodos para insertar, consultar, y obtener información de los deportistas.
 */
public class DaoDeportista {
    private static Connection connection;

    /**
     * Crea un objeto `ModeloDeportista` a partir del ID de un deportista.
     * 
     * @param id El ID del deportista.
     * @return Un objeto `ModeloDeportista` correspondiente al deportista con el ID proporcionado,
     *         o null si no se encuentra en la base de datos.
     */
    public static ModeloDeportista createDeportistaModel(String id) {
        // Conexión a la base de datos
        connection = ConexionBBDD.getConnection();
        String consultaSQL = "SELECT nombre, sexo, peso, altura FROM Deportista WHERE id_deportista = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(consultaSQL)) {
            pstmt.setString(1, id); // Asigna el ID del deportista a la consulta
            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                connection.commit(); // Confirma la transacción si se encuentra un resultado
                String nombre = resultado.getString("nombre");
                char sexo = resultado.getString("sexo").charAt(0);
                int altura = resultado.getInt("altura");
                int peso = resultado.getInt("peso");
                
                return new ModeloDeportista(nombre, sexo, altura, peso);
            } else {
                System.out.println("No se encontró ningún deportista con el ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el modelo del deportista: " + e.getMessage());
        }

        // Retorna null si no se encontró el deportista o ocurrió una excepción
        return null;
    }

    /**
     * Encuentra deportistas cuyo nombre coincida parcialmente con la cadena proporcionada.
     * 
     * @param cadena La cadena con la que se busca coincidencias en el nombre del deportista.
     * @return Una lista de objetos `ModeloDeportista` que coinciden con el nombre proporcionado.
     */
    public static ArrayList<ModeloDeportista> findDeportistaName(String cadena) {
        connection = ConexionBBDD.getConnection();
        ArrayList<ModeloDeportista> lst = new ArrayList<>();
        String select = "SELECT id_deportista FROM Deportista WHERE nombre LIKE ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1, "%" + cadena + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                connection.commit();
                lst.add(DaoDeportista.createDeportistaModel(rs.getInt("id_deportista") + ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * Inserta un nuevo deportista en la base de datos.
     * 
     * @param nombre El nombre del deportista.
     * @param sexo El sexo del deportista (M/F).
     * @param edad La edad del deportista.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
    public static void insertDeportista(String nombre, String sexo, int edad) throws SQLException {
        String sql = "INSERT INTO `Deportista` (`nombre`, `sexo`, `edad`) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, sexo);
            pstmt.setInt(3, edad);
            pstmt.executeUpdate();
        }
    }

    /**
     * Obtiene el ID de un deportista basado en su nombre, sexo, peso y altura.
     * 
     * @param nombreDeportista El nombre del deportista.
     * @param sexo El sexo del deportista (M/F).
     * @param peso El peso del deportista.
     * @param altura La altura del deportista.
     * @return El ID del deportista si existe, o null si no se encuentra.
     */
    public static String conseguirIdDeportista(String nombreDeportista, char sexo, float peso, int altura) {
        connection = ConexionBBDD.getConnection();
        String select = "SELECT id_deportista FROM Deportista WHERE nombre=? AND sexo=? AND peso=? AND altura=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1, nombreDeportista);
            pstmt.setString(2, sexo + "");
            pstmt.setFloat(3, peso);
            pstmt.setInt(4, altura);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id_deportista");
                connection.commit();
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta un nuevo deportista en la base de datos con los parámetros proporcionados.
     * 
     * @param nombreDeportista El nombre del deportista.
     * @param sexo El sexo del deportista (M/F).
     * @param peso El peso del deportista.
     * @param altura La altura del deportista.
     */
    public static void aniadirDeportista(String nombreDeportista, char sexo, float peso, int altura) {
        connection = ConexionBBDD.getConnection();
        String insertar = "INSERT INTO Deportista (nombre, sexo, peso, altura) VALUES (?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(insertar);
            pstmt.setString(1, nombreDeportista);
            pstmt.setString(2, sexo + "");
            pstmt.setFloat(3, peso);
            pstmt.setInt(4, altura);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
