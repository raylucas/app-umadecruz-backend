# Documentação do Sistema - Umadecruz

## O que é este sistema?

Este sistema foi desenvolvido para gerenciar as atividades administrativas e financeiras do Grupo de Jovens da Igreja Assembléia de Deus de Cruzeiro, Umadecruz. Ele serve como uma plataforma centralizada para controle de membros, eventos, avisos e, principalmente, o controle financeiro da organização.

O sistema é utilizado por administradores do grupo e usuários autorizados para registrar, consultar e analisar todas as movimentações financeiras, garantindo transparência e organização na gestão dos recursos.

Com ele é possível cadastrar novos membros, organizar eventos, enviar avisos para a comunidade e, mais importante, controlar todas as entradas e saídas de dinheiro de forma detalhada e organizada.

O sistema não processa pagamentos automáticos, não se integra com sistemas bancários externos e não gera relatórios fiscais complexos. Ele foca no controle básico e transparente das finanças do grupo.

## Funcionalidades do sistema

### Cadastro de Usuários

**O que faz:**
Permite gerenciar todas as pessoas cadastradas do grupo, mantendo um registro completo dos membros com seus dados pessoais e informações de contato.

**O que você pode fazer aqui:**
- Cadastrar um novo usuário com nome, data de nascimento, telefone, endereço completo e e-mail
- Consultar os dados de um usuário pelo nome ou identificadores
- Atualizar informações de contato ou endereço de usuários existentes
- Definir o tipo de usuário (administrador ou usuário comum)
- Registrar data de batismo e congregação do usuário

**Informações que o sistema guarda:**
- Nome completo (obrigatório)
- Data de nascimento (obrigatório)
- Telefone de contato (opcional)
- Endereço completo (opcional)
- E-mail (obrigatório, único por usuário)
- Data de batismo (opcional)
- Congregação (opcional)
- Tipo de usuário (ADMIN ou USER)

**Regras importantes:**
- Não é possível cadastrar dois usuários com o mesmo e-mail
- A senha do usuário é criptografada para segurança
- Usuários podem ser desativados mas não são excluídos permanentemente

### Gestão de Eventos

**O que faz:**
Organiza e controla todos os eventos do grupo, desde reuniões pequenas até grandes celebrações, permitindo planejamento e acompanhamento da participação dos membros.

**O que você pode fazer aqui:**
- Criar novos eventos com título, descrição, data e horários
- Consultar todos os eventos cadastrados
- Verificar eventos da semana atual para planejamento
- Atualizar informações de eventos existentes
- Registrar presença dos usuários nos eventos
- Excluir eventos que não serão mais realizados

**Informações que o sistema guarda:**
- Título do evento (obrigatório)
- Descrição completa (obrigatório)
- Data do evento (obrigatório)
- Horário de início e término (obrigatório)
- Usuário responsável pela criação
- Data de criação do registro

**Regras importantes:**
- A data do evento não pode ser anterior à data de criação
- Eventos podem ser consultados por período específico
- A presença dos usuários é registrada individualmente

### Sistema de Avisos

**O que faz:**
Envia e organiza comunicados importantes para toda a comunidade, garantindo que todos estejam informados sobre mudanças, eventos especiais ou avisos da liderança.

**O que você pode fazer aqui:**
- Criar novos avisos para a comunidade
- Consultar todos os avisos cadastrados
- Verificar avisos do dia para exibição em tempo real
- Atualizar conteúdo de avisos existentes
- Remover avisos que já expiraram

**Informações que o sistema guarda:**
- Título do aviso (obrigatório)
- Mensagem completa (obrigatório)
- Data de criação (obrigatório)
- Data de início e fim da exibição

**Regras importantes:**
- Avisos têm período definido de exibição
- Somente administradores podem criar ou editar avisos
- Avisos expirados não aparecem nas consultas normais

### Autenticação e Segurança

**O que faz:**
Controla o acesso ao sistema, garantindo que somente usuários autorizados possam visualizar e manipular informações sensíveis, especialmente as financeiras.

**O que você pode fazer aqui:**
- Fazer login no sistema com e-mail e senha
- Receber um token de autenticação temporário
- Acessar funcionalidades conforme seu nível de permissão

**Informações que o sistema guarda:**
- E-mail do usuário (obrigatório)
- Senha criptografada (obrigatório)
- Token de acesso temporário
- Data de expiração do token

**Regras importantes:**
- Senhas são armazenadas de forma segura
- Tokens têm validade limitada por segurança
- Acesso não autorizado é bloqueado automaticamente

## Módulo Financeiro — Controle de Caixa

Este módulo é o coração do sistema para gestão financeira, permitindo controle completo de todas as movimentações de dinheiro do grupo.

### O que faz:

O sistema permite registrar todas as entradas e saídas de dinheiro do grupo, separando claramente o que está na conta bancária/digital do que está em dinheiro físico no caixa. Cada movimentação é detalhada com categoria, descrição e data, facilitando auditorias e relatórios.

### Tipos de movimentação:

