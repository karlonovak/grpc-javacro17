package hr.svgroup.ws.upload;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class MyCustomHandler implements ValidationEventHandler {

    public boolean handleEvent(ValidationEvent event) {
        return true;
    }

}