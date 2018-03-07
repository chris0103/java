package org.chris.study.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType
@XmlRootElement(name = "Countries")
public class Countries {

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    @XmlElement(name = "Country")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /*
     * Element that is going to be marshaled in the XML
     */
    @Override
    public String toString() {
        StandardToStringStyle style = new StandardToStringStyle();
        style.setFieldNameValueSeparator(": ");
        style.setFieldSeparator("\n");
        ToStringBuilder builder = new ToStringBuilder(this, style);
        for (Country country : countries) {
            builder.append(country.toString());
        }
        return builder.toString();
    }
}
