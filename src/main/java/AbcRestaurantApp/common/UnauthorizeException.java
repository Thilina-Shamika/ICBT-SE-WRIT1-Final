package AbcRestaurantApp.common;

import org.springframework.http.HttpStatus;

public class UnauthorizeException extends Throwable {

    public UnauthorizeException() {
    }

    public UnauthorizeException(String message) {
        super();
    }

}
