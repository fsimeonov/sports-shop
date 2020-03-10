package mk.ukim.finki.emt.sportsshop.service.Impl;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.ukim.finki.emt.sportsshop.models.Transactions;
import mk.ukim.finki.emt.sportsshop.models.dto.ChargeRequest;
import mk.ukim.finki.emt.sportsshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${STRIPE_SECRET_KEY}")
        private String stripePrivateKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = "sk_test_uOveI2SFAXbU4XnnSunDN8kN00Q2y172o5";
    }

    @Override
    public Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {


        Map<String,Object> chargeParam = new HashMap<>();
        chargeParam.put("amount",chargeRequest.getAmount());
        chargeParam.put("description",chargeRequest.getDescription());
        chargeParam.put("currency",chargeRequest.getCurrency());
        chargeParam.put("source",chargeRequest.getStripeToken());

        return Charge.create(chargeParam);
    }

}
