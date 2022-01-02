package MentcareApplication.Controllers;

import MentcareApplication.Controllers.Utils.*;
import MentcareApplication.Models.*;
import MentcareApplication.Repositories.*;
import MentcareApplication.Controllers.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CommunicationRepository communicationRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RequestRepository requestRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /* Classe eseguita all'avvio dell'applicazione.
     * Utilizzata per inserire alcuni dati d'esempio all'interno del Database. */
    @Component
    private static class fillRepositoriesWithFakeData {
        public fillRepositoriesWithFakeData(AppointmentRepository appointmentRepository, CommunicationRepository communicationRepository, MedicRepository medicRepository, PatientRepository patientRepository, RequestRepository requestRepository) throws ParseException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Medic medic1 = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
            medicRepository.save(medic1);
            Medic medic2 = new Medic("Tom", "Cruise", "Disturbo del sonno", "Studia con precisione eventuali alterazioni del sonno e della respirazione durante il sonno e offre le soluzioni terapeutiche adeguate.");
            medicRepository.save(medic2);

            Patient patient1 = new Patient(medic1, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);
            patientRepository.save(patient1);
            Patient patient2 = new Patient(medic2, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);
            patientRepository.save(patient2);
            Patient patient3 = new Patient(medic1, "Robert", "De Niro", "RD75TO", "07/08/1943", "Verona", "Attacchi di ira", "Repentini cambio d'umore caratterizzano la persona, spesso rendendola aggressiva e pericolosa.", true);
            patientRepository.save(patient3);

            LocalDate now = LocalDate.now();

            Patient patient4 = new Patient(null, "Harrison", "Ford", "HF75ND", "13/07/1942", "Belluno", "", "Difficoltà nel mantenere l'attenzione, anche per brevi periodi di tempo", false);
            patientRepository.save(patient4);
            requestRepository.save(new Request(patient4, dtf.format(now)));

            Appointment appointment1 = new Appointment(medic1, patient1, dtf.format(now), "Mestre");
            appointmentRepository.save(appointment1);
            communicationRepository.save(new Communication(appointment1, dtf.format(now.minusDays(1)), "Sarà presente anche il fratello del paziente, il quale vorrà porle delle domande.", true, false));
            communicationRepository.save(new Communication(appointment1, dtf.format(now), "Ti ricordiamo l'appuntamento di oggi!", false, true));

            Appointment appointment2 = new Appointment(medic2, patient2, dtf.format(now), "Vicenza");
            appointmentRepository.save(appointment2);
            communicationRepository.save(new Communication(appointment2, dtf.format(now), "L'appuntamento è stato spostato in data odierna", true, true));

            LocalDate tomorrow = now.plusDays(1);
            Appointment appointment3 = new Appointment(medic1, patient3, dtf.format(now), "Verona");
            appointmentRepository.save(appointment3);
            communicationRepository.save(new Communication(appointment3, dtf.format(now), "Ti ricordiamo che domani l'appuntamento si svolgerà nella clinica di Verona!", false, true));
            Appointment appointment4 = new Appointment(medic1, patient1, dtf.format(tomorrow), "Marghera");
            appointmentRepository.save(appointment4);
            Appointment appointment5 = new Appointment(medic2, patient2, dtf.format(tomorrow), "Padova");
            appointmentRepository.save(appointment5);

            LocalDate yesterday = now.minusDays(2);
            Appointment appointment6 = new Appointment(medic1, patient3, dtf.format(yesterday), "Bassano");
            Appointment appointment7 = new Appointment(medic1, patient1, dtf.format(yesterday), "Oriago");
            Appointment appointment8 = new Appointment(medic2, patient2, dtf.format(yesterday), "Monselice");
            appointmentRepository.save(appointment6);
            appointmentRepository.save(appointment7);
            appointmentRepository.save(appointment8);

        }
    }

    /* ROUTE: /clean-database
     *  METHOD: GET
     *  DESC: route temporanea di sviluppo per svuotare il database.
     *  PARAMS: -
     *  ATTRIBUTES: -
     *
    @RequestMapping("/clean-database")
    public String cleanDatabase(){
        requestRepository.deleteAll();
        communicationRepository.deleteAll();
        appointmentRepository.deleteAll();
        patientRepository.deleteAll();
        medicRepository.deleteAll();
        return "redirect:/";
    }*/

    // ****************************************************************************************************************

    /* ROUTE: /
    *  METHOD: GET
    *  DESC: Homepage dell'applicazione web.
    *  PARAMS: -
    *  ATTRIBUTES: -
    */
    @RequestMapping("/")
    public String home(
            @RequestParam(name="error", required = false) boolean error,
            Model model
    ){
        model.addAttribute("appointments", Utils.getTodayAppointments(appointmentRepository));
        model.addAttribute("requests", Utils.getRequests(requestRepository));

        if(error) model.addAttribute("error", true);

        return "home";
    }

    /*  ROUTE: /medici
     *  METHOD: GET
     *  DESC: Lista dei medici
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping("/medici")
    public String medics(Model model){
        model.addAttribute("medics", medicRepository.findAll());
        return "medics/medici";
    }

    /*  ROUTE: /pazienti
     *  METHOD: GET
     *  DESC: Lista dei pazienti
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping("/pazienti")
    public String patients(Model model){
        model.addAttribute("patients", Utils.getPatients(patientRepository));
        return "patients/pazienti";
    }

    /*  ROUTE: /appuntamenti
     *  METHOD: GET
     *  DESC: Lista degli appuntamenti
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping("/appuntamenti")
    public String appointments(Model model){
        model.addAttribute("appointments", Utils.getFutureAppointments(appointmentRepository, true));
        model.addAttribute("medics", medicRepository.findAll());
        model.addAttribute("patients", Utils.getPatients(patientRepository));

        return "appointments/appuntamenti";
    }

    /*  ROUTE: /medico/{idMedico}
     *  METHOD: GET
     *  DESC: Dettaglio di un medico
     *  PARAMS: idMedico è l'id del medico
     *  ATTRIBUTES: -
     */
    @RequestMapping("/medico/{idMedico}")
    public String medicById(
            @PathVariable(value = "idMedico") long medicID,
            Model model
    ){
        Medic medic = medicRepository.findById(medicID);

        if(medic != null){
            model.addAttribute("medic", medic);
            model.addAttribute("patients", patientRepository.findByMedicMedicID(medicID));
            model.addAttribute("futureAppointments", Utils.getAppointmentsByMedicID(appointmentRepository, medicID, false));
            model.addAttribute("pastAppointments", Utils.getAppointmentsByMedicID(appointmentRepository, medicID, true));
            return "medics/medico";
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /paziente/{idPaziente}
     *  METHOD: GET
     *  DESC: Dettaglio di un paziente
     *  PARAMS: idPaziente è l'id del paziente
     *  ATTRIBUTES: -
     */
    @RequestMapping("/paziente/{idPaziente}")
    public String patientById(
            @PathVariable(value = "idPaziente") long patientID,
            Model model
    ){
        Patient patient = patientRepository.findById(patientID);
        if(patient != null){
            Medic medic = patient.getMedic();

            model.addAttribute("patient", patient);
            model.addAttribute("medic", medic);
            model.addAttribute("futureAppointments", Utils.getAppointmentsByPatientID(appointmentRepository, patientID, false));
            model.addAttribute("pastAppointments", Utils.getAppointmentsByPatientID(appointmentRepository, patientID, true));
            return "patients/paziente";

        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /appuntamento/{idAppuntamento}
     *  METHOD: GET
     *  DESC: Dettaglio di un Appuntamento
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping("/appuntamento/{idAppuntamento}")
    public String appointmentById(
            @PathVariable(value = "idAppuntamento") long appointmentID,
            Model model
    ){
        Appointment appointment = appointmentRepository.findById(appointmentID);
        if(appointment != null){
            List<Communication> communications = communicationRepository.findByAppointmentAppointmentID(appointmentID);
            communications.sort((Communication c1, Communication c2) -> ((int) c2.getCommunicationID()) - ((int) c1.getCommunicationID()));

            model.addAttribute("appointment", appointment);
            model.addAttribute("communications", communications);
            return "appointments/appuntamento";

        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /nuovo-appuntamento
     *  METHOD: GET
     *  DESC: Form nuovo appuntamento
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-appuntamento", method = RequestMethod.GET)
    public String formNewAppointment(
            @RequestParam(name="IDMedico", required = false, defaultValue = "-1") long medicID,
            @RequestParam(name="IDPaziente", required = false, defaultValue = "-1") long patientID,
            Model model
    ){
        boolean error = false;

        if(medicID != -1){
            Medic medic = medicRepository.findById(medicID);
            if(medic != null){
                model.addAttribute("byMedic", true);
                model.addAttribute("medic", medic);
                model.addAttribute("medicPatients", Utils.getPatientsByMedicID(patientRepository, medicID));
            }else{
                error = true;
            }
        } else if(patientID != -1){
            Patient patient = patientRepository.findById(patientID);
            if(patient != null){
                model.addAttribute("byPatient", true);
                model.addAttribute("patient", patient);
            }else{
                error = true;
            }
        } else{ error = true; }

        if(error){
            return "redirect:/?error=true";
        }else{
            model.addAttribute("nuovo", true);
            return "appointments/form_appuntamento";
        }
    }

    /*  ROUTE: /nuovo-appuntamento
     *  METHOD: POST
     *  DESC: Inserimento nuovo appuntamento
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-appuntamento", method = RequestMethod.POST)
    public String insertNewAppointment(
            @RequestParam(name = "medic", required = true) String medicID,
            @RequestParam(name = "patient", required = true) String patientID,
            @RequestParam(name = "clinic", required = true) String clinic,
            @RequestParam(name = "appointmentDate", required = true) String appointmentDate,
            Model model
    ){
        appointmentDate = Utils.convertDateToSlash(appointmentDate);

        Appointment appointment = new Appointment(medicRepository.findById(Long.parseLong(medicID)), patientRepository.findById(Long.parseLong(patientID)), appointmentDate, clinic);
        appointmentRepository.save(appointment);

        return "redirect:/appuntamenti";
    }

    /*  ROUTE: /nuovo-paziente
     *  METHOD: GET
     *  DESC: Form nuovo paziente
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-paziente", method = RequestMethod.GET)
    public String formNewPatient(Model model){
        model.addAttribute("nuovo", true);
        model.addAttribute("medics", medicRepository.findAll());
        return "patients/form_paziente";
    }

    /*  ROUTE: /nuovo-paziente
     *  METHOD: POST
     *  DESC: Inserimento nuovo paziente
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-paziente", method = RequestMethod.POST)
    public String insertNewPatient(
            @RequestParam(name = "nome", required = true) String firstname,
            @RequestParam(name = "cognome", required = true) String lastname,
            @RequestParam(name = "cf", required = true) String cf,
            @RequestParam(name = "cittaResidenza", required = true) String cityOfResidence,
            @RequestParam(name = "categoriaProblematica", required = true) String problemCategory,
            @RequestParam(name = "descrizioneProblematica", required = true) String problemDescription,
            @RequestParam(name = "dataNascita", required = true) String birthDate,
            @RequestParam(name = "medico", required = true) String medicID,
            @RequestParam(name = "pericoloso", defaultValue = "false") boolean dangerous
    ){
        birthDate = Utils.convertDateToSlash(birthDate);

        Patient patient = new Patient(medicRepository.findById(Long.parseLong(medicID)), firstname, lastname, cf, birthDate, cityOfResidence, problemCategory, problemDescription, dangerous);
        patientRepository.save(patient);

        return "redirect:/pazienti";
    }

    /*  ROUTE: /modifica-paziente/{idPaziente}
     *  METHOD: GET
     *  DESC: Form modifica paziente
     *  PARAMS: idPaziente è l'id del paziente
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/modifica-paziente/{idPaziente}", method = RequestMethod.GET)
    public String formEditPatient(
            @PathVariable(name = "idPaziente") long patientID,
            Model model
    ){
        Patient patient = patientRepository.findById(patientID);
        if(patient != null){

            model.addAttribute("active", patient.isConfirmed());
            System.out.println(patient.isConfirmed());
            model.addAttribute("patient", patient);
            model.addAttribute("medics", medicRepository.findAll());
            model.addAttribute("nuovo", false);
            return "patients/form_paziente";
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /modifica-paziente/{idPaziente}
     *  METHOD: dovrebbe essere PUT, ma i form HTML permettono solo GET e POST
     *  DESC: Modifica di un paziente
     *  PARAMS: idPaziente è l'id del paziente
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/modifica-paziente/{idPaziente}", method = RequestMethod.POST)
    public String editPatient(
            @PathVariable(name = "idPaziente") long patientID,
            @RequestParam(name = "nome", required = true) String firstname,
            @RequestParam(name = "cognome", required = true) String lastname,
            @RequestParam(name = "cf", required = true) String cf,
            @RequestParam(name = "cittaResidenza", required = true) String cityOfResidence,
            @RequestParam(name = "categoriaProblematica", required = true) String problemCategory,
            @RequestParam(name = "descrizioneProblematica", required = true) String problemDescription,
            @RequestParam(name = "dataNascita", required = true) String birthDate,
            @RequestParam(name = "medico", required = true) String medicID,
            @RequestParam(name = "pericoloso", defaultValue = "false") boolean dangerous
    ){
        birthDate = Utils.convertDateToSlash(birthDate);

        Patient patient = patientRepository.findById(patientID);
        if(patient != null){
            patient.setFirstname(firstname);
            patient.setLastname(lastname);
            patient.setCf(cf);
            patient.setCityOfResidence(cityOfResidence);
            patient.setProblemCategory(problemCategory);
            patient.setProblemDescription(problemDescription);
            patient.setBirthDate(birthDate);
            patient.setMedic(medicRepository.findById(Long.parseLong(medicID)));
            patient.setDangerous(dangerous);

            Request request = requestRepository.findByPatientPatientID(patientID);
            if(request!=null) {
                request.setActive(false);
                requestRepository.save(request);
            }

            patientRepository.save(patient);
            return "redirect:/paziente/" + patientID;
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /modifica-appuntamento/{idAppuntamento}
     *  METHOD: GET
     *  DESC: Form modifica appuntamento
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/modifica-appuntamento/{idAppuntamento}", method = RequestMethod.GET)
    public String formEditAppointment(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            Model model
    ){
        Appointment appointment = appointmentRepository.findById(appointmentID);
        if(appointment != null){
            model.addAttribute("appointment", appointment);
            model.addAttribute("nuovo", false);

            return "appointments/form_appuntamento";
        }
        else {
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /modifica-appuntamento/{idAppuntamento}
     *  METHOD: dovrebbe essere PUT, ma i form HTML permettono solo GET e POST
     *  DESC: Modifica di un appuntamento
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/modifica-appuntamento/{idAppuntamento}", method = RequestMethod.POST)
    public String editAppointment(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            @RequestParam(name = "medic", required = false) String medicID,
            @RequestParam(name = "patient", required = false) String patientID,
            @RequestParam(name = "clinic", required = true) String clinic,
            @RequestParam(name = "appointmentDate", required = true) String appointmentDate,
            Model model
    ){
        appointmentDate = Utils.convertDateToSlash(appointmentDate);

        Appointment appointment = appointmentRepository.findById(appointmentID);
        if(appointment != null){
            appointment.setClinic(clinic);
            appointment.setAppointmentDate(appointmentDate);
            appointmentRepository.save(appointment);
            return "redirect:/appuntamento/" + appointmentID;
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /elimina-appuntamento/{idAppuntamento}
     *  METHOD: dovrebbe essere DELETE, ma i form HTML permettono solo GET e POST
     *  DESC: Eliminazione di un appuntamento
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/elimina-appuntamento/{idAppuntamento}", method = RequestMethod.GET)
    public String deleteAppointment(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            Model model
    ){
        Appointment appointment = appointmentRepository.findById(appointmentID);
        if(appointment != null){
            appointment.setActive(false);
            appointmentRepository.save(appointment);

            return "redirect:/appuntamenti";
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /elimina-richiesta/{idRichiesta}
     *  METHOD: dovrebbe essere DELETE, ma i form HTML permettono solo GET e POST
     *  DESC: Eliminazione di una richiesta
     *  PARAMS: idRichiesta è l'id della richiesta
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/elimina-richiesta/{idRichiesta}")
    public String deleteRequest(
            @PathVariable(name = "idRichiesta") long requestID,
            Model model
    ){
        Request request = requestRepository.findById(requestID);
        if(request != null){
            request.setActive(false);
            requestRepository.save(request);
            return "redirect:/";
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /nuova-comunicazione/{idAppuntamento}
     *  METHOD: GET
     *  DESC: Form nuova comunicazione
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuova-comunicazione/{idAppuntamento}", method = RequestMethod.GET)
    public String formNewCommunication(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            Model model
    ){
        Appointment appointment = appointmentRepository.findById(appointmentID);
        if(appointment != null){
            model.addAttribute("appointment", appointment);
            return "communications/form_comunicazione";
        }else{
            return "redirect:/?error=true";
        }
    }

    /*  ROUTE: /nuova-comunicazione/{idAppuntamento}
     *  METHOD: POST
     *  DESC: Inserimento nuova comunicazione
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuova-comunicazione/{idAppuntamento}", method = RequestMethod.POST)
    public String insertNewCommunication(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            @RequestParam(name = "testoComunicazione", required = true) String communicationText,
            @RequestParam(name = "checkMedico", required = false) boolean medicCheck,
            @RequestParam(name = "checkPaziente", required = false) boolean patientCheck
    ){
        Appointment appointment = appointmentRepository.findById(appointmentID);
        if(appointment != null){
            Communication communication = new Communication(appointment, dtf.format(LocalDate.now()), communicationText, medicCheck, patientCheck);
            communicationRepository.save(communication);
            return "redirect:/appuntamento/" + appointmentID;
        }else{
            return "redirect:/?error=true";
        }
    }

}