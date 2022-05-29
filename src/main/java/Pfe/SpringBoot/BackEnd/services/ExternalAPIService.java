package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.dtos.Godaddy;
import Pfe.SpringBoot.BackEnd.dtos.GodaddyAvailableDomainsResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.entities.Domaine;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.repositories.DomainRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalAPIService {
    @Value(value = "${godaddy.baseURL}")
    private String godaddyBaseURL;

    @Value(value = "${godaddy.key}")
    private String godaddyAPIKey;

    @Value(value = "${godaddy.secret}")
    private String godaddySECRET;

    @Autowired
    DomainRepository domainRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public NGHostResponseDTO getDomainAvailability(String domain) throws NGHost400Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(godaddyBaseURL)
                .append("domains/available?domain=")
                .append(domain);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        NGHostResponseDTO res = new NGHostResponseDTO(null);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Godaddy> response = restTemplate.exchange(
                    sb.toString(), HttpMethod.GET, requestEntity, Godaddy.class);
            if (response.getStatusCodeValue() == 200) {
                Godaddy api = response.getBody();
                res.setPayload(api);
            } else {
                res.setSuccess(false);
                res.setMessage(" format ou nom de domain invalide");
            }
        } catch (Exception ex) {
            throw new NGHost400Exception("Impossible d'accèder à l'API Godaddy");
        }
        return res;
    }

    public NGHostResponseDTO getDomainsAvailabilities(List<String> domains) throws NGHost400Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(godaddyBaseURL)
                .append("domains/available");

        JSONArray array = new JSONArray();
        for (String domain : domains) {
            array.put(domain);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        NGHostResponseDTO res = new NGHostResponseDTO(null);

        HttpEntity<String> request = new HttpEntity<String>(array.toString(), headers);
        try {
            ResponseEntity<GodaddyAvailableDomainsResponseDTO> response =
                    restTemplate.postForEntity(
                            sb.toString()
                            , request,
                            GodaddyAvailableDomainsResponseDTO.class
                    );

            if (response.getStatusCodeValue() == 200) {
                List<String> domainsNames = new ArrayList<>();
                Iterable<Domaine> subscribedDomains = domainRepository.findAll();

                for (Domaine itDomaine : subscribedDomains) {
                    domainsNames.add(
                            itDomaine.getNom()
                    );
                }

                res.setPayload(response.getBody().getDomains());
                if (!domainsNames.isEmpty()) {
                    List<Godaddy> domainsList = new ArrayList<>();
                    for (Godaddy godaddy : response.getBody().getDomains()) {
                        if (!domainsNames.contains(godaddy.getDomain())) {
                            domainsList.add(godaddy);
                        }
                    }

                    res.setPayload(domainsList);

                }
                res.setSuccess(true);
            } else {
                res.setSuccess(false);
                res.setMessage(" format ou nom de domain invalide");
            }
        } catch (Exception ex) {
            throw new NGHost400Exception("Impossible d'accèder à l'API Godaddy");
        }
        return res;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "sso-key " + godaddyAPIKey + ":" + godaddySECRET);
        return headers;
    }
}
