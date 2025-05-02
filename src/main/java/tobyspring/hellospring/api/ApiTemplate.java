package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExRateExtractor exRateExtractor;

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ErApiExRateExtractor();
    }

    //Default Callback (url만 전달하고, 템플릿이 가지고 있는 기본적인 콜백을 사용함)
    public BigDecimal getExRate(String url) {
        return this.getForExRate(url, this.apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        String response;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
