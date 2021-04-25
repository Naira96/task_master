package am.itspace.task_master.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private Date startDate;

    private Date deadline;
    @OneToMany
    private List<User> users;
    @ManyToOne
    private Project project;

    private double logTime;

    @Enumerated(value = EnumType.STRING)
    private Status status;


}
