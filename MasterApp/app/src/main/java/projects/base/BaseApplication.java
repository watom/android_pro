package projects.base;

import android.app.Application;

import cn.com.weather.cj.widget.view.WeatherConfig;

/**
 * Application和Activity,Service一样是Android框架的一个系统组件.
 * Android系统自动会为每个程序运行时创建一个Application类的对象且只创建一个，所以Application可以说是单例（singleton）模式的一个类。
 * 作    用：当Android程序启动时系统会创建一个 Application对象，用来存储系统的一些信息。
 * 启动Application时，系统会创建一个PID，即进程ID，所有的Activity都会在此进程上运行。那么我们在Application创建的时候初始化全局变量，
 * 同一个应用的所有Activity都可以取到这些全局变量的值，换句话说，我们在某一个Activity中改变了这些全局变量的值，那么在同一个应用的其他Activity中值就会改变。
 * Application对象的生命周期是整个程序中最长的，它的生命周期就等于这个程序的生命周期。因为它是全局的单例的，所以在不同的Activity,Service中
 * 获得的对象都是同一个对象。 所以可以通过Application来进行一些，如：数据传递、数据共享和数据缓存等操作。
 * <p>
 * 用    法：当没有指定Application时，系统会帮助我们创建；当自定义时,需要在AndroidManifest.xml文件中的application标签中进行注册，
 * 注册方式：application标签增加name属性
 * <p>
 * 获取Application对象发的方法：
 * Activity通过getApplication
 * Context通过getApplicationContext
 */
public class BaseApplication extends Application {
    private static final String TAG = "watom";

    @Override
    public void onCreate() {
        super.onCreate();
        initGlobalDeploy();
    }

    private void initGlobalDeploy() {
        initWeather();
        initConfigLocal();
    }

    private void initConfigLocal() {

    }


    private void initWeather() {
        /**
         * @param key   用户的ID:xecLJweVwN
         * @param location  地址详解，若不传或为空则调用Android源生定位
         */
        WeatherConfig.init("xecLJweVwN","西安");
    }
}
