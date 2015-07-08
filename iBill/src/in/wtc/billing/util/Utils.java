/**
 * 
 */
package in.wtc.billing.util;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.BeanMap;

/**
 * @author Alok Ranjan
 *
 */
public class Utils {
	public static void dumpBean(Object obj) {
		if (obj != null) {
			org.apache.commons.collections.BeanMap beanMap = new BeanMap(obj);
			Set keys = beanMap.keySet();

			Iterator keyIterator = keys.iterator();
			System.out.println("------ Dumping " + obj.getClass().toString() + " -------");
			while(keyIterator.hasNext()) {
				String propertyName = (String)keyIterator.next();

				if (propertyName.equalsIgnoreCase("class"))
					continue;

				System.out.println(propertyName + " : " + beanMap.get(propertyName));			
			}
			System.out.println("--------------------");
		}
	}

}
