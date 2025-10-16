package vn.quyetptit03.springbootshop_non_jwt.repository.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;
import vn.quyetptit03.springbootshop_non_jwt.entity.RentAreaEntity;

import java.util.ArrayList;
import java.util.List;

public class BuildingSpecification {
    /**
     * Phương thức chính để tạo Specification cho BuildingEntity từ BuildingDTO.
     */
    public static Specification<BuildingEntity> buildSpecification(BuildingDTO criteria) {
        return (Root<BuildingEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // 1. Tên (name) - Tìm kiếm gần đúng (LIKE)
            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                Predicate nameLike = builder.like(
                        builder.lower(root.get("name")),
                        "%" + criteria.getName().toLowerCase() + "%"
                );
                predicates.add(nameLike);
            }

            // 2. Địa chỉ (address) - Tìm kiếm gần đúng trong street HOẶC ward
            if (criteria.getAddress() != null && !criteria.getAddress().isEmpty()) {
                String addressPattern = "%" + criteria.getAddress().toLowerCase() + "%";
                Predicate streetLike = builder.like(builder.lower(root.get("street")), addressPattern);
                Predicate wardLike = builder.like(builder.lower(root.get("ward")), addressPattern);

                // Kết hợp street HOẶC ward
                predicates.add(builder.or(streetLike, wardLike));
            }

            // 3. Số tầng hầm (numberOfBasement) - Tìm kiếm Chính xác (EQUAL)
            if (criteria.getNumberOfBasement() != null && criteria.getNumberOfBasement() > 0) {
                // Ép kiểu Integer của DTO sang Long của Entity
                Predicate basementEqual = builder.equal(
                        root.get("numberofbasement"),
                        criteria.getNumberOfBasement().longValue()
                );
                predicates.add(basementEqual);
            }

            // 4. Tên người quản lý (managerName) - Tìm kiếm gần đúng (LIKE)
            if (criteria.getManagerName() != null && !criteria.getManagerName().isEmpty()) {
                Predicate managerNameLike = builder.like(
                        builder.lower(root.get("managername")),
                        "%" + criteria.getManagerName().toLowerCase() + "%"
                );
                predicates.add(managerNameLike);
            }

            // 5. Số điện thoại người quản lý (managerPhoneNumber) - Tìm kiếm chính xác hoặc gần đúng
            if (criteria.getManagerPhoneNumber() != null && !criteria.getManagerPhoneNumber().isEmpty()) {
                Predicate phoneEqual = builder.equal(
                        root.get("managerphonenumber"),
                        criteria.getManagerPhoneNumber()
                );
                predicates.add(phoneEqual);
            }

            // 6. Diện tích sàn (floorArea) - Tìm kiếm Lớn hơn hoặc Bằng (GREATER THAN OR EQUAL)
            if (criteria.getFloorArea() != null && criteria.getFloorArea() > 0) {
                // Ép kiểu Integer của DTO sang Long của Entity
                Predicate floorAreaGE = builder.greaterThanOrEqualTo(
                        root.get("floorarea"),
                        criteria.getFloorArea().longValue()
                );
                predicates.add(floorAreaGE);
            }

            // --- Xử lý các điều kiện liên quan đến RentAreaEntity (One-To-Many) ---

            // 7. Diện tích còn trống (emptyArea)
            // Tìm kiếm các tòa nhà có ít nhất 1 RentAreaEntity với diện tích >= emptyArea
            if (criteria.getEmptyArea() != null && criteria.getEmptyArea() > 0) {
                // Sử dụng Join để truy vấn vào bảng RentAreaEntity
                Join<BuildingEntity, RentAreaEntity> rentAreaJoin = root.join("rentAreaEntities", JoinType.INNER);

                Predicate areaGE = builder.greaterThanOrEqualTo(
                        rentAreaJoin.get("value"), // Giả sử field diện tích là 'value' trong RentAreaEntity
                        criteria.getEmptyArea()
                );
                predicates.add(areaGE);

                // Đảm bảo kết quả là DISTINCT (tránh lặp lại tòa nhà do join)
                query.distinct(true);
            }

            // 8. Diện tích thuê (rentArea) - Tìm kiếm gần đúng các giá trị diện tích thuê
            // Trường này thường là chuỗi các diện tích (ví dụ: "100, 200, 300"), cần tìm LIKE
            if (criteria.getRentArea() != null && !criteria.getRentArea().isEmpty()) {
                Join<BuildingEntity, RentAreaEntity> rentAreaJoin = root.join("rentAreaEntities", JoinType.INNER);

                Predicate areaLike = builder.like(
                        builder.lower(rentAreaJoin.get("value").as(String.class)), // Ép kiểu số sang chuỗi để tìm LIKE
                        "%" + criteria.getRentArea() + "%"
                );
                predicates.add(areaLike);
                query.distinct(true);
            }

            // 9. Phí dịch vụ (serviceFee) - Tìm kiếm gần đúng (LIKE)
            if (criteria.getServiceFee() != null && !criteria.getServiceFee().isEmpty()) {
                Predicate serviceFeeLike = builder.like(
                        builder.lower(root.get("servicefee")),
                        "%" + criteria.getServiceFee().toLowerCase() + "%"
                );
                predicates.add(serviceFeeLike);
            }

            // 10. Giá thuê (rentPrice) - Tìm kiếm Lớn hơn hoặc Bằng (GREATER THAN OR EQUAL)
            if (criteria.getRentPrice() != null && criteria.getRentPrice() > 0) {
                Predicate rentPriceGE = builder.greaterThanOrEqualTo(
                        root.get("rentprice"),
                        criteria.getRentPrice().longValue()
                );
                predicates.add(rentPriceGE);
            }

            // 11. Phí môi giới (brokerageFee) - Tìm kiếm Chính xác (EQUAL)
            if (criteria.getBrokerageFee() != null && criteria.getBrokerageFee() > 0) {
                Predicate brokerageFeeEqual = builder.equal(
                        root.get("brokeragefee"),
                        criteria.getBrokerageFee()
                );
                predicates.add(brokerageFeeEqual);
            }

            // 12. Hạng (level) - Tìm kiếm Chính xác (EQUAL)
            if (criteria.getLevel() != null && !criteria.getLevel().isEmpty()) {
                Predicate levelEqual = builder.equal(
                        root.get("level"),
                        criteria.getLevel()
                );
                predicates.add(levelEqual);
            }

            // Kết hợp tất cả các điều kiện lại với toán tử AND
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
