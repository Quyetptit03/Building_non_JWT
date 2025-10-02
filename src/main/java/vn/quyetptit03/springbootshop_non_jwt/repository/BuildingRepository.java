package vn.quyetptit03.springbootshop_non_jwt.repository;

import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;

import java.util.ArrayList;
import java.util.List;

public interface BuildingRepository {
    List<BuildingEntity> findAll(String name);
}
