package tech.gtech.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="tb_users")
public class UserModel {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID userId;
    private String name;
    private String email;

}
