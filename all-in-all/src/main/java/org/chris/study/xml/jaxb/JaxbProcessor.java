package org.chris.study.xml.jaxb;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.chris.study.xml.XMLProcessor;
import org.xml.sax.SAXException;

public class JaxbProcessor implements XMLProcessor<File> {

    private JAXBContext jaxbContext;

    private Marshaller jaxbMarshaller;

    private Unmarshaller jaxbUnmarshaller;

    private Schema schema;

    @Override
    public File createDocument() throws JAXBException, SAXException, IOException {
        return marshal();
    }

    public File marshal() throws JAXBException, SAXException, IOException {
        Countries countries = new Countries();
        List<Country> countryList = new ArrayList<>();

        Country spain = new Country();
        spain.setImportance(1);
        spain.setName("Spain");
        spain.setCapital("Madrid");
        // spain.setFoundation(LocalDate.of(1469, 10, 19));
        spain.setContinent("Europe");
        spain.setPopulation(45000000);

        Country usa = new Country();
        usa.setImportance(0);
        usa.setName("USA");
        usa.setCapital("Washington");
        usa.setFoundation(LocalDate.of(1776, 7, 4));
        usa.setContinent("America");
        usa.setPopulation(320000000);

        // validate objects validity explicitly
        JAXBSource sourceSpain = new JAXBSource(jaxbContext, spain);
        JAXBSource sourceUsa = new JAXBSource(jaxbContext, usa);
        Validator validator = schema.newValidator();
        validator.setErrorHandler(new MyErrorHandler());
        validator.validate(sourceSpain);
        validator.validate(sourceUsa);

        countryList.add(spain);
        countryList.add(usa);
        countries.setCountries(countryList);

        /* marshaling of java objects in XML (standard output and output to file) */
        jaxbMarshaller.setEventHandler(new MyValidationEventHandler());
        jaxbMarshaller.marshal(countries, System.out);
        File output = new File("countryList.xml");
        jaxbMarshaller.marshal(countries, output);
        return output;
    }

    @Override
    public Object modifyDocument(File document, String path, Object value) {
        return null;
    }

    @Override
    public void setup() throws JAXBException, SAXException {
        jaxbContext = JAXBContext.newInstance(Country.class, Countries.class);
        jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // set this flag to true to format the output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schema = sf.newSchema(new File("countries_validation.xsd"));

        // set schema for validation
        jaxbMarshaller.setSchema(schema);
        jaxbUnmarshaller.setSchema(schema);
    }

    public void unmarshal(File xml) throws JAXBException {
        Countries countries = (Countries) jaxbUnmarshaller.unmarshal(xml);
        System.out.println(countries);
    }

    @Override
    public void validate() throws Exception {

    }

    @Override
    public void walkDocument(File document) throws Exception {
        unmarshal(document);
    }
}
