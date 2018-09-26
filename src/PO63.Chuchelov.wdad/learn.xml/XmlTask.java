package PO63.Chuchelov.wdad.learn.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlTask {
    private Document document;

    public XmlTask(String filepath) {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int salaryAverage(){
        NodeList nodes = document.getElementsByTagName("salary");

        int sum = 0;

        for (int i = 0; i < nodes.getLength(); i++){
            sum += Integer.parseInt(nodes.item(i).getNodeValue());
        }

        return sum/nodes.getLength();
    }

    public int salaryAverage(String departmentName){
        return 0;
    }

    public void setJobTitile(String firstName, String
            secondName, String newJobTitle){

    }

    public void setSalary(String firstName, String
            secondName, int newSalary){

    }

    public void fireEmployee(String firstName, String
            secondName){

    }
}
