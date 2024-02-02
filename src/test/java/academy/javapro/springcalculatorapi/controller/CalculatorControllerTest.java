package academy.javapro.springcalculatorapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import academy.javapro.springcalculatorapi.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    public void testAdd() throws Exception {
        double number1 = 10;
        double number2 = 5;
        double expectedResult = 15;

        given(calculatorService.add(number1, number2)).willReturn(expectedResult);

        mockMvc.perform(get("/add")
                        .param("number1", String.valueOf(number1))
                        .param("number2", String.valueOf(number2)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedResult)));
    }

    @Test
    public void testDivide() throws Exception {
        double number1 = 10;
        double number2 = 2;
        double expectedResult = 5;

        given(calculatorService.divide(number1, number2)).willReturn(expectedResult);

        mockMvc.perform(get("/divide")
                        .param("number1", String.valueOf(number1))
                        .param("number2", String.valueOf(number2)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedResult)));
    }

    @Test
    public void testDivideByZero() throws Exception {
        double number1 = 10;
        double number2 = 0;

        given(calculatorService.divide(number1, number2)).willThrow(new ArithmeticException("Division by zero is not allowed."));

        mockMvc.perform(get("/divide")
                        .param("number1", String.valueOf(number1))
                        .param("number2", String.valueOf(number2)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Division by zero is not allowed."));
    }
}