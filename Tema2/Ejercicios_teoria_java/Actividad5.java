import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Actividad5 {
        public static void main(String[] args) {
            try (RandomAccessFile raf = new RandomAccessFile("Empleados3.dat", "rw")) {
                Empleado empleado1 = new Empleado("E001", "Juan", "Pérez", "Ventas");
                Empleado empleado2 = new Empleado("E002", "María", "Gómez", "Marketing");
                Empleado empleado3 = new Empleado("E003", "Pedro", "López", "Desarrollo");
                Empleado.escribirEmpleado(raf, empleado1);
                Empleado.escribirEmpleado(raf, empleado2);
                Empleado.escribirEmpleado(raf, empleado3);
                // Creamos un documento XML vacío
                DocumentBuilderFactory XML = DocumentBuilderFactory.newInstance();
                Document document = XML.newDocumentBuilder().newDocument();

                // Creamos el elemento raíz
                Element raiz = document.createElement("empleados");
                document.appendChild(raiz);

                // Leemos el fichero de RandomAccess y creamos los elementos XML
                raf.seek(0); // Nos posicionamos al principio del archivo
                while (raf.getFilePointer() < raf.length()) {
                    try {
                        Empleado empleado = leerEmpleado(raf);
                        Element empleadoElement = document.createElement("empleado");
                        raiz.appendChild(empleadoElement);

                        Element idElement = document.createElement("id");
                        idElement.setTextContent(empleado.getId());
                        empleadoElement.appendChild(idElement);

                        Element nombreElement = document.createElement("nombre");
                        nombreElement.setTextContent(empleado.getNombre());
                        empleadoElement.appendChild(nombreElement);

                        Element apellidoElement = document.createElement("apellido");
                        apellidoElement.setTextContent(empleado.getApellido());
                        empleadoElement.appendChild(apellidoElement);

                        Element departamentoElement = document.createElement("departamento");
                        departamentoElement.setTextContent(empleado.getDepartamento());
                        empleadoElement.appendChild(departamentoElement);
                    } catch (IOException e) {
                        System.out.println("Error al leer el empleado: " + e.getMessage());
                        break;
                    }
                }

                // Escribimos el documento XML en un fichero
                TransformerFactory transformacion = TransformerFactory.newInstance();
                Transformer transformer = transformacion.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult resultado = new StreamResult(new java.io.File("empleados.xml"));
                transformer.transform(source, resultado);
            } catch (IOException | ParserConfigurationException | TransformerException e) {
                System.out.println("Error al crear el documento XML: " + e.getMessage());
            }
        }

        private static Empleado leerEmpleado(RandomAccessFile raf) throws IOException {
            String id = raf.readUTF();
            String nombre = raf.readUTF();
            String apellido = raf.readUTF();
            String departamento = raf.readUTF();
            return new Empleado(id, nombre, apellido, departamento);
        }
}
