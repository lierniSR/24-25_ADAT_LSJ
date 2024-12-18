package es.liernisarraoa;

import com.db4o.ObjectContainer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import es.liernisarraoa.BBDD.DB4O;
import es.liernisarraoa.DAO.*;
import es.liernisarraoa.Entidades.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PracticaDB4O {

    public static void cargarDatos(File ruta, ObjectContainer db) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            List<String[]> lineas = reader.readAll();
            lineas.remove(0); // Remover cabecera
            for (String[] linea : lineas) {
                Deporte deporte = DaoDeporte.conseguirPorNombre(linea[12], db);
                if (deporte == null) {
                    deporte = new Deporte(linea[12]);
                }
                Deportista deportista = DaoDeportista.conseguirPorNombre(linea[1], db);
                if (deportista == null) {
                    float f;
                    try {
                        f = Float.parseFloat(linea[5]);
                    } catch (NumberFormatException e) {
                        f = 0f;
                    }
                    int i;
                    try {
                        i = Integer.parseInt(linea[4]);
                    } catch (NumberFormatException e) {
                        i = 0;
                    }
                    deportista = new Deportista(linea[1], linea[2].charAt(0), f, i);
                }
                Equipo equipo = DaoEquipo.conseguirPorNombre(linea[6], db);
                if (equipo == null) {
                    equipo = new Equipo(linea[6], linea[7]);
                }
                Olimpiada olimpiada = DaoOlimpiada.conseguirPorNombre(linea[8], db);
                if (olimpiada == null) {
                    int i;
                    try {
                        i = Integer.parseInt(linea[9]);
                    } catch (NumberFormatException e) {
                        i = 0;
                    }
                    olimpiada = new Olimpiada(linea[8], i, linea[10], linea[11]);
                }
                Evento evento = DaoEvento.conseguirPorNombre(linea[13], db);
                if (evento == null) {
                    evento = new Evento(linea[13], olimpiada, deporte);
                }
                Participacion participacion = DaoParticipacion.conseguirPorDeportistaEvento(deportista, evento, db);
                if (participacion == null) {
                    int i;
                    try {
                        i = Integer.parseInt(linea[3]);
                    } catch (NumberFormatException e) {
                        i = 0;
                    }
                    participacion = new Participacion(deportista, evento, equipo, i, linea[14]);
                }
                DaoDeporte.insertar(deporte, db);
                System.out.println("Deporte insertado");
                DaoDeportista.insertar(deportista, db);
                System.out.println("Deportista insertado");
                DaoEquipo.insertar(equipo, db);
                System.out.println("Equipo insertado");
                DaoEvento.insertar(evento, db);
                System.out.println("Evento insertado");
                DaoOlimpiada.insertar(olimpiada, db);
                System.out.println("Olimpiada insertado");
                DaoParticipacion.insertar(participacion, db);
                System.out.println("Participacion insertado");
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ObjectContainer db = DB4O.conexion();
        Scanner scanner = new Scanner(System.in);
        Deporte d = new Deporte();
        File f = null;
        try {
            f = new File(d.getClass().getResource("/csv/athlete_events-cleaned.csv").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Descomentar para cargar los datos
        //cargarDatos(f, db);
        System.out.println("Por favor inserte el numero correspondido");
        System.out.println("1 --> Listado de deportistas participantes:");
        System.out.println("2 --> Modificar medalla deportista:");
        System.out.println("3 --> Añadir deportista/participación::");
        System.out.println("4 --> Eliminar participación:");
        System.out.println("0 --> SALIR:");
        int respuesta = scanner.nextInt();
        scanner.nextLine();
        switch (respuesta) {
            case 1:
                listar(scanner, db);
                break;
            case 2:
                modificarMedalla(db, scanner);
                break;
            case 3:
                aniadirParticipacion(db, scanner);
                break;
            case 4:
                eliminarParticipacion(db, scanner);
                break;
        }
        db.close();
    }

    private static void eliminarParticipacion(ObjectContainer db, Scanner scanner) {
        int resp = 0;
        List<Deportista> deportistas = null;
        do {
            System.out.println("Inserte el nombre del deportista:");
            String nombre = scanner.nextLine();
            deportistas = DaoDeportista.conseguirPorFragmentoNombre(nombre, db);
            if (deportistas == null) {
                System.out.println("No se ha encontrado ningún deportista.");

            } else {
                for (int i = 0; i < deportistas.size(); i++) {
                    System.out.println((i + 1) + " " + deportistas.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            }
        } while (resp < 1 || resp > deportistas.size());
        Deportista deportista = deportistas.get(resp - 1);
        List<Participacion> participaciones = DaoParticipacion.conseguirPorDeportista(deportista, db);
        do {
            System.out.println("Elige la participacion por número:");
            for (int i = 0; i < participaciones.size(); i++) {
                System.out.println((i + 1) + " " + participaciones.get(i).getEvento().getNombre());
            }
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp < 1 || resp > participaciones.size());
        Participacion p = participaciones.get(resp - 1);
        DaoParticipacion.eliminar(deportista, p.getEvento(), db);
    }

    private static void aniadirParticipacion(ObjectContainer db, Scanner scanner) {
        int resp = 0;
        List<Deportista> deportistas = null;
        boolean nuevo = false;
        Deportista deportista = null;
        do {
            System.out.println("Inserte el nombre del deportista:");
            String nombre = scanner.nextLine();
            deportistas = DaoDeportista.conseguirPorFragmentoNombre(nombre, db);
            if (deportistas == null) {
                Deportista almacenarDeportista = new Deportista();
                almacenarDeportista.setNombre(nombre);
                DaoDeportista.insertar(almacenarDeportista, db);
                deportista = almacenarDeportista;
                nuevo = true;
            } else {
                for (int i = 0; i < deportistas.size(); i++) {
                    System.out.println((i + 1) + " " + deportistas.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            }
        } while (((resp < 1 || resp > deportistas.size()) && !nuevo));
        if (!nuevo) {
            deportista = deportistas.get(resp - 1);
        }
        String temporada = "Summer";
        do {
            System.out.println("Elige el número de la temporada:\n1 Winter\n2 Summer");
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp != 1 && resp != 2);
        if (resp == 1) {
            temporada = "Winter";
        }
        List<Olimpiada> olimpiadas = DaoOlimpiada.conseguirPorTemporada(temporada, db);
        do {
            System.out.println("Elige el número de la edición olímpica:");
            for (int i = 0; i < olimpiadas.size(); i++) {
                System.out.println((i + 1) + " " + olimpiadas.get(i).getNombre());
            }
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp < 1 || resp > olimpiadas.size());
        Olimpiada olimpiada = olimpiadas.get(resp - 1);
        List<Evento> eventos = DaoEvento.conseguirPorOlimpiada(olimpiada, db);
        if (eventos.isEmpty()) {
            System.out.println("No se han encontrado deportes en esa olimpiada");
        } else {
            ArrayList<Deporte> deportesDisponibles = new ArrayList<Deporte>();
            for (Evento e : eventos) {
                if (!deportesDisponibles.contains(e.getDeporte())) {
                    deportesDisponibles.add(e.getDeporte());
                }
            }
            do {
                System.out.println("Elige el número el deporte");
                for (int i = 0; i < deportesDisponibles.size(); i++) {
                    System.out.println((i + 1) + " " + deportesDisponibles.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            } while (resp < 1 || resp > deportesDisponibles.size());
            Deporte deporte = deportesDisponibles.get(resp - 1);
            List<Evento> eventosConFiltro =
                    DaoEvento.conseguirPorOlimpiadaDeporte(olimpiada, deporte, db);
            do {
                System.out.println("Elige el evento");
                for (int i = 0; i < eventosConFiltro.size(); i++) {
                    System.out.println((i + 1) + " " + eventosConFiltro.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            } while (resp < 1 || resp > eventosConFiltro.size());
            Evento evento = eventosConFiltro.get(resp - 1);
            DaoParticipacion.insertar(new Participacion(deportista, evento, new Equipo("Baldur's Gate 3", "BG3"), 0, temporada), db);
        }
    }

    private static void modificarMedalla(ObjectContainer db, Scanner scanner) {
        int resp = 0;
        List<Deportista> deportistas = null;
        do {
            System.out.println("Inserte el nombre del deportista a buscar");
            String nombre = scanner.nextLine();
            deportistas = DaoDeportista.conseguirPorFragmentoNombre(nombre, db);
            if (deportistas == null) {
                System.out.println("No se ha encontrado deportistas con el nombre insertado");

            } else {
                for (int i = 0; i < deportistas.size(); i++) {
                    System.out.println((i + 1) + " " + deportistas.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            }
        } while (resp < 1 || resp > deportistas.size());
        Deportista deportista = deportistas.get(resp - 1);
        List<Participacion> participaciones = DaoParticipacion.conseguirPorDeportista(deportista, db);
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        for (Participacion p : participaciones) {
            if (!eventos.contains(p)) {
                eventos.add(p.getEvento());
            }
        }
        do {
            System.out.println("Elige el evento");
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println((i + 1) + " " + eventos.get(i).getNombre());
            }
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp < 1 || resp > eventos.size());
        Evento e = eventos.get(resp - 1);
        do {
            System.out.println("Elige el número de la medalla elegida.");
            System.out.println("1 Gold\n2 Silver\n3 Bronze\n4 NA");
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp < 1 || resp > 4);
        String medalla = "NA";
        switch (resp) {
            case 1:
                medalla = "Gold";
                break;
            case 2:
                medalla = "Silver";
                break;
            case 3:
                medalla = "Bronze";
                break;
        }
        DaoParticipacion.actualizarMedallas(medalla, deportista, e, db);
    }

    private static void listar(Scanner scanner, ObjectContainer db) {
        int resp = 0;
        String temporada = "Summer";
        do {
            System.out.println("Elige el número de la temporada:\n1 Winter\n2 Summer");
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp != 1 && resp != 2);
        if (resp == 1) {
            temporada = "Winter";
        }
        List<Olimpiada> olimpiadas = DaoOlimpiada.conseguirPorTemporada(temporada, db);
        do {
            System.out.println("Elige el número de la edición olímpica:");
            for (int i = 0; i < olimpiadas.size(); i++) {
                System.out.println((i + 1) + " " + olimpiadas.get(i).getNombre());
            }
            resp = scanner.nextInt();
            scanner.nextLine();
        } while (resp < 1 || resp > olimpiadas.size());
        Olimpiada olimpiada = olimpiadas.get(resp - 1);
        List<Evento> eventos = DaoEvento.conseguirPorOlimpiada(olimpiada, db);
        if (eventos.size() == 0) {
            System.out.println("No hay deportes en esa olimpiada");
        } else {
            ArrayList<Deporte> deportesDisponibles = new ArrayList<Deporte>();
            for (Evento e : eventos) {
                if (!deportesDisponibles.contains(e.getDeporte())) {
                    deportesDisponibles.add(e.getDeporte());
                }
            }
            do {
                System.out.println("Elige el número del deporte");
                for (int i = 0; i < deportesDisponibles.size(); i++) {
                    System.out.println((i + 1) + " " + deportesDisponibles.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            } while (resp < 1 || resp > deportesDisponibles.size());
            Deporte deporte = deportesDisponibles.get(resp - 1);
            List<Evento> eventosConFiltro =
                    DaoEvento.conseguirPorOlimpiadaDeporte(olimpiada, deporte, db);
            do {
                System.out.println("Elige el número del evento");
                for (int i = 0; i < eventosConFiltro.size(); i++) {
                    System.out.println((i + 1) + " " + eventosConFiltro.get(i).getNombre());
                }
                resp = scanner.nextInt();
                scanner.nextLine();
            } while (resp < 1 || resp > eventosConFiltro.size());
            Evento evento = eventosConFiltro.get(resp - 1);
            List<Participacion> participaciones =
                    DaoParticipacion.conseguirPorEvento(evento, db);
            ArrayList<Deportista> deportistas = new ArrayList<Deportista>();
            for (Participacion par : participaciones) {
                if (!deportistas.contains(par.getDeportista())) {
                    deportistas.add(par.getDeportista());
                }
            }
            for (int i = 0; i < deportistas.size(); i++) {
                Deportista dep = deportistas.get(i);
                Participacion par =
                        DaoParticipacion.conseguirPorDeportistaEvento(dep, evento, db);
                System.out.println("Nombre: " + dep.getNombre() + "\n Altura: " +
                        dep.getAltura() + "\n Peso: " + dep.getPeso() + "\n Edad: " +
                        par.getEdad() + "\n Equipo: " + par.getEquipo().getNombre() +
                        "\n Medalla: " + par.getMedalla());
            }
        }

    }
}