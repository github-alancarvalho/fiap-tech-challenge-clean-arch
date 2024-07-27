package br.com.fiap.techchallenge.fiapfood.core.usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ItemPedidoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PedidoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.*;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Telefone;
import br.com.fiap.techchallenge.fiapfood.core.mapper.DadosPagamentoMapper;
import br.com.fiap.techchallenge.fiapfood.core.mapper.ItemPedidoRequestMapper;
import br.com.fiap.techchallenge.fiapfood.core.mapper.PedidoResponseMapper;
import br.com.fiap.techchallenge.fiapfood.core.mapper.ProdutoResponseMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PedidoUseCase implements PedidoUseCaseBoundary {

    public static final List<StatusPedido> STATUS_PRIORITARIOS = List.of(StatusPedido.PRONTO, StatusPedido.EM_PREPARACAO, StatusPedido.RECEBIDO);
    private PedidoGateway pedidoGateway;
    private ItemPedidoGateway itemPedidoGateway;
    private ClienteUseCaseBoundary clienteUseCaseBoundary;
    private ProdutoUseCaseBoundary produtoUseCaseBoundary;
    private PagamentoUseCaseBoundary pagamentoUseCaseBoundary;

    public PedidoUseCase(PedidoGateway pedidoGateway,
                         ItemPedidoGateway itemPedidoGateway,
                         ClienteUseCaseBoundary clienteUseCaseBoundary,
                         ProdutoUseCaseBoundary produtoUseCaseBoundary,
                         PagamentoUseCaseBoundary pagamentoUseCaseBoundary) {
        this.pedidoGateway = pedidoGateway;
        this.itemPedidoGateway = itemPedidoGateway;
        this.clienteUseCaseBoundary = clienteUseCaseBoundary;
        this.produtoUseCaseBoundary = produtoUseCaseBoundary;
        this.pagamentoUseCaseBoundary = pagamentoUseCaseBoundary;
    }

    public PedidoResponse buscarPedidoPorId(Long id) {
        try {
            Pedido pedido = this.pedidoGateway.buscarPorId(id);

            if (pedido != null)
                return PedidoResponse.builder().id(pedido.getId())
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
            else
                return null;
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public List<PedidoResponse> buscarTodosPedidos() {
        try {
            List<Pedido> list = this.pedidoGateway.listarTudo();
            return list.stream().map(c -> PedidoResponse.builder()
                    .id(c.getId())
                    .cliente(c.getCliente())
                    .status(c.getStatus())
                    .listItens(c.getListItens()).build()).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public List<PedidoResponse> buscarPedidosPorStatus(StatusPedido statusPedido) {
        try {
            List<Pedido> list = this.pedidoGateway.listarPedidosPorStatus(statusPedido);
            return list.stream().map(c -> PedidoResponse.builder()
                    .id(c.getId())
                    .cliente(c.getCliente())
                    .status(c.getStatus())
                    .listItens(c.getListItens()).build()).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public List<Pedido> ordenarPedidos(List<Pedido> pedidos) {
        try {
            return pedidos.stream()
                    .sorted(Comparator.comparing(pedido -> STATUS_PRIORITARIOS.indexOf(((Pedido) pedido).getStatus()))
                            .thenComparing(Comparator.comparing(pedido -> ((Pedido) pedido).getData())))
                    .collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public List<PedidoResponse> buscarPedidosEmAberto() {
        try {
            List<Pedido> list = this.pedidoGateway.listarPedidosEmAberto();

            List<Pedido> listOrdenada = ordenarPedidos(list);

            return listOrdenada.stream().map(c -> PedidoResponse.builder()
                    .id(c.getId())
                    .cliente(c.getCliente())
                    .status(c.getStatus())
                    .listItens(c.getListItens()).build()).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public List<PedidoResponse> buscarPedidosAguardandoPagamento() {
        try {
            List<Pedido> list = this.pedidoGateway.listarPedidosAguardandoPagamento();
            return list.stream().map(c -> PedidoResponse.builder()
                    .id(c.getId())
                    .cliente(c.getCliente())
                    .status(c.getStatus())
                    .listItens(c.getListItens()).build()).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public PedidoResponse inserirPedido(PedidoRequest pedidoRequest) throws FiapFoodException {
        try {
            ClienteResponse clienteResponse = clienteUseCaseBoundary.buscarClientePorCpf(new Cpf(pedidoRequest.getCpfCliente()));

            Cliente cliente = Cliente.builder()
                    .cpf(new Cpf(clienteResponse.getCpf()))
                    .nome(clienteResponse.getNome())
                    .email(clienteResponse.getEmail())
                    .telefone(new Telefone(clienteResponse.getTelefone())).build();

            Pedido pedido = Pedido.builder()
                    .cliente(cliente)
                    .listItens(new ArrayList<>())
                    .data(LocalDateTime.now())
                    .build();

            Pedido pedidoSaved = this.pedidoGateway.inserir(pedido);

            List<ItemPedidoRequest> listaItens = pedidoRequest.getListItens();
            List<ItemPedido> listItemPedido = new ArrayList<>();

            for (ItemPedidoRequest item : listaItens) {
                ItemPedido ip = ItemPedido.builder()
                        .pedido(pedidoSaved)
                        .produto(Produto.builder().id(item.getIdProduto()).build())
                        .quantidade(item.getQuantidade()).build();
                listItemPedido.add(ip);
            }
//        item.setPedido(pedidoSaved);

            List<ItemPedido> retList = this.itemPedidoGateway.inserir(listItemPedido);

            return PedidoResponse.builder().id(pedidoSaved.getId())
                    .id(pedidoSaved.getId())
                    .cliente(pedidoSaved.getCliente())
                    .status(pedidoSaved.getStatus())
                    .listItens(retList)
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public PedidoResponse inserirItensNoPedido(PedidoRequest pedidoRequest) throws FiapFoodException {
        try {
            ClienteResponse clienteResponse = clienteUseCaseBoundary.buscarClientePorCpf(new Cpf(pedidoRequest.getCpfCliente()));

            Cliente cliente = Cliente.builder()
                    .cpf(new Cpf(clienteResponse.getCpf()))
                    .nome(clienteResponse.getNome())
                    .email(clienteResponse.getEmail())
                    .telefone(new Telefone(clienteResponse.getTelefone())).build();

            Pedido pedido = Pedido.builder()
                    .cliente(cliente)
//                .listItens(pedidoRequest.getListItens())
                    .listItens(ItemPedidoRequestMapper.mapFromRequestToDomain(pedidoRequest.getListItens()))
                    .build();

            Pedido pedidoSaved = this.pedidoGateway.inserir(pedido);

            return PedidoResponse.builder().id(pedidoSaved.getId())
                    .id(pedidoSaved.getId())
                    .cliente(pedidoSaved.getCliente())
                    .status(pedidoSaved.getStatus())
                    .listItens(pedidoSaved.getListItens())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public PedidoResponse atualizarProgresso(AlterarProgressoPedidoRequest alterarProgressoPedidoRequest) throws FiapFoodException {
        try {
            Pedido pedido = Pedido.builder().id(alterarProgressoPedidoRequest.getId()).build();

            Pedido pedidoSaved = this.pedidoGateway.atualizarProgresso(pedido, StatusPedido.valueOf(alterarProgressoPedidoRequest.getNovoStatus()));

            return PedidoResponse.builder().id(pedidoSaved.getId())
                    .id(pedidoSaved.getId())
                    .cliente(pedidoSaved.getCliente())
                    .status(pedidoSaved.getStatus())
                    .listItens(pedidoSaved.getListItens())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public PedidoResponse enviarPedidoParaFilaDePreparacao(Long idPedido) throws FiapFoodException {
        try {
            Pedido pedido = Pedido.builder().id(idPedido).build();

            Pedido pedidoSaved = this.pedidoGateway.atualizarProgresso(pedido, StatusPedido.RECEBIDO);

            return PedidoResponse.builder().id(pedidoSaved.getId())
                    .id(pedidoSaved.getId())
                    .cliente(pedidoSaved.getCliente())
                    .status(pedidoSaved.getStatus())
                    .listItens(pedidoSaved.getListItens())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public Boolean excluir(Long id) throws FiapFoodException {
        try {
            return this.pedidoGateway.excluir(id);
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public PagamentoResponse checkout(PedidoRequest pedidoRequest) {
        try {
            PedidoResponse pedidoResponse = inserirPedido(pedidoRequest);

            Pedido pedido = PedidoResponseMapper.mapToEntity(pedidoResponse);

            for (ItemPedido itemPedido : pedido.getListItens()) {
                ProdutoResponse produtoResponse = produtoUseCaseBoundary.buscarProdutoPorId(itemPedido.getProduto().getId());
                itemPedido.setProduto(ProdutoResponseMapper.mapToEntity(produtoResponse));
            }
//        pedidoRequest.setListItens(pedidoResponse.getListItens());


            Double valorTotalPedido = calcularValorTotalDoPedido(pedido);

            Pagamento2 pagamento = pagamentoUseCaseBoundary.prepararPagamento(pedidoResponse.getCliente(), pedido, valorTotalPedido, DadosPagamentoMapper.mapFromRequestToEntity(pedidoRequest.getDadosPagamento()));

            return pagamentoUseCaseBoundary.efetuarPagamentoViaCartao(pagamento);
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public Double calcularValorTotalDoPedido(Pedido pedido) {
        try {
            Double valorTotalPedido = 0D;
            Double valorProduto;

            for (ItemPedido ip : pedido.getListItens()) {
                valorProduto = ip.getProduto().getPreco();
                valorTotalPedido += valorProduto * ip.getQuantidade();
            }
            return valorTotalPedido;
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }
}
