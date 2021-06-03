package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {
    @JsonProperty("Enquiring Member Short Name")
    private String enquiringMember;
    @JsonProperty("Enquiry Amount")
    private String enquiryAmount;
    @JsonProperty("Enquiry Purpose")
    private String enquiryPurpose;
    @JsonProperty("Date of Enquiry")
    private String dateEnquiry;
}
