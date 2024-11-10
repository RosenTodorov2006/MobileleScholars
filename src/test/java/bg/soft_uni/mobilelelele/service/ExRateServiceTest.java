package bg.soft_uni.mobilelelele.service;

import bg.soft_uni.mobilelelele.config.ExRateConfig;
import bg.soft_uni.mobilelelele.models.entities.ExRate;
import bg.soft_uni.mobilelelele.repositories.ExRateRepository;
import bg.soft_uni.mobilelelele.services.impl.ExRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ExRateServiceTest {
    @Mock
    private RestClient restClient;
    @Mock
    private ExRateConfig exRateConfig;
    @Mock
    private ExRateRepository exRateRepository;
    private ExRateService exRateService;
    @BeforeEach
    public void serUp(){
        exRateService= new ExRateService(restClient, exRateConfig, exRateRepository);
    }
    @Test
    public void getAllRatesNames(){
        ExRate exRate = new ExRate();
        exRate.setName("Ivan");
        exRate.setCurrency(1);
        Mockito.when(exRateRepository.count()).thenReturn(1L);
        Mockito.when(exRateRepository.findAll()).thenReturn(List.of(exRate));
        Assertions.assertEquals(1, exRateRepository.count());
        Assertions.assertEquals(exRateService.getAllRatesNames().get(0), exRate.getName());
    }
}
