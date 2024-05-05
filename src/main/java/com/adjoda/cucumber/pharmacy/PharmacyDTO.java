package com.adjoda.cucumber.pharmacy;

import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String address;

    private Double lat;

    private Double lng;

}
