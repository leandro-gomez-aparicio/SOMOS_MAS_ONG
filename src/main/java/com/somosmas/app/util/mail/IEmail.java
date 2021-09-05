package com.somosmas.app.util.mail;

public interface IEmail {

    String getTo();

    String getFrom();

    String getSubject();

    IContent getContent();

}
