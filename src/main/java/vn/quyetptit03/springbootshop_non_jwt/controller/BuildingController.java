package vn.quyetptit03.springbootshop_non_jwt.controller;

import org.slf4j.ILoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.quyetptit03.springbootshop_non_jwt.dto.BuildingDTO;
import vn.quyetptit03.springbootshop_non_jwt.repository.ConnectJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/building/")
public class BuildingController {

    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection conn = connectJDBC.connection();

    @GetMapping
    public List<BuildingDTO> getBuilding(
         @RequestParam String name
    ) {

        List<BuildingDTO> result = new ArrayList<>();

        String sql = "select * from building where name like '%"+name+"%'";
        try (
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                ){
            while(rs.next()){
                BuildingDTO buildingDTO = new BuildingDTO();
                buildingDTO.setName(rs.getString("name"));
                buildingDTO.setStreet(rs.getString("street"));
                buildingDTO.setWard(rs.getString("ward"));
                buildingDTO.setNumberOfBasement(rs.getString("numberofbasement"));
                result.add(buildingDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connected database failed!");
        }

        System.out.println(result.toString());
        return result;
    }

}
