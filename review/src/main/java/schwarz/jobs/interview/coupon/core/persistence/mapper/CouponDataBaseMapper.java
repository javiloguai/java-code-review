package schwarz.jobs.interview.coupon.core.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import schwarz.jobs.interview.coupon.constants.MapperConstants;
import schwarz.jobs.interview.coupon.core.persistence.entity.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.model.domain.CouponDomain;

/**
 * The Interface CouponDataBaseMapper.
 */
@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface CouponDataBaseMapper extends DatabaseMapper<CouponDomain, CouponEntity> {

    /**
     * Gets the mapper.
     *
     * @return the mapper
     */
    CouponDataBaseMapper INSTANCE = Mappers.getMapper(CouponDataBaseMapper.class);


    @Mapping(target = "id", ignore = true)
    void copyToEntity(CouponDomain domain, @MappingTarget CouponEntity entity);

}
