package com.masm.quickpoll.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Option {

    @Id @GeneratedValue
    @Column(name="OPTION_ID")
    private Long id;
    @Column(name = "OPTION_VALUE")
    private String value;

    public Option(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(id, option.id) && Objects.equals(value, option.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
