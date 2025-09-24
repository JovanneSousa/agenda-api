package br.com.jsousa.agenda.contacts.controller;

import br.com.jsousa.agenda.contacts.dto.ContactWithUserDTO;
import br.com.jsousa.agenda.contacts.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ContactController.java
 * <p>
 * Descrição da classe: [adicione aqui uma breve descrição]
 *
 * @author jovan
 * @version 1.0
 * @since 9/22/2025
 */
@RestController
@RequestMapping(path = "/users/{userId}/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<ContactWithUserDTO> getUserContacts(@PathVariable String userId) {
        return this.contactService.getContactByUserId(userId);
    }
}
