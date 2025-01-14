package com.itsue.controllers.admins;

import com.itsue.commons.configs.ConfigInfoService;
import com.itsue.commons.configs.ConfigSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigSaveService saveService;
    private final ConfigInfoService infoService;
    private String code = "siteConfig";

    @GetMapping
    public String config(Model model){
        commonProcess(model);
        ConfigForm configForm = infoService.get(code, ConfigForm.class);

        if (configForm != null) {
            log.info(configForm.toString());
        }
        model.addAttribute("configForm", configForm);
        return "admin/config";
    }

    @PostMapping
    public String configPs(ConfigForm configForm, Errors errors, Model model){
        commonProcess(model);

        saveService.save(code, configForm);

        return "admin/config";
    }

    private void commonProcess(Model model){
        String title = "사이트 설정";
        String menuCode = "config";
        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);
        model.addAttribute("menuCode", menuCode);
    }
}
