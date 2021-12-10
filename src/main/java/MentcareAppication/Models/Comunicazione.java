package MentcareAppication.Models;

import javax.persistence.*;

@Entity
public class Comunicazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "ID_COMUNICAZIONE")
    private long id;

}
