package vn.quyetptit03.springbootshop_non_jwt.repository.impl;

import org.springframework.stereotype.Repository;
import vn.quyetptit03.springbootshop_non_jwt.entity.BuildingEntity;
import vn.quyetptit03.springbootshop_non_jwt.repository.BuildingRepository;
import vn.quyetptit03.springbootshop_non_jwt.repository.ConnectJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    private final ConnectJDBC connectJDBC= new ConnectJDBC();

    @Override
    public List<BuildingEntity> findAll(String name) {

        List<BuildingEntity> result = new ArrayList<>();

        String sql = "select * from building where name like '%" + name + "%'";
            try (
                 Connection conn = connectJDBC.connection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                    ){
            while(rs.next()){
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

            System.out.println(result.toString());
            return result;
        }
}
