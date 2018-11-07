
package PO63.Chuchelov.wdad.data.managers.appConfigClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "registry", "bindedobject"
})
@XmlRootElement(name = "server")
public class Server {
    protected Registry registry;
    protected List<Bindedobject> bindedobject;

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public List<Bindedobject> getBindedobject() {
        if(bindedobject == null){
            bindedobject = new ArrayList<>();
        }
        return this.bindedobject;
    }

    public void setBindedobject(List<Bindedobject> bindedobject) {
        this.bindedobject = bindedobject;
    }

    public void addBindedObject(String name, String className){
        if(bindedobject == null){
            bindedobject = new ArrayList<>();
        }
        Bindedobject bindedobject = new Bindedobject();
        bindedobject.name = name;
        bindedobject.clazz = className;
        this.bindedobject.add(bindedobject);
    }

    public void removeBindedObject(String name){
        if(bindedobject == null){
            bindedobject = new ArrayList<>();
        }
        for(Bindedobject bindedobject : this.bindedobject){
            if(bindedobject.getName().equals(name)){
                this.bindedobject.remove(bindedobject);
                return;
            }
        }
    }
}
