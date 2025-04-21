package tobyspring.hellospring;

public class ObjectFactory {
    //ObjectFactory 클래스의 이점 -> 예를 들어, exRateProvider를 변경하고 싶을 때, 바로 접근하여 변경이 가능하다. + 관심사 분리

    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
