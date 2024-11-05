package bg.soft_uni.mobilelelele.service;

import bg.soft_uni.mobilelelele.models.dtos.BrandExportDto;
import bg.soft_uni.mobilelelele.models.entities.Brand;
import bg.soft_uni.mobilelelele.models.entities.Model;
import bg.soft_uni.mobilelelele.repositories.BrandRepository;
import bg.soft_uni.mobilelelele.services.impl.BrandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTests {
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ModelMapper modelMapper;
    private BrandServiceImpl brandService;


    @BeforeEach
    public void setUp(){
        brandService = new BrandServiceImpl(brandRepository, modelMapper);
    }
    @Test
    public void testFindAll(){
        Brand brand = new Brand();
        brand.setName("Ivan");
        BrandExportDto brandExportDto = new BrandExportDto();
        brandExportDto.setName("Ivan");
        Mockito.when(this.brandRepository.findAll())
                .thenReturn(List.of(brand));
        Mockito.when(this.modelMapper.map(brand, BrandExportDto.class))
                .thenReturn(brandExportDto);
        brand.setModels(List.of(new Model()));
        List<BrandExportDto> all = this.brandService.findAll();
        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals(brand.getName(), all.get(0).getName());
    }
}
