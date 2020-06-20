package br.com.flaviogf.strikeproductcatalog.models;

import java.util.UUID;

public class Image {
    private final UUID id;
    private final String name;
    private final String path;
    private final String ext;

    public Image(UUID id, String name, String path, String ext) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.ext = ext;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getExt() {
        return ext;
    }
}
