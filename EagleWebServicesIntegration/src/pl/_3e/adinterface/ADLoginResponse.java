
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ADLoginResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ADLoginResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="roles" type="{http://3e.pl/ADInterface}LookupValues"/>
 *         &lt;element name="langs" type="{http://3e.pl/ADInterface}LookupValues"/>
 *         &lt;element name="orgs" type="{http://3e.pl/ADInterface}LookupValues"/>
 *         &lt;element name="clients" type="{http://3e.pl/ADInterface}LookupValues"/>
 *         &lt;element name="warehouses" type="{http://3e.pl/ADInterface}LookupValues"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ADLoginResponse", propOrder = {
    "status",
    "roles",
    "langs",
    "orgs",
    "clients",
    "warehouses"
})
public class ADLoginResponse {

    protected int status;
    @XmlElement(required = true)
    protected LookupValues roles;
    @XmlElement(required = true)
    protected LookupValues langs;
    @XmlElement(required = true)
    protected LookupValues orgs;
    @XmlElement(required = true)
    protected LookupValues clients;
    @XmlElement(required = true)
    protected LookupValues warehouses;

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the roles property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setRoles(LookupValues value) {
        this.roles = value;
    }

    /**
     * Gets the value of the langs property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getLangs() {
        return langs;
    }

    /**
     * Sets the value of the langs property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setLangs(LookupValues value) {
        this.langs = value;
    }

    /**
     * Gets the value of the orgs property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getOrgs() {
        return orgs;
    }

    /**
     * Sets the value of the orgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setOrgs(LookupValues value) {
        this.orgs = value;
    }

    /**
     * Gets the value of the clients property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getClients() {
        return clients;
    }

    /**
     * Sets the value of the clients property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setClients(LookupValues value) {
        this.clients = value;
    }

    /**
     * Gets the value of the warehouses property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getWarehouses() {
        return warehouses;
    }

    /**
     * Sets the value of the warehouses property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setWarehouses(LookupValues value) {
        this.warehouses = value;
    }

}
