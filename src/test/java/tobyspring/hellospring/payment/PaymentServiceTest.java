package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        //given
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(500)));

        //when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //then
        // 1. 환율 정보를 가져온다, (환율은 항시 변동되기에 notnull 테스트로 진행)
        assertThat(payment.getExRate()).isEqualTo(valueOf(500));
        // 2. 원화 환산 금액 계산
        assertThat(payment.getConvertedAmount())
                .isEqualTo(valueOf(5_000));
        // 3. 원화 환산금액 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}