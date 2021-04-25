package am.itspace.task_master.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "surname is required")
    private String surname;
    @Email(message = "email is not valid")
    @NotEmpty(message = "email can not be null")
    private String email;
    @NotEmpty(message = "password can not be empty")
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserType userType = UserType.EMPLOYER;



}
