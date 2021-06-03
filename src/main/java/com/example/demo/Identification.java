package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Identification {
    @JsonProperty("ID Type")
    private String id_Type;
    @JsonProperty("ID Number")
    private String id_Number;
    @JsonProperty("Date Reported")
    private String date_Reported;
}
