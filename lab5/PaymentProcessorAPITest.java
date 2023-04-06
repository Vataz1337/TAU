import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class PaymentProcessorAPITest {

    @MockBean
    private PaymentProcessorAPI paymentProcessorAPI;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentProcessorAPI).build();
    }

    @Test
    void testProcessPaymentSuccess() throws Exception {
        String successResponse = "{\"status\":\"success\",\"message\":\"Payment processed successfully.\"}";
        when(paymentProcessorAPI.processPayment()).thenReturn(ResponseEntity.ok(successResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/process-payment").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Payment processed successfully."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testProcessPaymentFailure() throws Exception {
        String failureResponse = "{\"status\":\"failure\",\"message\":\"Payment failed. Please try again.\"}";
        when(paymentProcessorAPI.processPayment()).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/process-payment").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("failure"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Payment failed. Please try again."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testProcessPaymentPending() throws Exception {
        String pendingResponse = "{\"status\":\"pending\",\"message\":\"Payment is pending.\"}";
        when(paymentProcessorAPI.processPayment()).thenReturn(ResponseEntity.ok(pendingResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/process-payment").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("pending"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Payment is pending."))
                .andDo(MockMvcResultHandlers.print());
    }
}
