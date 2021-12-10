package MentcareAppication.Models;

import javax.persistence.*;

@Entity
public class Appuntamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "ID_APPUNTAMENTO")
    private long id;

}
