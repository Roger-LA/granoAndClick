package com.granoAndClick.granoAndClick.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.granoAndClick.granoAndClick.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByUsuario_UsuarioId(Long usuarioId);
}
