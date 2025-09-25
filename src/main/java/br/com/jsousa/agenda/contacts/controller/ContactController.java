package br.com.jsousa.agenda.contacts.controller;

import br.com.jsousa.agenda.auth.infra.security.CustomUserDetails;
import br.com.jsousa.agenda.contacts.domain.Contact;
import br.com.jsousa.agenda.contacts.dto.ContactWithUserDTO;
import br.com.jsousa.agenda.contacts.services.ContactService;
import br.com.jsousa.agenda.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(path = "/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<ContactWithUserDTO> getUserContacts(@AuthenticationPrincipal CustomUserDetails loggedUser) {
        String userId = loggedUser.getId();
        return contactService.getContactByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> createContact(
            @RequestBody ContactRequestDTO contactRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        try {
            User loggedUser = userDetails.getUser(); // pega o usuário logado

            Contact savedContact = contactService.save(
                    contactRequest.getName(),
                    contactRequest.getEmail(),
                    contactRequest.getPhone(),
                    loggedUser
            );

            return ResponseEntity.ok(savedContact);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
