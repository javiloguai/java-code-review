package schwarz.jobs.interview.coupon.core.persistence.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

/**
 * Base Mapper for Infrastructure Services.
 * Responsible for the transformation between domain model objects and persistence layer objects
 * Domain model objects are those objects which represents a business entity.
 * Entity model objects are those objects which represents a JPA entities to persist.
 * Only defines particular methods to make clear which type of beans are involved on transformations, making use of
 * BaseMapper methods.
 */
public interface DatabaseMapper<DOMAIN, ENTITY> {

    /**
     * Map domain model object of type A on a entity model object type B object. Both contained in Optional container.
     *
     * @param optionalA Optional container containing A type domain model object
     * @return Optional container containing entity model object of type B.
     */
    default Optional<ENTITY> mapToOptionalEntity(Optional<DOMAIN> optionalA) {
        return optionalA.map(this::domainToEntity);
    }

    /**
     * Map entity model object of type B on a domain model of type A. Both contained in Optional container.
     *
     * @param optionalB Optional container containing B type entity model object
     * @return Optional container containing domain model object of A type.
     */
    default Optional<DOMAIN> entityToDomain(Optional<ENTITY> optionalB) {
        return optionalB.map(this::entityToDomain);
    }

    /**
     * Map domain model object of type A on a entity model object type B object.
     *
     * @param domain A type domain model object
     * @return B type entity model object.
     */
    ENTITY domainToEntity(DOMAIN domain);

    /**
     * Map entity model object of type B on a domain model of type A. Both contained in Optional container.
     *
     * @param entity entity model model object of B type.
     * @return domain model object of A type.
     */
    DOMAIN entityToDomain(ENTITY entity);


//    /**
//     * Map list of domain model objects of type A on a list of domain model objects of type A.
//     *
//     * @param domainList List containing entity model object of type B
//     * @return List containing domain model object of type A.
//     */
//    default List<ENTITY> domainToEntity(List<DOMAIN> domainList) {
//        if (domainList == null || domainList.isEmpty()) {
//            return List.of();
//        } else {
//            return domainList.stream().map(this::domainToEntity).toList();
//        }
//    }
//
//    /**
//     * Map list of entity model objects of type B on a list of domain model objects of type A.
//     *
//     * @param entityList List containing entity model objects of type B
//     * @return List containing domain model object of type A.
//     */
//    default List<DOMAIN> entityToDomain(List<ENTITY> entityList) {
//        if (entityList == null || entityList.isEmpty()) {
//            return List.of();
//        } else {
//            return entityList.stream().map(this::entityToDomain).toList();
//        }
//    }

    /**
     * Map Iterable of domain model objects of type A on a List of domain model objects of type A.
     *
     * @param domainList iterable containing entity model object of type B
     * @return List containing domain model object of type A.
     */
    Iterable<ENTITY> domainToEntity(Iterable<DOMAIN> domainList);

    /**
     * Map Iterable of entity model objects of type B on a List of domain model objects of type A.
     *
     * @param entityList iterable containing entity model object of type B
     * @return List containing domain model object of type A.
     */
    List<DOMAIN> entityToDomain(Iterable<ENTITY> entityList);


}
