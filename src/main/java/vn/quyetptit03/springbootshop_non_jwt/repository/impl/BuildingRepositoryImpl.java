package vn.quyetptit03.springbootshop_non_jwt.repository.impl;

import org.springframework.stereotype.Repository;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;
import vn.quyetptit03.springbootshop_non_jwt.repository.BuildingRepository;
import vn.quyetptit03.springbootshop_non_jwt.repository.ConnectJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    private final ConnectJDBC connectJDBC= new ConnectJDBC();

    @Override
    public List<BuildingEntity> findAll(BuildingDTO dto) {


        List<BuildingEntity> result = new ArrayList<>();

        // 1. Khởi tạo truy vấn cơ sở
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");

        // 2. Cờ và List để xử lý JOIN và WHERE
        boolean joinRentArea = false;
        List<Object> params = new ArrayList<>();
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        // --- Logic Xây dựng Mệnh đề WHERE ---

        // 1. Tên (Name)
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            where.append(" AND b.name LIKE ? ");
            params.add("%" + dto.getName() + "%");
        }

        // 2. Địa chỉ (Address - map xuống ward + street)
        if (dto.getAddress() != null && !dto.getAddress().isEmpty()) {
            where.append(" AND (b.ward LIKE ? OR b.street LIKE ?) ");
            params.add("%" + dto.getAddress() + "%");
            params.add("%" + dto.getAddress() + "%");
        }

        // 3. Số tầng hầm (numberOfBasement)
        if (dto.getNumberOfBasement() != null) {
            where.append(" AND b.numberofbasement = ? ");
            params.add(dto.getNumberOfBasement());
        }

        // 4. Tên quản lý (managerName)
        if (dto.getManagerName() != null && !dto.getManagerName().isEmpty()) {
            where.append(" AND b.managername LIKE ? ");
            params.add("%" + dto.getManagerName() + "%");
        }

        // 5. Số điện thoại quản lý (managerPhoneNumber)
        if (dto.getManagerPhoneNumber() != null && !dto.getManagerPhoneNumber().isEmpty()) {
            where.append(" AND b.managerphonenumber LIKE ? ");
            params.add("%" + dto.getManagerPhoneNumber() + "%");
        }

        // 6. Diện tích sàn (floorArea - tìm kiếm chính xác)
        if (dto.getFloorArea() != null) {
            where.append(" AND b.floorarea = ? ");
            params.add(dto.getFloorArea());
        }

        // 7. Diện tích cho thuê (rentArea - Cần JOIN với bảng rentarea)
        if (dto.getRentArea() != null && !dto.getRentArea().isEmpty()) {
            joinRentArea = true;

            // Xử lý chuỗi rentArea thành danh sách các giá trị để dùng IN
            // Ví dụ: "100,200"
            String[] areas = dto.getRentArea().split(",");
            StringBuilder inClause = new StringBuilder();

            for (String area : areas) {
                try {
                    // Loại bỏ khoảng trắng và thêm dấu chấm hỏi
                    inClause.append("?,");
                    // Chuyển sang Integer để add vào params
                    params.add(Integer.parseInt(area.trim()));
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu giá trị không phải là số
                    System.err.println("Invalid rent area value: " + area);
                }
            }

            if (inClause.length() > 0) {
                // Xóa dấu phẩy cuối cùng
                inClause.deleteCharAt(inClause.length() - 1);
                where.append(" AND ra.value IN (" + inClause.toString() + ") ");
            } else {
                // Nếu chuỗi rỗng sau khi xử lý lỗi, có thể bỏ qua điều kiện này
                joinRentArea = false;
            }
        }

        // 8. Phí dịch vụ (serviceFee)
        if (dto.getServiceFee() != null && !dto.getServiceFee().isEmpty()) {
            where.append(" AND b.servicefee LIKE ? ");
            params.add("%" + dto.getServiceFee() + "%");
        }

        // 9. Giá thuê (rentPrice - tìm kiếm <= giá tối đa)
        if (dto.getRentPrice() != null) {
            where.append(" AND b.rentprice <= ? ");
            params.add(dto.getRentPrice());
        }

        // 10. Phí môi giới (brokerageFee - tìm kiếm <= phí tối đa)
        if (dto.getBrokerageFee() != null) {
            // Lưu ý: brokeragefee trong DB là DECIMAL(13,2), trong DTO là Long.
            // Phép so sánh sẽ hoạt động ổn.
            where.append(" AND b.brokeragefee <= ? ");
            params.add(dto.getBrokerageFee());
        }

        // 11. Cấp độ (level)
        if (dto.getLevel() != null && !dto.getLevel().isEmpty()) {
            where.append(" AND b.level LIKE ? ");
            params.add("%" + dto.getLevel() + "%");
        }

        // --- Xử lý JOIN và Hoàn thiện Truy vấn ---

        // Thêm JOIN vào truy vấn chính nếu có điều kiện tìm kiếm rentArea
        if (joinRentArea) {
            sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
        }

        sql.append(where);

        // GROUP BY để đảm bảo mỗi tòa nhà chỉ xuất hiện một lần
        // nếu có JOIN với các bảng 1-n (ví dụ: rentarea)
        sql.append(" GROUP BY b.id ");
        // Lưu ý: Nếu có các điều kiện HAVING, sẽ thêm vào đây.


        System.out.println("SQL Query: " + sql.toString());

        // --- Thực thi Truy vấn ---

        try (
                Connection conn = connectJDBC.connection();
                PreparedStatement ps = conn.prepareStatement(sql.toString())
        ) {
            // set giá trị cho tham số
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();

                // Set ID
                buildingEntity.setId(rs.getInt("id"));
                // Set các trường tìm kiếm
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setNumberofbasement(rs.getInt("numberofbasement"));
                buildingEntity.setManagername(rs.getString("managername"));
                buildingEntity.setManagerphonenumber(rs.getString("managerphonenumber"));
                buildingEntity.setFloorarea(rs.getInt("floorarea"));
                buildingEntity.setServicefee(rs.getString("servicefee"));
                buildingEntity.setRentprice(rs.getInt("rentprice"));
                buildingEntity.setBrokeragefee(rs.getLong("brokeragefee"));
                buildingEntity.setLevel(rs.getString("level"));

                // Cần set thêm các trường khác nếu muốn hiển thị đầy đủ thông tin tòa nhà

                result.add(buildingEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connected database failed or query error!");
        }
        return result;
    }
}
