package com.fxz.common.core.annotation;

import com.fxz.common.core.enums.CityTypeEnum;
import com.fxz.common.core.validator.CityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验城市地名是否合法，不接受null
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 15:28
 */
@Documented
@Constraint(validatedBy = CityValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface CheckCityValid {

	/**
	 * 校验字段类型
	 */
	CityTypeEnum value() default CityTypeEnum.CITY;

	/**
	 * 提示消息
	 */
	String message() default "{city.valid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
