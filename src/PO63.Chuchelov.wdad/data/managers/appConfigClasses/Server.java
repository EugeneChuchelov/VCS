
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
    protected Bindedobject bindedobject;

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Bindedobject getBindedobject() {
        return bindedobject;
    }

    public void setBindedobject(Bindedobject bindedobject) {
        this.bindedobject = bindedobject;
    }
}
