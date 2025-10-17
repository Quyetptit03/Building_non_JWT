package vn.quyetptit03.springbootshop_non_jwt.service;

import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> getBuilding (BuildingDTO buildingDTO);
    void createBuilding(BuildingDTO buildingDTO);
    void deleteBuilding(Long id);
    void updateBuilding(Long id, BuildingDTO buildingDTO);
    void deleteBuildings(List<Long> ids);
}
