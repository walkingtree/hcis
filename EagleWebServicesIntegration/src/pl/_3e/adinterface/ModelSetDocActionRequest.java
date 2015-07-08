
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelSetDocActionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelSetDocActionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelSetDocAction" type="{http://3e.pl/ADInterface}ModelSetDocAction"/>
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
@XmlType(name = "ModelSetDocActionRequest", propOrder = {
    "modelSetDocAction",
    "adLoginRequest"
})
public class ModelSetDocActionRequest {

    @XmlElement(name = "ModelSetDocAction", required = true)
    protected ModelSetDocAction modelSetDocAction;
    @XmlElement(name = "ADLoginRequest", required = true)
    protected ADLoginRequest adLoginRequest;

    /**
     * Gets the value of the modelSetDocAction property.
     * 
     * @return
     *     possible object is
     *     {@link ModelSetDocAction }
     *     
     */
    public ModelSetDocAction getModelSetDocAction() {
        return modelSetDocAction;
    }

    /**
     * Sets the value of the modelSetDocAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelSetDocAction }
     *     
     */
    public void setModelSetDocAction(ModelSetDocAction value) {
        this.modelSetDocAction = value;
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
