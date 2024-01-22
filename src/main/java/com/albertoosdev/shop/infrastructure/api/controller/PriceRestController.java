package com.albertoosdev.shop.infrastructure.api.controller;

import com.albertoosdev.shop.domain.port.FindPriceByParamUseCasePort;
import com.albertoosdev.shop.infrastructure.api.mapper.PriceResponseMapper;
import com.albertoosdev.shop.openapi.api.PriceApi;
import com.albertoosdev.shop.openapi.model.PriceResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

/**
 *  The price rest controller.
 */
@RestController
public class PriceRestController implements PriceApi {

    private final FindPriceByParamUseCasePort findPriceByParamUseCase;

    private final PriceResponseMapper priceMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PriceRestController.class);

    /**
     * The constructor
     *
     * @param findPriceByParamUseCase       the use case
     * @param priceMapper                   the mapper
     */
    public PriceRestController(final FindPriceByParamUseCasePort findPriceByParamUseCase,
                               final PriceResponseMapper priceMapper) {
        this.findPriceByParamUseCase = findPriceByParamUseCase;
        this.priceMapper = priceMapper;
    }


    /**
     * Find price by param: applicationDate, productId and brandId.
     *
     * @param applicationDate  (required)
     * @param productId  (required)
     * @param brandId  (required)
     * @return the price response
     */
    @Override
    public ResponseEntity<PriceResponseDTO> getPriceByParam(final OffsetDateTime applicationDate,
                                                            final Long productId,
                                                            final Long brandId) {
        LOG.debug("::getPriceByParams:: Entering getPriceByParams with params: " +
                        "applicationDate: {}, productId: {}, brandId: {}",
                applicationDate, productId, brandId);

        return ResponseEntity.ok(priceMapper.mapToPriceResponseDTO(
                findPriceByParamUseCase.execute(applicationDate.toLocalDateTime(), productId, brandId)
        ));

    }
}
