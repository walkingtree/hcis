package in.wtc.hcis.test.unit;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.BedMasterBM;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanMap;
import org.apache.log4j.Logger;
import org.exolab.castor.xml.Marshaller;
/**
 * 
 * @author Ajit Kumar
 *
 */
public class Util {

	public static void dumpBean(Object obj) {
		if (obj != null) {
			BeanMap beanMap = new BeanMap(obj);
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
	
	public static void dumpBeanJSRecord(Object obj) {
		if (obj != null) {
			BeanMap beanMap = new BeanMap(obj);
			Set keys = beanMap.keySet();

			Iterator keyIterator = keys.iterator();
			System.out.println("------ Dumping " + obj.getClass().toString() + " -------");
			while(keyIterator.hasNext()) {
				String propertyName = (String)keyIterator.next();

				if (propertyName.equalsIgnoreCase("class"))
					continue;

				System.out.println(",{name:'" + propertyName + "', mapping: '" + propertyName + "'}");			
			}
			System.out.println("--------------------");
		}
	}

	public static void dumpBeanJSRecordCompact(Object obj) {
		if (obj != null) {
			BeanMap beanMap = new BeanMap(obj);
			Set keys = beanMap.keySet();

			Iterator keyIterator = keys.iterator();
			System.out.println("------ Dumping " + obj.getClass().toString() + " -------");
			while(keyIterator.hasNext()) {
				String propertyName = (String)keyIterator.next();

				if (propertyName.equalsIgnoreCase("class"))
					continue;

				System.out.println(",{'name:" + propertyName + "'}");			
			}
			System.out.println("--------------------");
		}
	}
	
	public static void dumpBeanXML(Object obj) {
		try {
			if (obj != null) {
				FileWriter writer = new FileWriter("C:\\" + obj.getClass().getName() + ".xml");
				Marshaller.marshal(obj, writer);
			}
		}
		catch (Exception ex) {
			System.err.println("Oops! I failed to carry out my job correctly! Please curse me!");
		}
	}
	
	public static void main(String[] args) {

//		Logger log = Logger.getLogger(Util.class);
//		log.error("Logging starts");
		
		String str="what going on";
		   System.out.println(str);
		   int len=str.length();
		   for(int i=len-1;i>=0;i--)
		   System.out.print(str.charAt(i));
	}
	
}
