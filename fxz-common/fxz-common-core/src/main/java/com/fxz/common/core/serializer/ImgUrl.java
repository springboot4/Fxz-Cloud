package com.fxz.common.core.serializer;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author fxz
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = ImgUrlSerialize.class)
public @interface ImgUrl {

}
