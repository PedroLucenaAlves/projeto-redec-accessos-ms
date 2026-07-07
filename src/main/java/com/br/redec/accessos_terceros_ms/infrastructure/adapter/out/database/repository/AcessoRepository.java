package com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.database.repository;

import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.database.AcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //nao e necessario anotar com o repository, mas e interessante por fins de documentacao
public interface AcessoRepository extends JpaRepository<AcessoEntity, String> {


}
