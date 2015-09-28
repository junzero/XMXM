package test.gotop.crm.demo.action.celltest;

import java.lang.reflect.Proxy;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxy;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.security.wss4j.WSS4JOutHandler;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.util.dom.DOMOutHandler;

import com.gotop.tyjg.testwsdl.service.IAuthenticationService;
import com.gotop.util.wss4j.PasswordHandler;

public class ClientTest {

	protected static Logger log = Logger.getLogger(ClientTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientTest ct = new ClientTest();
		
		ct.testWSS4JEncSig();
	}
	
	public String testWSS4JEncSig(){
		Service service = new ObjectServiceFactory().create(IAuthenticationService.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
		String url = "http://127.0.0.1:8083/services/authenticationService.ws";
		String result = "";
		try{
			IAuthenticationService hw = (IAuthenticationService) factory.create(service, url);
			Client client = ((XFireProxy)Proxy.getInvocationHandler(hw)).getClient();
			client.addOutHandler(new DOMOutHandler());
			Properties properties = new Properties();
			
			properties.setProperty(WSHandlerConstants.USER, "ws_security");
			properties.setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT +" "+  WSHandlerConstants.SIGNATURE);
			properties.setProperty(WSHandlerConstants.ENCRYPTION_USER, "ws_security");
			properties.setProperty(WSHandlerConstants.ENC_PROP_FILE, "com/gotop/util/wss4j/security_enc.properties");
			properties.setProperty(WSHandlerConstants.SIG_PROP_FILE, "com/gotop/util/wss4j/client_security_sign.properties");
			properties.setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordHandler.class.getName());
			client.addOutHandler(new WSS4JOutHandler(properties));
			
			result = hw.helloWord("lz");
			log.info(result);
			
			client.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
