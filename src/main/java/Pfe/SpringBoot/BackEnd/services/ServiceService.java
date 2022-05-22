package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.configurations.jwt.JWTUtil;
import Pfe.SpringBoot.BackEnd.dtos.GetServiceDTO;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.PostServiceDTO;
import Pfe.SpringBoot.BackEnd.entities.Abonnement;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.repositories.AbonnementRepository;
import Pfe.SpringBoot.BackEnd.repositories.ServiceRepository;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    AbonnementRepository AbonRepository;

    @Autowired
    UserRepository userRepository;

    private static final String TOKEN_PREFIX = "Bearer ";

    public NGHostResponseDTO create(PostServiceDTO dto) {

        Pfe.SpringBoot.BackEnd.entities.Service service = dto.getServiceFromDTO();
        service = serviceRepository.save(service);

        return new NGHostResponseDTO(new GetServiceDTO(service));
    }

    public NGHostResponseDTO update(long id, GetServiceDTO dto) throws NGHost400Exception {

        if (id != dto.getId()) {
            throw new NGHost400Exception(" Conflits des identifiants");
        }

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
        List<GetServiceDTO> services = new ArrayList<>();

        for (Pfe.SpringBoot.BackEnd.entities.Service service : serviceRepository.findAll()) {
            services.add(
                    new GetServiceDTO(service)
            );
        }

        return new NGHostResponseDTO(services);
    }

    public NGHostResponseDTO getOne(long serviceId) throws NGHost400Exception {
        Optional<Pfe.SpringBoot.BackEnd.entities.Service> optionalService = serviceRepository
                .findById(serviceId);
        if (!optionalService.isPresent()) {
            throw new NGHost400Exception(" Service non trouvéé");
        }

        return new NGHostResponseDTO(
                new GetServiceDTO(optionalService.get())
        );
    }

    public NGHostResponseDTO delete(long id) throws NGHost400Exception {

        Optional<Pfe.SpringBoot.BackEnd.entities.Service> optionalService = serviceRepository.findById(id);
        if (!optionalService.isPresent()) {
            throw new NGHost400Exception("Le service n'existe pas");
        }

        serviceRepository.delete(optionalService.get());
        GetServiceDTO dto = new GetServiceDTO(optionalService.get());

        return new NGHostResponseDTO(dto);
    }

    public NGHostResponseDTO subscribeToService(
            String token,
            List<Long> productsIds
    ) throws NGHost400Exception {
        String username = jwtUtil.getUsernameFromToken(
                token.replace(TOKEN_PREFIX, "")
        );

        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (!optionalUser.isPresent()) {
            throw new NGHost400Exception("L' utilisateur introuvable");
        }

        // abonnement
        Abonnement abon = new Abonnement();
        abon.setCustomerEmail(optionalUser.get().getEmail());
        List<Pfe.SpringBoot.BackEnd.entities.Service> services = new ArrayList<>();

        float totalPrice = 0;
        for (Long productId : productsIds) {
            Optional<Pfe.SpringBoot.BackEnd.entities.Service>
                    optionalService = serviceRepository.findById(productId);

            if (optionalService.isPresent()) {
                totalPrice += optionalService.get().getPrice();
                services.add(optionalService.get());
            }
        }

        abon.setPrice(totalPrice);
        abon.setServices(services);
        AbonRepository.save(abon);

        return new NGHostResponseDTO(null);
    }


    public NGHostResponseDTO getCustomerSubscribedProduct(String token)  throws NGHost400Exception{
        String username = jwtUtil.getUsernameFromToken(
                token.replace(TOKEN_PREFIX, "")
        );

        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (!optionalUser.isPresent()) {
            throw new NGHost400Exception("L' utilisateur introuvable");
        }

        List<GetServiceDTO> services = new ArrayList<>();

        for (Abonnement abon : AbonRepository.findByCustomerEmail(optionalUser.get().getEmail())) {
          for( Pfe.SpringBoot.BackEnd.entities.Service service: abon.getServices()) {
              services.add(
                      new GetServiceDTO(service)
              );
          }
        }

        return  new NGHostResponseDTO(services);
    }
}