**Entrada:** quando dinheiro entra no caixa ou na conta
- Exemplos: ofertas, vendas de eventos, doações especiais

**Saída:** quando dinheiro sai do caixa ou da conta  
- Exemplos: pagamento de contas, compra de materiais, despesas com eventos

### Onde o dinheiro pode estar:

**Conta digital:** valores em conta bancária ou carteira digital
- Ideal para valores maiores e pagamentos eletrônicos
- Mais seguro para grandes quantias

**Dinheiro físico:** valores em espécie no caixa do grupo
- Usado para pequenas despesas e troco
- Recontagem periódica recomendada

### Situações de uma movimentação:

**Confirmada:** lançamento efetivado, entra no saldo
- Movimentação já verificada e aprovada
- Afeta diretamente os cálculos de saldo

**Pendente:** lançamento aguardando confirmação, ainda não afeta o saldo
- Aguardando comprovação ou aprovação
- Não entra nos relatórios financeiros

**Cancelada:** lançamento desfeito, não afeta o saldo
- Erro corrigido ou despesa não efetivada
- Mantido para histórico e auditoria

### O que o sistema calcula para você:

**Saldo atual:** quanto o grupo tem agora, separado por conta digital e dinheiro físico
- Mostra o total disponível em cada tipo de conta
- Ajuda a decidir onde guardar novos valores

**Resumo do período:** total de entradas, total de saídas e resultado líquido
- Permite análise de qualquer intervalo de datas
- Ideal para relatórios mensais ou anuais

**Por categoria:** quanto entrou e saiu em cada tipo de receita/despesa
- Ajuda a identificar onde mais se gasta
- Facilita planejamento orçamentário

**Fluxo diário:** um dia a dia mostrando quanto entrou, saiu e qual era o saldo acumulado
- Perfeito para identificar padrões de movimentação
- Ajuda a prever necessidades futuras

**Por tipo de conta:** comparativo entre o que está na conta digital e o que está em espécie
- Auxilia na decisão de transferências entre contas
- Mantém equilíbrio entre segurança e praticidade

**Maiores movimentações:** os maiores lançamentos do período
- Útil para identificar despesas ou receitas relevantes
- Facilita auditorias e revisões

### Regras que o sistema aplica automaticamente:

- Não é possível lançar um valor negativo ou zerado
- Movimentações canceladas não afetam o saldo
- O sistema avisa se o saldo de um tipo de conta ficar negativo
- A data da movimentação não pode ser futura
- Descrição é obrigatória para todas as movimentações
- Somente movimentações confirmadas entram nos relatórios

## Perguntas frequentes

**O sistema salva o histórico de tudo que foi feito?**
Sim. Todas as movimentações são salvas com data de criação e atualização. Mesmo quando canceladas, permanecem no sistema para auditoria e histórico.

**Se eu errar um lançamento, consigo corrigir?**
Sim. Você pode atualizar qualquer movimentação ou cancelá-la se necessário. O sistema mantém o histórico das alterações para transparência.

**O sistema funciona em celular ou só no computador?**
O sistema é uma API que pode ser acessada de qualquer dispositivo com conexão à internet. Interfaces podem ser desenvolvidas para celular, web ou desktop.

**Quem pode ver os relatórios financeiros?**
Apenas usuários com permissão de administrador podem acessar e visualizar os relatórios financeiros completos. Usuários comuns podem ter acesso limitado conforme definido.

**O sistema se conecta com algum outro software?**
Atualmente o sistema é independente, mas foi desenvolvido com arquitetura que permite integrações futuras com sistemas bancários ou de contabilidade.

**Como faço backup dos dados?**
O sistema utiliza PostgreSQL e recomenda-se backup regular do banco de dados. Consulte o administrador do sistema para obter cópias de segurança.

**Posso exportar os relatórios?**
Sim. Todos os relatórios podem ser acessados via API e exportados para formatos como Excel ou PDF através de interfaces desenvolvidas.

## Glossário

**Movimentação:** qualquer registro de entrada ou saída de dinheiro no sistema

**Saldo:** o total disponível em uma conta ou no caixa em determinado momento

**Conta digital:** dinheiro guardado em conta bancária ou carteira digital

**Dinheiro físico:** dinheiro em espécie, guardado no caixa do grupo

**Período:** intervalo de datas usado para filtrar ou calcular relatórios

**Status:** situação atual de um lançamento (confirmado, pendente ou cancelado)

**Categoria:** classificação da movimentação para agrupamento em relatórios

**Fluxo de caixa:** movimento diário de entradas e saídas ao longo do tempo

**Auditoria:** processo de verificação das movimentações financeiras

## Contato e suporte

Para dúvidas ou problemas com o sistema, entre em contato com:

- Responsável técnico: [preencher]
- E-mail de suporte: [preencher]
- Canal interno: [preencher — ex: Slack, Teams]
- Horário de atendimento: [preencher]

---

*Este documento foi atualizado em maio de 2026 e reflete as funcionalidades atuais do sistema. Para sugestões ou correções, entre em contato com a equipe técnica.*
