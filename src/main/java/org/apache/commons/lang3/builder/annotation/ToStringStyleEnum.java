package org.apache.commons.lang3.builder.annotation;

import org.apache.commons.lang3.builder.ToStringStyle;

public enum ToStringStyleEnum {
    NULL_STYLE(null),
    DEFAULT_STYLE(ToStringStyle.DEFAULT_STYLE),
    JSON_STYLE(ToStringStyle.JSON_STYLE),
    MULTI_LINE_STYLE(ToStringStyle.MULTI_LINE_STYLE),
    NO_CLASS_NAME_STYLE(ToStringStyle.NO_CLASS_NAME_STYLE),
    NO_FIELD_NAMES_STYLE(ToStringStyle.NO_CLASS_NAME_STYLE),
    SHORT_PREFIX_STYLE(ToStringStyle.SHORT_PREFIX_STYLE),
    SIMPLE_STYLE(ToStringStyle.SIMPLE_STYLE);
    
    
    private ToStringStyle style;
    
    ToStringStyleEnum(ToStringStyle style){
        this.style = style;
    }
    
    
    public ToStringStyle getStyle(){
        return this.style;
    }
}
