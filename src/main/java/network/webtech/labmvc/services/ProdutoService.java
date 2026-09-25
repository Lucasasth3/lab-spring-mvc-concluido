package network.webtech.labmvc.services;

import java.util.List;
import network.webtech.labmvc.models.Produto;
import network.webtech.labmvc.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) { this.repository = repository; }

    public List<Produto> listar() { return repository.findAll(); }

    public Produto buscar(String id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    public Produto criar(Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    public Produto atualizar(String id, Produto dados) {
        Produto produto = buscar(id);
        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        return repository.save(produto);
    }

    public void excluir(String id) {
        buscar(id);
        repository.deleteById(id);
    }
}
