
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetProcessParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProcessParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="AD_Process_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Menu_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Record_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProcessParams")
public class GetProcessParams {

    @XmlAttribute(name = "AD_Process_ID")
    protected Integer adProcessID;
    @XmlAttribute(name = "AD_Menu_ID")
    protected Integer adMenuID;
    @XmlAttribute(name = "AD_Record_ID")
    protected Integer adRecordID;

    /**
     * Gets the value of the adProcessID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADProcessID() {
        return adProcessID;
    }

    /**
     * Sets the value of the adProcessID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADProcessID(Integer value) {
        this.adProcessID = value;
    }

    /**
     * Gets the value of the adMenuID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADMenuID() {
        return adMenuID;
    }

    /**
     * Sets the value of the adMenuID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADMenuID(Integer value) {
        this.adMenuID = value;
    }

    /**
     * Gets the value of the adRecordID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADRecordID() {
        return adRecordID;
    }

    /**
     * Sets the value of the adRecordID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADRecordID(Integer value) {
        this.adRecordID = value;
    }

}
