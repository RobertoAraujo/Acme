package com.lamppit.pagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamppit.pagamento.controller.PagamentoController;
import com.lamppit.pagamento.model.dto.PagamentoDTO;
import com.lamppit.pagamento.model.entity.PagamentoEntity;
import com.lamppit.pagamento.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;

@SpringBootTest
@WebMvcTest(controllers = PagamentoController.class)
class TestePagamentoApplication {

	@Autowired
	private PagamentoService service;

	@Autowired ObjectMapper objectMapper;

	public static final PagamentoDTO PAGAMENTO_DTO =
			PagamentoDTO.builder()
					.codigo("01")
		.numero("12345678")
		.nome("Raimunda C Silva")
		.expiracao("10/29")
		.valor(BigDecimal.valueOf(150.00))
		.formaDePagamentoId(1L).build();


	@Test
	public void testpagamentoInsert() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup((WebApplicationContext) this.service);
		MockMvc mockMvc = builder.build();

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/pagamentos/insert")
						.content(objectMapper.writeValueAsString(PAGAMENTO_DTO))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		resultActions.andReturn().getResponse().getContentAsString();

	}

}
