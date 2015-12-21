package com.weather.model

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class Weather {

    Integer day
    String description

    public Weather(Integer day, String description) {
        this.day = day;
        this.description = description;
    }

    public Integer getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(day).hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) { return true; }
        if (!(obj instanceof Weather)) { return false; }

        Weather other = (Weather) obj;
        return new EqualsBuilder().append(day, other.day).isEquals();
    }

    static constraints = {
        day(nullable:false)
        description(nullable:false, maxSize:20)
    }

}
