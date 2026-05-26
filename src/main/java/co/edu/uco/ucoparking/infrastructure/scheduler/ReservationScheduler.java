package co.edu.uco.ucoparking.infrastructure.scheduler;

import co.edu.uco.ucoparking.application.outputport.ParkingSpaceOutputPort;
import co.edu.uco.ucoparking.application.outputport.ReservationOutputPort;
import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import co.edu.uco.ucoparking.infrastructure.entrypoint.sse.ParkingSseController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReservationScheduler {

    private final ReservationOutputPort reservationOutputPort;
    private final ParkingSpaceOutputPort parkingSpaceOutputPort;
    private final ParkingSseController parkingSseController;

    public ReservationScheduler(ReservationOutputPort reservationOutputPort,
                                ParkingSpaceOutputPort parkingSpaceOutputPort,
                                ParkingSseController parkingSseController) {
        this.reservationOutputPort = reservationOutputPort;
        this.parkingSpaceOutputPort = parkingSpaceOutputPort;
        this.parkingSseController = parkingSseController;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void expireReservations() {
        var activeReservations = reservationOutputPort.findByStatus(ReservationStatus.ACTIVE);

        for (var reservation : activeReservations) {
            if (reservation.isExpired()) {
                reservationOutputPort.save(reservation.expire());

                parkingSpaceOutputPort.findById(reservation.getParkingSpaceId())
                        .ifPresent(space -> {
                            parkingSpaceOutputPort.save(space.release());
                            parkingSseController.publish("SPACE_RELEASED", space.getId());
                        });
            }
        }
    }
}