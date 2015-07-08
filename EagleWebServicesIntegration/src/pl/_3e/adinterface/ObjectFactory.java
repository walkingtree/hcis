
package pl._3e.adinterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl._3e.adinterface package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ADLoginRequest_QNAME = new QName("http://3e.pl/ADInterface", "ADLoginRequest");
    private final static QName _RunProcessResponse_QNAME = new QName("http://3e.pl/ADInterface", "RunProcessResponse");
    private final static QName _Window_QNAME = new QName("http://3e.pl/ADInterface", "Window");
    private final static QName _ModelGetListRequest_QNAME = new QName("http://3e.pl/ADInterface", "ModelGetListRequest");
    private final static QName _Location_QNAME = new QName("http://3e.pl/ADInterface", "Location");
    private final static QName _RunProcess_QNAME = new QName("http://3e.pl/ADInterface", "RunProcess");
    private final static QName _WindowTabData_QNAME = new QName("http://3e.pl/ADInterface", "WindowTabData");
    private final static QName _WindowTabDataReq_QNAME = new QName("http://3e.pl/ADInterface", "WindowTabDataReq");
    private final static QName _DocAction_QNAME = new QName("http://3e.pl/ADInterface", "DocAction");
    private final static QName _GetLookupSearchDataReq_QNAME = new QName("http://3e.pl/ADInterface", "getLookupSearchDataReq");
    private final static QName _ModelRunProcessRequest_QNAME = new QName("http://3e.pl/ADInterface", "ModelRunProcessRequest");
    private final static QName _ADMenu_QNAME = new QName("http://3e.pl/ADInterface", "ADMenu");
    private final static QName _GetProcessParams_QNAME = new QName("http://3e.pl/ADInterface", "GetProcessParams");
    private final static QName _ModelSetDocActionRequest_QNAME = new QName("http://3e.pl/ADInterface", "ModelSetDocActionRequest");
    private final static QName _StandardResponse_QNAME = new QName("http://3e.pl/ADInterface", "StandardResponse");
    private final static QName _ModelRunProcess_QNAME = new QName("http://3e.pl/ADInterface", "ModelRunProcess");
    private final static QName _ADLoginResponse_QNAME = new QName("http://3e.pl/ADInterface", "ADLoginResponse");
    private final static QName _ModelCRUDRequest_QNAME = new QName("http://3e.pl/ADInterface", "ModelCRUDRequest");
    private final static QName _ProcessParams_QNAME = new QName("http://3e.pl/ADInterface", "ProcessParams");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl._3e.adinterface
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ADLoginResponse }
     * 
     */
    public ADLoginResponse createADLoginResponse() {
        return new ADLoginResponse();
    }

    /**
     * Create an instance of {@link TabList }
     * 
     */
    public TabList createTabList() {
        return new TabList();
    }

    /**
     * Create an instance of {@link ModelCRUD }
     * 
     */
    public ModelCRUD createModelCRUD() {
        return new ModelCRUD();
    }

    /**
     * Create an instance of {@link ModelSetDocActionRequest }
     * 
     */
    public ModelSetDocActionRequest createModelSetDocActionRequest() {
        return new ModelSetDocActionRequest();
    }

    /**
     * Create an instance of {@link ADMenuItemList }
     * 
     */
    public ADMenuItemList createADMenuItemList() {
        return new ADMenuItemList();
    }

    /**
     * Create an instance of {@link LookupValues }
     * 
     */
    public LookupValues createLookupValues() {
        return new LookupValues();
    }

    /**
     * Create an instance of {@link ADLoginRequest }
     * 
     */
    public ADLoginRequest createADLoginRequest() {
        return new ADLoginRequest();
    }

    /**
     * Create an instance of {@link ProcessParamList }
     * 
     */
    public ProcessParamList createProcessParamList() {
        return new ProcessParamList();
    }

    /**
     * Create an instance of {@link RunProcessResponse }
     * 
     */
    public RunProcessResponse createRunProcessResponse() {
        return new RunProcessResponse();
    }

    /**
     * Create an instance of {@link ADMenuItem }
     * 
     */
    public ADMenuItem createADMenuItem() {
        return new ADMenuItem();
    }

    /**
     * Create an instance of {@link ModelRunProcess }
     * 
     */
    public ModelRunProcess createModelRunProcess() {
        return new ModelRunProcess();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link LookupInfo }
     * 
     */
    public LookupInfo createLookupInfo() {
        return new LookupInfo();
    }

    /**
     * Create an instance of {@link ModelRunProcessRequest }
     * 
     */
    public ModelRunProcessRequest createModelRunProcessRequest() {
        return new ModelRunProcessRequest();
    }

    /**
     * Create an instance of {@link WindowTabDataReq }
     * 
     */
    public WindowTabDataReq createWindowTabDataReq() {
        return new WindowTabDataReq();
    }

    /**
     * Create an instance of {@link ModelSetDocAction }
     * 
     */
    public ModelSetDocAction createModelSetDocAction() {
        return new ModelSetDocAction();
    }

    /**
     * Create an instance of {@link GetLookupSearchDataReq }
     * 
     */
    public GetLookupSearchDataReq createGetLookupSearchDataReq() {
        return new GetLookupSearchDataReq();
    }

    /**
     * Create an instance of {@link ModelGetList }
     * 
     */
    public ModelGetList createModelGetList() {
        return new ModelGetList();
    }

    /**
     * Create an instance of {@link ProcessParams }
     * 
     */
    public ProcessParams createProcessParams() {
        return new ProcessParams();
    }

    /**
     * Create an instance of {@link Tab }
     * 
     */
    public Tab createTab() {
        return new Tab();
    }

    /**
     * Create an instance of {@link DocAction }
     * 
     */
    public DocAction createDocAction() {
        return new DocAction();
    }

    /**
     * Create an instance of {@link GetProcessParams }
     * 
     */
    public GetProcessParams createGetProcessParams() {
        return new GetProcessParams();
    }

    /**
     * Create an instance of {@link DataRow }
     * 
     */
    public DataRow createDataRow() {
        return new DataRow();
    }

    /**
     * Create an instance of {@link StandardResponse }
     * 
     */
    public StandardResponse createStandardResponse() {
        return new StandardResponse();
    }

    /**
     * Create an instance of {@link Window }
     * 
     */
    public Window createWindow() {
        return new Window();
    }

    /**
     * Create an instance of {@link ModelCRUDRequest }
     * 
     */
    public ModelCRUDRequest createModelCRUDRequest() {
        return new ModelCRUDRequest();
    }

    /**
     * Create an instance of {@link DataField }
     * 
     */
    public DataField createDataField() {
        return new DataField();
    }

    /**
     * Create an instance of {@link ProcessParam }
     * 
     */
    public ProcessParam createProcessParam() {
        return new ProcessParam();
    }

    /**
     * Create an instance of {@link FieldList }
     * 
     */
    public FieldList createFieldList() {
        return new FieldList();
    }

    /**
     * Create an instance of {@link WindowTabData }
     * 
     */
    public WindowTabData createWindowTabData() {
        return new WindowTabData();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link RunProcess }
     * 
     */
    public RunProcess createRunProcess() {
        return new RunProcess();
    }

    /**
     * Create an instance of {@link ModelGetListRequest }
     * 
     */
    public ModelGetListRequest createModelGetListRequest() {
        return new ModelGetListRequest();
    }

    /**
     * Create an instance of {@link LookupValue }
     * 
     */
    public LookupValue createLookupValue() {
        return new LookupValue();
    }

    /**
     * Create an instance of {@link DataSet }
     * 
     */
    public DataSet createDataSet() {
        return new DataSet();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ADLoginRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ADLoginRequest")
    public JAXBElement<ADLoginRequest> createADLoginRequest(ADLoginRequest value) {
        return new JAXBElement<ADLoginRequest>(_ADLoginRequest_QNAME, ADLoginRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "RunProcessResponse")
    public JAXBElement<RunProcessResponse> createRunProcessResponse(RunProcessResponse value) {
        return new JAXBElement<RunProcessResponse>(_RunProcessResponse_QNAME, RunProcessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Window }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "Window")
    public JAXBElement<Window> createWindow(Window value) {
        return new JAXBElement<Window>(_Window_QNAME, Window.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelGetListRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ModelGetListRequest")
    public JAXBElement<ModelGetListRequest> createModelGetListRequest(ModelGetListRequest value) {
        return new JAXBElement<ModelGetListRequest>(_ModelGetListRequest_QNAME, ModelGetListRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Location }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "Location")
    public JAXBElement<Location> createLocation(Location value) {
        return new JAXBElement<Location>(_Location_QNAME, Location.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "RunProcess")
    public JAXBElement<RunProcess> createRunProcess(RunProcess value) {
        return new JAXBElement<RunProcess>(_RunProcess_QNAME, RunProcess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WindowTabData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "WindowTabData")
    public JAXBElement<WindowTabData> createWindowTabData(WindowTabData value) {
        return new JAXBElement<WindowTabData>(_WindowTabData_QNAME, WindowTabData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WindowTabDataReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "WindowTabDataReq")
    public JAXBElement<WindowTabDataReq> createWindowTabDataReq(WindowTabDataReq value) {
        return new JAXBElement<WindowTabDataReq>(_WindowTabDataReq_QNAME, WindowTabDataReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "DocAction")
    public JAXBElement<DocAction> createDocAction(DocAction value) {
        return new JAXBElement<DocAction>(_DocAction_QNAME, DocAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLookupSearchDataReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "getLookupSearchDataReq")
    public JAXBElement<GetLookupSearchDataReq> createGetLookupSearchDataReq(GetLookupSearchDataReq value) {
        return new JAXBElement<GetLookupSearchDataReq>(_GetLookupSearchDataReq_QNAME, GetLookupSearchDataReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelRunProcessRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ModelRunProcessRequest")
    public JAXBElement<ModelRunProcessRequest> createModelRunProcessRequest(ModelRunProcessRequest value) {
        return new JAXBElement<ModelRunProcessRequest>(_ModelRunProcessRequest_QNAME, ModelRunProcessRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ADMenuItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ADMenu")
    public JAXBElement<ADMenuItem> createADMenu(ADMenuItem value) {
        return new JAXBElement<ADMenuItem>(_ADMenu_QNAME, ADMenuItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProcessParams }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "GetProcessParams")
    public JAXBElement<GetProcessParams> createGetProcessParams(GetProcessParams value) {
        return new JAXBElement<GetProcessParams>(_GetProcessParams_QNAME, GetProcessParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelSetDocActionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ModelSetDocActionRequest")
    public JAXBElement<ModelSetDocActionRequest> createModelSetDocActionRequest(ModelSetDocActionRequest value) {
        return new JAXBElement<ModelSetDocActionRequest>(_ModelSetDocActionRequest_QNAME, ModelSetDocActionRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StandardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "StandardResponse")
    public JAXBElement<StandardResponse> createStandardResponse(StandardResponse value) {
        return new JAXBElement<StandardResponse>(_StandardResponse_QNAME, StandardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelRunProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ModelRunProcess")
    public JAXBElement<ModelRunProcess> createModelRunProcess(ModelRunProcess value) {
        return new JAXBElement<ModelRunProcess>(_ModelRunProcess_QNAME, ModelRunProcess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ADLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ADLoginResponse")
    public JAXBElement<ADLoginResponse> createADLoginResponse(ADLoginResponse value) {
        return new JAXBElement<ADLoginResponse>(_ADLoginResponse_QNAME, ADLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelCRUDRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ModelCRUDRequest")
    public JAXBElement<ModelCRUDRequest> createModelCRUDRequest(ModelCRUDRequest value) {
        return new JAXBElement<ModelCRUDRequest>(_ModelCRUDRequest_QNAME, ModelCRUDRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessParams }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://3e.pl/ADInterface", name = "ProcessParams")
    public JAXBElement<ProcessParams> createProcessParams(ProcessParams value) {
        return new JAXBElement<ProcessParams>(_ProcessParams_QNAME, ProcessParams.class, null, value);
    }

}
