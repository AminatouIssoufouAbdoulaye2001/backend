package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.dtos.GetServiceDTO;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.PostServiceDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.repositories.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    private static ServiceRepository serviceRepository;

    public NGHostResponseDTO create(PostServiceDTO dto) {

        Pfe.SpringBoot.BackEnd.entities.Service service = dto.getServiceFromDTO();
        service = serviceRepository.save(service);

        return new NGHostResponseDTO(new GetServiceDTO(service));
    }

    public NGHostResponseDTO update(GetServiceDTO dto) throws NGHost400Exception{

        Optional<Pfe.SpringBoot.BackEnd.entities.Service> optionalService = serviceRepository.findById(dto.getId());
        if (!optionalService.isPresent()) {
            throw new NGHost400Exception("Le service n'existe pas");
        }

        Pfe.SpringBoot.BackEnd.entities.Service service = optionalService.get();
        dto.getServiceDTO(service);

        serviceRepository.save(service);
        return new NGHostResponseDTO(dto);
    }

    public NGHostResponseDTO getAll() {
        List<GetServiceDTO> services =  new ArrayList<>();

        for (Pfe.SpringBoot.BackEnd.entities.Service service: serviceRepository.findAll()) {
            services.add(
              new GetServiceDTO(service)
            );
        }

        return new NGHostResponseDTO(services);
    }

    public NGHostResponseDTO delete(long id) throws NGHost400Exception{

        Optional<Pfe.SpringBoot.BackEnd.entities.Service> optionalService = serviceRepository.findById(id);
        if (!optionalService.isPresent()) {
            throw new NGHost400Exception("Le service n'existe pas");
        }
        GetServiceDTO dto = new GetServiceDTO(optionalService.get());

        return new NGHostResponseDTO(dto);
    }
}
