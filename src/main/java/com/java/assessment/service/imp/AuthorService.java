package com.java.assessment.service.imp;

import com.java.assessment.component.Authorcomponent;
import com.java.assessment.component.Bookcomponent;
import com.java.assessment.entity.Author;
import com.java.assessment.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    protected Authorcomponent authorcomponent;

    @Transactional
    public Author getAuthorByID(int Id) {

        return authorRepository.findByid(Id);
    }
}
