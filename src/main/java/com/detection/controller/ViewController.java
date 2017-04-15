/*
 * File Name：ReportViewController.java
 *
 * Copyrighe：copyright@2017 www.ggkbigdata.com. All Rights Reserved
 *
 * Create Time: 2017年2月22日 下午2:00:35
 */
package com.detection.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.detection.model.owner.OwnerUnitRepository;
import com.detection.services.AuthenticationService;
import com.detection.services.OwnerUnitService;
import com.detection.services.UserControlService;

@Controller
public class ViewController {

    @Autowired
    private UserControlService userControlService;
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private OwnerUnitService ownerUnitService;

    @RequestMapping(value = { "/", "/loginPage" }, method = RequestMethod.GET)
    public String index() {
        return "login";
    }
    @RequestMapping(value = { "downloadExcel" }, method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletResponse response,HttpServletRequest request) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        File outFile = new File(ownerUnitService.getDataList());

        int permittedRole = 1;
        if (authService.isLoggedin(request) && authService.isPermitted(request, permittedRole)) {
            FileSystemResource file = new FileSystemResource(outFile);
            if(file.exists()){
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"",
                    URLEncoder.encode("mail.xls", "UTF-8")));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(file.getInputStream()));
            }
        }
        
        return ResponseEntity.notFound().headers(headers).build();
    }
    
    @RequestMapping(value = { "/main" }, method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request) {

        String result = "main";
        int permittedRole = 1;
        if (!authService.isLoggedin(request)) {
            
            result = "redirect:/";
        } else if (!authService.isPermitted(request, permittedRole)) {
            result = "redirect:nopermissions";
        }
        ModelAndView mv = new ModelAndView(result);
        mv.addObject("userName", (String)request.getSession().getAttribute("userName"));
        return mv;
    }
    
    @RequestMapping({ "/404" })
    public String pageNotFound() {
        return "errors/404";
    }

    @RequestMapping("/505")
    public String visitError() {
        return "errors/505";
    }

    @RequestMapping("/nopermissions")
    public String NoPermisssions() {
        return "errors/nopermissions";
    }
}
