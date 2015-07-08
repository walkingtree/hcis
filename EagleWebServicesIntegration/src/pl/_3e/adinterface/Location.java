
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Location">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="C_Location_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C_Country_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="C_Region_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Countries" type="{http://3e.pl/ADInterface}LookupValues" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
    "cLocationID",
    "name",
    "address1",
    "address2",
    "city",
    "postalCode",
    "cCountryID",
    "cRegionID",
    "countries"
})
public class Location {

    @XmlElement(name = "C_Location_ID")
    protected int cLocationID;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Address1", required = true)
    protected String address1;
    @XmlElement(name = "Address2", required = true)
    protected String address2;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "PostalCode", required = true)
    protected String postalCode;
    @XmlElement(name = "C_Country_ID")
    protected int cCountryID;
    @XmlElement(name = "C_Region_ID")
    protected int cRegionID;
    @XmlElement(name = "Countries")
    protected LookupValues countries;

    /**
     * Gets the value of the cLocationID property.
     * 
     */
    public int getCLocationID() {
        return cLocationID;
    }

    /**
     * Sets the value of the cLocationID property.
     * 
     */
    public void setCLocationID(int value) {
        this.cLocationID = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the cCountryID property.
     * 
     */
    public int getCCountryID() {
        return cCountryID;
    }

    /**
     * Sets the value of the cCountryID property.
     * 
     */
    public void setCCountryID(int value) {
        this.cCountryID = value;
    }

    /**
     * Gets the value of the cRegionID property.
     * 
     */
    public int getCRegionID() {
        return cRegionID;
    }

    /**
     * Sets the value of the cRegionID property.
     * 
     */
    public void setCRegionID(int value) {
        this.cRegionID = value;
    }

    /**
     * Gets the value of the countries property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getCountries() {
        return countries;
    }

    /**
     * Sets the value of the countries property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setCountries(LookupValues value) {
        this.countries = value;
    }

}
