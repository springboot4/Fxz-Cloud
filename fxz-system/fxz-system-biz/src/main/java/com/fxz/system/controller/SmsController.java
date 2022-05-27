package com.fxz.system.controller;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.sms.service.AliyunSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/19 21:04
 */
@Slf4j
@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

	private final AliyunSmsService aliyunSmsService;

	@Ojbk
	@GetMapping("/sendSms")
	public <T> Result<T> sendSmsCode(String phoneNumber) {
		return Result.judge(aliyunSmsService.sendSmsCode(phoneNumber));
	}

}
