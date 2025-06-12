package ch.noseryoung.restfoodsbackend22024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString(exclude = "password") // Sicherheitsmaßnahme: Passwort nicht in toString()
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank
    @Size(max = 50)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 8, max = 255) // Sicherheits-Tipp: Mindestlänge z.B. 8 Zeichen
    //sollte man generell nicht als Klartext speichern
    private String password;

    @Column(length = 20, nullable = false)
    @NotBlank
    @Size(max = 20)
    private String role;

    // equals & hashCode auf Basis von ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass() &&
                !(o instanceof HibernateProxy hibernateProxy && getClass() == hibernateProxy.getHibernateLazyInitializer().getPersistentClass()))
            return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
