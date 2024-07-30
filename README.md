# Tech Challenge - SOAT07

Desafio desenvolvido com DDD, Docker e Arquitetura Hexagonal para o curso de Software Architecture da FIAP Pós Tech.

## Versão
1.0.0

## Índice
<a href="#tecnologias">Tecnologias</a> •
<a href="#requisitos-mínimos">Requisitos minimos</a> •
<a href="#como-rodar">Como rodar</a> •
<a href="criterios-de-aceite">Criterios de aceite</a> •
<a href="#autores">Autores</a>

## Tecnologias
<div style="display: inline_block"><br>
    <img align="center" alt="java" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg">    
    <img align="center" alt="jspring" height="40" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" />  
    <img align="center" alt="mariaDB" height="50" width="80" src="https://github.com/devicons/devicon/blob/v2.16.0/icons/mariadb/mariadb-original.svg">       
</div>

## Requisitos mínimos
- git
- docker e docker-compose
- Kubectl
- Conta no mercado pago para simulação dos pagamentos

## Como rodar?
Clone este repositório
```
bash
$ git clone https://github.com/github-alancarvalho/fiap-tech-challenge.git


- Abra o projeto na IDE de sua preferência
- Configure as varíaveis de ambiente dos arquivos .yaml localizados na pasta infra/k8s
  00-aws-configmap-mariadb.yaml
  00.1-aws-configmap-api.yaml
  02-aws-pv.yaml --> Configurar o volume

- No terminal
  cd infra/k8s
  kubectl apply -f 00-aws-configmap-mariadb.yaml
  kubectl apply -f 00.1-aws-configmap-api.yaml
  kubectl apply -f 01-aws-storage-class.yaml
  kubectl apply -f 02-aws-pv.yaml
  kubectl apply -f 03-aws-pvc.yaml
  kubectl apply -f 04-aws-mariadb-techchallenge-deployment.yaml
  kubectl apply -f 05-aws-mariadb-techchallenge-service.yaml
  kubectl apply -f 06-aws-api-deployment.yaml
  kubectl apply -f 07-aws-api-service.yaml
  kubectl apply -f 08-HPA.yaml
  kubectl apply -f high-availability.yaml
  kubectl apply -f metrics.yaml

- No Swagger ou no postman, rodar na sequência:
 - /api/v1/Produtos/inserir  -->> Cadastrar os produtos que deseja 
 - /api/v1/Clientes/inserir  --> Inclui um cliente 
 - /api/v1/Pedidos/checkout  --> Registrar um pedido com os dados do cartão de crédito para pagamento.
 - /api/v1/Pedidos/buscarPedidosEmAberto
 OBS: Essa sequência de execução foi demonstrada no vídeo, com a aplicação rodando na AWS.

```
## Problema
Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um clienteEntity faça um pedidoEntity complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedidoEntity em um papel e entregá-lo à cozinha, mas não há garantia de que o pedidoEntity será preparado corretamente.
Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios.
Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa.
Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades:

Pedido: Os clientes são apresentados a uma interface de seleção na qual podem optar por se identificarem via CPF, se cadastrarem com nome, e-mail ou não se identificar, podendo montar o combo na seguinte sequência, sendo todas elas opcionais: Lanche, Acompanhamento, Bebida e Sobremesa.
Em cada etapa é exibido o nome, descrição e preço de cada produtoEntity.

Pagamento: O sistema deverá possuir uma opção de pagamentoEntity integrada para MVP. A forma de pagamentoEntity oferecida será via QRCode do Mercado Pago.

Acompanhamento: Uma vez que o pedidoEntity é confirmado e pago, ele é enviado para a cozinha para ser preparado. Simultaneamente deve aparecer em um monitor para o clienteEntity acompanhar o progresso do seu pedidoEntity com as seguintes etapas:
- Recebido
- Em preparação
- Pronto
- Finalizado


Deve ser possível acompanhar os pedidos em andamento e tempo de espera de cada pedidoEntity.

Entrega: Quando o pedidoEntity estiver pronto, o sistema deverá notificar o clienteEntity que ele está pronto para retirada. Ao ser retirado, o pedidoEntity deve ser atualizado para o status finalizado.

