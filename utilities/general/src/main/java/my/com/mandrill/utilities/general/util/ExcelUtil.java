package my.com.mandrill.utilities.general.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import my.com.mandrill.utilities.general.annotation.ExcelColumnHeader;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import my.com.mandrill.utilities.general.exception.BusinessException;

public class ExcelUtil {

	public static <T> List<LinkedHashMap<String, String>> extractDataFromObjects(List<T> list) {
		List<LinkedHashMap<String, String>> data = new ArrayList<>();
		for (T item : list) {
			LinkedHashMap<String, String> rowData = new LinkedHashMap<>();
			Class<?> aClass = item.getClass();
			Field[] fields = aClass.getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					Object value = field.get(item);
					String key = field.getName();
					if (field.isAnnotationPresent(ExcelColumnHeader.class)) {
						ExcelColumnHeader annotation = field.getAnnotation(ExcelColumnHeader.class);
						key = annotation.value();
					}
					String cellValue = convertValueToString(value);
					rowData.put(key, cellValue);
				}
				catch (IllegalAccessException e) {
					// Handle access exceptions gracefully
					e.printStackTrace();
				}
			}
			data.add(rowData);
		}
		return data;
	}

	private static String convertValueToString(Object value) {
		if (value == null) {
			return "";
		}
		else if (value instanceof Boolean) {
			return (Boolean.TRUE.equals(value)) ? "True" : "False";
		}
		else if (hasProperty(value.getClass(), "name")) {
			try {
				Method getter = value.getClass().getMethod("getName");
				return (String) getter.invoke(value);
			}
			catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
				// Handle method invocation exceptions gracefully
				e.printStackTrace();
				throw new BusinessException(ErrorCodeGlobalEnum.RUNTIME_EXCEPTION, e.getMessage());
			}
		}
		else {
			return value.toString();
		}
	}

	// Check if the class has a property with the given name
	private static boolean hasProperty(Class<?> clazz, String propertyName) {
		try {
			clazz.getMethod("get" + capitalize(propertyName));
			return true;
		}
		catch (NoSuchMethodException e) {
			return false;
		}
	}

	// Capitalize the first letter of a string
	private static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

}
