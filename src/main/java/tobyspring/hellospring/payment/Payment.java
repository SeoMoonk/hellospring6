package tobyspring.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Payment {
    private Long orderId;                       //주문 번호
    private String currency;                    //통화 종류
    private BigDecimal foreignCurrencyAmount;   //외화 기준 가격
    private BigDecimal exRate;                  //적용 환율
    private BigDecimal convertedAmount;         //원화 환산 금액
    private LocalDateTime validUntil;           //원화 환산 금액 유효 시간


    //NOTE: 생성자 싧수를 줄이기 위한 빌더 패턴이나 Lombok
    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
                   BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    //**static 달아줘야 함**
    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount,
                                         BigDecimal exRate, LocalDateTime now) {
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = now.plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }
}
