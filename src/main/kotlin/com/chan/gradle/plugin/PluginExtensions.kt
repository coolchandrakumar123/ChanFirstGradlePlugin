package com.chan.gradle.plugin

import org.gradle.api.provider.Property

abstract class PluginExtensions {
    abstract val firstString: Property<String>
    abstract val secondString: Property<String>
}