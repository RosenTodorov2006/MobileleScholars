package bg.soft_uni.mobilelelele.config;

import org.springframework.stereotype.Component;

@Component
public class ExRateConfig {
    private final String key="https://openexchangerates.org/api/latest.json?app_id=f5e72c848e2f416bbd7c3f42b879f226";
    private final String base="USD";

    public String getBase() {
        return base;
    }

    public String getKey() {
        return key;
    }
}
