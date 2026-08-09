package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class CheckoutData {
    private final String firstName;
    private final String lastName;
    private final String postalCode;
    private final String expectedErrMsg;
}
