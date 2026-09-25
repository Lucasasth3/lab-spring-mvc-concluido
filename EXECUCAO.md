# Execução

O projeto entrega uma página Thymeleaf em `/`, uma página de mensagem em
`/message/{msg}` e uma interface para cadastrar, editar e excluir produtos.
A interface chama a API REST e os produtos são armazenados na coleção
`produtos` do MongoDB Atlas.

## Configuração do Atlas

1. Crie um cluster MongoDB Atlas, um usuário de banco com permissão para ler e
   escrever e libere o IP de onde a aplicação rodará em **Network Access**.
2. Copie a URI em **Connect > Drivers** e substitua usuário e senha. Se a senha
   contiver caracteres especiais, codifique-os para uso em URL.
3. Configure as variáveis no terminal, sem colocá-las no repositório:

```bash
export MONGODB_URI='mongodb+srv://USUARIO:SENHA@SEU-CLUSTER.mongodb.net/lab-mvc?retryWrites=true&w=majority'
export MONGODB_DB='lab-mvc'
./mvnw spring-boot:run
```

No PowerShell, use `$env:MONGODB_URI = '...'` e `$env:MONGODB_DB = 'lab-mvc'`,
seguido de `./mvnw.cmd spring-boot:run`. A variável `MONGODB_DB` é opcional e
usa `lab-mvc` por padrão. O Maven Wrapper precisa de acesso à internet na
primeira execução. Abra `http://localhost:8080/` e use o formulário.

## API

| Método | Rota | Resultado |
| --- | --- | --- |
| GET | `/api/produtos` | lista produtos |
| GET | `/api/produtos/{id}` | consulta um produto; 404 se não existir |
| POST | `/api/produtos` | cria; 201 com Location |
| PUT | `/api/produtos/{id}` | atualiza; 404 se não existir |
| DELETE | `/api/produtos/{id}` | exclui; 204 ou 404 |

Exemplo de corpo para POST/PUT: `{"nome":"Notebook","preco":3500.00}`.
Nome vazio e preço negativo produzem 400. Para rodar os testes sem Atlas:
`./mvnw test`.

## Publicação opcional

O projeto agora gera um JAR executável com `./mvnw clean package`.
Em uma hospedagem Java, use `java -jar target/lab-mvc-0.0.1-SNAPSHOT.jar`
e configure `MONGODB_URI`, `MONGODB_DB` e, se necessário, `PORT` no ambiente.
Libere o endereço de saída do serviço no Atlas. Nenhum serviço é publicado
automaticamente.