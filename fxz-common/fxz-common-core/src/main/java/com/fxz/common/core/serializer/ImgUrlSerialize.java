package com.fxz.common.core.serializer;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fxz.common.core.constant.RegexpConstant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/29 20:44
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ImgUrlSerialize extends JsonSerializer<String> implements ContextualSerializer {

	@Value("${biz.oss.resources-url}")
	private String imgDomain;

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (StrUtil.isBlank(value)) {
			gen.writeString(StrUtil.EMPTY);
			return;
		}
		else if (StrUtil.isBlank(imgDomain)) {
			gen.writeString(value);
			return;
		}

		StringBuilder sb = new StringBuilder();

		Pattern pattern = Pattern.compile(RegexpConstant.HTTP_PROTOCOL_REGEXP);
		boolean res = pattern.matcher(value).find();

		// 图片为http协议开头，直接返回
		if (res) {
			sb.append(value);
		}
		else {
			sb.append(imgDomain).append(value);
		}

		gen.writeString(sb.toString());
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty)
			throws JsonMappingException {

		if (Objects.nonNull(beanProperty)) {
			if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
				ImgUrl imgUrl = beanProperty.getAnnotation(ImgUrl.class);
				if (Objects.isNull(imgUrl)) {
					imgUrl = beanProperty.getContextAnnotation(ImgUrl.class);
				}
				if (Objects.nonNull(imgUrl)) {
					return new ImgUrlSerialize();
				}
			}
			return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
		}
		return serializerProvider.findNullValueSerializer(null);
	}

}
