package MentcareApplication.Controllers.Utils;

import MentcareApplication.Models.Appointment;
import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import MentcareApplication.Models.Request;
import MentcareApplication.Repositories.AppointmentRepository;
import MentcareApplication.Repositories.MedicRepository;
import MentcareApplication.Repositories.PatientRepository;
import MentcareApplication.Repositories.RequestRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Utils {

    public static List<Appointment> getTodayAppointments(AppointmentRepository appointmentRepository){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAll()){
            if(a.isActive() && a.getAppointmentDateToLocalDate().compareTo(LocalDate.now()) == 0){
                appointments.add(a);
            }
        }
        return appointments;
    }

    public static List<Appointment> getFutureAppointments(AppointmentRepository appointmentRepository, boolean sorted){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAll()){
            if(a.isActive() && a.getAppointmentDateToLocalDate().compareTo(LocalDate.now()) >= 0){
                appointments.add(a);
            }
        }
        if(sorted){
            appointments.sort(Comparator.comparing((Appointment a) -> LocalDate.parse(a.getAppointmentDate(), dtf)));
        }

        return appointments;
    }

    public static List<Request> getRequests(RequestRepository requestRepository) {
        List<Request> requests = new ArrayList<>();
        for(Request r : requestRepository.findAll()){
            if(r.isActive()) requests.add(r);
        }

        return requests;
    }

    public static List<Patient> getPatients(PatientRepository patientRepository){
        List<Patient> patients = new ArrayList<>();
        for(Patient p : patientRepository.findAll()){
            if(p.isConfirmed()){
                patients.add(p);
            }
        }

        return patients;
    }

    public static List<Appointment> getAppointmentsByMedicID(AppointmentRepository appointmentRepository, long medicID, boolean onlyPastAppointments){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Appointment> appointments = appointmentRepository.findByMedicMedicID(medicID);
        List<Appointment> futureAppointments = new ArrayList<>();
        List<Appointment> pastAppointments = new ArrayList<>();
        for(Appointment a : appointments){
            if(a.isActive()){
                if (a.getAppointmentDateToLocalDate().compareTo(LocalDate.now()) >= 0) {
                    futureAppointments.add(a);
                } else {
                    pastAppointments.add(a);
                }
            }
        }

        futureAppointments.sort(Comparator.comparing((Appointment a1) -> LocalDate.parse(a1.getAppointmentDate(), dtf)));
        pastAppointments.sort((Appointment a1, Appointment a2) -> LocalDate.parse(a2.getAppointmentDate(), dtf).compareTo(LocalDate.parse(a1.getAppointmentDate(), dtf)));

        return (onlyPastAppointments) ? pastAppointments : futureAppointments;
    }

    public static List<Appointment> getAppointmentsByPatientID(AppointmentRepository appointmentRepository, long patientID, boolean onlyPastAppointments){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Appointment> appointments = appointmentRepository.findByPatientPatientID(patientID);
        List<Appointment> futureAppointments = new ArrayList<>();
        List<Appointment> pastAppointments = new ArrayList<>();
        for(Appointment a : appointments){
            if(a.isActive()){
                if (a.getAppointmentDateToLocalDate().compareTo(LocalDate.now()) >= 0) {
                    futureAppointments.add(a);
                } else {
                    pastAppointments.add(a);
                }
            }
        }

        futureAppointments.sort(Comparator.comparing((Appointment a1) -> LocalDate.parse(a1.getAppointmentDate(), dtf)));
        pastAppointments.sort((Appointment a1, Appointment a2) -> LocalDate.parse(a2.getAppointmentDate(), dtf).compareTo(LocalDate.parse(a1.getAppointmentDate(), dtf)));

        return (onlyPastAppointments) ? pastAppointments : futureAppointments;
    }

    public static List<Patient> getPatientsByMedicID(PatientRepository patientRepository, long medicID){
        List<Patient> patients = new ArrayList<>();
        for(Patient p : patientRepository.findByMedicMedicID(medicID)){
            if(p.isConfirmed()){
                patients.add(p);
            }
        }

        return patients;
    }



    public static String convertDateToSlash(String originalDate){
        String[] parts = originalDate.split("-");
        return parts[2]+'/'+parts[1]+'/'+parts[0];
    }


}