Além das etapas do clienteEntity, o estabelecimento precisa de um acesso administrativo:

Gerenciar clientes: Com a identificação dos clientes o estabelecimento pode trabalhar em campanhas promocionais.
Gerenciar produtos e categorias: Os produtos dispostos para escolha do clienteEntity serão gerenciados pelo estabelecimento, definindo nome, categoriaEntity, preço, descrição e imagens. Para esse sistema teremos categorias fixas: Lanche, Acompanhamento, Bebida e Sobremesa.

As informações dispostas no sistema de pedidos precisarão ser gerenciadas pelo estabelecimento através de um painel administrativo.

## Critérios de aceite
### FASE 1
Entregável 1: Documentação do sistema (DDD) com Event Storming, incluindo todos os passos/tipos de diagrama mostrados na aula 6 do módulo de DDD, e utilizando a linguagem ubíqua, dos seguintes fluxos:
a. Realização do pedidoEntity e pagamentoEntity
b. Preparação e entrega do pedidoEntity

Entregável 2:Uma aplicação para todo o sistema de backend (monolito) que deverá ser desenvolvido seguindo os padrões apresentados nas aulas:
Utilizando arquitetura hexagonal
APIs:
- Cadastro do Cliente
- Identificação do Cliente via CPF
- Criar, editar e remover produtos
- Buscar produtos por categoriaEntity
- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedidoEntity.
- Listar os pedidos
  Disponibilizar também o Swagger para consumo dessas APIs
  Banco de dados à sua escolha - Inicialmente deveremos trabalhar e organizar a fila dos pedidos apenas em banco de dados

Entregável 3: A aplicação deve ser entregue com um Dockerfile configurado para executá-la corretamente, e um docker-compose.yml para subir o ambiente completo.


### FASE 2
Entregável 1: Atualizar a aplicação desenvolvida na FASE 1 refatorando o código para seguir os padrões clean code e clean architecture:
a. Alterar/criar as APIs:
     i.	Checkout Pedido que deverá receber os produtos solicitados e retornar a identificação do pedido.                
     ii.Consultar status pagamento pedido, que informa se o pagamento foi aprovado ou não.                
     iii.Webhook para receber confirmação de pagamento aprovado ou recusado.                
     iv. A lista de pedidos deverá retorná-los com suas descrições, ordenados com a seguinte regra:  
      Pronto > Em Preparação > Recebido;
      Pedidos mais antigos primeiro e mais novos depois;
      Pedidos com status Finalizado não devem aparecer na lista.                
     v.	Atualizar o status do pedido.                
     vi.Como desafio extra, opcionalmente, você pode implementar a integração com Mercado Pago para gerar o QRCode para pagamento e integrar com o WebHook para capturar os pagamentos. Caso contrário, será necessário realizar o mock da parte de pagamentos.
                
     
Entregável 2: Criar uma arquitetura em Kubernetes que atenda os seguintes requisitos:
    Os requisitos funcionais descritos nos itens anteriores (item problema).
    Escalabilidade com aumento e diminuição de Pods conforme demanda.
    Os arquivos manifestos (yaml) precisam estar no Github junto com a nova versão do código.
            
Entregável 3: Entrega da seguinte documentação no ReadMe
             Desenho da arquitetura pensado por você, pessoa arquiteta de software, contemplando:
                i.	Os requisitos do negócio (problema).
                ii.	Os requisitos de infraestrutura: Você pode utilizar o MiniKube, Docker Kubernetes, AKS, EKS, GKE ou qualquer nuvem que você desenha.
Collection com todas as APIs desenvolvidas com exemplo de requisição (que não seja vazia):
                i.	Link do Swagger no projeto ou link para download da collection do Postman (JSON).
Guia completo com todas as instruções para execução do projeto e a ordem de execução das APIs, caso seja necessário.
        Link para vídeo demonstrando a arquitetura desenvolvida na nuvem ou localmente
            


