package com.jacky.framework.anrtest.dumper

import android.text.TextUtils
import com.facebook.stetho.dumpapp.*
import java.io.*

/**
 * Created by Jacky on 2021/1/14
 */

class HelloWorldDumperPlugin : DumperPlugin {
    override fun getName() = "hello"

    @Throws(DumpException::class)
    override fun dump(dumpContext: DumperContext) {
        val writer: PrintStream = dumpContext.stdout
        val args: Iterator<String> = dumpContext.argsAsList.iterator()
        val helloToWhom = ArgsHelper.nextOptionalArg(args, null)

        if (helloToWhom != null) {
            doHello(dumpContext.stdin, writer, helloToWhom)
        } else {
            doUsage(writer)
        }
    }

    @Throws(DumpException::class)
    private fun doHello(`in`: InputStream, writer: PrintStream, name: String) {
        var name = name
        if (TextUtils.isEmpty(name)) {
            // This will print an error to the dumpapp user and cause a non-zero exit of the
            // script.
            throw DumpUsageException("Name is empty")
        } else if ("-" == name) {
            name = try {
                BufferedReader(InputStreamReader(`in`)).readLine()
            } catch (e: IOException) {
                throw DumpException(e.toString())
            }
        }
        writer.println("Hello $name!")
    }

    private fun doUsage(writer: PrintStream) {
        writer.println("Usage: dumpapp $name <name>")
        writer.println()
        writer.println("If <name> is '-', the name will be read from stdin.")
    }
}