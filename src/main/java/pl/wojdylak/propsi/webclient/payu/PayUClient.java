package pl.wojdylak.propsi.webclient.payu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.wojdylak.propsi.service.dto.payu.PayUAddOrderResponse;
import pl.wojdylak.propsi.service.dto.payu.PayUOrderRequest;
import pl.wojdylak.propsi.service.dto.payu.PayUTokenResponse;

@Component
public class PayUClient {
    private static final String PAYU_URL = "https://secure.snd.payu.com/";
    private RestTemplate restTemplate = new RestTemplate();

    public PayUTokenResponse authorizeClient(String clientId, String clientSecret){
        return restTemplate.getForObject(PAYU_URL+"pl/standard/user/oauth/authorize?grant_type=client_credentials&client_id={clientId}&client_secret={clientSecret}", PayUTokenResponse.class, clientId, clientSecret);
    }

    public PayUAddOrderResponse addOrder(PayUOrderRequest request, String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+accessToken);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(request);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest,headers);
        System.out.println(entity);
        System.out.println(entity.getBody());
        return restTemplate.postForObject(PAYU_URL + "api/v2_1/orders", entity, PayUAddOrderResponse.class);
    }
}
