package mk.ukim.finki.emt.sportsshop.service;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.ukim.finki.emt.sportsshop.models.Transactions;
import mk.ukim.finki.emt.sportsshop.models.dto.ChargeRequest;

public interface PaymentService {

    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException, APIConnectionException,
            CardException, APIException;


}
