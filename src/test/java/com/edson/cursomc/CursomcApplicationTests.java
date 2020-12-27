package com.edson.cursomc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.edson.cursomc.domain.Categoria;
import com.edson.cursomc.repositories.CategoriaRepository;
import com.edson.cursomc.request.CategoriaRequest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CursomcApplicationTests {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@LocalServerPort
	private Integer port;

	@Before
	public void setup() {

		stubCriarCategoria();

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = this.port;
		RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.build();
	}

	private void stubCriarCategoria() {
		Categoria categoria = new Categoria(99, "Cosméticos");
		categoriaRepository.save(categoria);
	}

	@Test
	public void salvarCategoriaTest() {

		CategoriaRequest categoriaRequest = new CategoriaRequest();

		categoriaRequest.setNome("Teste");

	       RestAssured.given()
           .body(categoriaRequest)
           .post("/categorias")
           .then()
           .assertThat()
           .statusCode(201)
           .contentType("application/json")
           .body("id", Matchers.any(Integer.class));
	}

}
