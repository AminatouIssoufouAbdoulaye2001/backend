package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.repositories.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ServiceService {
private static ServiceRepository serviceRepository;
}
