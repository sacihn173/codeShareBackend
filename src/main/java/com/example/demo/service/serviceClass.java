package com.example.demo.service;

import com.example.demo.models.userQueryData;
import com.example.demo.repo.userQueryDataInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class serviceClass {

    @Autowired
    private userQueryDataInterface userQueryDataInterfaceObj;

    public String saveUserQueryData(userQueryData obj){
        userQueryDataInterfaceObj.save(obj);
        return "Success";
    }

    public Optional<userQueryData> getUserQueryData(int id){
        return  userQueryDataInterfaceObj.findById(id);
    }

    public String updateData(userQueryData obj){
        userQueryData existingObj = userQueryDataInterfaceObj.findById(obj.getId()).orElse(null);
        System.out.println(existingObj.getProblemLink());
        existingObj.setProblemLink(obj.getProblemLink());
        existingObj.setGivenCode(obj.getGivenCode());
        existingObj.setUserDesc(obj.getUserDesc());
        userQueryDataInterfaceObj.save(existingObj);
        return "Update Success !";
    }

    public String deleteData(userQueryData obj){
        userQueryDataInterfaceObj.deleteById(obj.getId());
        return "Delete Success !";
    }

}
