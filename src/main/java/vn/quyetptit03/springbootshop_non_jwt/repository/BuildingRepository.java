package vn.quyetptit03.springbootshop_non_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>,
        JpaSpecificationExecutor<BuildingEntity> {
    // Phương thức tìm kiếm sẽ sử dụng findAll(Specification<T> spec) được cung cấp
}