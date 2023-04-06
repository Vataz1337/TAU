import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PaymentProcessorAPI {

    private Map<String, String> paymentResponses = new HashMap<>();

    public PaymentProcessorAPI() {
        paymentResponses.put("success", "{\"status\":\"success\",\"message\":\"Payment processed successfully.\"}");
        paymentResponses.put("failure", "{\"status\":\"failure\",\"message\":\"Payment failed. Please try again.\"}");
        paymentResponses.put("pending", "{\"status\":\"pending\",\"message\":\"Payment is pending.\"}");
    }

    @GetMapping("/process-payment")
    public ResponseEntity<String> processPayment() {
        // Here, we would implement the actual payment processing logic
        // But since we're mocking the API, we'll just return a random response
        int randomIndex = (int) (Math.random() * paymentResponses.size());
        String randomResponse = paymentResponses.values().toArray()[randomIndex].toString();
        return new ResponseEntity<String>(randomResponse, HttpStatus.OK);
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessorAPI.class, args);
    }
}
