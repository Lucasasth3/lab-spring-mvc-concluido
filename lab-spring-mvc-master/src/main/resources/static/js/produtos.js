const form = document.querySelector('#produto-form');
const lista = document.querySelector('#produtos');
const status = document.querySelector('#status');
const idInput = document.querySelector('#produto-id');
const nomeInput = document.querySelector('#nome');
const precoInput = document.querySelector('#preco');
const cancelar = document.querySelector('#cancelar');
const dinheiro = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' });

async function requisitar(url, options) {
  const response = await fetch(url, options);
  if (!response.ok) throw new Error(`Falha na requisição (HTTP ${response.status}).`);
  return response.status === 204 ? null : response.json();
}

function limparFormulario() {
  form.reset();
  idInput.value = '';
  cancelar.hidden = true;
  document.querySelector('#form-title').textContent = 'Novo produto';
}

async function carregar() {
  try {
    const produtos = await requisitar('/api/produtos');
    lista.replaceChildren();
    status.textContent = produtos.length ? '' : 'Nenhum produto cadastrado.';
    for (const produto of produtos) {
      const item = document.createElement('li');
      const descricao = document.createElement('span');
      descricao.textContent = `${produto.nome} — ${dinheiro.format(produto.preco)}`;
      const editar = document.createElement('button');
      editar.textContent = 'Editar';
      editar.type = 'button';
      editar.addEventListener('click', () => {
        idInput.value = produto.id;
        nomeInput.value = produto.nome;
        precoInput.value = produto.preco;
        cancelar.hidden = false;
        document.querySelector('#form-title').textContent = 'Editar produto';
        nomeInput.focus();
      });
      const excluir = document.createElement('button');
      excluir.textContent = 'Excluir';
      excluir.type = 'button';
      excluir.className = 'danger';
      excluir.addEventListener('click', async () => {
        if (!confirm(`Excluir ${produto.nome}?`)) return;
        try {
          await requisitar(`/api/produtos/${encodeURIComponent(produto.id)}`, { method: 'DELETE' });
          if (idInput.value === produto.id) limparFormulario();
          await carregar();
        } catch (error) { status.textContent = error.message; }
      });
      item.append(descricao, editar, excluir);
      lista.append(item);
    }
  } catch (error) { status.textContent = error.message; }
}

form.addEventListener('submit', async (event) => {
  event.preventDefault();
  const id = idInput.value;
  const produto = { nome: nomeInput.value.trim(), preco: Number(precoInput.value) };
  if (!produto.nome || !Number.isFinite(produto.preco) || produto.preco < 0) return;
  try {
    await requisitar(id ? `/api/produtos/${encodeURIComponent(id)}` : '/api/produtos', {
      method: id ? 'PUT' : 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(produto)
    });
    limparFormulario();
    await carregar();
  } catch (error) { status.textContent = error.message; }
});

cancelar.addEventListener('click', limparFormulario);
carregar();
