package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalAPIService {
    public NGHostResponseDTO getDomainAvailablity(String domain) {

        String url = "https://api.ote-godaddy.com/v1/domains/available?domain=donout.com";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new NGHostResponseDTO(null);
    }
}
