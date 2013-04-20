package me.thinkjet.auth;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.ehcache.util.ClassLoaderUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @ClassName Configuration
 * @author johnny_zyc
 * @Modified 2013-4-13 下午1:22:57
 * 
 */
public final class Configuration {
	private static Set<GlobeRoles> intercept_map = new HashSet<GlobeRoles>();
	private static String login_url;
	private static String auth_failure_url;
	private static String auth_success_default_url;
	private static final String DEFAULT_CLASSPATH_CONFIGURATION_FILE = "/auth.xml";

	public static void initConfiguration() {
		ClassLoader standardClassloader = ClassLoaderUtil
				.getStandardClassLoader();
		URL url = null;
		if (standardClassloader != null) {
			url = standardClassloader
					.getResource(DEFAULT_CLASSPATH_CONFIGURATION_FILE);
		}
		if (url == null) {
			url = Configuration.class
					.getResource(DEFAULT_CLASSPATH_CONFIGURATION_FILE);
		}

		InputStream input = null;
		try {
			input = url.openStream();
			InputStream translatedInputStream = translateSystemProperties(input);
			DocumentBuilder domBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = domBuilder.parse(translatedInputStream);
			NodeList intercepts = doc.getElementsByTagName("intercept-url");
			if (intercepts != null) {
				for (int i = 0; i < intercepts.getLength(); i++) {
					Node intercept = intercepts.item(i);
					if (intercept.getAttributes().getNamedItem("pattern") != null
							&& intercept.getAttributes().getNamedItem("access") != null) {
						String pattern = intercept.getAttributes()
								.getNamedItem("pattern").getNodeValue();
						String[] access = intercept.getAttributes()
								.getNamedItem("access").getNodeValue()
								.split(",");
						GlobeRoles gr = new GlobeRoles(pattern, access);
						intercept_map.add(gr);
					}

				}
			}

			if (doc.getElementsByTagName("login-url") != null) {
				login_url = doc.getElementsByTagName("login-url").item(0)
						.getAttributes().getNamedItem("value").getNodeValue();
			}
			if (doc.getElementsByTagName("auth-failure-url") != null) {
				auth_failure_url = doc.getElementsByTagName("auth-failure-url")
						.item(0).getAttributes().getNamedItem("value")
						.getNodeValue();
			}
			if (doc.getElementsByTagName("auth-success-default-url") != null) {
				auth_success_default_url = doc
						.getElementsByTagName("auth-success-default-url")
						.item(0).getAttributes().getNamedItem("value")
						.getNodeValue();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
			}
		}
	}

	private static InputStream translateSystemProperties(InputStream inputStream)
			throws IOException {
		StringBuilder sb = new StringBuilder();

		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		String configuration = sb.toString();

		Set<String> tokens = extractPropertyTokens(configuration);
		for (Iterator<String> i$ = tokens.iterator(); i$.hasNext();) {
			Object tokenObject = i$.next();
			String token = (String) tokenObject;
			String leftTrimmed = token.replaceAll("\\$\\{", "");
			String trimmedToken = leftTrimmed.replaceAll("\\}", "");

			String property = System.getProperty(trimmedToken);
			if (property == null) {
			} else {
				String propertyWithQuotesProtected = Matcher
						.quoteReplacement(property);
				configuration = configuration.replaceAll("\\$\\{"
						+ trimmedToken + "\\}", propertyWithQuotesProtected);

			}
		}

		return new ByteArrayInputStream(configuration.getBytes("UTF-8"));
	}

	static Set<String> extractPropertyTokens(String sourceDocument) {
		Set<String> propertyTokens = new HashSet<String>();
		Pattern pattern = Pattern.compile("\\$\\{.+?\\}");
		Matcher matcher = pattern.matcher(sourceDocument);
		while (matcher.find()) {
			String token = matcher.group();
			propertyTokens.add(token);
		}
		return propertyTokens;
	}

	public static Set<GlobeRoles> getIntercept_map() {
		return intercept_map;
	}

	public static String getLogin_url() {
		return login_url;
	}

	public static String getAuth_failure_url() {
		return auth_failure_url;
	}

	public static String getAuth_success_default_url() {
		return auth_success_default_url;
	}
}