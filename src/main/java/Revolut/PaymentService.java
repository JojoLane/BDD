package Revolut;

public class PaymentService {
    private String serviceName;

    private boolean requestStatus = true;



    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public void sufficient(){
        this.requestStatus = true;
    }

    public void insufficient(){
        this.requestStatus = false;
    }

    public boolean isRequestStatus(){
        return requestStatus;
    }

    public void isRequestSuccessful(double accBal, double requestAmount){
        if(accBal >= requestAmount){
            sufficient();
        }
        insufficient();
    }


}
