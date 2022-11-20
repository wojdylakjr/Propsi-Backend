package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.service.BillService;
import pl.wojdylak.propsi.model.payu.PayUPaymentNotification;

@RestController
@RequestMapping("/api")
public class PayUResource {
    private final BillService billService;

    public PayUResource(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/payu/notify")
//    @ResponseStatus(HttpStatus.OK)
    public HttpStatus addPaymentResponse(@RequestBody PayUPaymentNotification paymentNotification) {
        if(paymentNotification.getOrder().getStatus().equals("COMPLETED")){
            billService.addPaymentResponse(paymentNotification);
            return HttpStatus.OK;
        }
        return HttpStatus.CONTINUE;
    }
}
