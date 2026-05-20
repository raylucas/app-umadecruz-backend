# Postman Collection - Umadecruz API Completa

## Como usar esta collection

1. **Importe a collection no Postman:**
   - Abra o Postman
   - Clique em "Import"
   - Selecione o arquivo `Umadecruz-API.postman_collection.txt`
   - Escolha "Raw text" como formato
   - Confirme a importação

2. **Configure as variáveis de ambiente:**
   - No Postman, vá para "Environments"
   - Clique em "Add" para criar um novo ambiente
   - Configure as seguintes variáveis:
     ```
     baseUrl: http://localhost:8080
     token: [seu token JWT aqui]
     ```

3. **Obtenha o token de autenticação:**
   - Use o endpoint `POST /auth/login` com:
     ```json
     {
       "email": "seu@email.com",
       "senha": "sua_senha"
     }
     ```
   - Copie o token retornado e cole na variável `token` do ambiente

## Estrutura Completa da API

### 🔐 Autenticação
- **Login** - `POST /auth/login`

### 👥 Usuários
- **Criar Usuário** - `POST /usuario`
- **Listar Todos** - `GET /usuario/todos`
- **Buscar por ID** - `GET /usuario/id/{id}`
- **Atualizar** - `PUT /usuario`
- **Alterar Senha** - `PUT /usuario/alterarSenha`

### 📅 Eventos
- **Criar Evento** - `POST /evento`
- **Listar Todos** - `GET /evento/eventos`
- **Listar da Semana** - `GET /evento/eventos/semana`
- **Atualizar** - `PUT /evento`
- **Excluir** - `DELETE /evento/id/{id}`

### 📋 Presenças em Eventos
- **Registrar Presença** - `POST /usuario-evento/presenca`
- **Consultar Presença** - `GET /usuario-evento/presenca/{idEvento}/{idUsuario}`

### 📊 Relatórios de Presenças
- **Relatório por Evento** - `GET /usuario-evento/relatorio/presenca-evento/{idEvento}`
- **Todos Eventos com Presenças** - `GET /usuario-evento/eventos-com-presencas`
- **Usuários com Maior Presença** - `GET /usuario-evento/relatorio/usuarios-maior-presenca`
- **Ranking de Presenças** - `GET /usuario-evento/relatorio/ranking-presencas/{limite}`
- **Estatísticas Gerais** - `GET /usuario-evento/relatorio/estatisticas-presenca`

### 📢 Avisos
- **Criar Aviso** - `POST /aviso`
- **Listar de Hoje** - `GET /aviso/avisos/hoje`
- **Atualizar** - `PUT /aviso`
- **Excluir** - `DELETE /aviso/id/{id}`

### 📱 Tokens (Notificações)
- **Registrar Token** - `POST /token`

### 💰 Movimentações Financeiras
- **Criar Movimentação** - `POST /financeiro/movimentacoes`
- **Buscar por ID** - `GET /financeiro/movimentacoes/{id}`
- **Listar com Filtros** - `GET /financeiro/movimentacoes`
- **Atualizar** - `PUT /financeiro/movimentacoes/{id}`
- **Excluir/Cancelar** - `DELETE /financeiro/movimentacoes/{id}`

### 📊 Relatórios Financeiros
- **Saldo Atual** - `GET /financeiro/relatorios/saldo-atual`
- **Resumo do Período** - `GET /financeiro/relatorios/resumo`
- **Por Categoria** - `GET /financeiro/relatorios/por-categoria`
- **Fluxo Diário** - `GET /financeiro/relatorios/fluxo-diario`
- **Por Tipo de Conta** - `GET /financeiro/relatorios/por-conta`
- **Maiores Movimentações** - `GET /financeiro/relatorios/maiores-movimentacoes`

## Exemplos de Uso

### 🔑 Autenticação
```json
POST /auth/login
{
  "email": "admin@umadecruz.com",
  "senha": "123456"
}
```

