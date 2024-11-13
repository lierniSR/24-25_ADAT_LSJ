import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bbdd.ConexionBBDD;
import dao.*;
import model.*;

public class MenuOlimpiadas {
    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        ConexionBBDD conexion = new ConexionBBDD();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú
            System.out.println("Menú:");
            System.out.println("1. Crear BBDD MySQL");
            System.out.println("2. Listado de deportistas en diferentes deportes");
            System.out.println("3. Listado de deportistas participantes");
            System.out.println("4. Modificar medalla deportista");
            System.out.println("5. Añadir deportista/participación");
            System.out.println("6. Eliminar participación");
            System.out.println("0. Terminar programa");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Dime la ruta del archivo csv");
                    String path = scanner.nextLine();
                    DaoCrearTablaDocker.crearLaBBDD(path);
                    break;
                case 2:
                    ListDifferentSportsDeportists();

                    break;
                case 3:
                    int resp = 0;
                    String temporada;
                    int idDeporte;
                    int idOlimpiada;
                    int idEvento;

                    do {
                        System.out.println("\n==============================");
                        System.out.println("   Selecciona la Temporada:");
                        System.out.println("1. Winter");
                        System.out.println("2. Summer");
                        System.out.println("==============================");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp != 1 && resp != 2);

                    ArrayList<ModeloOlimpiada> lstOlimpiada = DaoOlimpiada.listaOlimpiadasPorTemporada(resp);

                    do {
                        System.out.println("\n==============================");
                        System.out.println("   Elige la Edición Olímpica:");
                        System.out.println("==============================");
                        for (int i = 0; i < lstOlimpiada.size(); i++) {
                            System.out.println((i + 1) + ": " + lstOlimpiada.get(i).toString());
                        }
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstOlimpiada.size());

