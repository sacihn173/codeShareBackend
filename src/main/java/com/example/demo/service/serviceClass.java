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

    public commentStruct addComment(commentStruct obj, int id){

        largestKey largestKeyObj = largestKeyInterfaceObj.findById(1).orElse(null);
        int commentId = largestKeyObj.getCommentValue();
        commentStruct newComment = new commentStruct();
        newComment.setCommentId(commentId);
        largestKeyObj.setCommentValue(commentId + 1);
        largestKeyInterfaceObj.save(largestKeyObj);

        newComment.setCommentCode(obj.getCommentCode());
        newComment.setCommentDesc(obj.getCommentDesc());
        newComment.setPostId(id);
        commentStructInterfaceObj.save(newComment);
        return newComment;
    }

    public String deleteData(int id){
        userQueryDataInterfaceObj.deleteById(id);
        // delete all comments bonded to this postId
        List<commentStruct> comments = commentStructInterfaceObj.findByPostId(id);
        for(int i = 0; i < comments.size(); i++){
            commentStructInterfaceObj.deleteById(comments.get(i).getCommentId());
        }
        return "deleted";
    }

    public String deleteComment(int id, int commentId){
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
        List<commentStruct> commentFind = commentStructInterfaceObj.findByPostId(id);
        return commentFind;
    }

    public String saveUserQueryData(userQueryData obj){
        largestKey lastId = largestKeyInterfaceObj.findById(1).orElse(null);
        int val = lastId.getValue();
        userQueryData newObj = new userQueryData();
        newObj.setId(val);

        lastId.setValue(val + 1);
        largestKeyInterfaceObj.save(lastId);

        newObj.setUserDesc(obj.getUserDesc());
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
