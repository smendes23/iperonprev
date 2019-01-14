package br.com.iperonprev.util.jsf;

import java.lang.reflect.Method;

public class ReflectionUtils {

	public static boolean isGetter(Method m){
		return m.getName().startsWith("get") &&
				m.getReturnType() != void.class &&
				m.getParameterTypes().length == 0;
	}
	
	public static boolean isSetter(Method m){
		return m.getName().startsWith("set") &&
				m.getReturnType() != void.class &&
				m.getParameterTypes().length == 0;
	}
	
	public static String fromGetterToProperties(String nameGetter){
		StringBuilder retorno = new StringBuilder();
		retorno.append(nameGetter.substring(3,4));
		retorno.append(nameGetter.substring(4));
		return retorno.toString();
	}
	
	public static boolean containMethodName(Method m,String name){
		return m.getName().contains(name);
	}
}
