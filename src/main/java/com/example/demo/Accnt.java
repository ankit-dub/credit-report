package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accnt {
    private String name;
    private String product;
    private String branch;
    private String code;
    private String mode;
    private String customerId;
}
