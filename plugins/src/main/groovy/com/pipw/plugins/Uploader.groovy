package com.pipw.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class Uploader implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println "hello.plugins"
    }
}