package com.example.demo.controller;

import com.example.demo.models.userQueryData;
import com.example.demo.service.serviceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin(origins = "")

//@RequestMapping("/")
@RestController
public class controllerClass {

    @Autowired
    private serviceClass serviceClassObj;

    public controllerClass(serviceClass service){
        serviceClassObj = service;
    }

    @PostMapping("/create")
    public String addQuery(@RequestBody userQueryData obj){
        return serviceClassObj.saveUserQueryData(obj);
    }

    // optional this used so that null values are never passed
    @GetMapping("/{id}")
    public Optional<userQueryData> sentQueryData(@PathVariable("id") int id){
        return serviceClassObj.getUserQueryData(id);
    }

    @PostMapping("/update")
    public String udpateData(@RequestBody userQueryData obj){
        return serviceClassObj.updateData(obj);
    }

    @PostMapping("/delete")
    public String deleteData(@RequestBody userQueryData obj){
        return serviceClassObj.deleteData(obj);
    }


}
