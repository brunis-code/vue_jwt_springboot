package com.brunis.vue_jwt_springboot.model;

public final class Views {
    public interface Id {}

    public interface IdName extends Id {}

    public interface FullComment extends IdName {}

    public interface FullMessage extends IdName {}

    public interface FullProfile extends IdName {}
}
