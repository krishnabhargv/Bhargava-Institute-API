package com.bhargava.institute.service;

import com.bhargava.institute.domain.ContactMessage;
import com.bhargava.institute.dto.Dtos.ContactRequest;
import com.bhargava.institute.dto.Dtos.ContactResponse;
import com.bhargava.institute.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

    private final ContactMessageRepository repository;

    public ContactService(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ContactResponse submit(ContactRequest request) {
        ContactMessage m = new ContactMessage();
        m.setName(request.name().trim());
        m.setEmail(request.email().trim());
        m.setMessage(request.message().trim());
        ContactMessage saved = repository.save(m);
        return new ContactResponse(saved.getId(), saved.getCreatedAt());
    }
}
