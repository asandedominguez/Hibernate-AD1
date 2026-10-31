package utils;

import model.Adestrador;
import model.Pokedex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class XmlExportUtil {

    public static void pokemonsToXML(List<Pokedex> pokemons, String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("PokedexList");
            doc.appendChild(root);

            if (pokemons != null) {
                for (Pokedex p : pokemons) {
                    Element pokemonElem = doc.createElement("Pokemon");

                    Element id = doc.createElement("Id");
                    id.appendChild(doc.createTextNode(String.valueOf(p.getId())));
                    pokemonElem.appendChild(id);

                    Element nome = doc.createElement("Nome");
                    nome.appendChild(doc.createTextNode(p.getNome() != null ? p.getNome() : ""));
                    pokemonElem.appendChild(nome);

                    Element peso = doc.createElement("Peso");
                    peso.appendChild(doc.createTextNode(p.getPeso() != null ? p.getPeso().toString() : ""));
                    pokemonElem.appendChild(peso);

                    Element misc = doc.createElement("Misc");
                    misc.appendChild(doc.createTextNode(p.getMisc() != null ? p.getMisc() : ""));
                    pokemonElem.appendChild(misc);

                    root.appendChild(pokemonElem);
                }
            }

            guardarXML(doc, filename);
            System.out.println("Pokemons exportados correctamente a " + filename);

        } catch (Exception e) {
            System.out.println("Erro ao exportar pokemons a XML: " + e.getMessage());
        }
    }

    public static void adestradoresToXML(List<Adestrador> adestradores, String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("AdestradorList");
            doc.appendChild(root);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (adestradores != null) {
                for (Adestrador a : adestradores) {
                    Element adestradorElem = doc.createElement("Adestrador");

                    Element id = doc.createElement("Id");
                    id.appendChild(doc.createTextNode(String.valueOf(a.getId())));
                    adestradorElem.appendChild(id);

                    Element nome = doc.createElement("Nome");
                    nome.appendChild(doc.createTextNode(a.getNome() != null ? a.getNome() : ""));
                    adestradorElem.appendChild(nome);

                    Element nacemento = doc.createElement("Nacemento");
                    String dataStr = a.getNacemento() != null ? sdf.format(a.getNacemento()) : "";
                    nacemento.appendChild(doc.createTextNode(dataStr));
                    adestradorElem.appendChild(nacemento);

                    root.appendChild(adestradorElem);
                }
            }

            guardarXML(doc, filename);
            System.out.println("Adestradores exportados correctamente a " + filename);

        } catch (Exception e) {
            System.out.println("Erro ao exportar adestradores a XML: " + e.getMessage());
        }
    }

    private static void guardarXML(Document doc, String filename) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }
}