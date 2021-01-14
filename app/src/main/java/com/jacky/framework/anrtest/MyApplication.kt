package com.jacky.framework.anrtest

import android.app.Application
import com.facebook.stetho.DumperPluginsProvider
import com.facebook.stetho.Stetho
import com.facebook.stetho.dumpapp.DumperPlugin
import com.jacky.framework.anrtest.dumper.HelloWorldDumperPlugin
import com.jacky.framework.anrtest.dumper.UITracePlugin

/**
 * Created by Jacky on 2021/1/14
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val application = this

        val pluginProvider = DumperPluginsProvider {
            Stetho.DefaultDumperPluginsBuilder(application)
                .provide(HelloWorldDumperPlugin())
                .provide(UITracePlugin())
                .finish()
        }

        Stetho.initialize(Stetho.newInitializerBuilder(application)
            .enableDumpapp(pluginProvider)
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(application))
            .build())
    }
}