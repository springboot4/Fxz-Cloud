package com.fxz.system.configure;

import com.fxz.common.dataPermission.dept.rule.DeptDataPermissionRuleCustomizer;
import com.fxz.system.entity.Dept;
import com.fxz.system.entity.OperLog;
import com.fxz.system.entity.SystemUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * system 模块的数据权限 Configuration
 *
 * @author fxz
 */
@Configuration(proxyBeanMethods = false)
public class DataPermissionConfiguration {

	@Bean
	public DeptDataPermissionRuleCustomizer sysDeptDataPermissionRuleCustomizer() {
		return rule -> {
			// dept
			rule.addDeptColumn(Dept.class);
			rule.addDeptColumn(OperLog.class);
			// user
			rule.addUserColumn(SystemUser.class, "user_id");
		};
	}

}
