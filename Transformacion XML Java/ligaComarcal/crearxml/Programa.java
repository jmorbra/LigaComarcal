package ligaComarcal.crearxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Programa {

	private static final String PATH_FICHERO = "/home/jorge/xml-workspace/ligaComarcal.xml";
	private static final String[] nombresEquipos = { "Carrascalejo", "Mohedas", "Puente del Arzobispo",
			"Villar del Pedroso", "Valdelacasa", "Aldeanovita", "Peraleda", "Garvin" };
	private static final String[] nombresEntrenadores = { "Crispin", "Pedro", "Pepe", "Juan", "Jose", "Pablo", "Paco",
			"Ivan" };
	private static final String[][] partidosJornadas= {{"Carrascalejo","Mohedas","Puente del Arzobispo","Villar del Pedroso","Valdelacasa","Aldeanovita","Peraleda","Garvin"},
			{"Carrascalejo","Garvin","Mohedas","Puente del Arzobispo","Villar del Pedroso","Valdelacasa","Aldeanovita","Peraleda"},
			{"Carrascalejo","Peraleda","Garvin","Mohedas","Puente del Arzobispo","Villar del Pedroso","Valdelacasa","Aldeanovita"},
			{"Carrascalejo","Aldeanovita","Peraleda","Garvin","Mohedas","Puente del Arzobispo","Villar del Pedroso","Valdelacasa"}};

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("liga");
			doc.appendChild(root);

			Element jornadas = doc.createElement("jornadas");
			root.appendChild(jornadas);
			for (int i = 1; i <= 4; i++) {
				Element jornada = doc.createElement("jornada");
				jornadas.appendChild(jornada);

				Attr numeroJornada = doc.createAttribute("numeroJornada");
				numeroJornada.setValue(String.valueOf(i));
				jornada.setAttributeNode(numeroJornada);
				
				Element partidos = doc.createElement("partidos");
				jornada.appendChild(partidos);
				int indiceArray = 0;
				for (int j = 1; j <= 4; j++) {
					
					Element partido = doc.createElement("partido");
					partidos.appendChild(partido);
					Attr numeroPartido = doc.createAttribute("numeroPartido");
					numeroPartido.setValue(String.valueOf(j));
					partido.setAttributeNode(numeroPartido);
					
					Element equipoLocal = doc.createElement("equipoLocal");
					partido.appendChild(equipoLocal);
					Element nombreLocal = doc.createElement("nombreLocal");
					nombreLocal.setTextContent(partidosJornadas[i-1][indiceArray]);
					equipoLocal.appendChild(nombreLocal);
					Element golesLocal = doc.createElement("golesLocal");
					int cantidadGolesLocal = 3;
					golesLocal.setTextContent(String.valueOf(cantidadGolesLocal));
					equipoLocal.appendChild(golesLocal);
					Element minutoGol1 = doc.createElement("minutoGol");
					minutoGol1.setTextContent(String.valueOf(20));
					equipoLocal.appendChild(minutoGol1);
					Element minutoGol2 = doc.createElement("minutoGol");
					minutoGol2.setTextContent(String.valueOf(30));
					equipoLocal.appendChild(minutoGol2);
					Element minutoGol3 = doc.createElement("minutoGol");
					minutoGol3.setTextContent(String.valueOf(35));
					equipoLocal.appendChild(minutoGol3);

					Element equipoVisitante = doc.createElement("equipoVisitante");
					partido.appendChild(equipoVisitante);
					Element nombreVisitante = doc.createElement("nombreVisitante");
					nombreVisitante.setTextContent(partidosJornadas[i-1][indiceArray+1]);
					equipoVisitante.appendChild(nombreVisitante);
					Element golesVisitante = doc.createElement("golesVisitante");
					int cantidadGolesVisitante = 1;
					golesVisitante.setTextContent(String.valueOf(cantidadGolesVisitante));
					equipoVisitante.appendChild(golesVisitante);
					Element minutoGol = doc.createElement("minutoGol");
					minutoGol.setTextContent(String.valueOf(50));
					equipoVisitante.appendChild(minutoGol);
					indiceArray+=2;
					
				}

				Element ranking = doc.createElement("ranking");
				jornada.appendChild(ranking);

				Element equipos = doc.createElement("equipos");
				ranking.appendChild(equipos);

				for (int j=0;j<nombresEntrenadores.length;j++) {
					Element equipo1 = doc.createElement("equipo");
					equipos.appendChild(equipo1);
					Element nombreEquipo1 = doc.createElement("nombreEquipo");
					nombreEquipo1.setTextContent(nombresEquipos[j]);
					equipo1.appendChild(nombreEquipo1);
					Element golesAFavor1 = doc.createElement("golesAFavor");
					golesAFavor1.setTextContent("3");
					equipo1.appendChild(golesAFavor1);
					Element golesEnContra1 = doc.createElement("golesEnContra");
					golesEnContra1.setTextContent(String.valueOf(1));
					equipo1.appendChild(golesEnContra1);
					Element partidosGanados1 = doc.createElement("partidosGanados");
					partidosGanados1.setTextContent(String.valueOf(1));
					equipo1.appendChild(partidosGanados1);
					Element partidosEmpatados1 = doc.createElement("partidosEmpatados");
					partidosEmpatados1.setTextContent(String.valueOf(0));
					equipo1.appendChild(partidosEmpatados1);
					Element partidosPerdidos1 = doc.createElement("partidosPerdidos");
					partidosPerdidos1.setTextContent(String.valueOf(0));
					equipo1.appendChild(partidosPerdidos1);
				}
			}
			Element participantes = doc.createElement("participante");
			root.appendChild(participantes);

			for (int i = 0; i < nombresEntrenadores.length; i++) {
				Element equipoParticipante = doc.createElement("equipoParticipante");
				participantes.appendChild(equipoParticipante);
				Element nombreEquipoParticipante = doc.createElement("nombreEquipoParticipante");
				nombreEquipoParticipante.setTextContent(nombresEquipos[i]);
				equipoParticipante.appendChild(nombreEquipoParticipante);
				Element nombreEntrenadorParticipante = doc.createElement("nombreEntrenadorParticipante");
				nombreEntrenadorParticipante.setTextContent(nombresEntrenadores[i]);
				equipoParticipante.appendChild(nombreEntrenadorParticipante);
			}

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer();
			DOMSource domS = new DOMSource(doc);
			StreamResult srt = new StreamResult(PATH_FICHERO);

			tr.transform(domS, srt);
			System.out.printf("\n\nConversion realizada correctamente\n");

		} catch (ParserConfigurationException e) {
			System.out.printf("%s\n", e.getMessage());
		} catch (TransformerConfigurationException e) {
			System.out.printf("%s\n", e.getMessage());
		} catch (TransformerException e) {
			System.out.printf("%s\n", e.getMessage());
		}
	}
}
