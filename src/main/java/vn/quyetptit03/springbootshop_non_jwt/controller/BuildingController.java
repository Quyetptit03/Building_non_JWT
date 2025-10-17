package vn.quyetptit03.springbootshop_non_jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<BuildingDTO>> getBuilding(
            @ModelAttribute BuildingDTO buildingDTO
    ) {
        List<BuildingDTO> result = buildingService.getBuilding(buildingDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createBuilding(
            @RequestBody BuildingDTO buildingDTO
    ) {
        buildingService.createBuilding(buildingDTO);
        return ResponseEntity.ok("Building created successfully!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBuildings(
        @RequestBody List<Long> ids
    ){
        try {
            buildingService.deleteBuildings(ids);
            return ResponseEntity.ok("Building deleted "+ids.size()+" successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuilding(
            @PathVariable Long id
    ) {
        try {
            buildingService.deleteBuilding(id);
            return ResponseEntity.ok("Building deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR: "+e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBuilding(
            @PathVariable Long id,
            @RequestBody BuildingDTO buildingDTO) {
        try {
            buildingService.updateBuilding(id, buildingDTO);
            return ResponseEntity.ok("Building updated successfully with id: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }
}
