package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

public interface MapperJPA<D, J> {

    J toJpaEntity(D domain);

    D toDomainEntity(J jpaEntity);
}
