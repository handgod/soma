#include "jni.h"
#include "android_runtime/AndroidRuntime.h"
#include "android/log.h"
#include "stdio.h"
#include "stdlib.h"
#include "MethodHooker.h"
#include <utils/CallStack.h>
#include "art.h"
#define log(a,b) __android_log_write(ANDROID_LOG_INFO,a,b); // LOG类型:info
#define log_(b) __android_log_write(ANDROID_LOG_INFO,"JNI_LOG_INFO",b); // LOG类型:info


extern "C" void InjectInterface(char*arg){
	log_("wqm*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	log_("wqm*-*-*-*-*-* Injected so *-*-*-*-*-*-*-*");
	log_("wqm*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	Hook();
	log_("wqm*-*-*-*-*-*-*- End -*-*-*-*-*-*-*-*-*-*");
}

extern "C" JNIEXPORT jstring JNICALL Java_com_example_testar_InjectApplication_test(JNIEnv *env, jclass clazz)
{
//	Abort_(); //test for art vm,remove it on android sdk api 19 ,running well.
    return env->NewStringUTF("wqm in so.cpp test () return haha ");;
}
