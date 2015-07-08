
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelRunProcessRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelRunProcessRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelRunProcess" type="{http://3e.pl/ADInterface}ModelRunProcess"/>
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
@XmlType(name = "ModelRunProcessRequest", propOrder = {
    "modelRunProcess",
    "adLoginRequest"
})
public class ModelRunProcessRequest {

    @XmlElement(name = "ModelRunProcess", required = true)
    protected ModelRunProcess modelRunProcess;
    @XmlElement(name = "ADLoginRequest", required = true)
    protected ADLoginRequest adLoginRequest;

    /**
     * Gets the value of the modelRunProcess property.
     * 
     * @return
     *     possible object is
     *     {@link ModelRunProcess }
     *     
     */
    public ModelRunProcess getModelRunProcess() {
        return modelRunProcess;
    }

    /**
     * Sets the value of the modelRunProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelRunProcess }
     *     
     */
    public void setModelRunProcess(ModelRunProcess value) {
        this.modelRunProcess = value;
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
