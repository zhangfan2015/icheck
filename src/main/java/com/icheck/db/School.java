package com.icheck.db;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/1/12
 * Time: 0:47
 * To change this template use File | Settings | File Templates.
 */
@Entity(value = "school", noClassnameStored = true)
public class School {

    final public static  String FIELD_initials = "initials";
    final public static  String FIELD_name = "name";


    @Property(FIELD_initials)
    public String initials;

    @Property(FIELD_name)
    public String name;

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
