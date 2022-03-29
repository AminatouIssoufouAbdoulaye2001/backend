package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TicketService {
private static TicketRepository ticketRepository;
}
