package com.chan.gradle.plugin

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "deskBinderPlugin"
const val TASK_NAME = "platformKotlinGen"

abstract class PlatformBinderGenPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<PluginExtensions>(
            EXTENSION_NAME,
            PluginExtensions::class.java
        )
        project.tasks.create<FileGenerateTask>(TASK_NAME,
            FileGenerateTask::class.java,
            Action { task: FileGenerateTask ->
                task.firstString.set(extension.firstString)
                task.secondString.set(extension.secondString)
            })
    }

}