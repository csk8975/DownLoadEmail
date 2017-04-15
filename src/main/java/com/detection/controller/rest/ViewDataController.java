package com.detection.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.detection.services.AuthenticationService;
import com.detection.services.OwnerUnitService;

@RestController
public class ViewDataController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private OwnerUnitService ownerUnitService;

    @RequestMapping(value = { "/getMailList" }, method = RequestMethod.GET)
    public JSONObject main(HttpServletRequest request) {

        int permittedRole = 1;
        if (!authService.isLoggedin(request)) {
            
            return null;
        } else if (!authService.isPermitted(request, permittedRole)) {
            return null;
        }

        return ownerUnitService.getJSONDataList();
    }
}
