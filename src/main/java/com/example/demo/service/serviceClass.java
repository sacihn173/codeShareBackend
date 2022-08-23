package com.example.demo.service;

import com.example.demo.models.commentStruct;
import com.example.demo.models.largestKey;
import com.example.demo.models.userQueryData;
import com.example.demo.repo.commentStructInterface;
import com.example.demo.repo.largestKeyInterface;
import com.example.demo.repo.userQueryDataInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceClass {

    @Autowired
    private userQueryDataInterface userQueryDataInterfaceObj;
    @Autowired
    private largestKeyInterface largestKeyInterfaceObj;
    @Autowired
    private commentStructInterface commentStructInterfaceObj;

    public String addComment(commentStruct obj, int id){
        userQueryData dataObj = userQueryDataInterfaceObj.findById(id).orElse(null);
        List<Integer> commentIds = dataObj.getComments();
        largestKey largestKeyObj = largestKeyInterfaceObj.findById(1).orElse(null);
        int commentId = largestKeyObj.getCommentValue();
        commentStruct newComment = new commentStruct();
        newComment.setCommentId(commentId);
        commentIds.add(commentId);
        dataObj.setComments(commentIds);
        userQueryDataInterfaceObj.save(dataObj);
        largestKeyObj.setCommentValue(commentId + 1);
        largestKeyInterfaceObj.save(largestKeyObj);

        newComment.setCommentCode(obj.getCommentCode());
        newComment.setCommentDesc(obj.getCommentDesc());
        newComment.setPostId(id);
        commentStructInterfaceObj.save(newComment);
        return "success";
    }

    public String deleteData(int id){
        userQueryDataInterfaceObj.deleteById(id);
        return "deleted";
    }

    public String deleteComment(int id, int commentId){
        userQueryData dataObj = userQueryDataInterfaceObj.findById(id).orElse(null);
        for(int i = 0; i < dataObj.getComments().size(); i++){
            if(dataObj.getComments().get(i) == commentId){
                dataObj.getComments().remove(i);
                userQueryDataInterfaceObj.save(dataObj);
                break;
            }
        }
        commentStructInterfaceObj.deleteById(commentId);
        return "success";
    }

    public String updateComment(commentStruct obj, int id){
        commentStruct comment = commentStructInterfaceObj.findById(obj.getCommentId()).orElse(null);
        comment.setCommentCode(obj.getCommentCode());
        comment.setCommentDesc(obj.getCommentDesc());
        commentStructInterfaceObj.save(comment);
        return "success";
    }

    public List<commentStruct> returnComments(int id){
        List<commentStruct> result = new ArrayList<>();
        userQueryData posts = userQueryDataInterfaceObj.findById(id).orElse(null);
        for(int i = 0; i < posts.getComments().size(); i++){
            result.add(commentStructInterfaceObj.findById(posts.getComments().get(i)).orElse(null));
        }
        return result;
    }

    public String saveUserQueryData(userQueryData obj){
        List<Integer> list = new ArrayList<>();
        largestKey lastId = largestKeyInterfaceObj.findById(1).orElse(null);
        int val = lastId.getValue();
        userQueryData newObj = new userQueryData();
        newObj.setId(val);

        lastId.setValue(val + 1);
        largestKeyInterfaceObj.save(lastId);

        newObj.setUserDesc(obj.getUserDesc());
        newObj.setComments(list);
        newObj.setGivenCode(obj.getGivenCode());
        newObj.setProblemLink(obj.getProblemLink());
        userQueryDataInterfaceObj.save(newObj);
        return String.valueOf(newObj.getId());
    }

    public Optional<userQueryData> getUserQueryData(int id){
        return  userQueryDataInterfaceObj.findById(id);
    }

    public String updateData(userQueryData obj){
        userQueryData existingObj = userQueryDataInterfaceObj.findById(obj.getId()).orElse(null);
        existingObj.setProblemLink(obj.getProblemLink());
        existingObj.setGivenCode(obj.getGivenCode());
        existingObj.setUserDesc(obj.getUserDesc());
        userQueryDataInterfaceObj.save(existingObj);
        return "Update Success !";
    }

}
