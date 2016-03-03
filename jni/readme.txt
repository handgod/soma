环境搭建：
1)Eclipse for Java ,Version: Mars.2 Release (4.5.2)
2)ADT-23.0.6.zip
3)android-ndk-r10e + cygwin,并设置路径
4)SDK android api 19.
使用方法：
1)编译完后，Run as Application
2)双击jni/run.bat
思路分析：
1)运行一个com.example.testar进程作为目标。
2)通过运行inject ,将libso.so注入com.example.testar进程中，在libTest.so中修改注入的方法。
调试native method的参考文章：
http://blog.csdn.net/yinyhy/article/details/9858413
其实这个方法复杂了，现在只需两步即可：
http://blog.csdn.net/wutianyin222/article/details/8222838
或者参考：
http://tools.android.com/recent/usingthendkplugin

NDK开发方法小结：
1)创建Android Project.
2)声明各种Native 方法的名字和参数，并build apk,生成class文件，在class目录或者src目录通过javah生成jni头文件；
3)增加native support,生成jni文件夹，并将javah生成的jni头文件拷贝到jni文件夹；
4)增加头文件对应的函数，并编写Android.mk和Application.mk两个文件实现编译。
5)在Eclipse的Builder中配置ndk-build NDK_DEBUG=1,并将AndroidManifest.xml中的Debuggabe修改为true;
6)开始动态调试c/C++代码。


进程安全分析：
1)http://www.360doc.com/content/13/1215/07/9462341_337249770.shtml
2)http://blog.163.com/yuanxiaohei@126/blog/static/6742308720122264441993/
3)https://www.zhihu.com/question/21074979
4)http://hold-on.iteye.com/blog/1901152


Android ptrace简介:
1)http://blog.csdn.net/myarrow/article/details/9617673


 Dalvik虚拟机垃圾收集机制简要介绍和学习计划:
1)http://blog.csdn.net/luoshengyang/article/details/41338251
2)http://blog.csdn.net/luoshengyang/article/details/40289405
3)http://blog.csdn.net/luoshengyang/article/details/8923485
