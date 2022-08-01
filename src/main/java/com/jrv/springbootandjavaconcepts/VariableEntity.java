package com.jrv.springbootandjavaconcepts;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Class for the Variables.java
 *
 * @author Jayarathinavel
 * @since 01/Aug/2022
 */
@Entity
@Table(name = "variables")
public class VariableEntity {
    @Id
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
