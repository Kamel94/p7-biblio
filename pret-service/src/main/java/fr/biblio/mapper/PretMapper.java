package fr.biblio.mapper;

import fr.biblio.dto.PretDto;
import fr.biblio.entities.Pret;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PretMapper {

    Pret pret (PretDto pretDto);

    PretDto pretDto (Pret pret);

    List<PretDto> listePret (List<Pret> prets);

}
