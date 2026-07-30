package com.example.emotionPlatform.mapper;

// MapStruct: libreria che genera automaticamente il codice per convertire oggetti
import org.mapstruct.Mapper;

import com.example.emotionPlatform.dto.role.RoleResponseDTO;
import com.example.emotionPlatform.entity.Role;
// @Mapper dice a MapStruct che l'interfaccia è un mapper e quindi di generare in automatico l'implementazione 

/* 
MapStruct durante la compilazione crea questo:
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponseDTO toResponse(Role role) {

        RoleResponseDTO dto = new RoleResponseDTO();

        dto.setId(role.getId());
        dto.setName(role.getName().toString());

        return dto;
    }
}

*/
// componentModel fa diventare il mapper un bean Spring, quindi si puo usare @Autowired o usare il costruttore
@Mapper(componentModel = "spring")
public interface RoleMapper {
    // RoleResponseDTO oggetto che si manda al client
    // toResponse ritorna un dto, e fa Entity -> DTO di risposta
    RoleResponseDTO toResponse(Role role);
}
