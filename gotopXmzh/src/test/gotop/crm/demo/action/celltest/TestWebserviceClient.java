package test.gotop.crm.demo.action.celltest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gotop.tyjg.testwsdl.service.IAuthenticationService;

public class TestWebserviceClient {
	protected static Logger log = Logger.getLogger(TestWebserviceClient.class);

	private static void testWSS4JEncSig() {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("config/common/common-zclientTest.xml");
			IAuthenticationService cws = (IAuthenticationService) ctx.getBean("AuthenticationService");
			String result = cws.helloWord("lz");
			log.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		testWSS4JEncSig();
	}

}
