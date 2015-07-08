
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ADLoginRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ADLoginRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lang" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClientID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RoleID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OrgID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="WarehouseID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ADLoginRequest", propOrder = {
    "user",
    "pass",
    "lang",
    "clientID",
    "roleID",
    "orgID",
    "warehouseID",
    "stage"
})
public class ADLoginRequest {

    @XmlElement(required = true)
    protected String user;
    @XmlElement(required = true)
    protected String pass;
    @XmlElement(required = true)
    protected String lang;
    @XmlElement(name = "ClientID")
    protected int clientID;
    @XmlElement(name = "RoleID")
    protected int roleID;
    @XmlElement(name = "OrgID")
    protected int orgID;
    @XmlElement(name = "WarehouseID")
    protected int warehouseID;
    protected int stage;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the pass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the value of the pass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPass(String value) {
        this.pass = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the clientID property.
     * 
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     */
    public void setClientID(int value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the roleID property.
     * 
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * Sets the value of the roleID property.
     * 
     */
    public void setRoleID(int value) {
        this.roleID = value;
    }

    /**
     * Gets the value of the orgID property.
     * 
     */
    public int getOrgID() {
        return orgID;
    }

    /**
     * Sets the value of the orgID property.
     * 
     */
    public void setOrgID(int value) {
        this.orgID = value;
    }

    /**
     * Gets the value of the warehouseID property.
     * 
     */
    public int getWarehouseID() {
        return warehouseID;
    }

    /**
     * Sets the value of the warehouseID property.
     * 
     */
    public void setWarehouseID(int value) {
        this.warehouseID = value;
    }

    /**
     * Gets the value of the stage property.
     * 
     */
    public int getStage() {
        return stage;
    }

    /**
     * Sets the value of the stage property.
     * 
     */
    public void setStage(int value) {
        this.stage = value;
    }

}
