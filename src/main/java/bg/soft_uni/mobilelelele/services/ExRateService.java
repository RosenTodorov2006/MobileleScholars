package bg.soft_uni.mobilelelele.services;

import bg.soft_uni.mobilelelele.config.ExRateConfig;
import bg.soft_uni.mobilelelele.models.dtos.ExRateDto;
import bg.soft_uni.mobilelelele.models.entities.ExRate;
import bg.soft_uni.mobilelelele.repositories.ExRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExRateService {
    private final RestClient restClient;
    private final ExRateConfig exRateConfig;
    private final ExRateRepository exRateRepository;

    public ExRateService(RestClient restClient, ExRateConfig exRateConfig, ExRateRepository exRateRepository) {
        this.restClient = restClient;
        this.exRateConfig = exRateConfig;
        this.exRateRepository = exRateRepository;
    }

    public void getRates() {
        ExRateDto body = restClient.get()
                .uri(exRateConfig.getKey())
                .retrieve()
                .body(ExRateDto.class);
        this.updateBase(body);
    }

    private void updateBase(ExRateDto body) {
        if(exRateRepository.count()==0){
            for (Map.Entry<String, Double> item:body.getRates().entrySet()){
                this.exRateRepository.save(new ExRate(item.getKey(), item.getValue()));
            }
        }
    }

    public List<String> getAllRatesNames() {
        if(exRateRepository.count()==0){
            this.getRates();
        }
        return this.exRateRepository.findAll().stream().map(ExRate::getName).collect(Collectors.toList());
    }
}
