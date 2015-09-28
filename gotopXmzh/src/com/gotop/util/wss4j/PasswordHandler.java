package com.gotop.util.wss4j;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityException;

/**
 * 
 * @author Administrator
 *
 */
public class PasswordHandler implements CallbackHandler {

	private final Map passwords = new HashMap();

	@SuppressWarnings("unchecked")
	public PasswordHandler() {
		passwords.put("ws_security", "keygotopord");
	}

	public void handle(Callback[] callbacks) throws WSSecurityException {
		WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
		String id = callback.getIdentifer();
		String validPw = (String) passwords.get(id);
		if (WSConstants.PASSWORD_TEXT.equals(callback.getPasswordType())) {
			// 密码是明文
			String pw = callback.getPassword();

			if (pw == null || !pw.equalsIgnoreCase(validPw)) {
				throw new WSSecurityException("password not match");
			}
		} else {
			callback.setPassword(validPw);
		}

	}
}
