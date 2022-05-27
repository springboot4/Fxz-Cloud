package com.fxz.common.canal.common;

/**
 * @author fxz
 * @description 属性名 -> 列名命名转换策略, 默认认为所有属性都是标准驼峰命名
 */
public enum FieldNamingPolicy implements NamingPolicy {

	/**
	 * 返回属性的原始名称作为列名,如"customerName" -> "customerName"
	 */
	DEFAULT {
		@Override
		public String convert(String source) {
			return source;
		}
	},

	/**
	 * 属性的原始名称转为下划线大写作为列名,如"customerName" -> "CUSTOMER_NAME"
	 */
	UPPER_UNDERSCORE {
		@Override
		public String convert(String source) {
			StringBuilder content = new StringBuilder();
			int len = source.length();
			for (int i = 0; i < len; i++) {
				char c = source.charAt(i);
				if (Character.isUpperCase(c)) {
					content.append(UNDER_LINE);
				}
				content.append(Character.toUpperCase(c));
			}
			return content.toString();
		}
	},

	/**
	 * 属性的原始名称转为下划线小写作为列名,如"customerName" -> "customer_name"
	 */
	LOWER_UNDERSCORE {
		@Override
		public String convert(String source) {
			StringBuilder content = new StringBuilder();
			int len = source.length();
			for (int i = 0; i < len; i++) {
				char c = source.charAt(i);
				if (Character.isUpperCase(c)) {
					content.append(UNDER_LINE);
				}
				content.append(Character.toLowerCase(c));
			}
			return content.toString();
		}
	};

	private static final String UNDER_LINE = "_";

}
