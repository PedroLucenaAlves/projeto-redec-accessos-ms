package com.br.redec.accessos_terceros_ms.infrastructure.mapper;

import com.br.redec.accessos_terceros_ms.domain.model.Acesso;
import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.database.AcessoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//essa anotacao serve para avisa ao processador para gerar a impl da classe e por o @component para o spring
@Mapper(componentModel = "spring")
public interface AcessoMapper {


    Acesso acessoEntityToAcessso(AcessoEntity acessoEntity);

    AcessoEntity acessoToAcessoEntity(Acesso acesso);

}
