package com.academy.core.annotation;

import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp="*@*")
public @interface Email {

}