                    temporada = lstOlimpiada.get(resp - 1).getTemporada();
                    idOlimpiada = Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(
                            lstOlimpiada.get(resp - 1).getNombreOlimpiada(),
                            lstOlimpiada.get(resp - 1).getAnio(),
                            temporada, lstOlimpiada.get(resp - 1).getCiudad()));

                    ArrayList<ModeloDeporte> lstDeporte = DaoDeporte.listaDeportesPorOlimpiada(idOlimpiada);

                    do {
                        System.out.println("\n==============================");
                        System.out.println("   Elige el Deporte:");
                        System.out.println("==============================");
                        for (int i = 0; i < lstDeporte.size(); i++) {
                            System.out.println((i + 1) + ": " + lstDeporte.get(i).toString());
                        }
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstDeporte.size());

                    idDeporte = Integer
                            .parseInt(DaoDeporte.conseguirIdDeporte(lstDeporte.get(resp - 1).getNombreDeporte()));
                    ArrayList<ModeloEvento> lstEventos = DaoEvento.crearListaModelosPorDeporteYOlimpiada(idDeporte,
                            idOlimpiada);

                    do {
                        System.out.println("\n==============================");
                        System.out.println("   Elige el Evento:");
                        System.out.println("==============================");
                        for (int i = 0; i < lstEventos.size(); i++) {
                            System.out.println((i + 1) + ": " + lstEventos.get(i).toString());
                        }
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstEventos.size());

                    idEvento = Integer.parseInt(DaoEvento.conseguirIdEvento(
                            lstEventos.get(resp - 1).getNombreEvento(), idOlimpiada, idDeporte));

                    System.out.println("\n==============================");
                    System.out.println("   Resumen Final:");
                    System.out.println("==============================");
                    System.out.println("   Temporada: " + temporada);
                    System.out.println("   Olimpiada: " + DaoOlimpiada.createOlimpiadaModel(idOlimpiada));
                    System.out.println("   Deporte: " + DaoDeporte.createDeporteModel(idDeporte));
                    System.out.println("   Evento: " + DaoEvento.createById(idEvento));
                    System.out.println("==============================");

                    System.out.println("\n==============================");
                    System.out.println("   Deportistas Participantes:");
                    System.out.println("==============================");

                    ArrayList<String> lstIdDeportistas = DaoParticipacion.darIdDeportista(idEvento);
                    for (String deportista : lstIdDeportistas) {
                        ModeloParticipacion participacion = DaoParticipacion.crearModeloParticipacion(
                                Integer.parseInt(deportista), idEvento);
                        System.out.println("   " + participacion.toString());
                    }

                    System.out.println("\n==============================");
                    System.out.println("   Fin de la consulta.");
                    System.out.println("==============================");

                    break;
                case 4:
                    resp = 0;
                    System.out.println("\n==============================");
                    System.out.println("   Busca el Deportista por Nombre:");
                    System.out.println("==============================");
                    String nombre = scanner.nextLine();
                    ArrayList<ModeloDeportista> lstDeportistas = DaoDeportista.findDeportistaName(nombre);

                    if (lstDeportistas.size() > 0) {
                        do {
                            System.out.println("\n==============================");
                            System.out.println("   Elige el Deportista (usando el número):");
                            System.out.println("==============================");
                            for (int i = 0; i < lstDeportistas.size(); i++) {
                                System.out.println((i + 1) + ": " + lstDeportistas.get(i).getNombreDeportista());
                            }
                            resp = scanner.nextInt();
                            scanner.nextLine();
                        } while (resp < 1 || resp > lstDeportistas.size());

                        ModeloDeportista deportista = lstDeportistas.get(resp - 1);
                        lstEventos = DaoEvento.listaModelosPorId(DaoParticipacion.getIdEvento(
                                Integer.parseInt(DaoDeportista.conseguirIdDeportista(
                                        lstDeportistas.get(resp - 1).getNombreDeportista(),
                                        lstDeportistas.get(resp - 1).getSexo(),
                                        lstDeportistas.get(resp - 1).getPeso(),
                                        lstDeportistas.get(resp - 1).getAltura()))));

                        do {
                            System.out.println("\n==============================");
                            System.out.println("   Elige el Evento (usando el número):");
                            System.out.println("==============================");
                            for (int i = 0; i < lstEventos.size(); i++) {
                                System.out.println((i + 1) + ": " + lstEventos.get(i).getNombreEvento());
                            }
                            resp = scanner.nextInt();
                            scanner.nextLine();
                        } while (resp < 1 || resp > lstEventos.size());

                        ModeloEvento evento = DaoEvento.createById(Integer.parseInt(DaoEvento.conseguirIdEvento(
                                lstEventos.get(resp - 1).getNombreEvento(),
                                Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(
                                        lstEventos.get(resp - 1).getOlimpiada().getNombreOlimpiada(),
                                        lstEventos.get(resp - 1).getOlimpiada().getAnio(),
                                        lstEventos.get(resp - 1).getOlimpiada().getTemporada(),
                                        lstEventos.get(resp - 1).getOlimpiada().getCiudad())),
                                Integer.parseInt(DaoDeporte.conseguirIdDeporte(
                                        lstEventos.get(resp - 1).getDeporte().getNombreDeporte())))));

                        do {
                            System.out.println("\n==============================");
                            System.out.println("   ¿Qué medalla quieres asignar?");
                            System.out.println("1. Ninguna");
                            System.out.println("2. Bronce");
                            System.out.println("3. Plata");
                            System.out.println("4. Oro");
                            System.out.println("==============================");
                            resp = scanner.nextInt();
                            scanner.nextLine();
                        } while (resp != 1 && resp != 2 && resp != 3 && resp != 4);

                        String medalla = "Gold"; // Valor por defecto
                        switch (resp) {
                            case 1:
                                medalla = "NA"; // Ninguna medalla
                                break;
                            case 2:
                                medalla = "Bronze"; // Bronce
                                break;
                            case 3:
                                medalla = "Silver"; // Plata
                                break;
                            case 4:
                                medalla = "Gold"; // Oro
                                break;
                        }

                        DaoParticipacion.editMedal(
                                Integer.parseInt(DaoDeportista.conseguirIdDeportista(
                                        deportista.getNombreDeportista(),
                                        deportista.getSexo(),
                                        deportista.getPeso(),
                                        deportista.getAltura())),
                                Integer.parseInt(DaoEvento.conseguirIdEvento(
                                        evento.getNombreEvento(),
                                        Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(
                                                evento.getOlimpiada().getNombreOlimpiada(),
                                                evento.getOlimpiada().getAnio(),
                                                evento.getOlimpiada().getTemporada(),
                                                evento.getOlimpiada().getCiudad())),
                                        Integer.parseInt(DaoDeporte.conseguirIdDeporte(
                                                evento.getDeporte().getNombreDeporte())))),
                                medalla);

                        System.out.println("\n==============================");
                        System.out.println("   Medalla asignada con éxito.");
                        System.out.println("==============================");

                    } else {
                        System.out.println("\n==============================");
                        System.out.println("   No se encontró ningún deportista con ese nombre.");
                        System.out.println("==============================");
                    }

                    break;
                case 5:
                    int idDeportista = 0;
                    resp = 0;
                    ModeloDeportista deportista;

                    System.out.println("\n==============================");
                    System.out.println("    Buscar Deportista");
                    System.out.println("==============================");
                    System.out.print("Dime el nombre a buscar: ");
                    nombre = scanner.nextLine();
                    lstDeportistas = DaoDeportista.findDeportistaName(nombre);

                    if (lstDeportistas.size() > 0) {
                        do {
                            System.out.println("\n==============================");
                            System.out.println("    Elige al Deportista");
                            System.out.println("==============================");
                            for (int i = 0; i < lstDeportistas.size(); i++) {
                                System.out
                                        .println("   " + (i + 1) + ": " + lstDeportistas.get(i).getNombreDeportista());
                            }
                            System.out.print("Selecciona el número: ");
                            resp = scanner.nextInt();
                            scanner.nextLine();
                        } while (resp < 1 || resp > lstDeportistas.size());
                        deportista = lstDeportistas.get(resp - 1);
                    } else {
                        System.out.println("\nNo hay ningún deportista con ese nombre. Se creará uno nuevo.");
                        System.out.print("Dime el nombre completo: ");
                        nombre = scanner.nextLine();

                        int sexo = 0;
                        do {
                            System.out.println("\n==============================");
                            System.out.println("    Indica el Sexo");
                            System.out.println("==============================");
                            System.out.println("1. Masculino (M)");
                            System.out.println("2. Femenino (F)");
                            System.out.print("Selecciona el número: ");
                            sexo = scanner.nextInt();
                            scanner.nextLine();
                        } while (sexo != 1 && sexo != 2);

                        System.out.print("¿Cuánto pesa? ");
                        int peso = Math.round(scanner.nextFloat());
                        scanner.nextLine();

                        System.out.print("¿Cuánto mide (en cm)? ");
                        int altura = scanner.nextInt();
                        scanner.nextLine();

                        if (sexo == 1) {
                            DaoDeportista.aniadirDeportista(nombre, 'M', peso, altura);
                            deportista = DaoDeportista.createDeportistaModel(
                                    DaoDeportista.conseguirIdDeportista(nombre, 'M', peso, altura));
                        } else {
                            DaoDeportista.aniadirDeportista(nombre, 'F', peso, altura);
                            deportista = DaoDeportista.createDeportistaModel(
                                    DaoDeportista.conseguirIdDeportista(nombre, 'F', peso, altura));
                        }
                    }

                    idDeportista = Integer
                            .parseInt(DaoDeportista.conseguirIdDeportista(deportista.getNombreDeportista(),
                                    deportista.getSexo(), deportista.getPeso(), deportista.getAltura()));

                    do {
                        System.out.println("\n==============================");
                        System.out.println("    Selecciona la Temporada");
                        System.out.println("==============================");
                        System.out.println("1. Winter");
                        System.out.println("2. Summer");
                        System.out.print("Selecciona el número: ");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp != 1 && resp != 2);

                    lstOlimpiada = DaoOlimpiada.listaOlimpiadasPorTemporada(resp);

                    do {
                        System.out.println("\n==============================");
                        System.out.println("    Selecciona la Edición Olímpica");
                        System.out.println("==============================");
                        for (int i = 0; i < lstOlimpiada.size(); i++) {
                            System.out.println("   " + (i + 1) + ": " + lstOlimpiada.get(i).toString());
                        }
                        System.out.print("Selecciona el número: ");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstOlimpiada.size());

                    temporada = lstOlimpiada.get(resp - 1).getTemporada();
                    idOlimpiada = Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(
                            lstOlimpiada.get(resp - 1).getNombreOlimpiada(), lstOlimpiada.get(resp - 1).getAnio(),
                            temporada, lstOlimpiada.get(resp - 1).getCiudad()));

                    lstDeporte = DaoDeporte.listaDeportesPorOlimpiada(idOlimpiada);

                    do {
                        System.out.println("\n==============================");
                        System.out.println("    Selecciona el Deporte");
                        System.out.println("==============================");
                        for (int i = 0; i < lstDeporte.size(); i++) {
                            System.out.println("   " + (i + 1) + ": " + lstDeporte.get(i).toString());
                        }
                        System.out.print("Selecciona el número: ");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstDeporte.size());

                    idDeporte = Integer
                            .parseInt(DaoDeporte.conseguirIdDeporte(lstDeporte.get(resp - 1).getNombreDeporte()));
                    lstEventos = DaoEvento.crearListaModelosPorDeporteYOlimpiada(idDeporte, idOlimpiada);

                    do {
                        System.out.println("\n==============================");
                        System.out.println("    Selecciona el Evento");
                        System.out.println("==============================");
                        for (int i = 0; i < lstEventos.size(); i++) {
                            System.out.println("   " + (i + 1) + ": " + lstEventos.get(i).toString());
                        }
                        System.out.print("Selecciona el número: ");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstEventos.size());

                    idEvento = Integer.parseInt(DaoEvento.conseguirIdEvento(lstEventos.get(resp - 1).getNombreEvento(),
                            idOlimpiada, idDeporte));

                    System.out.print("\nDime la edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();

                    do {
                        System.out.println("\n==============================");
                        System.out.println("    Selecciona la Medalla");
                        System.out.println("==============================");
                        System.out.println("1. Ninguna");
                        System.out.println("2. Bronce");
                        System.out.println("3. Plata");
                        System.out.println("4. Oro");
                        System.out.print("Selecciona el número: ");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp != 1 && resp != 2 && resp != 3 && resp != 4);

                    String medalla = "Gold";
                    switch (resp) {
                        case 1:
                            medalla = "NA";
                            break;
                        case 2:
                            medalla = "Bronze";
                            break;
                        case 3:
                            medalla = "Silver";
                            break;
                    }

                    System.out.print("Dime el nombre de su equipo: ");
                    String nombreEquipo = scanner.nextLine();
                    System.out.print("Dime su abreviación: ");
                    String abreviacion = scanner.nextLine();

                    if (DaoEquipo.conseguirIdEquipo(nombreEquipo, abreviacion) == null) {
                        DaoEquipo.aniadirEquipo(nombreEquipo, abreviacion);
                    }

                    int idEquipo = Integer.parseInt(DaoEquipo.conseguirIdEquipo(nombreEquipo, abreviacion));

                    System.out.println("\n==============================");
                    System.out.println("    Datos Finales de Participación");
                    System.out.println("==============================");
                    System.out.println("ID Evento: " + idEvento);
                    System.out.println("ID Equipo: " + idEquipo);
                    System.out.println("ID Deportista: " + idDeportista);

                    try {
                        DaoParticipacion.aniadirParticipacion(idDeportista, idEvento, idEquipo, edad, medalla);
                        System.out.println("\nParticipación añadida correctamente.");
                    } catch (Exception e) {
                        System.out.println("\nError: Esa participación ya está en la base de datos.");
                    }
                    break;
                case 6:
                resp = 0;
                System.out.println("\n==============================");
                System.out.println("   Búsqueda de Deportista");
                System.out.println("==============================");
                System.out.print("Dime el nombre a buscar: ");
                nombre = scanner.nextLine();
                
                lstDeportistas = DaoDeportista.findDeportistaName(nombre);
                
                if (lstDeportistas.size() > 0) {
                    do {
                        System.out.println("\n==============================");
                        System.out.println("   Selección de Deportista");
                        System.out.println("==============================");
                        for (int i = 0; i < lstDeportistas.size(); i++) {
                            System.out.println("   " + (i + 1) + ": " + lstDeportistas.get(i).getNombreDeportista());
                        }
                        System.out.print("Elige el número correspondiente: ");
                        resp = scanner.nextInt();
                        scanner.nextLine();
                    } while (resp < 1 || resp > lstDeportistas.size());
                
                    deportista = lstDeportistas.get(resp - 1);
                    idDeportista = Integer.parseInt(
                        DaoDeportista.conseguirIdDeportista(
                            deportista.getNombreDeportista(), 
                            deportista.getSexo(), 
                            deportista.getPeso(), 
                            deportista.getAltura()
                        )
                    );
                
                    lstEventos = DaoEvento.listaModelosPorId(
                        DaoParticipacion.getIdEvento(
                            Integer.parseInt(
                                DaoDeportista.conseguirIdDeportista(
                                    lstDeportistas.get(resp - 1).getNombreDeportista(),
                                    lstDeportistas.get(resp - 1).getSexo(),
                                    lstDeportistas.get(resp - 1).getPeso(),
                                    lstDeportistas.get(resp - 1).getAltura()
                                )
                            )
                        )
                    );
                
                    if (lstEventos.size() > 0) {
                        do {
                            System.out.println("\n==============================");
                            System.out.println("   Selección de Evento");
                            System.out.println("==============================");
                            for (int i = 0; i < lstEventos.size(); i++) {
                                System.out.println("   " + (i + 1) + ": " + lstEventos.get(i).getNombreEvento());
                            }
                            System.out.print("Elige el número correspondiente: ");
                            resp = scanner.nextInt();
                            scanner.nextLine();
                        } while (resp < 1 || resp > lstEventos.size());
                
                        ModeloEvento evento = DaoEvento.createById(
                            Integer.parseInt(
                                DaoEvento.conseguirIdEvento(
                                    lstEventos.get(resp - 1).getNombreEvento(),
                                    Integer.parseInt(
                                        DaoOlimpiada.conseguirIdOlimpiada(
                                            lstEventos.get(resp - 1).getOlimpiada().getNombreOlimpiada(),
                                            lstEventos.get(resp - 1).getOlimpiada().getAnio(),
                                            lstEventos.get(resp - 1).getOlimpiada().getTemporada(),
                                            lstEventos.get(resp - 1).getOlimpiada().getCiudad()
                                        )
                                    ),
                                    Integer.parseInt(
                                        DaoDeporte.conseguirIdDeporte(
                                            lstEventos.get(resp - 1).getDeporte().getNombreDeporte()
                                        )
                                    )
                                )
                            )
                        );
                
                        idEvento = Integer.parseInt(
                            DaoEvento.conseguirIdEvento(
                                evento.getNombreEvento(),
                                Integer.parseInt(
                                    DaoOlimpiada.conseguirIdOlimpiada(
                                        evento.getOlimpiada().getNombreOlimpiada(),
                                        evento.getOlimpiada().getAnio(),
                                        evento.getOlimpiada().getTemporada(),
                                        evento.getOlimpiada().getCiudad()
                                    )
                                ),
                                Integer.parseInt(
                                    DaoDeporte.conseguirIdDeporte(
                                        evento.getDeporte().getNombreDeporte()
                                    )
                                )
                            )
                        );
                
                        DaoParticipacion.eliminarParticipacion(idDeportista, idEvento);
                        System.out.println("\n==============================");
                        System.out.println("   Participación Eliminada");
                        System.out.println("==============================");
                        System.out.println("La participación ha sido eliminada con éxito.");
                    } else {
                        System.out.println("\n==============================");
                        System.out.println("   Sin Participaciones");
                        System.out.println("==============================");
                        System.out.println("Ese deportista no tiene participaciones registradas.");
                    }
                } else {
                    System.out.println("\n==============================");
                    System.out.println("   Deportista No Encontrado");
                    System.out.println("==============================");
                    System.out.println("No hay ningún deportista que contenga esa cadena de caracteres en el nombre.");
                }
                break;
                
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no disponible.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }
    /**
     * crear tablas
     * @param statement
     * @throws SQLException
     */
    public static void crearTablas(Statement statement) throws SQLException {
        String sqlCrearTablaDeporte = "CREATE TABLE IF NOT EXISTS `Deporte` (" +
                "`id_deporte` int(11) NOT NULL AUTO_INCREMENT, " +
                "`nombre` varchar(100) NOT NULL, " +
                "PRIMARY KEY (`id_deporte`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        statement.executeUpdate(sqlCrearTablaDeporte);

        String sqlCrearTablaDeportista = "CREATE TABLE IF NOT EXISTS `Deportista` (" +
                "`id_deportista` int(11) NOT NULL AUTO_INCREMENT, " +
                "`nombre` varchar(150) NOT NULL, " +
                "`sexo` enum('M', 'F') NOT NULL, " +
                "`peso` int(11) DEFAULT NULL, " +
                "`altura` int(11) DEFAULT NULL, " +
                "PRIMARY KEY (`id_deportista`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        statement.executeUpdate(sqlCrearTablaDeportista);

        String sqlCrearTablaEquipo = "CREATE TABLE IF NOT EXISTS `Equipo` (" +
                "`id_equipo` int(11) NOT NULL AUTO_INCREMENT, " +
                "`nombre` varchar(50) NOT NULL, " +
                "`iniciales` varchar(3) NOT NULL, " +
                "PRIMARY KEY (`id_equipo`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        statement.executeUpdate(sqlCrearTablaEquipo);

        String sqlCrearTablaOlimpiada = "CREATE TABLE IF NOT EXISTS `Olimpiada` (" +
                "`id_olimpiada` int(11) NOT NULL AUTO_INCREMENT, " +
                "`nombre` varchar(11) NOT NULL, " +
                "`anio` smallint(6) NOT NULL, " +
                "`temporada` enum('Summer', 'Winter') NOT NULL, " +
                "`ciudad` varchar(50) NOT NULL, " +
                "PRIMARY KEY (`id_olimpiada`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        statement.executeUpdate(sqlCrearTablaOlimpiada);

        String sqlCrearTablaEvento = "CREATE TABLE IF NOT EXISTS `Evento` (" +
                "`id_evento` int(11) NOT NULL AUTO_INCREMENT, " +
                "`nombre` varchar(150) NOT NULL, " +
                "`id_olimpiada` int(11) NOT NULL, " +
                "`id_deporte` int(11) NOT NULL, " +
                "PRIMARY KEY (`id_evento`), " +
                "CONSTRAINT `FK_Evento_Deporte` FOREIGN KEY (`id_deporte`) REFERENCES `Deporte` (`id_deporte`), " +
                "CONSTRAINT `FK_Evento_Olimpiada` FOREIGN KEY (`id_olimpiada`) REFERENCES `Olimpiada` (`id_olimpiada`)"
                +
                ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        statement.executeUpdate(sqlCrearTablaEvento);

        String sqlCrearTablaParticipacion = "CREATE TABLE IF NOT EXISTS `Participacion` (" +
                "`id_deportista` int(11) NOT NULL, " +
                "`id_evento` int(11) NOT NULL, " +
                "`id_equipo` int(11) NOT NULL, " +
                "`edad` tinyint(4) DEFAULT NULL, " +
                "`medalla` varchar(6) DEFAULT NULL, " +
                "PRIMARY KEY (`id_deportista`, `id_evento`), " +
                "CONSTRAINT `FK_Participacion_Deportista` FOREIGN KEY (`id_deportista`) REFERENCES `Deportista` (`id_deportista`), "
                +
                "CONSTRAINT `FK_Participacion_Equipo` FOREIGN KEY (`id_equipo`) REFERENCES `Equipo` (`id_equipo`), " +
                "CONSTRAINT `FK_Participacion_Evento` FOREIGN KEY (`id_evento`) REFERENCES `Evento` (`id_evento`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        statement.executeUpdate(sqlCrearTablaParticipacion);
    }
    /**
     * cargar datos del csv
     * @param conexion
     * @param archivoCSV
     */
    public static void cargarDatosDesdeCSV(Connection conexion, File archivoCSV) {
        String sqlInsertarDeportista = "INSERT INTO Deportista (nombre, sexo, peso, altura) VALUES (?, ?, ?, ?)";
        String sqlInsertarEquipo = "INSERT INTO Equipo (nombre, iniciales) VALUES (?, ?)";
        String sqlInsertarOlimpiada = "INSERT INTO Olimpiada (nombre, anio, temporada, ciudad) VALUES (?, ?, ?, ?)";
        String sqlInsertarEvento = "INSERT INTO Evento (nombre, id_olimpiada, id_deporte) VALUES (?, ?, ?)";
        String sqlInsertarParticipacion = "INSERT INTO Participacion (id_deportista, id_evento, id_equipo, edad, medalla) VALUES (?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String line;
            br.readLine(); // Leer la línea de encabezado para obtener la estructura del archivo
            PreparedStatement psDeportista = conexion.prepareStatement(sqlInsertarDeportista);
            PreparedStatement psEquipo = conexion.prepareStatement(sqlInsertarEquipo);
            PreparedStatement psOlimpiada = conexion.prepareStatement(sqlInsertarOlimpiada);
            PreparedStatement psEvento = conexion.prepareStatement(sqlInsertarEvento);
            PreparedStatement psParticipacion = conexion.prepareStatement(sqlInsertarParticipacion);

            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");

                // Validar y procesar las columnas, insertar en la tabla correcta
                if (campos.length > 0) {

                    if (campos[5].equals("NA")) {
                        campos[5] = "0";
                    }
                    if (campos[4].equals("NA")) {
                        campos[4] = "0";
                    }

                    campos[5] = Math.round(Float.parseFloat(campos[5])) + "";
                    psDeportista.setString(1, campos[1]); // Nombres
                    psDeportista.setString(2, campos[2]); // Sexo
                    psDeportista.setInt(3, Integer.parseInt(campos[5])); // Peso
                    psDeportista.setInt(4, Integer.parseInt(campos[4])); // Altura

                    // System.out.println(campos[2]);
                    psDeportista.executeUpdate();
                    conexion.commit();
                }
            }

        } catch (IOException | SQLException e) {
            System.out.println(
                    "Error al procesar el archivo CSV o realizar operaciones en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * diferentes deportes
     */
    public static void ListDifferentSportsDeportists() {
        int i = 10;
        System.out.println(DaoDeportista.createDeportistaModel(i + "") + ":");
        ArrayList<ModeloParticipacion> lst = listaParticipaciones(i);
        for (ModeloParticipacion part : lst) {
            System.out.println(part.getEvento().getDeporte().getNombreDeporte() + "," +
                    part.getEdad() + "," + part.getEvento().getNombreEvento() + "," +
                    part.getEquipo().getNombreEquipo() + "," + part.getEvento().getOlimpiada().getNombreOlimpiada()
                    + "," +
                    part.getMedalla());
        }
        i++;
    }
    /**
     * lista de participaciones
     * @param idDeportista
     * @return
     */
    public static ArrayList<ModeloParticipacion> listaParticipaciones(int idDeportista) {
        ArrayList<ModeloParticipacion> listaParticipaciones = new ArrayList<>();

        // Obtener lista de IDs de eventos en los que ha participado el deportista
        List<String> idsEventos = DaoParticipacion.getIdEvento(idDeportista);
        for (String idEvento : idsEventos) {
            int eventoId = Integer.parseInt(idEvento);
            ModeloParticipacion participacion = DaoParticipacion.crearModeloParticipacion(idDeportista, eventoId);
            listaParticipaciones.add(participacion);
        }

        return listaParticipaciones;
    }

}
