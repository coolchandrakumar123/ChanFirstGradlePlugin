package com.chan.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

abstract public class ChanFirstPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        ChanFirstExtension extension = project.getExtensions().create("chanFirstPlugin", ChanFirstExtension.class);
        project.getTasks().register("chanFirstTask", ChanFirstTask.class, task -> {
            task.getFirstString().set(extension.getFirstString());
            task.getSecondString().set(extension.getSecondString());
        });
    }
}
