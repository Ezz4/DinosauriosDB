package io.emma.dinosauriosdb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionUtils {
    public static Connection conexion = null;
    private  static  final String  URLCONEXION = new StringBuilder().append("jdbc:mysql://localhost:3306/Dinosaurios?")
            .append("useUnicode=true").append("&useJDBCCompliantTimezoneShift=true")
            .append("&useLegacyDatetimeCode=false").append("&serverTimezone=UTC").toString();

    private static final String USER = "root";
    private static final String PASWORD = "1234567";
    public static Connection getConexion() throws java.sql.SQLException{ //Lanzamos la exempcion para personalizar el mensaje de error

        if(conexion == null){
            conexion = DriverManager.getConnection(URLCONEXION, USER, PASWORD);
        }
        return conexion;
    }

    public static  void closeConnection(){
        try {
            conexion.close();
        }catch(SQLException e){
            System.out.println("Conexion no cerrada");
        }
    }

}
