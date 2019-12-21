package com.github.funthomas424242.maven.plugins.jarinstall;

import org.apache.maven.artifact.handler.ArtifactHandler;
import org.codehaus.plexus.component.annotations.Component;

@Component(role = ArtifactHandler.class)
public class LegacyJarHandler
        implements ArtifactHandler {
    private String extension;

    private String type;

    private String classifier;

    private String directory;

    private String packaging;

    private boolean includesDependencies;

    private String language;

    private boolean addedToClasspath;

    public LegacyJarHandler() {
    }

    public LegacyJarHandler(String type) {
        this.type = type;
    }

    public String getExtension() {
        if (extension == null) {
            extension = "jar";
        }
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public String getClassifier() {
        return classifier;
    }

    public String getDirectory() {
        if (directory == null) {
            directory = getPackaging() + "s";
        }
        return directory;
    }

    public String getPackaging() {
        if (packaging == null) {
            packaging = "jar";
        }
        return packaging;
    }

    public boolean isIncludesDependencies() {
        return includesDependencies;
    }

    public void setIncludesDependencies(boolean includesDependencies) {
        this.includesDependencies = includesDependencies;
    }

    public String getLanguage() {
        if (language == null) {
            language = "java";
        }

        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAddedToClasspath() {
        return addedToClasspath;
    }

    public void setAddedToClasspath(boolean addedToClasspath) {
        this.addedToClasspath = addedToClasspath;
    }

}