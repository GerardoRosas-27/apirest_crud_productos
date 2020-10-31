package com.bruster;

import com.crud.security.entity.Rol;
import com.crud.security.enums.RolNombre;
import com.crud.security.service.RolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiRestProductoApplicationTests {

	@Autowired
	RolService rolService;

	@Test
	public void crearRolesUserTest() {
		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
		Rol rolUser = new Rol(RolNombre.ROLE_USER);
		rolService.save(rolAdmin);
		rolService.save(rolUser);
	}

}
