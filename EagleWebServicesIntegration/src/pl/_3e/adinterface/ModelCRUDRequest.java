
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelCRUDRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelCRUDRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelCRUD" type="{http://3e.pl/ADInterface}ModelCRUD"/>
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
@XmlType(name = "ModelCRUDRequest", propOrder = {
    "modelCRUD",
    "adLoginRequest"
})
public class ModelCRUDRequest {

    @XmlElement(name = "ModelCRUD", required = true)
    protected ModelCRUD modelCRUD;
    @XmlElement(name = "ADLoginRequest", required = true)
    protected ADLoginRequest adLoginRequest;

    /**
     * Gets the value of the modelCRUD property.
     * 
     * @return
     *     possible object is
     *     {@link ModelCRUD }
     *     
     */
    public ModelCRUD getModelCRUD() {
        return modelCRUD;
    }

    /**
     * Sets the value of the modelCRUD property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelCRUD }
     *     
     */
    public void setModelCRUD(ModelCRUD value) {
        this.modelCRUD = value;
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
