package tobyspring.hellospring.payment;

import static java.lang.String.valueOf;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    void createPrepared() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(
                1L,"USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock)
        );

        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }
}
