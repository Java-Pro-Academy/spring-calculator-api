package academy.javapro.springcalculatorapi.controller;

import academy.javapro.springcalculatorapi.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private CalculatorService calculatorService;

    @Autowired
    public void setCalculatorService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public ResponseEntity<Double> add(@RequestParam(value = "number1") double number1,
                                      @RequestParam(value = "number2") double number2) {
        double result = calculatorService.add(number1, number2);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam(value = "number1") double number1,
                                    @RequestParam(value = "number2") double number2) {
        try {
            double result = calculatorService.divide(number1, number2);
            return ResponseEntity.ok(result);
        } catch (ArithmeticException e) {
            // Return a 400 Bad Request response if division by zero is attempted
            return ResponseEntity.badRequest().body("Division by zero is not allowed.");
        }
    }
}