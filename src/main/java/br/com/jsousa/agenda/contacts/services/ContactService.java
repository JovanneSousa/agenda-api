package br.com.jsousa.agenda.contacts.services;

import br.com.jsousa.agenda.contacts.domain.Contact;
import br.com.jsousa.agenda.contacts.dto.ContactWithUserDTO;
import br.com.jsousa.agenda.contacts.repositories.IContactRepository;
import br.com.jsousa.agenda.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ContactService.java
 * <p>
 * Descrição da classe: [adicione aqui uma breve descrição]
 *
 * @author jovan
 * @version 1.0
 * @since 9/22/2025
 */
@Service
@RequiredArgsConstructor
public class ContactService {

    private final IContactRepository contactRepository;

    public List<ContactWithUserDTO> getContactByUserId(String userId) {
        return contactRepository.findContactsWithUserByUserId(userId);
    }

    public Contact save(String contactName, String email, String phone, User user) {
        Optional<Contact> existingContact = contactRepository
                .findByNameAndUser(contactName, user);

        if (existingContact.isPresent()) {
            throw new RuntimeException("Contato já existe para este usuário");
        }

        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setUser(user);

        return contactRepository.save(contact);


    }

}
