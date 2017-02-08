# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\development\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志
-ignorewarnings

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆
-keepattributes Annotation
-keepattributes JavascriptInterface

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}


# 友盟第三方登录提供的混淆代码块
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
#-libraryjars libs/SocialSDK_QQZone_2.jar
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep public class [your_pkg].R$*{
    public static final int *;
}


# butterknife 注入相关
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


# 不混淆七牛的jar包
#（声明lib文件）-libraryjars **.jar
#（不提示警告）-dontwarn com.xx.bbb.**
#（不进行混淆）-keep class com.xx.bbb.** { *;}

#-libraryjars libs/qiniu-android-sdk-7.0.9.jar -->jar包已被统一声明,所以这里不用再重复声明
-dontwarn com.qiniu.android.**
-keep class com.qiniu.android.** { *;}



#eventbus
-dontwarn de.greenrobot.event.**
-keep class de.greenrobot.event.** { *; }
-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    public <init>(java.lang.Throwable);
}
# Don't warn for missing support classes
-dontwarn de.greenrobot.event.util.*$Support
-dontwarn de.greenrobot.event.util.*$SupportManagerFragment


# volley
-keep class com.android.volley.** {*;}
-keep class com.android.volley.toolbox.** {*;}
-keep class com.android.volley.Response$* { *; }
-keep class com.android.volley.Request$* { *; }
-keep class com.android.volley.RequestQueue$* { *; }
-keep class com.android.volley.toolbox.HurlStack$* { *; }
-keep class com.android.volley.toolbox.ImageLoader$* { *; }


#jpush
#-libraryjars libs/jpush-sdk-release1.6.3.jar

-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class com.sina.push.** { *; }

#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}
#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.** {*;}




# 保留domain包中的类
-keep class com.qxinli.android.kit.domain.** { *; }


#友盟统计相关
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keep public class com.qxinli.android.R$*{
    public static final int *;
}

-keep class  com.qxinli.android.kit.net.JSInterface {
  *;
}

-keepclassmembers class  com.qxinli.android.kit.net.JSInterface {
  public *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#-dontwarn class com.umeng.socialize.**

-dontwarn com.umeng.**
-keep class com.umeng*.** {*; }
-keep class com.umeng.socialize.** { *; }
-keep public class com.umeng.fb.** { *;}

#-keep u.aly.bt.** { *; }

#-dontwarn com.umeng.analytics.** { *; }
#-keep com.umeng.analytics.** { *; }


#fresco 混淆后加固出错，要让它不要混淆

# fresco start------------------------
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep ,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

-keep class com.facebook.imagepipeline.animated.factory.** { *; }

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}
-keep class com.facebook.animated.gif.** {
    *;
}


-keep class com.facebook.** { *; }
-keep class com.facebook.imagepipeline.animated.** { *; }
-dontwarn okio.**
-dontwarn javax.annotation.**
# Keep native methods
-dontwarn com.android.volley.toolbox.**

-keep class com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl {
public AnimatedFactoryImpl(com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory, com.facebook.imagepipeline.core.ExecutorSupplier);
}
# fresco end ------------------------`








-keep class de.greenrobot.dao.** {*;}
#保持greenDao的方法不被混淆
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {

 public static java.lang.String TABLENAME;
 }
 -keep class **$Properties

-keep class com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn com.thoughtworks.xstream.**
-keep class com.thoughtworks.xstream.** { *; }
-keep class org.dom4j.** { *; }

-keep class org.jdom.** { *; }
-dontwarn javax.**
-dontwarn java.nio.**
-keep class javax.** { *; }
-keep class nu.xom.** { *; }
-keep class org.codehaus.jettison.** { *; }


-keep class android.webkit.** { *; }
-keep class javax.xml.** { *; }
-keep class java.nio.** { *; }
-keep class org.codehaus.mojo.** { *; }

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
-keep interface com.zhy.http.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

# chart
-keep class com.github.mikephil.charting.** { *; }
-dontwarn io.realm.**

#superid face detect
-keep class **.R$* {*;}
-keep class com.isnc.facesdk.aty.**{*;}
-keep class com.isnc.facesdk.**{*;}
-keep class com.isnc.facesdk.common.**{*;}
-keep class com.isnc.facesdk.net.**{*;}
-keep class com.isnc.facesdk.view.**{*;}
-keep class com.isnc.facesdk.viewmodel.**{*;}
-keep class com.matrixcv.androidapi.face.**{*;}
 
-keep class com.qxinli.android.kit.receiver.MIPushMessageReceiver {*;}


-keep class cn.jiajixin.nuwa.** { *; }

#微信sdk 混淆
-keep class com.tencent.mm.sdk.** {
   *;
}
#ali pay 混淆

#-libraryjars libs/alipaySDK-20150602.jar

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}


#rocoofix 热修复
-keep class com.dodola.rocoofix.** {*;}
-keep class com.lody.legend.** {*;}
-keepclassmembers class com.qxinli.android.** {
  public <init>();
}

#//保留init,和include package保持一致


# ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }


#filepick
-keep class cn.qqtheme.framework.entity.** { *;}
-keep class cn.qqtheme.framework.picker.AddressPicker$* { *;}


#luban图片压缩,包括rxjava,rxandroid
-keep class top.zibin.luban.** { *;}
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**