# Solução
## Domain Storytelling
### Fluxo Realização do pedidoEntity e pagamentoEntity:
![Fast Food - Pedido e Pagamento_2024-04-14 (4)](https://github.com/github-alancarvalho/fiap-tech-challenge-initial/assets/166860938/fd01d09f-12f7-4d5c-ab10-85ee8a89566c)


### Fluxo Preparação e entrega do pedidoEntity:
![Fast Food - Preparação e entrega do pedido_2024-04-14 (2)](https://github.com/github-alancarvalho/fiap-tech-challenge-initial/assets/166860938/022d29a1-c181-47bd-bb56-95237aa196d3)


## Event Storming
Para documenação dos fluxos foi criado um board na no Miro onde foi desenhado o fluxo de negócio e disponibilizado o dicionário de linguagem ubíqua.
https://miro.com/app/board/uXjVKUnohjM=/?share_link_id=725040074360

### Domínios
- Subdomínio Principal:
    - Gestão de Pedido
- Subdomínio Genérico:
    - Lógica de pagamentoEntity integrada ao mercado pago.
    - Autenticação e Autorização
    - Notificações - Envio de notificações sobre o progresso dos pedidos para os clientes
    - Gestão de estoque
- Subdomínio Suporte:
    - Gestão de clientes
    - Gestão de Produtos e Categorias,
    - Acompanhamento online de pedidos,
    - Interface do usuário


### Contextos delimitados

- Clientes
- Produto(Cardápio)
- Pedido (Realização do pedidoEntity)
- Pagamento
- Sistema Externo - Mercado Pago
- Preparação
- Entrega
- Acompanhamento




### Dicionário de linguagem ubíqua
- Cliente: Pessoa que deseja efetuar ou já tenha efetuado uma compra no restaurante
- Cozinheiro: Pessoa que prepara o pedidoEntity
- Item: Item é uma referência a qualquer produtoEntity disponível ou adicionado ao pedidoEntity.
- Pedido: Itens que o clienteEntity deseja comprar ou que já comprou.
- Identificação: Pode se identificar usando CPF, nome, e-mail ou não se identificar.

Cardápio: A lista de ítens disponíveis para compra. Esses itens são agrupados em 4 categorias: Lanche, Acompanhamento, Bebida e Sobremesa. Todos os itens são opcionais.
- Lanche: No momento da compra, lanche pode ser, um hamburguer simples, cachorro quente, hamburguer gourmet, hamburguer vegano, entre outros
- Acompanhamento: No momento da compra, acompanhamento pode ser, por exemplo: Batata frita, frango frito, maçã, entre outros produtos.
- Bebida: No momento da compra, bebida pode ser, por exemplo: Sucos, água, chás gelados, entre outros.
- Sobremesa: No momento da compra, sobremesa pode ser, por exemplo: sorvetes, frutas, tortas, bolos, milkshakes especiais ou outros doces.

Etapas do Progresso do Pedido:
- RECEBIDO: Pagamento confirmado e o pedidoEntity está na fila para ser preparado
- EM PREPARAÇÃO: Preparação do pedidoEntity foi iniciada
- PRONTO: Pedido está liberado para ser retirado pelo clienteEntity
- FINALIZADO: Pedido foi retirado pelo clienteEntity


---

###### FASE II #####

### Desenho de Arquitetura

![FiapFood vpd](https://github.com/user-attachments/assets/7339fda5-6333-458d-8143-34e0146bb5e7)

![clean arch diagram drawio](https://github.com/user-attachments/assets/ea383f09-8879-4bc7-ace1-1597ed33a064)


### Link do vídeo demonstrando a arquitetura na AWS e a aplicação rodando
https://youtu.be/9V6KqR_jfvk

### Api
- Swagger: http://localhost:8080/swagger-ui/index.html
- Link Collections do postman - https://github.com/github-alancarvalho/fiap-tech-challenge-clean-arch/blob/03fa995cfb80fcd1af720239d655fadd2b64d491/docs/postman/FiapFood%20-%20Tech%20Challenge.postman_collection.json



## Autor
- [Alan Carvalho](https://github.com/github-alancarvalho)
