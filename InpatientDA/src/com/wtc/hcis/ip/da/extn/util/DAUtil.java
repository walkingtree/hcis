/**
 * 
 */
package com.wtc.hcis.ip.da.extn.util;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author Bhavesh
 *
 */
public class DAUtil {

	/**
	 * This static method checks whether the given object is valid for manipulation or not.
	 * @param obj
	 * @return
	 */
	public static boolean isValid(Object obj){
		
		if( obj instanceof java.lang.String  ){
			String stringObj = (String) obj;

			if( stringObj != null && stringObj.length() >0  ){
				return true;
			}
			
			return false;
		}
		
		if( obj instanceof java.util.List  ){
			List listObj = (List) obj;

			if( listObj != null && !listObj.isEmpty()  ){
				return true;
			}
			
			return false;
		}

		if( obj instanceof java.util.Map  ){
			Map mapObj = (Map) obj;

			if( mapObj != null && !mapObj.isEmpty()  ){
				return true;
			}
			
			return false;
		}

//For other objects i.e. Integer,Double or Custom objects		
		if( obj != null ){
			return true;
		}

		return false;

	}
	
/**
 * This static method returns HqlQuery for given Velocity template over given  VelocityContext
 * @param templateName
 * @param context
 * @return hqlQuery
 * @throws Exception
 */
	public static String getHqlQuery( String templateName,VelocityContext context ) throws Exception{
		try{		
			VelocityEngine engine = new VelocityEngine();
			Properties props = new Properties();
			props.setProperty(VelocityEngine.RESOURCE_LOADER, "classpath");
			props.setProperty("classpath." + VelocityEngine.RESOURCE_LOADER + ".class",
					ClasspathResourceLoader.class.getName());
			engine.init(props);
	
			Template template = engine.getTemplate(templateName);
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			writer.close();

			String hqlQuery = writer.getBuffer().toString();
			
			return hqlQuery;
		}catch( Exception e){
			throw new Exception("Failed to generate Hql: " + e.getMessage());
		}
		
	}
}
