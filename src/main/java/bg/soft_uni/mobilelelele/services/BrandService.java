package bg.soft_uni.mobilelelele.services;

import bg.soft_uni.mobilelelele.models.dtos.BrandExportDto;

import java.util.List;

public interface BrandService {
    List<BrandExportDto> findAll();

}
