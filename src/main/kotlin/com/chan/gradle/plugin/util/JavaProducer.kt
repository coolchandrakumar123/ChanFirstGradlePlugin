package com.chan.gradle.plugin.util

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import org.gradle.api.Project
import java.io.File
import java.io.IOException
import javax.lang.model.element.Modifier

class JavaProducer(private val project: Project) {

    fun generateFile() {
        // removing all previously generated files.
        //org.apache.commons.io.FileUtils.cleanDirectory(genJavaDir.get().asFile)
        println("Prepare Java File")
        val main = MethodSpec.methodBuilder("main")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(Void.TYPE)
            .addParameter(Array<String>::class.java, "args")
            .addStatement("\$T.out.println(\$S)", System::class.java, "Hello, JavaPoet!")
            .build()
        val classBuilder = TypeSpec.classBuilder("ChanFirstListBinder")
        classBuilder.addModifiers(Modifier.PUBLIC)
        classBuilder.addMethod(main)
        val javaFile = JavaFile.builder("com.chan.gradle.plugin.binder", classBuilder.build()).build()
        try {
            val resultFile = File(project.projectDir.toString() + "/src/main/java/")
            println("Directory-Path:" + resultFile.absolutePath)
            javaFile.writeTo(resultFile)
            println("Success")
        } catch (e: IOException) {
            e.printStackTrace()
            println("Failed")
        }
    }
}