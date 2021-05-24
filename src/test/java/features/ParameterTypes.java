package features;
import io.cucumber.java.ParameterType;
import Revolut.PaymentService;


public class ParameterTypes {
    @ParameterType("DebitCard|BankAccount")
    public PaymentService paymentService(String type){
        return new PaymentService(type);
    }
}
