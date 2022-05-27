package com.fxz.system.service.impl;

import com.fxz.system.entity.SystemUser;
import com.fxz.system.mapper.DemoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-30 10:30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DemoService {

	private final DemoMapper demoMapper;

	public void queryDatabase() {
		List<Map<String, String>> slave = demoMapper.selectTest("test_slave", "slave");
		List<SystemUser> master = demoMapper.selectUser("sys_user", "master");
		log.info("slave:{}", slave);
		log.info("master:{}", master);
	}

}
