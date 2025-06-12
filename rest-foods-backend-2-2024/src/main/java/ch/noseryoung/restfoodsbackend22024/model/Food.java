package ch.noseryoung.restfoodsbackend22024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @NotBlank
    private String name;

    @Size(max = 255)
    @NotBlank
    private String description;

    @PositiveOrZero
    @NotBlank
    private double price;

    @Size(max = 100)
    @NotBlank
    private String category;
}
