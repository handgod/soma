package com.android.soma;

import java.lang.reflect.*;

public class ReflectHelper {
	/**
	 * Look up a field in a class and set it to accessible. The result is
	 * cached. If the field was not found, a {@link NoSuchFieldError} will be
	 * thrown.
	 */
	public static Field findField(Class<?> clazz, String fieldName) {
		StringBuilder sb = new StringBuilder(clazz.getName());
		sb.append('#');
		sb.append(fieldName);
		String fullFieldName = sb.toString();

		try {
			Field field = findFieldRecursiveImpl(clazz, fieldName);
			field.setAccessible(true);
			return field;
		} catch (NoSuchFieldException e) {
			throw new NoSuchFieldError(fullFieldName);
		}
	}

	private static Field findFieldRecursiveImpl(Class<?> clazz, String fieldName) throws NoSuchFieldException {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			while (true) {
				clazz = clazz.getSuperclass();
				if (clazz == null || clazz.equals(Object.class))
					break;

				try {
					return clazz.getDeclaredField(fieldName);
				} catch (NoSuchFieldException ignored) {
				}
			}
			throw e;
		}
	}

	public static int getIntField(Object obj, String fieldName) {
		try {
			return findField(obj.getClass(), fieldName).getInt(obj);
		} catch (IllegalAccessException e) {
			// should not happen
			throw new IllegalAccessError(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
}
