package vn.quyetptit03.springbootshop_non_jwt.repository;

import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepository {
    List<BuildingEntity> findAll(BuildingDTO name);
}
