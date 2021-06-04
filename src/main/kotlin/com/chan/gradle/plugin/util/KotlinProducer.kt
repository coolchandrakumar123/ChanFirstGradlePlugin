package com.chan.gradle.plugin.util

import com.squareup.kotlinpoet.*
import org.gradle.api.Project
import java.io.File
import java.io.IOException

class KotlinProducer(private val project: Project) {

    fun generateFile() {
        val greeterClass = ClassName("com.chan.gradle.plugin.binder", "ChanFirstListBinder")
        val kotlinFile = FileSpec.builder("", "HelloWorld")
            .addType(
                TypeSpec.classBuilder("ChanFirstListBinder")
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameter("name", String::class)
                            .build()
                    )
                    .addProperty(
                        PropertySpec.builder("name", String::class)
                            .initializer("name")
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("greet")
                            .addStatement("println(%P)", "Hello, \$name")
                            .build()
                    )
                    .build()
            )
            .addFunction(
                FunSpec.builder("main")
                    .addParameter("args", String::class, KModifier.VARARG)
                    .addStatement("%T(args[0]).greet()", greeterClass)
                    .build()
            )
            .build()
        try {
            val resultFile = File(project.projectDir.toString() + "/src/main/kotlin/")
            println("Directory-Path:" + resultFile.absolutePath)
            kotlinFile.writeTo(resultFile)
            println("Success")
        } catch (e: IOException) {
            e.printStackTrace()
            println("Failed")
        }
    }
}