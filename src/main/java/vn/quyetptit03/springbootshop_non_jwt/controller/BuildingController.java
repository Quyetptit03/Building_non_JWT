package vn.quyetptit03.springbootshop_non_jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/building/")
public class BuildingController {
    // Sử dụng constructor injection thay vì @Autowired cho dễ test và immutable
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    /**
     * Endpoint tìm kiếm các tòa nhà dựa trên tiêu chí được truyền qua query parameters.
     * Spring sẽ tự động mapping query params vào các fields của BuildingDTO.
     * * Ví dụ URL tìm kiếm:
     * /api/building/?name=Nam Giao&numberOfBasement=2&rentPrice=20000000
     */
    @GetMapping
    public List<BuildingDTO> getBuilding(
         BuildingDTO buildingDTO
    ) {
        List<BuildingDTO> result = buildingService.getBuilding(buildingDTO);
        return  result;
    }

}
