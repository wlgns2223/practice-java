package org.example.commerce.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CLOTHE")
public class Clothe extends Item {

    private String size;
    private String gender;
}
