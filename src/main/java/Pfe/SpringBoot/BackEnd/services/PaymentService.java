package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class PaymentService {
private static PaymentRepository paymentRepository;
}
