package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @JsonProperty("Reason Code 1")
    private String reasonCode;
    @JsonProperty("Score")
    private String score;
    @JsonProperty("Score Name")
    private String scoreName;
    @JsonProperty("Score Card Name")
    private String scoreCard;
    @JsonProperty("Score Date")
    private String scoreDate;
}
