
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelGetListRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelGetListRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelGetList" type="{http://3e.pl/ADInterface}ModelGetList"/>
 *         &lt;element name="ADLoginRequest" type="{http://3e.pl/ADInterface}ADLoginRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModelGetListRequest", propOrder = {
    "modelGetList",
    "adLoginRequest"
})
public class ModelGetListRequest {

    @XmlElement(name = "ModelGetList", required = true)
    protected ModelGetList modelGetList;
    @XmlElement(name = "ADLoginRequest", required = true)
    protected ADLoginRequest adLoginRequest;

    /**
     * Gets the value of the modelGetList property.
     * 
     * @return
     *     possible object is
     *     {@link ModelGetList }
     *     
     */
    public ModelGetList getModelGetList() {
        return modelGetList;
    }

    /**
     * Sets the value of the modelGetList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelGetList }
     *     
     */
    public void setModelGetList(ModelGetList value) {
        this.modelGetList = value;
    }

    /**
     * Gets the value of the adLoginRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ADLoginRequest }
     *     
     */
    public ADLoginRequest getADLoginRequest() {
        return adLoginRequest;
    }

    /**
     * Sets the value of the adLoginRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ADLoginRequest }
     *     
     */
    public void setADLoginRequest(ADLoginRequest value) {
        this.adLoginRequest = value;
    }

}
