package bg.soft_uni.mobilelelele.services;

import bg.soft_uni.mobilelelele.models.dtos.AddOfferDto;
import bg.soft_uni.mobilelelele.models.dtos.OfferExportDto;

import java.util.List;

public interface OfferService {
    void addOffer(AddOfferDto addOfferDto);

    List<OfferExportDto> findAllOffers();

    OfferExportDto findOfferById(Long id);
}
