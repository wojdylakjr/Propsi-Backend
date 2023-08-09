package pl.wojdylak.propsi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.*;
import pl.wojdylak.propsi.model.dto.UserDto;
import pl.wojdylak.propsi.model.payu.PayUAddOrderResponse;
import pl.wojdylak.propsi.model.payu.PayUOrderRequest;
import pl.wojdylak.propsi.model.payu.PayUTokenResponse;
import pl.wojdylak.propsi.webclient.payu.PayUClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;

@Service
public class PayUService {
    private final PayUClient payUClient;
    private final UserService userService;
    private final OwnerService ownerService;

    private final String NGROK_URL = "https://201f-78-8-49-114.eu.ngrok.io";
    private final String NOTIFY_URL = NGROK_URL + "/api/payu/notify";
    private final String CUSTOMER_IP = "127.0.0.1";
    private final String CURRENCY_CODE = "PLN";
    private final String CONTINUE_URL = "https://www.onet.pl";

    public PayUService(PayUClient payUClient, UserService userService, OwnerService ownerService) {
        this.payUClient = payUClient;
        this.userService = userService;
        this.ownerService = ownerService;
    }

    private void authenticate(String payUClientId, String payUClientSecret, Long ownerId) {
        PayUTokenResponse payUTokenResponse = payUClient.authorizeClient(payUClientId, payUClientSecret);
        this.ownerService.updatePayUToken(payUTokenResponse.getAccess_token(), payUTokenResponse.getExpires_in(), ownerId);
    }

    public PayUAddOrderResponse addOrder(Bill bill) throws Exception {
        Rental rental = bill.getRental();
        Owner owner = rental.getPremises().getProperty().getOwner();
        Optional<UserDto> currentUser = userService.getCurrentUser();

        if (currentUser.isEmpty()) {
            throw new Exception("User not found");
        }
        if (owner.getPayUClientId() == null || owner.getPayUClientSecret() == null) {
            throw new Exception("no payU account for this owner");
        }
        if (owner.getPayUAccessToken() == null || owner.getPayUAccessTokenExpiration().isAfter(Instant.now())) {
            authenticate(owner.getPayUClientId(), owner.getPayUClientSecret(), owner.getId());
        }
        UserDto user = currentUser.get();
        String orderDescription = bill.getRental().getTenant().getName() + " monthly bill for " + LocalDate.ofInstant(bill.getDate(), ZoneId.of("Europe/Paris")).getMonth().name();
        PayUOrderRequest payUOrderRequestObject = new PayUOrderRequest(NOTIFY_URL, CUSTOMER_IP, owner.getPayUClientId(), orderDescription, CURRENCY_CODE, convertBigDecimalToString(bill.getTotalPrice()), CONTINUE_URL, bill.getId().toString());
        PayUOrderRequest.Buyer buyer = new PayUOrderRequest.Buyer(user.getEmail(), user.getFirstName(), user.getLastName());
        PayUOrderRequest.Product product = new PayUOrderRequest.Product("Bill", convertBigDecimalToString(bill.getTotalPrice()), "1");
        payUOrderRequestObject.setBuyer(buyer);
        payUOrderRequestObject.setProducts(Collections.singletonList(product));
        PayUAddOrderResponse payUAddOrderResponse = null;
        try {
            payUAddOrderResponse = this.payUClient.addOrder(payUOrderRequestObject, owner.getPayUAccessToken());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(payUOrderRequestObject);
        System.out.println(payUAddOrderResponse);

        return payUAddOrderResponse;
    }

    private String convertBigDecimalToString(BigDecimal number) {
        return number.multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.UP).toString();
    }

}
