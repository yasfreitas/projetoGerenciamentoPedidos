package com.projetoPedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoPedidos.entities.Pedido;
import com.projetoPedidos.repository.PedidoRepository;

@Service
public class PedidoService {
	private final PedidoRepository pedidoRepository;
	
	@Autowired
	public PedidoService (PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	public List<Pedido> buscaTodosPedidos(){
		return pedidoRepository.findAll();
	}
	
	public Pedido buscaPedidoById(Long id) {
		Optional <Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElse(null);
	}
	
	public Pedido salvaPedido (Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido alteraPedido(long id, Pedido alteraPedido) {
		Optional <Pedido> existePedido = pedidoRepository.findById(id);
		if (existePedido.isPresent()) {
			alteraPedido.setId(id);
			return pedidoRepository.save(alteraPedido);
		}
		return null;
	}
	public boolean apagarPedido(Long id) {
		Optional <Pedido> existePedido = pedidoRepository.findById(id);
		if (existePedido.isPresent()) {
			pedidoRepository.deleteById(id);
			return true;
		}
		return false;
	}
	

}
