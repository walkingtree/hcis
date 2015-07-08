
package pl._3e.adinterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tab complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tab">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Help" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReadOnlyLogic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayLogic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Fields" type="{http://3e.pl/ADInterface}FieldList"/>
 *         &lt;element name="CommitWarning" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WhereClause" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrderByClause" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsSoTrx" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="TabNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="WindowNo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Table_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Window_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Tab_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="IsSingleRow" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsReadOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsInsertRecord" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="HasTree" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsView" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AD_Column_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="TableName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccessLevel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsSecurityEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsDeleteable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsHighVolume" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AD_Process_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="TabLevel" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_Image_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Included_Tab_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ReplicationType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsSortTab" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AD_ColumnSortOrder_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AD_ColumnSortYesNo_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="onlyCurrentRows" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="onlyCurrentDays" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tab", propOrder = {
    "description",
    "help",
    "readOnlyLogic",
    "displayLogic",
    "fields",
    "commitWarning",
    "whereClause",
    "orderByClause"
})
public class Tab {

    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Help", required = true)
    protected String help;
    @XmlElement(name = "ReadOnlyLogic", required = true)
    protected String readOnlyLogic;
    @XmlElement(name = "DisplayLogic", required = true)
    protected String displayLogic;
    @XmlElement(name = "Fields", required = true)
    protected FieldList fields;
    @XmlElement(name = "CommitWarning", required = true)
    protected String commitWarning;
    @XmlElement(name = "WhereClause", required = true)
    protected String whereClause;
    @XmlElement(name = "OrderByClause", required = true)
    protected String orderByClause;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "IsSoTrx")
    protected Boolean isSoTrx;
    @XmlAttribute(name = "TabNo")
    protected Integer tabNo;
    @XmlAttribute(name = "WindowNo")
    protected Integer windowNo;
    @XmlAttribute(name = "AD_Table_ID")
    protected Integer adTableID;
    @XmlAttribute(name = "AD_Window_ID")
    protected Integer adWindowID;
    @XmlAttribute(name = "AD_Tab_ID")
    protected Integer adTabID;
    @XmlAttribute(name = "IsSingleRow")
    protected Boolean isSingleRow;
    @XmlAttribute(name = "IsReadOnly")
    protected Boolean isReadOnly;
    @XmlAttribute(name = "IsInsertRecord")
    protected Boolean isInsertRecord;
    @XmlAttribute(name = "HasTree")
    protected Boolean hasTree;
    @XmlAttribute(name = "IsView")
    protected Boolean isView;
    @XmlAttribute(name = "AD_Column_ID")
    protected Integer adColumnID;
    @XmlAttribute(name = "TableName")
    protected String tableName;
    @XmlAttribute(name = "AccessLevel")
    protected String accessLevel;
    @XmlAttribute(name = "IsSecurityEnabled")
    protected Boolean isSecurityEnabled;
    @XmlAttribute(name = "IsDeleteable")
    protected Boolean isDeleteable;
    @XmlAttribute(name = "IsHighVolume")
    protected Boolean isHighVolume;
    @XmlAttribute(name = "AD_Process_ID")
    protected Integer adProcessID;
    @XmlAttribute(name = "TabLevel")
    protected Integer tabLevel;
    @XmlAttribute(name = "AD_Image_ID")
    protected Integer adImageID;
    @XmlAttribute(name = "Included_Tab_ID")
    protected Integer includedTabID;
    @XmlAttribute(name = "ReplicationType")
    protected String replicationType;
    @XmlAttribute(name = "IsSortTab")
    protected Boolean isSortTab;
    @XmlAttribute(name = "AD_ColumnSortOrder_ID")
    protected Integer adColumnSortOrderID;
    @XmlAttribute(name = "AD_ColumnSortYesNo_ID")
    protected Integer adColumnSortYesNoID;
    @XmlAttribute
    protected Boolean onlyCurrentRows;
    @XmlAttribute
    protected Integer onlyCurrentDays;

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
     * Gets the value of the fields property.
     * 
     * @return
     *     possible object is
     *     {@link FieldList }
     *     
     */
    public FieldList getFields() {
        return fields;
    }

    /**
     * Sets the value of the fields property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldList }
     *     
     */
    public void setFields(FieldList value) {
        this.fields = value;
    }

    /**
     * Gets the value of the commitWarning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitWarning() {
        return commitWarning;
    }

    /**
     * Sets the value of the commitWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitWarning(String value) {
        this.commitWarning = value;
    }

    /**
     * Gets the value of the whereClause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClause() {
        return whereClause;
    }

    /**
     * Sets the value of the whereClause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClause(String value) {
        this.whereClause = value;
    }

    /**
     * Gets the value of the orderByClause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * Sets the value of the orderByClause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderByClause(String value) {
        this.orderByClause = value;
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
     * Gets the value of the adTabID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADTabID() {
        return adTabID;
    }

    /**
     * Sets the value of the adTabID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADTabID(Integer value) {
        this.adTabID = value;
    }

    /**
     * Gets the value of the isSingleRow property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSingleRow() {
        return isSingleRow;
    }

    /**
     * Sets the value of the isSingleRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSingleRow(Boolean value) {
        this.isSingleRow = value;
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
     * Gets the value of the isInsertRecord property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsInsertRecord() {
        return isInsertRecord;
    }

    /**
     * Sets the value of the isInsertRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInsertRecord(Boolean value) {
        this.isInsertRecord = value;
    }

    /**
     * Gets the value of the hasTree property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasTree() {
        return hasTree;
    }

    /**
     * Sets the value of the hasTree property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasTree(Boolean value) {
        this.hasTree = value;
    }

    /**
     * Gets the value of the isView property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsView() {
        return isView;
    }

    /**
     * Sets the value of the isView property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsView(Boolean value) {
        this.isView = value;
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
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the accessLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets the value of the accessLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessLevel(String value) {
        this.accessLevel = value;
    }

    /**
     * Gets the value of the isSecurityEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSecurityEnabled() {
        return isSecurityEnabled;
    }

    /**
     * Sets the value of the isSecurityEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSecurityEnabled(Boolean value) {
        this.isSecurityEnabled = value;
    }

    /**
     * Gets the value of the isDeleteable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDeleteable() {
        return isDeleteable;
    }

    /**
     * Sets the value of the isDeleteable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDeleteable(Boolean value) {
        this.isDeleteable = value;
    }

    /**
     * Gets the value of the isHighVolume property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsHighVolume() {
        return isHighVolume;
    }

    /**
     * Sets the value of the isHighVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsHighVolume(Boolean value) {
        this.isHighVolume = value;
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
     * Gets the value of the tabLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTabLevel() {
        return tabLevel;
    }

    /**
     * Sets the value of the tabLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTabLevel(Integer value) {
        this.tabLevel = value;
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
     * Gets the value of the includedTabID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIncludedTabID() {
        return includedTabID;
    }

    /**
     * Sets the value of the includedTabID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIncludedTabID(Integer value) {
        this.includedTabID = value;
    }

    /**
     * Gets the value of the replicationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplicationType() {
        return replicationType;
    }

    /**
     * Sets the value of the replicationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplicationType(String value) {
        this.replicationType = value;
    }

    /**
     * Gets the value of the isSortTab property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSortTab() {
        return isSortTab;
    }

    /**
     * Sets the value of the isSortTab property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSortTab(Boolean value) {
        this.isSortTab = value;
    }

    /**
     * Gets the value of the adColumnSortOrderID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADColumnSortOrderID() {
        return adColumnSortOrderID;
    }

    /**
     * Sets the value of the adColumnSortOrderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADColumnSortOrderID(Integer value) {
        this.adColumnSortOrderID = value;
    }

    /**
     * Gets the value of the adColumnSortYesNoID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getADColumnSortYesNoID() {
        return adColumnSortYesNoID;
    }

    /**
     * Sets the value of the adColumnSortYesNoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setADColumnSortYesNoID(Integer value) {
        this.adColumnSortYesNoID = value;
    }

    /**
     * Gets the value of the onlyCurrentRows property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlyCurrentRows() {
        return onlyCurrentRows;
    }

    /**
     * Sets the value of the onlyCurrentRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlyCurrentRows(Boolean value) {
        this.onlyCurrentRows = value;
    }

    /**
     * Gets the value of the onlyCurrentDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOnlyCurrentDays() {
        return onlyCurrentDays;
    }

    /**
     * Sets the value of the onlyCurrentDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOnlyCurrentDays(Integer value) {
        this.onlyCurrentDays = value;
    }

}
