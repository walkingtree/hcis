
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Window complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Window">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Help" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Tabs" type="{http://3e.pl/ADInterface}TabList"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsSoTrx" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="WindowNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Table_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Window_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="WindowType" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Image_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Color_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="IsReadWrite" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="WinWidth" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="WinHeight" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="IsError" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Error" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Window", propOrder = {
    "description",
    "help",
    "tabs"
})
public class Window {

    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Help", required = true)
    protected String help;
    @XmlElement(name = "Tabs", required = true)
    protected TabList tabs;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "IsSoTrx")
    protected Boolean isSoTrx;
    @XmlAttribute(name = "WindowNo")
    protected Integer windowNo;
    @XmlAttribute(name = "AD_Table_ID")
    protected Integer adTableID;
    @XmlAttribute(name = "AD_Window_ID")
    protected Integer adWindowID;
    @XmlAttribute(name = "WindowType")
    protected Integer windowType;
    @XmlAttribute(name = "AD_Image_ID")
    protected Integer adImageID;
    @XmlAttribute(name = "AD_Color_ID")
    protected Integer adColorID;
    @XmlAttribute(name = "IsReadWrite")
    protected Boolean isReadWrite;
    @XmlAttribute(name = "WinWidth")
    protected Integer winWidth;
    @XmlAttribute(name = "WinHeight")
    protected Integer winHeight;
    @XmlAttribute(name = "IsError")
    protected Boolean isError;
    @XmlAttribute(name = "Error")
    protected String error;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the help property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHelp() {
        return help;
    }

    /**
     * Sets the value of the help property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHelp(String value) {
        this.help = value;
    }

    /**
     * Gets the value of the tabs property.
     * 
     * @return
     *     possible object is
     *     {@link TabList }
     *     
     */
    public TabList getTabs() {
        return tabs;
    }

    /**
     * Sets the value of the tabs property.
     * 
     * @param value
     *     allowed object is
     *     {@link TabList }
     *     
     */
    public void setTabs(TabList value) {
        this.tabs = value;
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
     * Gets the value of the isSoTrx property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSoTrx() {
        return isSoTrx;
    }

    /**
     * Sets the value of the isSoTrx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSoTrx(Boolean value) {
        this.isSoTrx = value;
    }

    /**
     * Gets the value of the windowNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowNo() {
        return windowNo;
    }

    /**
     * Sets the value of the windowNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowNo(Integer value) {
        this.windowNo = value;
    }

    /**
     * Gets the value of the adTableID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADTableID() {
        return adTableID;
    }

    /**
     * Sets the value of the adTableID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADTableID(Integer value) {
        this.adTableID = value;
    }

    /**
     * Gets the value of the adWindowID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADWindowID() {
        return adWindowID;
    }

    /**
     * Sets the value of the adWindowID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADWindowID(Integer value) {
        this.adWindowID = value;
    }

    /**
     * Gets the value of the windowType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowType() {
        return windowType;
    }

    /**
     * Sets the value of the windowType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowType(Integer value) {
        this.windowType = value;
    }

    /**
     * Gets the value of the adImageID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADImageID() {
        return adImageID;
    }

    /**
     * Sets the value of the adImageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADImageID(Integer value) {
        this.adImageID = value;
    }

    /**
     * Gets the value of the adColorID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADColorID() {
        return adColorID;
    }

    /**
     * Sets the value of the adColorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADColorID(Integer value) {
        this.adColorID = value;
    }

    /**
     * Gets the value of the isReadWrite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReadWrite() {
        return isReadWrite;
    }

    /**
     * Sets the value of the isReadWrite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReadWrite(Boolean value) {
        this.isReadWrite = value;
    }

    /**
     * Gets the value of the winWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWinWidth() {
        return winWidth;
    }

    /**
     * Sets the value of the winWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWinWidth(Integer value) {
        this.winWidth = value;
    }

    /**
     * Gets the value of the winHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWinHeight() {
        return winHeight;
    }

    /**
     * Sets the value of the winHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWinHeight(Integer value) {
        this.winHeight = value;
    }

    /**
     * Gets the value of the isError property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsError() {
        return isError;
    }

    /**
     * Sets the value of the isError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsError(Boolean value) {
        this.isError = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

}
