package com.projetoPedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoPedidos.entities.Pedido;
import com.projetoPedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description="API REST DE GERENCIAMENTO DE PEDIDOS")
public class PedidoController {
	private final PedidoService pedidoService; 

	@Autowired 
	public PedidoController(PedidoService pedidoService) { 
	this.pedidoService = pedidoService; 
	} 

	@PostMapping("/") 
	@Operation(summary = "Cadastra um pedido") 
	public ResponseEntity<Pedido> createPedido(@RequestBody @Valid Pedido pedido) { 
		Pedido salvaPedido = pedidoService.salvaPedido(pedido); 
	return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedido); 
	} 

	@PutMapping("/{id}") 
	@Operation(summary = "Alterar um pedido") 
	public ResponseEntity<Pedido> alteraPedidoControl(@PathVariable Long id, @RequestBody @Valid Pedido pedido){ 
		Pedido alteraPedido = pedidoService.alteraPedido(id, pedido); 
		if (alteraPedido != null) { 
			return ResponseEntity.ok(pedido); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 

	@GetMapping("/{id}") 
	@Operation(summary = "Localiza pedido por ID") 
		public ResponseEntity<Pedido> buscaPedidoControlId (@PathVariable Long id){ 
			Pedido pedido = pedidoService.buscaPedidoById(id); 
			if(pedido != null) { 
				return ResponseEntity.ok(pedido); 
			} 
			else { 
				return ResponseEntity.notFound().build(); 
			} 
		} 

		@GetMapping("/") 
		@Operation(summary = "Apresenta todos os pedidos") 
		public ResponseEntity<List<Pedido>> buscaTodosPedidosControl(){ 
			List<Pedido> pedido = pedidoService.buscaTodosPedidos(); 
			return ResponseEntity.ok(pedido); 
		} 

	@DeleteMapping("/{id}") 
	@Operation(summary = "Apagar um pedido") 
	public ResponseEntity<Pedido> apagarPedidoControl(@PathVariable Long id){ 
		boolean apagar = pedidoService.apagarPedido(id); 
		if (apagar) { 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 	} 

	} 

}
