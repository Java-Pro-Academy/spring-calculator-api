package academy.javapro.springcalculatorapi.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double add(double number1, double number2) {
        return number1 + number2;
    }

    public double divide(double number1, double number2) {
        if (number2 == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return number1 / number2;
    }
}
