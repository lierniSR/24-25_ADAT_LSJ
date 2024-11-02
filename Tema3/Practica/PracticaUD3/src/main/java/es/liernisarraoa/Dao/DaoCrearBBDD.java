package es.liernisarraoa.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoCrearBBDD {
    public void crearBDMySQL() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/","root", "12345");
            String sqlBDDeporte = "CREATE TABLE olimpiadas.Deporte (" +
                    "id_deporte int(11) NOT NULL AUTO_INCREMENT," +
                    "nombre varchar(100)  NOT NULL," +
                    "PRIMARY KEY (id_deporte)" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
            String sqlBDDeportista = "CREATE TABLE olimpiadas.Deportista (" +
                    "id_deportista int(11) NOT NULL AUTO_INCREMENT," +
                    "nombre varchar(150)  NOT NULL," +
                    "sexo enum('M', 'F')  NOT NULL," +
                    "peso int(11) DEFAULT NULL," +
                    "altura int(11) DEFAULT NULL," +
                    "PRIMARY KEY (id_deportista)" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
            String sqlBDEquipo = "CREATE TABLE olimpiadas.Equipo (" +
                    "id_equipo int(11) NOT NULL AUTO_INCREMENT," +
                    "nombre varchar(50)  NOT NULL," +
                    "iniciales varchar(3)  NOT NULL," +
                    "PRIMARY KEY (id_equipo)" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
            String sqlBDOlimpiada = "CREATE TABLE olimpiadas.Olimpiada (" +
                    "id_olimpiada int(11) NOT NULL AUTO_INCREMENT," +
                    "nombre varchar(11)  NOT NULL," +
                    "anio smallint(6) NOT NULL," +
                    "temporada enum('Summer', 'Winter')  NOT NULL," +
                    "ciudad varchar(50)  NOT NULL ," +
                    "PRIMARY KEY (id_olimpiada)" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
            String sqlBDEvento = "CREATE TABLE olimpiadas.Evento (" +
                    "id_evento int(11) NOT NULL AUTO_INCREMENT," +
                    "nombre varchar(150) NOT NULL," +
                    "id_olimpiada int(11) NOT NULL," +
                    "id_deporte int(11) NOT NULL," +
                    "PRIMARY KEY (id_evento)," +
                    "CONSTRAINT `FK_Evento_Deporte` FOREIGN KEY (id_deporte) REFERENCES Deporte (id_deporte)," +
                    "CONSTRAINT `FK_Evento_Olimpiada` FOREIGN KEY (id_olimpiada) REFERENCES Olimpiada (id_olimpiada)" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
            String sqlBDParticipacion = "CREATE TABLE olimpiadas.Participacion (" +
                    "id_deportista int(11) NOT NULL," +
                    "id_evento int(11) NOT NULL," +
                    "id_equipo int(11) NOT NULL," +
                    "PRIMARY KEY (id_deportista, id_evento)," +
                    "CONSTRAINT `FK_Participacion_Deportista` FOREIGN KEY (id_deportista) REFERENCES Deportista (id_deportista)," +
                    "CONSTRAINT `FK_Participacion_Equipo` FOREIGN KEY (id_equipo) REFERENCES Equipo (id_equipo)," +
                    "CONSTRAINT `FK_Participacion_Evento` FOREIGN KEY (id_evento) REFERENCES Evento (id_evento)" +
                    ") ENGINE = InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";

            Statement sentenciaBDCrear = conexion.createStatement();

            sentenciaBDCrear.executeUpdate("DROP DATABASE IF EXISTS olimpiadas;");
            sentenciaBDCrear.executeUpdate("CREATE DATABASE IF NOT EXISTS olimpiadas;");
            sentenciaBDCrear.executeUpdate("DROP SCHEMA IF EXISTS olimpiadas;");
            sentenciaBDCrear.executeUpdate("CREATE SCHEMA IF NOT EXISTS olimpiadas;");
            sentenciaBDCrear.executeUpdate("SET SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';");
            sentenciaBDCrear.executeUpdate("SET AUTOCOMMIT = 0;");
            sentenciaBDCrear.executeUpdate("START TRANSACTION;");
            sentenciaBDCrear.executeUpdate("COMMIT;");
            sentenciaBDCrear.executeUpdate(sqlBDDeporte);
            sentenciaBDCrear.executeUpdate(sqlBDDeportista);
            sentenciaBDCrear.executeUpdate(sqlBDEquipo);
            sentenciaBDCrear.executeUpdate(sqlBDOlimpiada);
            sentenciaBDCrear.executeUpdate(sqlBDEvento);
            sentenciaBDCrear.executeUpdate(sqlBDParticipacion);

            System.out.println("**********BASE DE DATOS CREADA**********");
            System.out.println("**********TABLA DEPORTE CREADA**********");
            System.out.println("**********TABLA DEPORTISTA CREADA**********");
            System.out.println("**********TABLA EQUIPO CREADA**********");
            System.out.println("**********TABLA OLIMPIADA CREADA**********");
            System.out.println("**********TABLA EVENTO CREADA**********");
            System.out.println("**********TABLA PARTICIPACION CREADA**********");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearBDSQLite() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:/C:/DM2/ADAT/24-25_ADAT/Tema3/Practica/olimpiadas4.db");
            String sqlBDDeporte = "CREATE TABLE Deporte (" +
                    "id_deporte int(11) NOT NULL," +
                    "nombre varchar(100)  NOT NULL," +
                    "PRIMARY KEY (id_deporte)" +
                    ");";
            String sqlBDDeportista = "CREATE TABLE Deportista (" +
                    "id_deportista int(11) NOT NULL," +
                    "nombre varchar(150)  NOT NULL," +
                    "sexo text check(sexo in ('M','F'))  NOT NULL," +
                    "peso int(11) DEFAULT NULL," +
                    "altura int(11) DEFAULT NULL," +
                    "PRIMARY KEY (id_deportista)" +
                    ");";
            String sqlBDEquipo = "CREATE TABLE Equipo (" +
                    "id_equipo int(11) NOT NULL," +
                    "nombre varchar(50)  NOT NULL," +
                    "iniciales varchar(3)  NOT NULL," +
                    "PRIMARY KEY (id_equipo)" +
                    ");";
            String sqlBDOlimpiada = "CREATE TABLE Olimpiada (" +
                    "id_olimpiada int(11) NOT NULL," +
                    "nombre varchar(11)  NOT NULL," +
                    "anio smallint(6) NOT NULL," +
                    "temporada text check(temporada in ('Summer','Winter'))  NOT NULL," +
                    "ciudad varchar(50)  NOT NULL ," +
                    "PRIMARY KEY (id_olimpiada)" +
                    ");";
            String sqlBDEvento = "CREATE TABLE Evento (" +
                    "id_evento int(11) NOT NULL," +
                    "nombre varchar(150) NOT NULL," +
                    "id_olimpiada int(11) NOT NULL," +
                    "id_deporte int(11) NOT NULL," +
                    "PRIMARY KEY (id_evento)," +
                    "CONSTRAINT `FK_Evento_Deporte` FOREIGN KEY (id_deporte) REFERENCES Deporte (id_deporte)," +
                    "CONSTRAINT `FK_Evento_Olimpiada` FOREIGN KEY (id_olimpiada) REFERENCES Olimpiada (id_olimpiada)" +
                    ");";
            String sqlBDParticipacion = "CREATE TABLE Participacion (" +
                    "id_deportista int(11) NOT NULL," +
                    "id_evento int(11) NOT NULL," +
                    "id_equipo int(11) NOT NULL," +
                    "PRIMARY KEY (id_deportista, id_evento)," +
                    "CONSTRAINT `FK_Participacion_Deportista` FOREIGN KEY (id_deportista) REFERENCES Deportista (id_deportista)," +
                    "CONSTRAINT `FK_Participacion_Equipo` FOREIGN KEY (id_equipo) REFERENCES Equipo (id_equipo)," +
                    "CONSTRAINT `FK_Participacion_Evento` FOREIGN KEY (id_evento) REFERENCES Evento (id_evento)" +
                    ");";

            Statement sentenciaBDCrear = conexion.createStatement();

            sentenciaBDCrear.executeUpdate(sqlBDDeporte);
            sentenciaBDCrear.executeUpdate(sqlBDDeportista);
            sentenciaBDCrear.executeUpdate(sqlBDEquipo);
            sentenciaBDCrear.executeUpdate(sqlBDOlimpiada);
            sentenciaBDCrear.executeUpdate(sqlBDEvento);
            sentenciaBDCrear.executeUpdate(sqlBDParticipacion);

            System.out.println("**********BASE DE DATOS CREADA**********");
            System.out.println("**********TABLA DEPORTE CREADA**********");
            System.out.println("**********TABLA DEPORTISTA CREADA**********");
            System.out.println("**********TABLA EQUIPO CREADA**********");
            System.out.println("**********TABLA OLIMPIADA CREADA**********");
            System.out.println("**********TABLA EVENTO CREADA**********");
            System.out.println("**********TABLA PARTICIPACION CREADA**********");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listadoDeportistasDiferenteDeporte() {
    }

    public void listadoDeportistasParticipantes() {
    }

    public void aniadirDeportistaAParticipacion() {
    }

    public void eliminarParticipacion() {
    }
}
