package MentcareAppication.Repositories;

import MentcareAppication.Models.Communication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommunicationRepository extends CrudRepository<Communication, Long> {
    Communication findById(long id);

    List<Communication> findByAppointmentAppointmentID(long appointmentID);
}
