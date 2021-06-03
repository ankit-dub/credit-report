package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @JsonProperty("Address Line1")
    private String address;
    @JsonProperty("PIN Code")
    private String pinCode;
    @JsonProperty("Date Reported")
    private String date_Reported;
}
