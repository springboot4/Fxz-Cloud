package com.fxz.common.sms.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.sms.config.AliyunSmsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 阿里云短信业务类
 *
 * @author fxz
 */
@AutoConfiguration
@RequiredArgsConstructor
public class AliyunSmsService {

	private final AliyunSmsProperties aliyunSmsProperties;

	private final StringRedisTemplate stringRedisTemplate;

	/**
	 * 发送短信
	 * @param phoneNumber 手机号
	 */
	public Boolean sendSmsCode(String phoneNumber) {
		// 随机生成6位的验证码
		String code = RandomUtil.randomNumbers(6);
		// 缓存生成的验证码
		stringRedisTemplate.opsForValue().set(SecurityConstants.SMS_CODE_PREFIX + phoneNumber, code, 600,
				TimeUnit.SECONDS);

		DefaultProfile profile = DefaultProfile.getProfile(aliyunSmsProperties.getRegionId(),
				aliyunSmsProperties.getAccessKeyId(), aliyunSmsProperties.getAccessKeySecret());

		IAcsClient client = new DefaultAcsClient(profile);

		// 创建通用的请求对象
		CommonRequest request = new CommonRequest();
		// 指定请求方式
		request.setSysMethod(MethodType.POST);
		// 短信api的请求地址 固定
		request.setSysDomain(aliyunSmsProperties.getDomain());
		// 签名算法版本 固定
		request.setSysVersion("2017-05-25");
		// 请求 API 的名称
		request.setSysAction("SendSms");
		// 指定地域名称
		request.putQueryParameter("RegionId", aliyunSmsProperties.getRegionId());
		// 要给哪个手机号发送短信 指定手机号
		request.putQueryParameter("PhoneNumbers", phoneNumber);
		// 您的申请签名
		request.putQueryParameter("SignName", aliyunSmsProperties.getSignName());
		// 您申请的模板 code
		request.putQueryParameter("TemplateCode", aliyunSmsProperties.getTemplateCode());

		Map<String, Object> params = new HashMap<>();
		params.put("code", code);

		request.putQueryParameter("TemplateParam", JSONUtil.toJsonStr(params));

		try {
			CommonResponse response = client.getCommonResponse(request);
			return response.getHttpResponse().isSuccess();
		}
		catch (ClientException e) {
			e.printStackTrace();
		}
		return Boolean.TRUE;
	}

}
