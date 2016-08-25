package org.chris.study.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType
@XmlRootElement(name = "Countries")
public class Countries {

	private List<Country> countries;

	public List<Country> getCountries() {
		return countries;
	}
	
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		for (Country country : countries) {
			builder.append(country);
		}
		return builder.toString();
	}

	/*
	 * element that is going to be marshaled in the XML
	 */
	
	@XmlElement(name = "Country")
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}
