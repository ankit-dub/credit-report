package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @JsonProperty("Consumer Name Field1")
    private String name;
    @JsonProperty("Date of birth")
    private String dateofbirth;
    @JsonProperty("Date Reported")
    private String date;
    private String date_Reported;
    private String id_Number;
    private String id_Type;
    public Customer(String date_Reported,String id_Number,String id_Type){
        this.date_Reported=date_Reported;
        this.id_Number=id_Number;
        this.id_Type=id_Type;
    }
}
