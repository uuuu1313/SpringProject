package com.itsue.controllers.admins;

import lombok.Data;

@Data
public class ConfigForm {
    private String siteTitle;
    private String siteDescription;
    private String cssJsVersion;
    private String joinTerms;
}
