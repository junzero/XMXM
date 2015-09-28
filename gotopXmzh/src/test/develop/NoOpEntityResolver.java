package test.develop;  
import java.io.IOException;  
import java.io.StringBufferInputStream;  
import org.xml.sax.EntityResolver;  
import org.xml.sax.InputSource;  
import org.xml.sax.SAXException;  
public class NoOpEntityResolver implements EntityResolver {  
    public InputSource resolveEntity(String arg0, String arg1)  
            throws SAXException, IOException {  
        return new InputSource(new StringBufferInputStream(""));  
    }  
}  
