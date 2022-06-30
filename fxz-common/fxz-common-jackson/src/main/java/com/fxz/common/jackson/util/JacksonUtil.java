package com.fxz.common.jackson.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/6/30 17:44
 */
public class JacksonUtil {

	private static ObjectMapper objectMapper;

	public static void setObjectMapper(ObjectMapper objectMapper) {
		JacksonUtil.objectMapper = objectMapper;
	}

	@SneakyThrows
	public static String toJsonString(Object object) {
		return objectMapper.writeValueAsString(object);
	}

	@SneakyThrows
	public static <T> T parseObject(String text, Class<T> clazz) {
		if (StrUtil.isEmpty(text)) {
			return null;
		}
		return objectMapper.readValue(text, clazz);
	}

	@SneakyThrows
	public static <T> T parseObject(String text, TypeReference<T> typeReference) {
		return objectMapper.readValue(text, typeReference);
	}

	@SneakyThrows
	public static <T> List<T> parseArray(String text, Class<T> clazz) {
		if (StrUtil.isEmpty(text)) {
			return new ArrayList<>();
		}
		return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));

	}

	@SneakyThrows
	public static JsonNode parseTree(String text) {
		return objectMapper.readTree(text);
	}

}
