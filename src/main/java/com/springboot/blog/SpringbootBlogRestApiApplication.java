package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SpringbootBlogRestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

	private RoleRepository roleRepository;
	
	public SpringbootBlogRestApiApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);
		if(adminRole == null) {
			adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
		}

		Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
		if(userRole == null) {
			userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}
	}
}
