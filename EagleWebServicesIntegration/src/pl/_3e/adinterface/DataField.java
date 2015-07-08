
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="val" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lookup" type="{http://3e.pl/ADInterface}LookupValues" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="column" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lval" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="disp" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="edit" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="error" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="errorVal" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataField", propOrder = {
    "val",
    "lookup"
})
public class DataField {

    @XmlElement(required = true)
    protected String val;
    protected LookupValues lookup;
    @XmlAttribute
    protected String type;
    @XmlAttribute
    protected String column;
    @XmlAttribute
    protected String lval;
    @XmlAttribute
    protected Boolean disp;
    @XmlAttribute
    protected Boolean edit;
    @XmlAttribute
    protected Boolean error;
    @XmlAttribute
    protected String errorVal;

    /**
     * Gets the value of the val property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVal() {
        return val;
    }

    /**
     * Sets the value of the val property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVal(String value) {
        this.val = value;
    }

    /**
     * Gets the value of the lookup property.
     * 
     * @return
     *     possible object is
     *     {@link LookupValues }
     *     
     */
    public LookupValues getLookup() {
        return lookup;
    }

    /**
     * Sets the value of the lookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupValues }
     *     
     */
    public void setLookup(LookupValues value) {
        this.lookup = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the column property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumn() {
        return column;
    }

    /**
     * Sets the value of the column property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumn(String value) {
        this.column = value;
    }

    /**
     * Gets the value of the lval property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLval() {
        return lval;
    }

    /**
     * Sets the value of the lval property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLval(String value) {
        this.lval = value;
    }

    /**
     * Gets the value of the disp property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisp() {
        return disp;
    }

    /**
     * Sets the value of the disp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisp(Boolean value) {
        this.disp = value;
    }

    /**
     * Gets the value of the edit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEdit() {
        return edit;
    }

    /**
     * Sets the value of the edit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEdit(Boolean value) {
        this.edit = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setError(Boolean value) {
        this.error = value;
    }

    /**
     * Gets the value of the errorVal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorVal() {
        return errorVal;
    }

    /**
     * Sets the value of the errorVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorVal(String value) {
        this.errorVal = value;
    }

}
