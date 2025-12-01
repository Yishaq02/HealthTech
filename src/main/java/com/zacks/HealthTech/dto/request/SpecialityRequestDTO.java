package com.zacks.HealthTech.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SpecialityRequestDTO(

                @NotBlank(message = "Name is required") String name,
                @NotBlank(message = "Description is required") String description

) {

}
