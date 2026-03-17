package com.surepay.qa.domains.users.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Setter
@ToString
public class Geo {
    private String lat;
    private String lng;
}