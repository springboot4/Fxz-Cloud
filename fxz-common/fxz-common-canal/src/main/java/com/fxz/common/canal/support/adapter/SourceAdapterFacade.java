package com.fxz.common.canal.support.adapter;

/**
 * @author fxz
 */
public enum SourceAdapterFacade {

	/**
	 * 单例
	 */
	X;

	private static final SourceAdapter<String, String> I_S_A = RawStringSourceAdapter.of();

	@SuppressWarnings("unchecked")
	public <T> T adapt(Class<T> klass, String source) {
		if (klass.isAssignableFrom(String.class)) {
			return (T) I_S_A.adapt(source);
		}
		return FastJsonSourceAdapter.of(klass).adapt(source);
	}

}
