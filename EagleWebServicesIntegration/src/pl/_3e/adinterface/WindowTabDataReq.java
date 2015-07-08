
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WindowTabDataReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WindowTabDataReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FindCriteria" type="{http://3e.pl/ADInterface}DataRow"/>
 *       &lt;/sequence>
 *       &lt;attribute name="WindowNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Window_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Menu_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="TabNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="PrevTabNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="PrevRecNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="getData" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RowStart" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="RowCount" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fromZoom" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="fromZoom_Window_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fromZoom_Tab_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fromZoom_Row_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fromZoom_ColumnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fromZoom_ColumnValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WindowTabDataReq", propOrder = {
    "findCriteria"
})
public class WindowTabDataReq {

    @XmlElement(name = "FindCriteria", required = true)
    protected DataRow findCriteria;
    @XmlAttribute(name = "WindowNo")
    protected Integer windowNo;
    @XmlAttribute(name = "AD_Window_ID")
    protected Integer adWindowID;
    @XmlAttribute(name = "AD_Menu_ID")
    protected Integer adMenuID;
    @XmlAttribute(name = "TabNo")
    protected Integer tabNo;
    @XmlAttribute(name = "PrevTabNo")
    protected Integer prevTabNo;
    @XmlAttribute(name = "PrevRecNo")
    protected Integer prevRecNo;
    @XmlAttribute
    protected Boolean getData;
    @XmlAttribute(name = "RowStart")
    protected Integer rowStart;
    @XmlAttribute(name = "RowCount")
    protected Integer rowCount;
    @XmlAttribute
    protected Boolean fromZoom;
    @XmlAttribute(name = "fromZoom_Window_ID")
    protected Integer fromZoomWindowID;
    @XmlAttribute(name = "fromZoom_Tab_ID")
    protected Integer fromZoomTabID;
    @XmlAttribute(name = "fromZoom_Row_ID")
    protected Integer fromZoomRowID;
    @XmlAttribute(name = "fromZoom_ColumnName")
    protected String fromZoomColumnName;
    @XmlAttribute(name = "fromZoom_ColumnValue")
    protected String fromZoomColumnValue;

    /**
     * Gets the value of the findCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link DataRow }
     *     
     */
    public DataRow getFindCriteria() {
        return findCriteria;
    }

    /**
     * Sets the value of the findCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataRow }
     *     
     */
    public void setFindCriteria(DataRow value) {
        this.findCriteria = value;
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
     * Gets the value of the tabNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTabNo() {
        return tabNo;
    }

    /**
     * Sets the value of the tabNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTabNo(Integer value) {
        this.tabNo = value;
    }

    /**
     * Gets the value of the prevTabNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrevTabNo() {
        return prevTabNo;
    }

    /**
     * Sets the value of the prevTabNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrevTabNo(Integer value) {
        this.prevTabNo = value;
    }

    /**
     * Gets the value of the prevRecNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrevRecNo() {
        return prevRecNo;
    }

    /**
     * Sets the value of the prevRecNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrevRecNo(Integer value) {
        this.prevRecNo = value;
    }

    /**
     * Gets the value of the getData property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGetData() {
        return getData;
    }

    /**
     * Sets the value of the getData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGetData(Boolean value) {
        this.getData = value;
    }

    /**
     * Gets the value of the rowStart property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRowStart() {
        return rowStart;
    }

    /**
     * Sets the value of the rowStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRowStart(Integer value) {
        this.rowStart = value;
    }

    /**
     * Gets the value of the rowCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRowCount() {
        return rowCount;
    }

    /**
     * Sets the value of the rowCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRowCount(Integer value) {
        this.rowCount = value;
    }

    /**
     * Gets the value of the fromZoom property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFromZoom() {
        return fromZoom;
    }

    /**
     * Sets the value of the fromZoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFromZoom(Boolean value) {
        this.fromZoom = value;
    }

    /**
     * Gets the value of the fromZoomWindowID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromZoomWindowID() {
        return fromZoomWindowID;
    }

    /**
     * Sets the value of the fromZoomWindowID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromZoomWindowID(Integer value) {
        this.fromZoomWindowID = value;
    }

    /**
     * Gets the value of the fromZoomTabID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromZoomTabID() {
        return fromZoomTabID;
    }

    /**
     * Sets the value of the fromZoomTabID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromZoomTabID(Integer value) {
        this.fromZoomTabID = value;
    }

    /**
     * Gets the value of the fromZoomRowID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromZoomRowID() {
        return fromZoomRowID;
    }

    /**
     * Sets the value of the fromZoomRowID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromZoomRowID(Integer value) {
        this.fromZoomRowID = value;
    }

    /**
     * Gets the value of the fromZoomColumnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromZoomColumnName() {
        return fromZoomColumnName;
    }

    /**
     * Sets the value of the fromZoomColumnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromZoomColumnName(String value) {
        this.fromZoomColumnName = value;
    }

    /**
     * Gets the value of the fromZoomColumnValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromZoomColumnValue() {
        return fromZoomColumnValue;
    }

    /**
     * Sets the value of the fromZoomColumnValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromZoomColumnValue(String value) {
        this.fromZoomColumnValue = value;
    }

}
