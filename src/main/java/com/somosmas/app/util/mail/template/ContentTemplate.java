package com.somosmas.app.util.mail.template;

import com.somosmas.app.util.mail.IContent;

public class ContentTemplate implements IContent {

    private final String type;
    private final String value;

    public ContentTemplate(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
