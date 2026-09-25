package network.webtech.labmvc.repositories;

import network.webtech.labmvc.models.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> { }
