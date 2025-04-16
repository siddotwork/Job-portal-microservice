package com.acc.app.companyms.company.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;
}
