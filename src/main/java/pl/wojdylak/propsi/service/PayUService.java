package pl.wojdylak.propsi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.*;
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

    private final String NOTIFY_URL = "https://5c46-5-173-48-82.eu.ngrok.io/api/payu/notify";
    private final String CUSTOMER_IP = "127.0.0.1";
    private final String CURRENCY_CODE = "PLN";
    private final String CONTINUE_URL = "https://www.onet.pl";

    public PayUService(PayUClient payUClient, UserService userService, OwnerService ownerService) {
        this.payUClient = payUClient;
        this.userService = userService;
        this.ownerService = ownerService;
    }

    private void authorize(String payUClientId, String payUClientSecret, Long ownerId) {
        PayUTokenResponse payUTokenResponse = payUClient.authorizeClient(payUClientId, payUClientSecret);
        this.ownerService.updatePayUToken(payUTokenResponse.getAccess_token(), payUTokenResponse.getExpires_in(), ownerId);
    }

    public PayUAddOrderResponse addOrder(Bill bill) {
        Rental rental = bill.getRental();
        Tenant tenant = rental.getTenant();
        Owner owner = rental.getPremises().getProperty().getOwner();
        Optional<User> currentUser = userService.getCurrentUser();
        User user = new User();
        if (currentUser.isPresent()) {
            user = currentUser.get();
        } else {
            //TODO; add exception
            //   throw new Exception("not user found");
        }

        if (owner.getPayUClientId() == null || owner.getPayUClientSecret() == null) {
            //TODO: exception - no payU account for this owner
            System.out.println("no payU account for this owner");
            return null;
        }
        if (owner.getPayUAccessToken() == null || owner.getPayUAccessTokenExpiration().isAfter(Instant.now())) {
            authorize(owner.getPayUClientId(), owner.getPayUClientSecret(), owner.getId());
        }

        String orderDescription = tenant.getName() + " monthly bill for " + LocalDate.ofInstant(bill.getDate(), ZoneId.of("Europe/Paris")).getMonth().name();
        PayUOrderRequest payUOrderRequest = new PayUOrderRequest(NOTIFY_URL, CUSTOMER_IP, owner.getPayUClientId(), orderDescription, CURRENCY_CODE, convertBigDecimalToString(bill.getTotalPrice()), CONTINUE_URL, bill.getId().toString());
        PayUOrderRequest.Buyer buyer = new PayUOrderRequest.Buyer(user.getEmail(), user.getFirstName(), user.getLastName());
        PayUOrderRequest.Product product = new PayUOrderRequest.Product("Bill", convertBigDecimalToString(bill.getTotalPrice()), "1");
        payUOrderRequest.setBuyer(buyer);
        payUOrderRequest.setProducts(Collections.singletonList(product));
        PayUAddOrderResponse payUAddOrderResponse = null;
        try {
            payUAddOrderResponse = this.payUClient.addOrder(payUOrderRequest, owner.getPayUAccessToken());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(payUOrderRequest);
        System.out.println(payUAddOrderResponse);

        return payUAddOrderResponse;
    }

    private String convertBigDecimalToString(BigDecimal number){
      return number.multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.UP).toString();
    }

}
