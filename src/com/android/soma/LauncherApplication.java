/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.soma;

import java.lang.reflect.Field;

import android.app.Application;
import android.util.Log;

public class LauncherApplication extends Application {
    @Override
    public void onCreate() {
//    	injectLoader(); removed this line,because new method never tried.
        super.onCreate();
        LauncherAppState.setApplicationContext(this);
        LauncherAppState.getInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LauncherAppState.getInstance().onTerminate();
    }
    static {
        System.loadLibrary("hello-jni");
        System.loadLibrary("so");
    }
    private void injectLoader() {
		try {
			System.out.println("Inject loader");
			ClassLoader ldr = this.getClassLoader();// findLoaderObject();
			Log.i("findLoaderObject", ldr.toString());
			Field[] fieldS = Class.forName("java.lang.ClassLoader").getDeclaredFields();
			for (Field field : fieldS) {
				Log.i("field of PathclassLoader", field.getName());
				if (field.getName().endsWith("parent")) {
					System.out.println("parent");
					field.set(ldr, new InjectClassloader());
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    private static ClassLoader findLoaderObject() {
		try {
			Class cCl = Class.forName("java.lang.ClassLoader");
			Class[] cls = cCl.getDeclaredClasses();
			for (int i = 0; i < cls.length; i++) {
				if (cls[i].getName().endsWith("SystemClassLoader")) {
					System.out.println("SystemClassLoader");
					return (ClassLoader) cls[i].getDeclaredField("loader").get(null);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	
}