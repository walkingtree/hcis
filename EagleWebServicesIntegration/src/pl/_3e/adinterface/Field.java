
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Field complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Field">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tab" type="{http://3e.pl/ADInterface}Tab"/>
 *         &lt;element name="lookupInfo" type="{http://3e.pl/ADInterface}LookupInfo"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Help" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ColumnSQL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayLogic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReadOnlyLogic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lookup" type="{http://3e.pl/ADInterface}LookupValues" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="WindowNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="TabNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Window_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="tabReadOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isProcess" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ColumnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Header" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="displayType" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Column_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="DisplayLength" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="IsSameLine" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsDisplayed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DefaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsMandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsReadOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsUpdateable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsAlwaysUpdateable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsHeading" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsFieldOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsEncryptedField" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsEncryptedColumn" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsSelectionColumn" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SortNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="FieldLength" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="VFormat" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ValueMin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ValueMax" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FieldGroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsKey" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsParent" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Callout" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AD_Process_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ObscureType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ValidationCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AD_Reference_Value_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="isRange" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DefaultValue2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Field", propOrder = {
    "tab",
    "lookupInfo",
    "description",
    "help",
    "columnSQL",
    "displayLogic",
    "readOnlyLogic",
    "lookup"
})
public class Field {

    @XmlElement(name = "Tab", required = true)
    protected Tab tab;
    @XmlElement(required = true)
    protected LookupInfo lookupInfo;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Help", required = true)
    protected String help;
    @XmlElement(name = "ColumnSQL", required = true)
    protected String columnSQL;
    @XmlElement(name = "DisplayLogic", required = true)
    protected String displayLogic;
    @XmlElement(name = "ReadOnlyLogic", required = true)
    protected String readOnlyLogic;
    protected LookupValues lookup;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "WindowNo")
    protected Integer windowNo;
    @XmlAttribute(name = "TabNo")
    protected Integer tabNo;
    @XmlAttribute(name = "AD_Window_ID")
    protected Integer adWindowID;
    @XmlAttribute
    protected Boolean tabReadOnly;
    @XmlAttribute
    protected Boolean isProcess;
    @XmlAttribute(name = "ColumnName")
    protected String columnName;
    @XmlAttribute(name = "Header")
    protected String header;
    @XmlAttribute
    protected Integer displayType;
    @XmlAttribute(name = "AD_Column_ID")
    protected Integer adColumnID;
    @XmlAttribute(name = "DisplayLength")
    protected Integer displayLength;
    @XmlAttribute(name = "IsSameLine")
    protected Boolean isSameLine;
    @XmlAttribute(name = "IsDisplayed")
    protected Boolean isDisplayed;
    @XmlAttribute(name = "DefaultValue")
    protected String defaultValue;
    @XmlAttribute(name = "IsMandatory")
    protected Boolean isMandatory;
    @XmlAttribute(name = "IsReadOnly")
    protected Boolean isReadOnly;
    @XmlAttribute(name = "IsUpdateable")
    protected Boolean isUpdateable;
    @XmlAttribute(name = "IsAlwaysUpdateable")
    protected Boolean isAlwaysUpdateable;
    @XmlAttribute(name = "IsHeading")
    protected Boolean isHeading;
    @XmlAttribute(name = "IsFieldOnly")
    protected Boolean isFieldOnly;
    @XmlAttribute(name = "IsEncryptedField")
    protected Boolean isEncryptedField;
    @XmlAttribute(name = "IsEncryptedColumn")
    protected Boolean isEncryptedColumn;
    @XmlAttribute(name = "IsSelectionColumn")
    protected Boolean isSelectionColumn;
    @XmlAttribute(name = "SortNo")
    protected Integer sortNo;
    @XmlAttribute(name = "FieldLength")
    protected Integer fieldLength;
    @XmlAttribute(name = "VFormat")
    protected String vFormat;
    @XmlAttribute(name = "ValueMin")
    protected String valueMin;
    @XmlAttribute(name = "ValueMax")
    protected String valueMax;
    @XmlAttribute(name = "FieldGroup")
    protected String fieldGroup;
    @XmlAttribute(name = "IsKey")
    protected Boolean isKey;
    @XmlAttribute(name = "IsParent")
    protected Boolean isParent;
    @XmlAttribute(name = "Callout")
    protected String callout;
    @XmlAttribute(name = "AD_Process_ID")
    protected Integer adProcessID;
    @XmlAttribute(name = "ObscureType")
    protected String obscureType;
    @XmlAttribute(name = "ValidationCode")
    protected String validationCode;
    @XmlAttribute(name = "AD_Reference_Value_ID")
    protected Integer adReferenceValueID;
    @XmlAttribute
    protected Boolean isRange;
    @XmlAttribute(name = "DefaultValue2")
    protected String defaultValue2;

    /**
     * Gets the value of the tab property.
     * 
     * @return
     *     possible object is
     *     {@link Tab }
     *     
     */
    public Tab getTab() {
        return tab;
    }

    /**
     * Sets the value of the tab property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tab }
     *     
     */
    public void setTab(Tab value) {
        this.tab = value;
    }

    /**
     * Gets the value of the lookupInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LookupInfo }
     *     
     */
    public LookupInfo getLookupInfo() {
        return lookupInfo;
    }

    /**
     * Sets the value of the lookupInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupInfo }
     *     
     */
    public void setLookupInfo(LookupInfo value) {
        this.lookupInfo = value;
    }

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
     * Gets the value of the columnSQL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnSQL() {
        return columnSQL;
    }

    /**
     * Sets the value of the columnSQL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnSQL(String value) {
        this.columnSQL = value;
    }

    /**
     * Gets the value of the displayLogic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayLogic() {
        return displayLogic;
    }

    /**
     * Sets the value of the displayLogic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayLogic(String value) {
        this.displayLogic = value;
    }

    /**
     * Gets the value of the readOnlyLogic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadOnlyLogic() {
        return readOnlyLogic;
    }

    /**
     * Sets the value of the readOnlyLogic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadOnlyLogic(String value) {
        this.readOnlyLogic = value;
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
     * Gets the value of the tabReadOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTabReadOnly() {
        return tabReadOnly;
    }

    /**
     * Sets the value of the tabReadOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTabReadOnly(Boolean value) {
        this.tabReadOnly = value;
    }

    /**
     * Gets the value of the isProcess property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsProcess() {
        return isProcess;
    }

    /**
     * Sets the value of the isProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsProcess(Boolean value) {
        this.isProcess = value;
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

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeader(String value) {
        this.header = value;
    }

    /**
     * Gets the value of the displayType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisplayType() {
        return displayType;
    }

    /**
     * Sets the value of the displayType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisplayType(Integer value) {
        this.displayType = value;
    }

    /**
     * Gets the value of the adColumnID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADColumnID() {
        return adColumnID;
    }

    /**
     * Sets the value of the adColumnID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADColumnID(Integer value) {
        this.adColumnID = value;
    }

    /**
     * Gets the value of the displayLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisplayLength() {
        return displayLength;
    }

    /**
     * Sets the value of the displayLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisplayLength(Integer value) {
        this.displayLength = value;
    }

    /**
     * Gets the value of the isSameLine property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSameLine() {
        return isSameLine;
    }

    /**
     * Sets the value of the isSameLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSameLine(Boolean value) {
        this.isSameLine = value;
    }

    /**
     * Gets the value of the isDisplayed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDisplayed() {
        return isDisplayed;
    }

    /**
     * Sets the value of the isDisplayed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDisplayed(Boolean value) {
        this.isDisplayed = value;
    }

    /**
     * Gets the value of the defaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the isMandatory property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsMandatory() {
        return isMandatory;
    }

    /**
     * Sets the value of the isMandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsMandatory(Boolean value) {
        this.isMandatory = value;
    }

    /**
     * Gets the value of the isReadOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReadOnly() {
        return isReadOnly;
    }

    /**
     * Sets the value of the isReadOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReadOnly(Boolean value) {
        this.isReadOnly = value;
    }

    /**
     * Gets the value of the isUpdateable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsUpdateable() {
        return isUpdateable;
    }

    /**
     * Sets the value of the isUpdateable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsUpdateable(Boolean value) {
        this.isUpdateable = value;
    }

    /**
     * Gets the value of the isAlwaysUpdateable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAlwaysUpdateable() {
        return isAlwaysUpdateable;
    }

    /**
     * Sets the value of the isAlwaysUpdateable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAlwaysUpdateable(Boolean value) {
        this.isAlwaysUpdateable = value;
    }

    /**
     * Gets the value of the isHeading property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsHeading() {
        return isHeading;
    }

    /**
     * Sets the value of the isHeading property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsHeading(Boolean value) {
        this.isHeading = value;
    }

    /**
     * Gets the value of the isFieldOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsFieldOnly() {
        return isFieldOnly;
    }

    /**
     * Sets the value of the isFieldOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsFieldOnly(Boolean value) {
        this.isFieldOnly = value;
    }

    /**
     * Gets the value of the isEncryptedField property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEncryptedField() {
        return isEncryptedField;
    }

    /**
     * Sets the value of the isEncryptedField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEncryptedField(Boolean value) {
        this.isEncryptedField = value;
    }

    /**
     * Gets the value of the isEncryptedColumn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEncryptedColumn() {
        return isEncryptedColumn;
    }

    /**
     * Sets the value of the isEncryptedColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEncryptedColumn(Boolean value) {
        this.isEncryptedColumn = value;
    }

    /**
     * Gets the value of the isSelectionColumn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSelectionColumn() {
        return isSelectionColumn;
    }

    /**
     * Sets the value of the isSelectionColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSelectionColumn(Boolean value) {
        this.isSelectionColumn = value;
    }

    /**
     * Gets the value of the sortNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * Sets the value of the sortNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSortNo(Integer value) {
        this.sortNo = value;
    }

    /**
     * Gets the value of the fieldLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFieldLength() {
        return fieldLength;
    }

    /**
     * Sets the value of the fieldLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFieldLength(Integer value) {
        this.fieldLength = value;
    }

    /**
     * Gets the value of the vFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVFormat() {
        return vFormat;
    }

    /**
     * Sets the value of the vFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVFormat(String value) {
        this.vFormat = value;
    }

    /**
     * Gets the value of the valueMin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueMin() {
        return valueMin;
    }

    /**
     * Sets the value of the valueMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueMin(String value) {
        this.valueMin = value;
    }

    /**
     * Gets the value of the valueMax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueMax() {
        return valueMax;
    }

    /**
     * Sets the value of the valueMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueMax(String value) {
        this.valueMax = value;
    }

    /**
     * Gets the value of the fieldGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldGroup() {
        return fieldGroup;
    }

    /**
     * Sets the value of the fieldGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldGroup(String value) {
        this.fieldGroup = value;
    }

    /**
     * Gets the value of the isKey property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsKey() {
        return isKey;
    }

    /**
     * Sets the value of the isKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsKey(Boolean value) {
        this.isKey = value;
    }

    /**
     * Gets the value of the isParent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsParent() {
        return isParent;
    }

    /**
     * Sets the value of the isParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsParent(Boolean value) {
        this.isParent = value;
    }

    /**
     * Gets the value of the callout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallout() {
        return callout;
    }

    /**
     * Sets the value of the callout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallout(String value) {
        this.callout = value;
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
     * Gets the value of the obscureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObscureType() {
        return obscureType;
    }

    /**
     * Sets the value of the obscureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObscureType(String value) {
        this.obscureType = value;
    }

    /**
     * Gets the value of the validationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationCode() {
        return validationCode;
    }

    /**
     * Sets the value of the validationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationCode(String value) {
        this.validationCode = value;
    }

    /**
     * Gets the value of the adReferenceValueID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADReferenceValueID() {
        return adReferenceValueID;
    }

    /**
     * Sets the value of the adReferenceValueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADReferenceValueID(Integer value) {
        this.adReferenceValueID = value;
    }

    /**
     * Gets the value of the isRange property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRange() {
        return isRange;
    }

    /**
     * Sets the value of the isRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRange(Boolean value) {
        this.isRange = value;
    }

    /**
     * Gets the value of the defaultValue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue2() {
        return defaultValue2;
    }

    /**
     * Sets the value of the defaultValue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue2(String value) {
        this.defaultValue2 = value;
    }

}
