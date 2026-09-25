package network.webtech.labmvc.controllers;

import org.springframework.test.context.TestPropertySource;
import network.webtech.labmvc.models.Produto;
import network.webtech.labmvc.services.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
@TestPropertySource(properties = "MONGODB_URI=mongodb://localhost:27017/test")
class ProdutoControllerTests {
    @Autowired MockMvc mvc;
    @MockBean ProdutoService service;

    @Test
    void criaProdutoComLocation() throws Exception {
        Produto salvo = new Produto();
        salvo.setId("abc");
        salvo.setNome("Notebook");
        salvo.setPreco(3500);
        when(service.criar(any(Produto.class))).thenReturn(salvo);

        mvc.perform(post("/api/produtos").contentType("application/json")
                .content("{\"nome\":\"Notebook\",\"preco\":3500}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/produtos/abc"))
            .andExpect(jsonPath("$.id").value("abc"));
    }

    @Test
    void rejeitaNomeVazioEPrecoNegativo() throws Exception {
        mvc.perform(post("/api/produtos").contentType("application/json")
                .content("{\"nome\":\"\",\"preco\":-1}"))
            .andExpect(status().isBadRequest());
    }
}
