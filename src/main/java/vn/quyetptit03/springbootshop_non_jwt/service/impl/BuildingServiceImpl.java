package vn.quyetptit03.springbootshop_non_jwt.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;
import vn.quyetptit03.springbootshop_non_jwt.repository.BuildingRepository;
import vn.quyetptit03.springbootshop_non_jwt.repository.specification.BuildingSpecification;
import vn.quyetptit03.springbootshop_non_jwt.service.BuildingService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    // Sử dụng constructor injection thay vì @Autowired cho dễ test và immutable
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    /**
     * tao moi toa nha va luu xong db
     *
     * @param buildingDTO doi tuong chua thong tin toa nha can tao
     */
    @Override
    public void createBuilding(BuildingDTO buildingDTO) {
        BuildingEntity entity = new BuildingEntity();
        entity.setName(buildingDTO.getName());
        /*
         * DTO có field "address" (VD: "59 Phan Xích Long, Phường 2")
         * nhưng Entity lại tách thành street + ward.
         * Vì vậy ta tách chuỗi address thành 2 phần nếu có dấu phẩy.
         */
        if (buildingDTO.getAddress() != null && buildingDTO.getAddress().contains(",")) {
            String[] parts = buildingDTO.getAddress().split(",", 2);
            entity.setStreet(parts[0].trim());
            entity.setWard(parts[1].trim());
        } else {
            entity.setStreet(buildingDTO.getAddress());
            entity.setWard(null);
        }
        entity.setNumberofbasement(buildingDTO.getNumberOfBasement() != null ? buildingDTO.getNumberOfBasement().longValue() : null);
        entity.setManagername(buildingDTO.getManagerName());
        entity.setManagerphonenumber(buildingDTO.getManagerPhoneNumber());
        entity.setFloorarea(buildingDTO.getFloorArea() != null ? buildingDTO.getFloorArea().longValue() : null);
        entity.setServicefee(buildingDTO.getServiceFee());
        entity.setRentprice(buildingDTO.getRentPrice() != null ? buildingDTO.getRentPrice().longValue() : null);
        entity.setBrokeragefee(buildingDTO.getBrokerageFee());
        entity.setLevel(buildingDTO.getLevel());

        // Gọi repository lưu xuống DB
        buildingRepository.save(entity);

    }


    /**
     * Xoa mot toa nha theo id
     *
     * @param id cua toa nha can xoa
     * @throws RuntimeException neu khong tim thay toa nha
     */

    @Override
    public void deleteBuilding(Long id) {
        // kiem tra id co ton tai chưa
        if (!buildingRepository.existsById(id)) {
            throw new RuntimeException("Building not found" + id);
        }
        //thuc hien xoa
        buildingRepository.deleteById(id);
    }

    /**
     * @param id          cua toa nha can cap nhat
     * @param buildingDTO du lieu moi
     * @throws RuntimeException neu khong tim thay toa nha
     */

    @Override
    public void updateBuilding(Long id, BuildingDTO buildingDTO) {
        BuildingEntity existingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));

        existingEntity.setName(buildingDTO.getName());
        existingEntity.setManagername(buildingDTO.getManagerName());
        existingEntity.setManagerphonenumber(buildingDTO.getManagerPhoneNumber());
        existingEntity.setFloorarea(buildingDTO.getFloorArea() != null ? buildingDTO.getFloorArea().longValue() : existingEntity.getFloorarea());
        existingEntity.setNumberofbasement(buildingDTO.getNumberOfBasement() != null ? buildingDTO.getNumberOfBasement().longValue() : existingEntity.getNumberofbasement());
        existingEntity.setStreet(buildingDTO.getAddress()); // hoặc tách nếu address chứa ward, street

        buildingRepository.save(existingEntity);
    }

    @Override
    public void deleteBuildings(List<Long> ids) {
        // kiem tra danh sach id co rong khong
        if(ids == null || ids.isEmpty()) {
            throw new RuntimeException("Danh sách Id cần xóa đang rỗng");
        }

        // Kiem tra id co ton tai khong
        List<Long> list = new ArrayList<>();
        for (Long id : ids) {
            if(!buildingRepository.existsById(id)) {
                list.add(id);
            }
        }

        if(!list.isEmpty()) {
            throw new RuntimeException("Không tìm thấy tòa nhà có ID: "+list);
        }

        // Neu tat ca ton tai, thuc hien xoa
        buildingRepository.deleteAllById(ids);
    }

    /**
     * Lay danh sach toa nha
     *
     */
    @Override
    public List<BuildingDTO> getBuilding(BuildingDTO buildingDTO) {

        List<BuildingEntity> entities;
        List<BuildingDTO> dtos = new ArrayList<>();
        if (isEmptySearchCondition(buildingDTO)) {
            // Nếu không có điều kiện tìm kiếm nào, lấy tất cả
            entities = buildingRepository.findAll();
        } else {
            // 1. Tạo Specification từ DTO
            Specification<BuildingEntity> spec = BuildingSpecification.buildSpecification(buildingDTO);
            // 2. Thực hiện tìm kiếm bằng JpaSpecificationExecutor
            entities = buildingRepository.findAll(spec);
        }

        for (BuildingEntity entity : entities) {
            //mapping tu dto sang entity
            BuildingDTO dto = new BuildingDTO();
            dto.setName(entity.getName());
            dto.setAddress(entity.getStreet() + ", " + entity.getWard());
            dto.setNumberOfBasement(entity.getNumberofbasement().intValue());
            dto.setManagerName(entity.getManagername());
            dto.setManagerPhoneNumber(entity.getManagerphonenumber());
            dto.setServiceFee(entity.getServicefee());
            dto.setRentPrice(entity.getRentprice().intValue());
            dto.setBrokerageFee(entity.getBrokeragefee());
            dto.setLevel(entity.getLevel());
            dtos.add(dto);
        }

        return dtos;
    }

    private boolean isEmptySearchCondition(BuildingDTO dto) {
        try {
            for (Field field : dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(dto);
                if (value != null) {
                    if (value instanceof String && !((String) value).isEmpty()) {
                        return false; // Có ít nhất 1 field String có giá trị
                    } else if (!(value instanceof String)) {
                        return false; // Có field không phải String (ví dụ int, double)
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true; // tất cả đều null hoặc rỗng
    }
}
