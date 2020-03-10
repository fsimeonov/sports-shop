package mk.ukim.finki.emt.sportsshop.web;


import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.ukim.finki.emt.sportsshop.models.Transactions;
import mk.ukim.finki.emt.sportsshop.models.dto.ChargeRequest;
import mk.ukim.finki.emt.sportsshop.models.Product;
import mk.ukim.finki.emt.sportsshop.service.PaymentService;
import mk.ukim.finki.emt.sportsshop.service.ProductService;
import mk.ukim.finki.emt.sportsshop.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PaymentController {


    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey = "pk_test_5kfWz9M6LAxJBS8Sy0hKPr9V004M2OCtYo";

    private ProductService productService;
    private PaymentService paymentService;
    private TransactionService transactionService;

    public PaymentController(ProductService productService, PaymentService paymentService, TransactionService transactionService) {
        this.productService = productService;
        this.paymentService = paymentService;
        this.transactionService = transactionService;
    }

    @RequestMapping("/checkout/{id}")
    public String checkout(@PathVariable("id") Long id, Model model){

        Product product = productService.getProductById(id);
        model.addAttribute("name", product.getName());
        model.addAttribute("amount", product.getPrice()); ///in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);

        return "checkout";
    }


    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException{

        chargeRequest.setDescription("EMT descrption for lab3");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id",charge.getId());
        model.addAttribute("status",charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balanceTransaction", charge.getBalanceTransaction());

        Transactions transactions = new Transactions();
        transactions.setId(charge.getId());
        transactions.setStatus(charge.getStatus());
        transactions.setChargeId(charge.getId());
        //transactions.setBalanceTransaction(charge.getBalanceTransaction());
        transactionService.addNewTransaction(transactions);
        return "result";
    }

    @ExceptionHandler({StripeException.class})
    public String errorHandler(Model model, StripeException ex){
        model.addAttribute("error",ex.getMessage());
        return "result";
    }


}