### 👤 Criar Usuário
```json
POST /usuario
{
  "nome": "João Silva",
  "email": "joao.silva@umadecruz.com",
  "tipo": "USER",
  "foto": "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBD..."
}
```

### 📅 Criar Evento
```json
POST /evento
{
  "titulo": "Encontro de Jovens",
  "descricao": "Encontro mensal do grupo de jovens para estudo e comunhão",
  "data": "2024-05-20",
  "inicio": "19:00:00",
  "fim": "21:00:00",
  "idUsuario": 1
}
```

### 📢 Criar Aviso
```json
POST /aviso
{
  "titulo": "Aviso Importante",
  "corpo": "Não se esqueçam do encontro de jovens deste sábado às 19h. Tragam seus amigos!",
  "idUsuario": 1
}
```

### 💰 Criar Movimentação Financeira
```json
POST /financeiro/movimentacoes
{
  "tipoMovimentacao": "ENTRADA",
  "tipoConta": "CONTA_DIGITAL",
  "categoria": "VENDA",
  "descricao": "Venda de camisetas do evento jovem",
  "valor": 350.00,
  "dataMovimentacao": "2024-05-11",
  "status": "CONFIRMADA",
  "observacao": "Cliente: João Silva - Pagamento via PIX"
}
```

### 📱 Registrar Token de Notificação
```json
POST /token
{
  "token": "fcm_token_aqui",
  "idUsuario": 1
}
```

### 📋 Registrar Presença em Evento
```json
POST /usuario-evento/presenca
{
  "idEvento": 1,
  "idUsuario": 1
}
```

### 📊 Exemplos de Relatórios

#### Relatório de Presença por Evento
```json
GET /usuario-evento/relatorio/presenca-evento/1
```

#### Ranking dos 10 Usuários com Maior Presença
```json
GET /usuario-evento/relatorio/ranking-presencas/10
```

#### Estatísticas Gerais de Presença
```json
GET /usuario-evento/relatorio/estatisticas-presenca
```

#### Todos os Eventos com Presenças
```json
GET /usuario-evento/eventos-com-presencas
```

## Valores Possíveis

### TipoUsuario
- `ADMIN` - Administrador do sistema
- `USER` - Usuário comum

### TipoMovimentacao
- `ENTRADA` - Dinheiro entrando
- `SAIDA` - Dinheiro saindo

### TipoConta  
- `CONTA_DIGITAL` - Conta bancária/carteira digital
- `DINHEIRO_FISICO` - Dinheiro em espécie

### Categoria Financeira
- `VENDA` - Vendas de produtos/eventos
- `COMPRA` - Compras diversas
- `DOACAO` - Doações recebidas
- `SALARIO` - Pagamentos de salários
- `ALUGUEL` - Aluguel de espaços
- `SERVICOS` - Contratação de serviços
- `IMPOSTOS` - Pagamentos de impostos
- `MATERIAL_ESCRITORIO` - Material de escritório
- `EVENTOS` - Despesas com eventos
- `OUTROS` - Outras categorias

### Status Movimentação
- `CONFIRMADA` - Lançamento confirmado (afeta saldo)
- `PENDENTE` - Aguardando confirmação (nÃO afeta saldo)
- `CANCELADA` - Lançamento cancelado (NÃO afeta saldo)

## Filtros Disponíveis

### Movimentações Financeiras
- `tipo` - ENTRADA ou SAIDA
- `tipoConta` - CONTA_DIGITAL ou DINHEIRO_FISICO
- `status` - CONFIRMADA, PENDENTE ou CANCELADA
- `dataInicio` - Data inicial do período (YYYY-MM-DD)
- `dataFim` - Data final do período (YYYY-MM-DD)

### Relatórios Financeiros
- `dataInicio` - Data inicial do período (YYYY-MM-DD)
- `dataFim` - Data final do período (YYYY-MM-DD)
- `limite` - Quantidade de resultados (padrão: 10)

