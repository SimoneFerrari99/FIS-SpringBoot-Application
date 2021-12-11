package MentcareAppication.Controllers;

import MentcareAppication.Models.Appointment;
import MentcareAppication.Models.Communication;
import MentcareAppication.Models.Medic;
import MentcareAppication.Models.Patient;
import MentcareAppication.Repositories.AppointmentRepository;
import MentcareAppication.Repositories.CommunicationRepository;
import MentcareAppication.Repositories.MedicRepository;
import MentcareAppication.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    /* Classe eseguita all'avvio dell'applicazione.
     * Utilizzata per inserire alcuni dati d'esempio all'interno del Database. */
    @Component
    private static class fillRepositoriesWithFakeData {
        public fillRepositoriesWithFakeData(AppointmentRepository appointmentRepository, CommunicationRepository communicationRepository, MedicRepository medicRepository, PatientRepository patientRepository) throws ParseException {
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

            Appointment appointment1 = new Appointment(medic1, patient1, dtf.format(now), "Mestre");
            appointmentRepository.save(appointment1);
            communicationRepository.save(new Communication(appointment1, dtf.format(now), "Ti ricordiamo l'appuntamento di oggi!", false, true));
            communicationRepository.save(new Communication(appointment1, dtf.format(now), "Sarà presente anche il fratello del paziente, il quale vorrà porle delle domande.", true, false));

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

    /* ROUTE: /
    *  METHOD: GET
    *  DESC: Homepage dell'applicazione web.
    *  PARAMS: -
    *  ATTRIBUTES: -
    */
    @RequestMapping("/")
    public String home(Model model){

        List<Appointment> l = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAll()){
            if(a.getAppointmentDateToLocalDate().compareTo(LocalDate.now()) == 0){
                l.add(a);
            }
        }

        model.addAttribute("appointments", l);
        return "home";
    }

    /* ROUTE: /medici
     *  METHOD: GET
     *  DESC: Lista dei medici
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping("/medici")
    public String medics(Model model){
        return "medics/medici";
    }

    /* ROUTE: /pazienti
     *  METHOD: GET
     *  DESC: Lista dei pazienti
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping("/pazienti")
    public String patients(Model model){
        return "patients/pazienti";
    }

    /* ROUTE: /appuntamenti
     *  METHOD: GET
     *  DESC: Lista degli appuntamenti
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping("/appuntamenti")
    public String appointments(Model model){
        return "appointments/appuntamenti";
    }

    /* ROUTE: /medico/{idMedico}
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
        return "medics/medico";
    }

    /* ROUTE: /medico/{idPaziente}
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
        return "patients/paziente";
    }

    /* ROUTE: /appuntamento/{idAppuntamento}
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
        return "appointments/appuntamento";
    }

    /* ROUTE: /nuovo-appuntamento
     *  METHOD: GET
     *  DESC: Form nuovo appuntamento
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-appuntamento", method = RequestMethod.GET)
    public String formNewAppointment(Model model){
        return "appointments/form_appuntamento";
    }

    /* ROUTE: /nuovo-appuntamento
     *  METHOD: POST
     *  DESC: Inserimento nuovo appuntamento
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-appuntamento", method = RequestMethod.POST)
    public String insertNewAppointment(
            @RequestParam(name = "test") String test,
            Model model
    ){
        return "redirect:/appuntamenti";
    }

    /* ROUTE: /nuovo-paziente
     *  METHOD: GET
     *  DESC: Form nuovo paziente
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-paziente", method = RequestMethod.GET)
    public String formNewPatient(Model model){
        return "patients/form_paziente";
    }

    /* ROUTE: /nuovo-paziente
     *  METHOD: POST
     *  DESC: Inserimento nuovo paziente
     *  PARAMS: -
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuovo-paziente", method = RequestMethod.POST)
    public String insertNewPatient(
            @RequestParam(name = "test") String test,
            Model model
    ){
        return "redirect:/pazienti";
    }

    /* ROUTE: /modifica-paziente/{idPaziente}
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
        return "patients/form_paziente";
    }

    /* ROUTE: /modifica-paziente/{idPaziente}
     *  METHOD: POST
     *  DESC: Modifica di un paziente
     *  PARAMS: idPaziente è l'id del paziente
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/modifica-paziente/{idPaziente}", method = RequestMethod.PUT)
    public String editPatient(
            @PathVariable(name = "idPaziente") long patientID,
            @RequestParam(name = "test") String test,
            Model model
    ){
        return "redirect:/pazienti";
    }

    /* ROUTE: /modifica-appuntamento/{idAppuntamento}
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
        return "appointments/form_appuntamento";
    }

    /* ROUTE: /modifica-appuntamento/{idAppuntamento}
     *  METHOD: POST
     *  DESC: Modifica di un appuntamento
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/modifica-appuntamento/{idAppuntamento}", method = RequestMethod.PUT)
    public String editAppointment(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            @RequestParam(name = "test") String test,
            Model model
    ){
        return "redirect:/appuntamenti";
    }

    /* ROUTE: /elimina-appuntamento/{idAppuntamento}
     *  METHOD: DELETE
     *  DESC: Eliminazione di un appuntamento
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/elimina-appuntamento/{idAppuntamento}", method = RequestMethod.DELETE)
    public String deleteAppointment(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            Model model
    ){
        return "redirect:/appuntamenti";
    }

    /* ROUTE: /nuova-comunicazione/{idAppuntamento}
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
        return "communications/form_comunicazione";
    }

    /* ROUTE: /nuovo-paziente
     *  METHOD: POST
     *  DESC: Inserimento nuova comunicazione
     *  PARAMS: idAppuntamento è l'id dell'appuntamento
     *  ATTRIBUTES: -
     */
    @RequestMapping(value = "/nuova-comunicazione/{idAppuntamento}", method = RequestMethod.POST)
    public String insertNewCommunication(
            @PathVariable(name = "idAppuntamento") long appointmentID,
            @RequestParam(name = "test") String test,
            Model model
    ){
        return "redirect:/appuntamento/" + appointmentID;
    }

}