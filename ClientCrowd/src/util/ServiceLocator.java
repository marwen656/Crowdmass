package util;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * Implementation ServiceLocator design pattern which implements Singeltion
 * @author Amine
 *
 */
public class ServiceLocator {
	private static ServiceLocator instance;
	private static Context context;
	private static Map<String,Object> cache;
/**
 * searches for a serviceLocator instance , else instanties it
 * @return the only instance of ServiceLocator
 */
	public static ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}

	private ServiceLocator() {
		try {
			context = new InitialContext();
			cache= new HashMap<String, Object>();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * This method return a proxy either from a cahce (if already exists) or it
 * invokes a lookup method
 * @param jndi is the JNDI binding for a Session Bean
 * @return a proxy (it's to cast after call)
 */
	public static Object getProxy(String jndi) {
		Object object = null;
		try {
			if(cache.get(jndi)!=null){
				object= cache.get(jndi);
			}else{
				object = context.lookup(jndi);
				cache.put(jndi, object);
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

}
