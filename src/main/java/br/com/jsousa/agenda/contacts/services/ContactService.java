package br.com.jsousa.agenda.contacts.services;

import br.com.jsousa.agenda.contacts.dto.ContactWithUserDTO;
import br.com.jsousa.agenda.contacts.repositories.IContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
