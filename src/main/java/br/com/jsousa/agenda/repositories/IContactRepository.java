package br.com.jsousa.agenda.repositories;

import br.com.jsousa.agenda.auth.dto.ContactWithUserDTO;
import br.com.jsousa.agenda.domain.contacts.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * IContactRepository.java
 * <p>
 * Descrição da classe: [adicione aqui uma breve descrição]
 *
 * @author jovan
 * @version 1.0
 * @since 9/22/2025
 */
public interface IContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT new br.com.jsousa.agenda.auth.dto.ContactWithUserDTO(" +
            "c.id, c.name, c.email, c.phone, u.id, u.username, u.email) " +
            "FROM Contact c JOIN c.user u " +
            "WHERE u.id = :userId")
    List<ContactWithUserDTO> findContactsWithUserByUserId(@Param("userId") String userId);
}
