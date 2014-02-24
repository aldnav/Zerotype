package zero.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * General utility for retrieving data.
 * 
 * @author aldnav
 */
public class XMLParser {
	
	/**
	 * Retrieve preferences.
	 * 
	 * @param FilePath
	 * @return Preference
	 */
	public static Preference pullPreference(String url) {
		Preference preference = null;
		
		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();
			InputStream input = new FileInputStream(url);
			XMLEventReader inputEventReader = factory.createXMLEventReader(input);			

			while(inputEventReader.hasNext()) {
				XMLEvent event = inputEventReader.nextEvent();
				
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					String startElementName = startElement.getName().getLocalPart();
					if (startElementName.equals("environment")) {
						preference = new Preference();
					}
					if (startElementName.equals("population-size")) {
						event = inputEventReader.nextEvent();  
                        preference.populationSize = Integer.parseInt(event.asCharacters().getData());  
					}
					if (startElementName.equals("infection-rate")) {
						event = inputEventReader.nextEvent();  
                        preference.infectionRate = Double.parseDouble(event.asCharacters().getData());  
					}
					if (startElementName.equals("initial-infected")) {
						event = inputEventReader.nextEvent();  
                        preference.initialInfected = Integer.parseInt(event.asCharacters().getData());  
					}
					if (startElementName.equals("initial-recovered")) {
						event = inputEventReader.nextEvent();  
                        preference.initialRecovered = Integer.parseInt(event.asCharacters().getData());  
					}
					if (startElementName.equals("run-duration")) {
						event = inputEventReader.nextEvent();  
                        preference.runDuration = Integer.parseInt(event.asCharacters().getData());  
					}
					if (startElementName.equals("in-years")) {
						event = inputEventReader.nextEvent();  
                        preference.inYears = Boolean.parseBoolean(event.asCharacters().getData());  
					}
				}				
			}
			if (!preference.inYears)
				preference.runDuration = preference.runDuration * 365;
		} catch (Exception e) {
			System.out.println("Caught");
			e.printStackTrace();
		}
		return preference;
	}

}