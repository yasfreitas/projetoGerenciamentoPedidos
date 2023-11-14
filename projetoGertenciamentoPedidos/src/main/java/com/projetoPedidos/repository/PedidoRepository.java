package com.projetoPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoPedidos.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
