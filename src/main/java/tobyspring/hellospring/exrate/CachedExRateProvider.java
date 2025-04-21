package tobyspring.hellospring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider{
    //Decorator 디자인 패턴 (코드를 직업수정하지 않고 부가적인 기능을 동적으로 추가할 수 있는 패턴)
    //CachedExRateProvider 클래스의 목적 : 환율 정보를 캐시에 저장하여 계속된 웹 API 호출 대신 대체하여 사용할 수 있도록 하기 위함.

    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if(cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache Updated!!");
        }
        return cachedExRate;
    }
}
