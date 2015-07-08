
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelRunProcess complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelRunProcess">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ParamValues" type="{http://3e.pl/ADInterface}DataRow"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AD_Process_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Menu_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Record_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="DocAction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModelRunProcess", propOrder = {
    "serviceType",
    "paramValues"
})
public class ModelRunProcess {

    @XmlElement(required = true)
    protected String serviceType;
    @XmlElement(name = "ParamValues", required = true)
    protected DataRow paramValues;
    @XmlAttribute(name = "AD_Process_ID")
    protected Integer adProcessID;
    @XmlAttribute(name = "AD_Menu_ID")
    protected Integer adMenuID;
    @XmlAttribute(name = "AD_Record_ID")
    protected Integer adRecordID;
    @XmlAttribute(name = "DocAction")
    protected String docAction;

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the paramValues property.
     * 
     * @return
     *     possible object is
     *     {@link DataRow }
     *     
     */
    public DataRow getParamValues() {
        return paramValues;
    }

    /**
     * Sets the value of the paramValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataRow }
     *     
     */
    public void setParamValues(DataRow value) {
        this.paramValues = value;
    }

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

    /**
     * Gets the value of the docAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocAction() {
        return docAction;
    }

    /**
     * Sets the value of the docAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocAction(String value) {
        this.docAction = value;
    }

}
