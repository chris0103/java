package org.chris.study.xml.jaxb;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType(propOrder = { "name", "capital", "foundation", "continent", "population" })
@XmlRootElement(name = "Country")
public class Country {

    private String name;

    private String capital;

    private LocalDate foundation;

    private String continent;

    private int population;

    private int importance;

    public String getCapital() {
        return capital;
    }

    public String getContinent() {
        return continent;
    }

    public LocalDate getFoundation() {
        return foundation;
    }

    public int getImportance() {
        return importance;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    @XmlElement(name = "Country_Capital")
    public void setCapital(String capital) {
        this.capital = capital;
    }

    @XmlElement(name = "Country_Continent")
    public void setContinent(String continent) {
        this.continent = continent;
    }

    @XmlElement(name = "Country_Foundation_Date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    @XmlAttribute(name = "importance", required = true)
    public void setImportance(int importance) {
        this.importance = importance;
    }

    @XmlElement(name = "Country_Name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Country_Population")
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        StandardToStringStyle style = new StandardToStringStyle();
        style.setFieldNameValueSeparator(": ");
        style.setFieldSeparator("\n");
        return new ToStringBuilder(this, style).append("Name", name).append("Capital", capital).append("", continent).toString();
    }
}
