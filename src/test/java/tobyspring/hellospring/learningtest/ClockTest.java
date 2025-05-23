package tobyspring.hellospring.learningtest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {
    // Clock을 이용해서 LocalDatetTime.now?
    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt3 = LocalDateTime.now(clock);
        LocalDateTime dt4 = LocalDateTime.now(clock);

        assertThat(dt4).isAfter(dt3);
    }

    // Clock을 Test에서 사용할 때 내가 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
    @Test
    void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());       //고정된 시계(Fixed Clock)

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(dt2).isEqualTo(dt1);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }
}
