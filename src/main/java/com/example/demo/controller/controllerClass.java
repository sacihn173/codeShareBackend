package com.example.demo.controller;

import com.example.demo.models.commentStruct;
import com.example.demo.models.userQueryData;
import com.example.demo.service.serviceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/")
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

    @PostMapping("/{id}/addcomment")
    public String addComment(@RequestBody commentStruct obj, @PathVariable("id") int id){
        return serviceClassObj.addComment(obj, id);
    }

    @GetMapping("/{id}/comments")
    public List<commentStruct> returnComments(@PathVariable("id") int id){
        return serviceClassObj.returnComments(id);
    }

    // optional this used so that null values are never passed
    @GetMapping("/{id}")
    public Optional<userQueryData> sentQueryData(@PathVariable("id") int id){
        return serviceClassObj.getUserQueryData(id);
    }

    @PostMapping("/updatepost")
    public String udpateData(@RequestBody userQueryData obj){
        return serviceClassObj.updateData(obj);
    }

    @PostMapping("/{id}/updatecomment")
    public String updateComment(@RequestBody commentStruct commentStructObj, @PathVariable("id") int id){
        return serviceClassObj.updateComment(commentStructObj, id);
    }

    @DeleteMapping("/deletecomment/{id}/{commentId}")
    public String deleteComment(@PathVariable("id") int id, @PathVariable("commentId") int commentId){
        return serviceClassObj.deleteComment(id, commentId);
    }

    @DeleteMapping ("/deletepost/{id}")
    public String deleteData(@PathVariable("id") int id){
        return serviceClassObj.deleteData(id);
    }


}
