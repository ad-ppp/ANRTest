package com.jacky.framework.anrtest.dumper;

import android.os.Looper
import com.facebook.stetho.dumpapp.DumperContext
import com.facebook.stetho.dumpapp.DumperPlugin
import com.jacky.framework.anrtest.util.Utils

/**
 * Created by Jacky on 2021/1/14
 */
class UITracePlugin : DumperPlugin {
    override fun getName() = "ui-trace"

    override fun dump(dumpContext: DumperContext) {
        val stackTrace = Looper.getMainLooper().thread.stackTrace
        dumpContext.stdout.println(Utils.getStack(stackTrace))
    }
}
