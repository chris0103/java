package org.chris.study.xml.jaxb;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.chris.study.xml.XMLProcessor;

public class JaxbProcessor implements XMLProcessor<File> {
	
	private JAXBContext jaxbContext;
	private Marshaller jaxbMarshaller;
	private Unmarshaller jaxbUnmarshaller;

	public File marshal() throws JAXBException {
		Countries countries = new Countries();
		
		List<Country> countryList = new ArrayList<>();
		
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setContinent("Europe");
		spain.setImportance(1);
		spain.setFoundation(LocalDate.of(1469, 10, 19));
		spain.setPopulation(45000000);
		
		Country usa = new Country();
		usa.setName("USA");
		usa.setCapital("Washington");
		usa.setContinent("America");
		usa.setImportance(0);
		usa.setFoundation(LocalDate.of(1776, 7, 4));
		usa.setPopulation(320000000);
		
		countryList.add(spain);
		countryList.add(usa);
		countries.setCountries(countryList);

		
		/* marshaling of java objects in XML (standard output and output to file) */
		jaxbMarshaller.marshal(countries, System.out);
		File output = new File("countryList.xml");
		jaxbMarshaller.marshal(countries, output);
		return output;
	}
	
	public void unmarshal(File xml) throws JAXBException {
		Countries countries = (Countries) jaxbUnmarshaller.unmarshal(xml);
		System.out.println(countries);
	}

	@Override
	public void setup() throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Country.class, Countries.class);
		jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		/* set this flag to true to format the output */
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}

	@Override
	public void walkDocument(File document) throws Exception {
		unmarshal(document);
	}

	@Override
	public File createDocument() throws JAXBException {
		return marshal();
	}

	@Override
	public Object modifyDocument(File document, String path, Object value) {
		return null;
	}
}
