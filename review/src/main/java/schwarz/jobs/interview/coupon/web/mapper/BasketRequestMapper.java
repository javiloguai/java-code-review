package schwarz.jobs.interview.coupon.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import schwarz.jobs.interview.coupon.constants.MapperConstants;
import schwarz.jobs.interview.coupon.core.services.model.domain.BasketDomain;
import schwarz.jobs.interview.coupon.web.dto.request.BasketRequest;

/**
 * The BasketRequestMapper request mapper.
 */
@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface BasketRequestMapper extends RequestMapper<BasketRequest, BasketDomain> {

    BasketRequestMapper INSTANCE = Mappers.getMapper(BasketRequestMapper.class);

    /**
     * Gets mapper.
     *
     * @return the mapper
     */
    static BasketRequestMapper getMapper() {
        return Mappers.getMapper(BasketRequestMapper.class);
    }


}
