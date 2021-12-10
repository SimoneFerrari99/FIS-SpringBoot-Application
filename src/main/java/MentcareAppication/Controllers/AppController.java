package MentcareAppication.Controllers;

import MentcareAppication.Repositories.AppuntamentoRepository;
import MentcareAppication.Repositories.ComunicazioneRepository;
import MentcareAppication.Repositories.MedicoRepository;
import MentcareAppication.Repositories.PazienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @Autowired
    private AppuntamentoRepository appuntamentoRepository;
    private ComunicazioneRepository comunicazioneRepository;
    private MedicoRepository medicoRepository;
    private PazienteRepository pazienteRepository;

    @RequestMapping("/")
    public String index(){
        return "test";
    }


}