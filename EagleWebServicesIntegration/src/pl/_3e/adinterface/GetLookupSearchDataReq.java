
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLookupSearchDataReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLookupSearchDataReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Params" type="{http://3e.pl/ADInterface}DataRow" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="WindowNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="TabNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="RowNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ColumnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLookupSearchDataReq", propOrder = {
    "params"
})
public class GetLookupSearchDataReq {

    @XmlElement(name = "Params")
    protected DataRow params;
    @XmlAttribute(name = "WindowNo")
    protected Integer windowNo;
    @XmlAttribute(name = "TabNo")
    protected Integer tabNo;
    @XmlAttribute(name = "RowNo")
    protected Integer rowNo;
    @XmlAttribute(name = "ColumnName")
    protected String columnName;

    /**
     * Gets the value of the params property.
     * 
     * @return
     *     possible object is
     *     {@link DataRow }
     *     
     */
    public DataRow getParams() {
        return params;
    }

    /**
     * Sets the value of the params property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataRow }
     *     
     */
    public void setParams(DataRow value) {
        this.params = value;
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
     * Gets the value of the rowNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRowNo() {
        return rowNo;
    }

    /**
     * Sets the value of the rowNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRowNo(Integer value) {
        this.rowNo = value;
    }

    /**
     * Gets the value of the columnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets the value of the columnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnName(String value) {
        this.columnName = value;
    }

}
