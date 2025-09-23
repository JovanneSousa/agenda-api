package br.com.jsousa.agenda.auth.dto;

/**
 * ContactWithUserDTO.java
 * <p>
 * Descrição da classe: [adicione aqui uma breve descrição]
 *
 * @author jovan
 * @version 1.0
 * @since 9/22/2025
 */
public record ContactWithUserDTO ( Long contactId,
                                   String contactName,
                                   String contactEmail,
                                   String contactPhone,
                                   String userId,
                                   String userName,
                                   String userEmail) {
}
