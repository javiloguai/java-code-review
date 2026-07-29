package schwarz.jobs.interview.coupon.web.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import schwarz.jobs.interview.coupon.constants.MapperConstants;
import schwarz.jobs.interview.coupon.core.services.model.domain.CouponDomain;
import schwarz.jobs.interview.coupon.web.dto.response.CouponResponse;

/**
 * The CouponResponseMapper
 */
@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface CouponResponseMapper extends ResponseMapper<CouponDomain, CouponResponse> {

    CouponResponseMapper INSTANCE = Mappers.getMapper(CouponResponseMapper.class);

}
