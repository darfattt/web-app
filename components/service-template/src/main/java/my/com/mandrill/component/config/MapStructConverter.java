package my.com.mandrill.component.config;

import my.com.mandrill.component.domain.Domain;
import my.com.mandrill.component.dto.request.DomainRequestDTO;
import my.com.mandrill.component.dto.response.DomainResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructConverter {

	MapStructConverter MAPPER = Mappers.getMapper(MapStructConverter.class);

	default <T> Optional<T> wrapOptional(T object) {
		return Optional.of(object);
	}

	Domain convert(DomainRequestDTO domainRequestDTO);

	@Mapping(target = "text", expression = "java(String.join(\"-\", domain.getCode(), domain.getName()))")
	DomainResponseDTO convert(Domain domain);

	List<DomainResponseDTO> convert(Collection<Domain> domains);

}