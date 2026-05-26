package co.edu.uco.ucoparking.infrastructure.persistence.repository.adapter.sql.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.application.outputport.ReservationOutputPort;
import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import co.edu.uco.ucoparking.infrastructure.persistence.mapper.ReservationMapperJPA;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.ReservationJpaRepository;

@Repository
public class ReservationRepositoryJpaAdapter implements ReservationOutputPort {

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationMapperJPA reservationMapperJPA;

    public ReservationRepositoryJpaAdapter(ReservationJpaRepository reservationJpaRepository,
                                           ReservationMapperJPA reservationMapperJPA) {
        this.reservationJpaRepository = reservationJpaRepository;
        this.reservationMapperJPA = reservationMapperJPA;
    }

    @Override
    public Reservation save(Reservation reservation) {
        var saved = reservationJpaRepository.save(reservationMapperJPA.toJpaEntity(reservation));
        return reservationMapperJPA.toDomainEntity(saved);
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        return reservationJpaRepository.findById(id)
                .map(reservationMapperJPA::toDomainEntity);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationJpaRepository.findAll()
                .stream()
                .map(reservationMapperJPA::toDomainEntity)
                .toList();
    }

    @Override
    public List<Reservation> findByStatus(ReservationStatus status) {
        return reservationJpaRepository.findByStatus(status)
                .stream()
                .map(reservationMapperJPA::toDomainEntity)
                .toList();
    }
}