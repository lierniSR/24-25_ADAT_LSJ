import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Actividad6 {
    public static void main(String[] args) {
        try {
            //Con la clase SacParser creamos un objeto SAXParser y lo utilizamos para parsear el fichero XML
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse("empleados.xml", new EmpleadoHandler());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al parsear el fichero XML: " + e.getMessage());
        }
    }
}

//Creamos la clase EmpleadoHandle que extiende de DefaultHandle y sobreescribe los metodos startElement, endElement y characters
class EmpleadoHandler extends DefaultHandler {
    private String id;
    private String nombre;
    private String apellido;
    private String departamento;

    //Verificamos si es empleado y reseteamos las variables
    @Override
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("empleado")) {
            id = "";
            nombre = "";
            apellido = "";
            departamento = "";
        }
    }

    //Verificamos si ele lemento es empleado y mostramos los valores
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("empleado")) {
            System.out.println("Empleado:");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Departamento: " + departamento);
            System.out.println();
        }
    }

    //Obtenemos el texto del elemento y lo asignamos a la variable correspondiente
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length);
        if (id.isEmpty()) {
            id = texto;
        } else if (nombre.isEmpty()) {
            nombre = texto;
        } else if (apellido.isEmpty()) {
            apellido = texto;
        } else if (departamento.isEmpty()) {
            departamento = texto;
        }
    }
}
