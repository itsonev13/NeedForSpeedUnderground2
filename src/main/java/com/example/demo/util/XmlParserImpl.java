package com.example.demo.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlParserImpl implements XmlParser {

	@Override
	@SuppressWarnings("unchecked")
	public <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (O) unmarshaller.unmarshal(new File(filePath));
	}

}
