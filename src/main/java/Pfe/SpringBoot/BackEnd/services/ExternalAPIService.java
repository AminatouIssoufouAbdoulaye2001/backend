package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.dtos.Godaddy;
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

        String url = "https://api.ote-godaddy.com/v1/domains/available?domain=" + domain;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "sso-key 3mM44UcBKj1Fs8_GFVHAqD8JvP5uJZ2XjRcU3:RuJJcKqduDaWrEquQdCCRZ");
        NGHostResponseDTO res =  new NGHostResponseDTO(null);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<Godaddy> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, Godaddy.class);
            Godaddy api = response.getBody();
            res.setPayload(api);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
