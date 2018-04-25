package com.sjh.study.first.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
/**
 * 리플렉션을 이용하여 자동 세팅처리 유틸리티 
 * @author restnfeel
 *
 */
public class ReflectPojoSetUtil {

	private static Logger LOG = LoggerFactory.getLogger(ReflectPojoSetUtil.class);
	
	public static Object setPojo(Object tzz, Object tls) throws Exception{

		Map<String,String> map = new HashMap<String,String>();
		
		Class clazz = tzz.getClass();
		Class cls = tls.getClass();
		
		Method[] methods = cls.getDeclaredMethods();
		for(Method m : methods){
			String methodName = m.getName();
			if(methodName.startsWith("set")){
				String mName = methodName.substring(3, methodName.length());
				//LOG.info("[check] {} ||  {} " , methodName, mName);
				map.put(mName.toUpperCase(), methodName);
			}
		}
		
		
		Field[] fields = cls.getDeclaredFields();
		
		for(Field fd : fields){
			String fieldName = fd.getName();

			if(map.containsKey(fieldName.toUpperCase())){					
				Field field = ReflectionUtils.findField(cls, fieldName);
				ReflectionUtils.makeAccessible(field);
				if(field.get(tls)!=null){							
					//LOG.info("value = " + field.get(tls));
					BeanUtils.setProperty(tzz, fieldName, field.get(tls));
				}
			}
		}
		
		return clazz.cast(tzz);
	}
	
}
