package MentcareAppication.Models;

import javax.persistence.*;

@Entity
public class Paziente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "ID_PAZIENTE")
    private long id;

}
