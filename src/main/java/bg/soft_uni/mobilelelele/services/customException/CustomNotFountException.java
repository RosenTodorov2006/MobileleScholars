package bg.soft_uni.mobilelelele.services.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomNotFountException extends RuntimeException{
    public CustomNotFountException(String message) {
        super(message);
    }
}
