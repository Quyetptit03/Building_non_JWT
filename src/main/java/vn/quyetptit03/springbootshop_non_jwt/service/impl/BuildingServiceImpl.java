package vn.quyetptit03.springbootshop_non_jwt.service.impl;

import org.springframework.stereotype.Service;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;
import vn.quyetptit03.springbootshop_non_jwt.repository.BuildingRepository;
import vn.quyetptit03.springbootshop_non_jwt.service.BuildingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    // Sử dụng constructor injection thay vì @Autowired cho dễ test và immutable
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<BuildingDTO> getBuilding(BuildingDTO name) {

        List<BuildingEntity> entities = buildingRepository.findAll(name);
        List<BuildingDTO> dtos = new ArrayList<>();

        for (BuildingEntity entity : entities) {
            BuildingDTO dto = new BuildingDTO();
            dto.setName(entity.getName());
            dto.setAddress(entity.getStreet() + ", " + entity.getWard());
            dto.setNumberOfBasement(entity.getNumberOfBasement());
            dtos.add(dto);
        }

        return dtos;
    }
}
