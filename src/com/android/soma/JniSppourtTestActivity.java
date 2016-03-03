package com.android.soma;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.GetChars;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import cn.trinea.android.common.util.*;

public class JniSppourtTestActivity extends Activity {
	private final Map<String, ClassLoader> mLoaders = new HashMap<String, ClassLoader>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jni_sppourt_test_activity);

		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
				// TODO Auto-generated method stub
				WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wifi.getConnectionInfo();
				System.out.println("wqm Wifi real mac before inject:" + info.getMacAddress());
//				InjectApplication ia = (InjectApplication) getApplication();
//				System.out.println(ia.test());
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			 System.out.println("wqm injecting.....");
			 
			 String[] commands = new String[] { "chmod 777 /data/local/inject","chmod 777 /data/local/libso.so",
					"chmod 777 /data/local/libTest.so","su -c /data/local/inject"
					 };
			 ShellUtils.execCommand(commands, true);
			 
			 String apkRoot = "chmod 777 " + getPackageCodePath();			 
			 ShellUtils.execCommand(apkRoot, true);
			 
			 try {
				Process process = Runtime.getRuntime().exec("su");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			 
//			 System.out.println("wqm injecting:"+stringFromJNI());
			 stringFromJNI();
			}
		});
		
		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wifi.getConnectionInfo();
				System.out.println("wqm Wifi mac after inject:" + info.getMacAddress());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public native String  stringFromJNI();
	
	
}
