package com.java.assessment.component;



import com.java.assessment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bookcomponent {

    @Autowired
    private BookRepository bookRepository;

    public boolean checkexisting(Long id ){
        if (bookRepository.existsById(id) == true){
            return true;
        }
        return false;
    }
}
