
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LookupInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LookupInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZoomWindow" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ZoomWindowPO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LookupInfo", propOrder = {
    "zoomWindow",
    "zoomWindowPO"
})
public class LookupInfo {

    @XmlElement(name = "ZoomWindow")
    protected int zoomWindow;
    @XmlElement(name = "ZoomWindowPO")
    protected int zoomWindowPO;

    /**
     * Gets the value of the zoomWindow property.
     * 
     */
    public int getZoomWindow() {
        return zoomWindow;
    }

    /**
     * Sets the value of the zoomWindow property.
     * 
     */
    public void setZoomWindow(int value) {
        this.zoomWindow = value;
    }

    /**
     * Gets the value of the zoomWindowPO property.
     * 
     */
    public int getZoomWindowPO() {
        return zoomWindowPO;
    }

    /**
     * Sets the value of the zoomWindowPO property.
     * 
     */
    public void setZoomWindowPO(int value) {
        this.zoomWindowPO = value;
    }

}
