package com.post.harvust.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GrainQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cultivar_id")
    @JsonIgnoreProperties("grainQualities")
    private Cultivars cultivars;
    private int year;
    private float field_fissures;
    private float protein_content;
    private String grain_size;
    private float lipid_content;
}
