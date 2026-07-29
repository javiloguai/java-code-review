package schwarz.jobs.interview.coupon.core.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import schwarz.jobs.interview.coupon.core.persistence.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

    Optional<CouponEntity> findByCodeIgnoreCase(final String code);

}
