package com.chan.gradle.plugin

import com.chan.gradle.plugin.util.JavaProducer
import com.chan.gradle.plugin.util.KotlinProducer
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction

abstract class FileGenerateTask : DefaultTask() {

    @get:org.gradle.api.tasks.Input
    abstract val firstString: Property<String>

    @get:org.gradle.api.tasks.Input
    abstract val language: Property<String>

    @TaskAction
    fun initTask() {
        println("Task Initiated ${firstString.get()}, ${language.get()}")
        if(language.get() == "kotlin") {
            KotlinProducer(project).generateFile()
        } else {
            JavaProducer(project).generateFile()
        }
    }

}