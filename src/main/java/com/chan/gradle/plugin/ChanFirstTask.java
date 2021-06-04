package com.chan.gradle.plugin;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.TaskAction;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

abstract public class ChanFirstTask extends DefaultTask {
    abstract public Property<String> getFirstString();
    abstract public Property<String> getSecondString();

    //@OutputFile
    //File resultFile = new File("${project.buildDir}/diff-result.txt");

    @TaskAction
    public void diff() {
        System.out.println(getFirstString().get() + "-" + getSecondString().get());
        generateJavaFile();
    }

    private void generateJavaFile() {
        // removing all previously generated files.
        //org.apache.commons.io.FileUtils.cleanDirectory(genJavaDir.get().asFile)
        System.out.println("Prepare Java File");
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("ChanFirstListBinder");
        classBuilder.addModifiers(Modifier.PUBLIC);
        classBuilder.addMethod(main);

        JavaFile javaFile = JavaFile.builder("com.chan.gradleplugin", classBuilder.build()).build();

        try {
            File dir = getProject().getProjectDir();
            File resultFile = new File(getProject().getProjectDir() + "/src/main/java/");
            System.out.println("Directory-Path:" + resultFile.getAbsolutePath());
            javaFile.writeTo(resultFile);
            System.out.println("Success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed");
        }
    }
}
