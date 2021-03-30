package com.careerit.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.careerit.configurations.model.AppConfiguration;
import com.careerit.configurations.model.Contact;
import com.careerit.configurations.model.Server;
import com.careerit.configurations.model.ServerDetails;

/**
 * Unit test for simple App.
 */
public class AppTest {
		
		private final String fileName = "src/test/resources/application.properties";
		private final String serverDetailsPath = "src/test/resources/server.properties";
		@Test
		void loadUsernameAndPasswordTest() throws FileNotFoundException, IOException {
			
			Properties properties = new Properties();
			properties.load(new FileInputStream(fileName));
			
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			assertEquals("krish", username);
			assertEquals("krish@123!", password);
			
		}
		@Test
		void loadServerDataTest() throws FileNotFoundException, IOException {
			Properties properties = new Properties();
			properties.load(new FileInputStream(serverDetailsPath));
			List<Server> list = new ArrayList<>();
			
			for(int i=0;i<=3;i++) {
					String ipKey = String.format("lwl.server[%d].ip", i);
					String userKey = String.format("lwl.server[%d].ip", i);
					String pwdKey = String.format("lwl.server[%d].ip", i);
					String ip = properties.getProperty(ipKey);
					String username = properties.getProperty(userKey);
					String password = properties.getProperty(pwdKey);
					Server serverDetails = new Server(ip, username, password);
					list.add(serverDetails);
			}
			System.out.println(list);
			assertEquals(4, list.size());
			
		}
		@Test
		void yamlReadingFromString() {
			Yaml yaml = new Yaml();
			String document = "\n- Hesperiidae\n- Papilionidae\n- Apatelodidae\n- Epiplemidae";
			List<String> list = yaml.load(document);
			System.out.println(list);
		}
		@Test
		void yamlReadingFromFile() throws FileNotFoundException {
			Yaml yaml = new Yaml();
			ServerDetails serverDetails = yaml.loadAs(new FileInputStream("src/test/resources/server.yaml"),ServerDetails.class);
			System.out.println(serverDetails.getServers().size());
			System.out.println(serverDetails);
		}
		
		@Test
		void readYamlContactsTest() throws FileNotFoundException {
			Yaml yaml = new Yaml(new Constructor(Contact.class));
			Iterable<Object> itr = yaml.loadAll(new FileInputStream("src/test/resources/contacts.yaml"));
			List<Contact> list = new ArrayList<Contact>();
			itr.forEach(e->{
					if(e instanceof Contact) {
						Contact contact = (Contact)e;
						list.add(contact);
					}
			});
			assertEquals(5, list.size());
		}
		
		@Test
		void appConfigTest() throws FileNotFoundException {
				AppConfiguration appConfiguration = appConfig();
				assertEquals("192.123.12.3", appConfiguration.getIpAddress());
		}
		
		
		
		private AppConfiguration appConfig() throws FileNotFoundException {
			Yaml yaml = new Yaml();
			AppConfiguration appConfig = yaml.loadAs(new FileInputStream("src/test/resources/serverconfig.yaml"),AppConfiguration.class);
		
			Properties properties = new Properties();
		    for (Object data : yaml.loadAll(new FileInputStream("src/test/resources/serverconfig.yaml"))) {
		    	LinkedHashMap<String,Object> map= (LinkedHashMap<String,Object>) data;
		    	map.entrySet().forEach(e->{
		    		properties.put(e.getKey(),e.getValue());
		    	});
		    }
			System.out.println(properties);
			return appConfig;
		
		}
		
		
		
		
		
		
}
