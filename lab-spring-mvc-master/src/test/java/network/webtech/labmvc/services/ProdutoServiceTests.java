package network.webtech.labmvc.services;

import java.util.Optional;
import network.webtech.labmvc.models.Produto;
import network.webtech.labmvc.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTests {
    private final ProdutoRepository repository = mock(ProdutoRepository.class);
    private final ProdutoService service = new ProdutoService(repository);

    @Test
    void buscarProdutoInexistenteRetorna404() {
        when(repository.findById("ausente")).thenReturn(Optional.empty());
        ResponseStatusException erro = assertThrows(ResponseStatusException.class,
            () -> service.buscar("ausente"));
        assertEquals(404, erro.getStatusCode().value());
    }

    @Test
    void atualizarConservaIdEGravaNovosDados() {
        Produto existente = new Produto();
        existente.setId("abc");
        Produto alteracao = new Produto();
        alteracao.setId("outro-id");
        alteracao.setNome("Mouse");
        alteracao.setPreco(90);
        when(repository.findById("abc")).thenReturn(Optional.of(existente));
        when(repository.save(existente)).thenReturn(existente);

        Produto resultado = service.atualizar("abc", alteracao);
        assertEquals("abc", resultado.getId());
        assertEquals("Mouse", resultado.getNome());
        assertEquals(90, resultado.getPreco());
        verify(repository).save(existente);
    }
}
