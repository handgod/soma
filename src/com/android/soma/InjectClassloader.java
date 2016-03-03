package com.android.soma;

import java.lang.reflect.Method;

import android.util.Log;
import static com.android.soma.ReflectHelper.getIntField;

public class InjectClassloader extends ClassLoader {

	static {
		System.loadLibrary("Test");
	}

	public InjectClassloader() {
		// TODO Auto-generated constructor stub
		super(ClassLoader.getSystemClassLoader().getParent());

		try {
			Class<?> ret = Class.forName("com.example.testar.MainActivity");
			Method[] ms = ret.getDeclaredMethods();
			for (Method method : ms) {
				Log.i("method of class", method.getName());
				if (method.getName().equals("test")) {
					HookMethod(method);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Log.i("InjectClassloader_loadClass", className);
		return super.loadClass(className, resolve);
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Log.i("InjectClassloader_findClass", className);
		return super.findClass(className);
	}

	private void HookMethod(Method hookMethod) {
		System.out.println("Hook Method:" + hookMethod.getName());
		Class<?> declaringClass = hookMethod.getDeclaringClass();
		int slot = (int) getIntField(hookMethod, "slot");
		hookMethodNative(hookMethod, declaringClass, slot);
	}

	private native void hookMethodNative(Method hookMethod, Class<?> declaringClass, int slot);
}