package com.chan.gradle.plugin;

import org.gradle.api.provider.Property;

abstract public class ChanFirstExtension {
    abstract public Property<String> getFirstString();
    abstract public Property<String> getSecondString();
}