## Observações Importantes

### 🔐 Segurança
- ⚠️ **Token Obrigatório**: A maioria dos endpoints requer autenticação via Bearer Token
- 🔄 **Token Expira**: Tokens JWT têm validade limitada, obtenha um novo quando expirar

### 💰 Regras Financeiras
- ⚠️ **Saldo Insuficiente**: O sistema valida se há saldo disponível antes de permitir saídas
- 📅 **Data Futura**: Não é permitido criar movimentações com data futura
- 💰 **Valor Zero**: Valores devem ser maiores que zero
- 🔄 **Cancelamento**: DELETE não remove o registro, apenas muda status para CANCELADA
- 📊 **Relatórios**: Apenas movimentações CONFIRMADAS entram nos cálculos

### 📅 Eventos
- 📆 **Data Obrigatória**: Eventos devem ter data válida
- ⏰ **Horários**: Início deve ser anterior ao fim
- 👥 **Presença**: Sistema controla presença de usuários em eventos

### 📢 Avisos
- 📅 **Período**: Avisos podem ter data de início e fim de exibição
- 📋 **Conteúdo**: Título e corpo são obrigatórios

## Dicas de Teste

### 🔧 Primeiros Passos
1. **Faça login** para obter o token
2. **Configure o ambiente** com baseUrl e token
3. **Teste endpoints públicos** primeiro (login)
4. **Crie um usuário** para testes
5. **Crie eventos e avisos** básicos

### 💰 Testes Financeiros
1. **Teste primeiro com status PENDENTE** para não afetar o saldo
2. **Use valores pequenos** nos testes iniciais
3. **Verifique o saldo** antes de criar saídas grandes
4. **Teste os filtros** combinados (tipo + data, status + categoria, etc.)

### 📅 Testes de Eventos
1. **Crie eventos futuros** para testar listagem da semana
2. **Marque presença** para testar funcionalidade
3. **Teste filtros** de data e período

### 📢 Testes de Avisos
1. **Crie avisos para hoje** para testar listagem
2. **Teste atualização** de conteúdo
3. **Verifique período** de exibição

## Exemplos Práticos do Contexto Umadecruz

### 🎪 Evento Jovem Típico
```json
{
  "titulo": "Noite de Louvor e Adoração",
  "descricao": "Encontro especial do grupo de jovens com música, palavra e comunhão",
  "data": "2024-05-25",
  "inicio": "20:00:00",
  "fim": "22:30:00",
  "idUsuario": 1
}
```

### 💰 Entrada Financeira Realista
```json
{
  "tipoMovimentacao": "ENTRADA",
  "tipoConta": "DINHEIRO_FISICO",
  "categoria": "DOACAO",
  "descricao": "Oferta do encontro de jovens",
  "valor": 200.00,
  "dataMovimentacao": "2024-05-11",
  "status": "CONFIRMADA",
  "observacao": "Oferta espontânea - 20 participantes"
}
```

### 🍕 Saída Financeira Realista
```json
{
  "tipoMovimentacao": "SAIDA",
  "tipoConta": "DINHEIRO_FISICO",
  "categoria": "EVENTOS",
  "descricao": "Lanche para noite de louvor",
  "valor": 180.00,
  "dataMovimentacao": "2024-05-11",
  "status": "CONFIRMADA",
  "observacao": "Refrigerantes, salgados, doces - 30 pessoas"
}
```

### 📢 Aviso para Grupo
```json
{
  "titulo": "🎤 Noite de Louvor - Sábado 25/05",
  "corpo": "Venham celebrar conosco! Teremos música, palavra e muita comunhão. Tragam seus amigos! 🎵✨",
  "idUsuario": 1
}
```

---

**🚀 Pronto!** Agora você tem acesso completo a todos os endpoints da API Umadecruz com exemplos práticos do contexto do grupo de jovens. Use esta collection para testar todas as funcionalidades do sistema!
