package tobyspring.hellospring.payment;

import java.math.BigDecimal;

public class ExRateProviderStub implements ExRateProvider {

    // 해당 TestStub을 도입함으로써, PaymentService의 prepare는, PaymentService에 주입된(의존하고 있는)
    // 다른 환율 제공 오브젝트로부터 환율을 가져올 수 있음을 테스트 할 수있으며,
    // 가져온 환율이 올바르게 계산되었는지 또한 테스트를 할 수 있게되었다.
    private BigDecimal exRate;

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public ExRateProviderStub(BigDecimal exRate) {
        this.exRate = exRate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        return exRate;
    }
}
