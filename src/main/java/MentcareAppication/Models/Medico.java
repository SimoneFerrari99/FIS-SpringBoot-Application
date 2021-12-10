package MentcareAppication.Models;

import javax.persistence.*;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "ID_MEDICO")
    private long id;

}
