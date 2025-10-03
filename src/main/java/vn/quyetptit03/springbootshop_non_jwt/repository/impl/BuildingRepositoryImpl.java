package vn.quyetptit03.springbootshop_non_jwt.repository.impl;

import org.springframework.stereotype.Repository;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;
import vn.quyetptit03.springbootshop_non_jwt.repository.BuildingRepository;
import vn.quyetptit03.springbootshop_non_jwt.repository.ConnectJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    private final ConnectJDBC connectJDBC= new ConnectJDBC();

    @Override
    public List<BuildingEntity> findAll(BuildingDTO dto) {

        List<BuildingEntity> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM building WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        // search theo name
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            sql.append(" AND name LIKE ? ");
            params.add("%" + dto.getName() + "%");
        }

        // search theo address (map xuống ward + street trong entity)
        if (dto.getAddress() != null && !dto.getAddress().isEmpty()) {
            sql.append(" AND (ward LIKE ? OR street LIKE ?) ");
            params.add("%" + dto.getAddress() + "%");
            params.add("%" + dto.getAddress() + "%");
        }

        // search theo numberOfBasement
        if (dto.getNumberOfBasement() != null) {
            sql.append(" AND numberofbasement = ? ");
            params.add(dto.getNumberOfBasement());
        }

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
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
                result.add(buildingEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connected database failed!");
        }
            return result;
        }
}
