package PO63.Chuchelov.wdad.learn.xml;

public class TestXmlTask {
    public static void main(String[] args){
        XmlTask xml = new XmlTask("src/PO63.Chuchelov.wdad/learn.xml/CF.xml");
        System.out.println("Av. salary: " + xml.salaryAverage());
        System.out.println("Av. salary in First Engineering dep.: " + xml.salaryAverage("First Engineering"));

        xml.setJobTitile("Igor", "Teleastrov", "assistant");
        xml.setSalary("Igor", "Teleastrov", 11000);
        xml.fireEmployee("Igor", "Teleastrov");
    }
}
