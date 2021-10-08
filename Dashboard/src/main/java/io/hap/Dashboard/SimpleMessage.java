package io.hap.Dashboard;

import java.io.Serializable;

public class SimpleMessage implements Serializable {
    private static final long serialVersionUID = -295422703255886286L;
    private String name;
    private String description;

    public SimpleMessage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
