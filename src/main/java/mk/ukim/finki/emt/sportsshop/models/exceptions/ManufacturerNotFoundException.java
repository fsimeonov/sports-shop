package mk.ukim.finki.emt.sportsshop.models.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Man not found")
public class ManufacturerNotFoundException extends RuntimeException {
}
