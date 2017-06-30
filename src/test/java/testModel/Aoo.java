package testModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.xinda.entity.Role;
import com.xinda.service.RoleService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
@WebAppConfiguration
//这里可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否  
@TransactionConfiguration(defaultRollback = true)  
@Transactional
public class Aoo
{
	@Autowired
	private RoleService rs;
	@Autowired
	private WebApplicationContext wac;
	@Before  
    public void setup() {   
		//mockMvc = MockMvcBuilders.standaloneSetup(TestController).build();  
    }
	@Test
	public void func1(){
		Role role=new Role();
		role.setRoleName("普通用户");
		role.setRoleStatus(0);
		System.out.println(rs.saveRole(role));
	}
}
