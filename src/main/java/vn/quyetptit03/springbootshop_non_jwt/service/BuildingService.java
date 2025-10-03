package vn.quyetptit03.springbootshop_non_jwt.service;

import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> getBuilding(BuildingDTO name);
}
