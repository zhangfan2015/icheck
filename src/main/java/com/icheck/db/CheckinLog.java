package com.icheck.db;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;


@Entity(value = "checklog")
public class CheckinLog {

    public static final String FIELD_name = "name";

    @Property(FIELD_name)
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
