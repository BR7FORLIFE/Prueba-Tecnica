package com.todo.ToDo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ToDoApplicationTests {

	@Autowired(required = false)
	private JdbcTemplate jdbcTemplate;

	@Test
	public void jdbcTemplateProve() {
		assertThat(jdbcTemplate)
				.as("JdbcTemplate debe estar disponible en el contexto de Spring")
				.isNotNull();
	}
}
