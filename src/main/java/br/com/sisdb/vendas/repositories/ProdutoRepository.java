package br.com.sisdb.vendas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.domains.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	//Similar ao JpaData
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat "
//			+ "WHERE obj.descricao LIKE %:descricao% AND cat IN :categorias")
//	Page<Produto> search(@Param("descricao") String descricao,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	//Obs: este método acima faz a mesma coisa que o metodo de baixo.
	
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByDescricaoContainingAndCategoriasIn(String descricao, List<Categoria> categorias, Pageable pageRequest);
}
