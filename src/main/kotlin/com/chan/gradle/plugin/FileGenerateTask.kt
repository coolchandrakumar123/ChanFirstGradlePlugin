package com.chan.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction

abstract class FileGenerateTask: DefaultTask() {

    @get:org.gradle.api.tasks.Input
    abstract val firstString: Property<String>

    @get:org.gradle.api.tasks.Input
    abstract val secondString: Property<String>

    @TaskAction
    fun initTask() {
        println("Task Initiated")
    }

}