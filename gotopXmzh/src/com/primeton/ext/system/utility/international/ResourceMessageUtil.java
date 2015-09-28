package com.primeton.ext.system.utility.international;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.eos.system.utility.StringUtil;
import com.gotop.util.file.JavaCallLogic;
import com.gotop.util.security.ForUtil;
import com.primeton.system.utility.impl.ResourceLoadHelper;

public class ResourceMessageUtil {
	public static final Locale EOS_DEFAULT_LOCALE = new Locale("EOS_DEFAULT");
	private static final String RESOURCE_EXCEPITON_MAP_FILE_NAME = "META-INF/com.primeton.ext.international.resource.exception";
	private static Map<Locale, Object> lockMap;
	private static Properties caseProperties;

	public static String getExceptionResourceMessage(String message_id) {
		return getExceptionResourceMessage(message_id, Locale.getDefault());
	}

	public static String getExceptionResourceMessage(String message_id,
			Locale locale) {
		return getResourceMessage("EXCEPTION", message_id, locale);
	}

	public static String getI18nResourceMessage(String message_id) {
		return getI18nResourceMessage(message_id, Locale.getDefault());
	}

	public static String getI18nResourceMessage(String message_id, Locale locale) {
		return getResourceMessage("I18N", message_id, locale);
	}

	public static String getResourceMessage1(String resourceType,
			String message_id, Locale locale) {
		if (message_id == null) {
			return null;
		}

		Properties prop = autoSearchProperties(
				ResourceRegister.PropertiesCache.SYS_PROPERTY,
				ResourceRegister.ResourceCache.SYS_RESOURCE, resourceType,
				locale, true);
		String message = prop.getProperty(message_id);

		if (message == null) {
			prop = autoSearchProperties(
					ResourceRegister.PropertiesCache.USER_PROPERTY,
					ResourceRegister.ResourceCache.USER_RESOURCE, resourceType,
					locale, true);
			message = prop.getProperty(message_id);

			if (message == null) {
				prop = ResourceRegister.PropertiesCache.SYS_PROPERTY.get(
						resourceType, EOS_DEFAULT_LOCALE);
				message = prop.getProperty(message_id);

				if (message == null)
					prop = ResourceRegister.PropertiesCache.USER_PROPERTY.get(
							resourceType, EOS_DEFAULT_LOCALE);
			}
		} else {
			return message;
		}

		return prop.getProperty(message_id);
	}
	public static String getResourceMessage(String resourceType,
			String message_id, Locale locale) {
		if (StringUtils.isBlank(message_id)) {
			return null;
		}
		if(caseProperties==null){
			caseProperties = loadResourceMessage();
		}
		return caseProperties.getProperty(message_id);
	}
	
	public static Properties loadResourceMessage(){
		Properties prop = new Properties();
		InputStream in = null;
		try{
			String templateFile = JavaCallLogic.getSystemPath()+"resources/i18n/";
			File i18n = ForUtil.createFile(templateFile);
			File[] files = i18n.listFiles();
			for (int i = 0; i < files.length; i++) {
				if(files[i].getName().endsWith("_zh_CN.properties")){
					try {
						in = ForUtil.createFileInputStream(files[i]);
						prop.load(in);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(in!=null){
							in.close();
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prop;
	}

	private static synchronized Object getLock(Locale locale) {
		Object lock = lockMap.get(locale);
		if (lock == null) {
			lock = new Object();
			lockMap.put(locale, lock);
		}
		return lock;
	}

	private static Properties autoSearchProperties(
			ResourceRegister.PropertiesCache propertyCache,
			ResourceRegister.ResourceCache resourceCache, String resourceType,
			Locale locale, boolean isOnlyOnce) {
		Properties propLocale = propertyCache.get(resourceType, locale);

		if ((propLocale == null) || ((!isOnlyOnce) && (propLocale.isEmpty()))) {
			Object lock = getLock(locale);
			synchronized (lock) {
				propLocale = propertyCache.get(resourceType, locale);
//				if ((propLocale == null)
//						|| ((!isOnlyOnce) && (propLocale.isEmpty()))) {
					registerLocalePropperties(propertyCache, resourceCache,
							resourceType, locale);
					propLocale = propertyCache.get(resourceType, locale);
					if (propLocale == null) {
						propLocale = new Properties();

						if (isOnlyOnce) {
							propertyCache.put(resourceType, locale, propLocale);
						}
					}
//				}
			}
		}
		return propLocale;
	}

	private static void registerLocalePropperties(
			ResourceRegister.PropertiesCache propertyCache,
			ResourceRegister.ResourceCache resourceCache, String resourceType,
			Locale locale) {
		String[] resoureFiles = resourceCache.getResourceFileNames(
				resourceType, EOS_DEFAULT_LOCALE);
		for (String resource : resoureFiles) {
			int index = resource.lastIndexOf('.');
			String localFileName = resource;
			String extention = "";

			if (index != -1) {
				localFileName = resource.substring(0, index);
				extention = resource.substring(index);
			}
			localFileName = StringUtil.concat(new Object[] { localFileName,
					"_", locale.toString(), extention });
			Properties prop = ResourceLoadHelper
					.loadResourceProperties(localFileName);
			if (!prop.isEmpty()) {
				resourceCache.registerResource(resourceType, locale,
						localFileName);
				propertyCache.put(resourceType, locale, prop);
			}
		}
	}

	static {
		Enumeration resoureceUrl = null;
		try {
			resoureceUrl = ResourceMessageUtil.class
					.getClassLoader()
					.getResources(
							"META-INF/com.primeton.ext.international.resource.exception");
		} catch (IOException ignore) {
		}

		if (resoureceUrl != null) {
			while (resoureceUrl.hasMoreElements()) {
				Properties resourceProp = new Properties();
				InputStream stream = null;
				URL exceptionResourceUrl = (URL) resoureceUrl.nextElement();
				try {
					stream = exceptionResourceUrl.openStream();
					resourceProp.load(stream);
				} catch (IOException ignore) {
				} finally {
					try {
						if (stream != null) {
							stream.close();
						}
					} catch (IOException ignore) {
					}
				}
				for (Map.Entry entry : resourceProp.entrySet()) {
					String resource = String.valueOf(entry.getKey())
							+ String.valueOf(entry.getValue());
					Locale locale = ResourceLoadHelper
							.getResourceLoacle(resource);
					ResourceRegister.ResourceCache.SYS_RESOURCE
							.registerResource("EXCEPTION", locale, resource);
					ResourceRegister.PropertiesCache.SYS_PROPERTY.put(
							"EXCEPTION", locale, ResourceLoadHelper
									.loadResourceProperties(resource));
				}

			}

		}

		lockMap = new HashMap();
	}
	public static void main(String[] arg){
		String rm = getResourceMessage(null,"applicationManager_AcApplication.appcode",null);
		System.out.println(rm);
		String templateFile = JavaCallLogic.getSystemPath();
		System.out.println(templateFile);
		System.out.println(loadResourceMessage());
	}
}