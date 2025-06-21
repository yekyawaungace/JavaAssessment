package com.java.assessment.component;

import com.java.assessment.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Authorcomponent {

    @Autowired
    private AuthorRepository authorRepository;

    public boolean checkexisting(int id ){
        if (authorRepository.existsById(id) == true){
            return true;
        }
        return false;
    }
}
