package PO63.Chuchelov.wdad.data.managers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.*;

public class PreferencesManager {
    //todo вместо JAXB здесь уже лечше использовать DOM и с помощью XPath получать доступ к ноду этого DOM-а
    private static PreferencesManager instance;

    private XPath xPath;
    private Document doc;
    private String appconfigPath;

    private PreferencesManager(String appconfigPath){
        this.appconfigPath = appconfigPath;

        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new FileInputStream(appconfigPath));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PreferencesManager getInstance(String appconfigPath){
        if(instance == null){
            instance = new PreferencesManager(appconfigPath);
        }
        return instance;
    }

    public void setProperty(String key, String value){
        String exp = key.replace(".", "/");
        try {
            ((Node)xPath.evaluate(exp, doc, XPathConstants.NODE)).getFirstChild().setNodeValue(value);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        String exp = key.replace(".", "/");
        try {
            return ((Node)xPath.evaluate(exp, doc, XPathConstants.NODE)).getFirstChild().getNodeValue();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProperties(Properties prop){
        try{
            String exp;
            for(Object key : prop.keySet()){
                exp = key.toString().replace(".", "/");
                ((Node)xPath.evaluate(exp, doc, XPathConstants.NODE))
                        .getFirstChild().setNodeValue(prop.getProperty(key.toString()));
            }
            saveChanges();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties(){
        Properties prop = new Properties();
        prop.put(CREATEREGISTRY,
                getProperty(CREATEREGISTRY));
        prop.put(REGISTRYADDRESS,
                getProperty(REGISTRYADDRESS));
        prop.put(REGISTRYPORT,
                getProperty(REGISTRYPORT));
        prop.put(POLICYPATH,
                getProperty(POLICYPATH));
        prop.put(USECODEBASEONLY,
                getProperty(USECODEBASEONLY));
        prop.put(CLASSPROVIDER,
                getProperty(CLASSPROVIDER));
        return prop;
    }

    public void addBindedObject(String name, String className){
        try {
            Node reg = (Node)xPath.evaluate("appconfig/rmi/server", doc, XPathConstants.NODE);
            Element binobj = doc.createElement("bindedobject");
            binobj.setAttribute("class", className);
            binobj.setAttribute("name", name);
            reg.appendChild(binobj);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        saveChanges();
    }

    public void removeBindedObject(String name){
        try {
            Node reg = (Node)xPath.evaluate("appconfig/rmi/server", doc, XPathConstants.NODE);
            NodeList childs = reg.getChildNodes();
            Node current;
            for(int i = 0; i < childs.getLength(); i++){
                current = childs.item(i);
                if (current.hasAttributes()) {
                    if(current.getAttributes().getNamedItem("name").getTextContent().equals(name)){
                        reg.removeChild(childs.item(i));
                    }
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        saveChanges();
    }

    public String getBindedObjectName(String className){
        try {
            Node reg = (Node)xPath.evaluate("appconfig/rmi/server", doc, XPathConstants.NODE);
            NodeList childs = reg.getChildNodes();
            Node current;
            for(int i = 0; i < childs.getLength(); i++){
                current = childs.item(i);
                if (current.hasAttributes()) {
                    if(current.getAttributes().getNamedItem("class").getTextContent().equals(className)){
                        return current.getAttributes().getNamedItem("name").getTextContent();
                    }
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveChanges(){
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DocumentType docType = doc.getDoctype();
            if(docType != null){
                String systemID = docType.getSystemId();
                String publicID = docType.getPublicId();
                transformer.setOutputProperty (OutputKeys.DOCTYPE_PUBLIC, systemID + publicID);
            }
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
            DOMSource dom_source = new DOMSource(doc);
            StreamResult out_stream = new StreamResult(appconfigPath);
            transformer.transform (dom_source, out_stream);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /*    @Deprecated
        public Appconfig getAppConfig () {
        return appconfig;
    }

        @Deprecated
        public void setAppConfig (Appconfig appconfig){
        Marshal();
    }

        @Deprecated
        public Rmi getRmi () {
        return appconfig.getRmi();
    }

        @Deprecated
        public void setRmi (Rmi rmi){
        appconfig.setRmi(rmi);
        Marshal();
    }

        @Deprecated
        public Server getServer () {
        return appconfig.getRmi().getServer();
    }

        @Deprecated
        public void setServer (Server server){
        appconfig.getRmi().setServer(server);
        Marshal();
    }

        @Deprecated
        public Registry getRegistry () {
        return appconfig.getRmi().getServer().getRegistry();
    }

        @Deprecated
        public void setRegistry (Registry registry){
        appconfig.getRmi().getServer().setRegistry(registry);
        Marshal();
    }

        @Deprecated
        public Client getClient () {
        return appconfig.getRmi().getClient();
    }

        @Deprecated
        public void setClient (Client client){
        appconfig.getRmi().setClient(client);
        Marshal();
    }

        @Deprecated
        public String getCreateRegistry () {
        return appconfig.getRmi().getServer().getRegistry().getCreateregistry();
    }

        @Deprecated
        public void setCreateRegistry (String createregistry){
        appconfig.getRmi().getServer().getRegistry().setCreateregistry(createregistry);
        Marshal();
    }

        @Deprecated
        public String getRegistryAddress () {
        return appconfig.getRmi().getServer().getRegistry().getRegistryaddress();
    }

        @Deprecated
        public void setRegistryAddress (String registryaddress){
        appconfig.getRmi().getServer().getRegistry().setRegistryaddress(registryaddress);
        Marshal();
    }

        @Deprecated
        public int getRegistryPort () {
        return appconfig.getRmi().getServer().getRegistry().getRegistryport();
    }

        @Deprecated
        public void setRegistryPort ( int registryport){
        appconfig.getRmi().getServer().getRegistry().setRegistryport(registryport);
    }

        @Deprecated
        public List<Bindedobject> getBindedObject () {
        return appconfig.getRmi().getServer().getBindedobject();
    }

        @Deprecated
        public void setBindedObject (List < Bindedobject > bindedobject) {
        appconfig.getRmi().getServer().setBindedobject(bindedobject);
    }

        @Deprecated
        public String getPolicyPath () {
        return appconfig.getRmi().getClient().getPolicypath();
    }

        @Deprecated
        public void setPolicyPath (String policypath){
        appconfig.getRmi().getClient().setPolicypath(policypath);
    }

        @Deprecated
        public String getUseCodeBaseOnly () {
        return appconfig.getRmi().getClient().getUsecodebaseonly();
    }

        @Deprecated
        public void setUseCodeBaseOnly (String usecodebaseonly){
        appconfig.getRmi().getClient().setUsecodebaseonly(usecodebaseonly);
    }

        @Deprecated
        public String getClassProvider () {
        return appconfig.getRmi().getClassprovider();
    }

        @Deprecated
        public void setClassProvider (String classprovider){
        appconfig.getRmi().setClassprovider(classprovider);
    }
    */
}
