package com.netflix_clone.userservice.enums;

import lombok.Getter;

public abstract class Rabbit {
    @Getter
    public enum Exchange {
        PROFILE("netflix-clone-profile");

        private String value;
        Exchange(String value) { this.value = value; }
    }
    public enum RoutingKey {
        SAVE,CHANGE;
    }
}
