package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Repository
public class JdbcCourseRepository implements ICourseRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCourse(Course course) {
        String sql = "insert into course(id,name,description,credit) values(?,?,?,?)";

        jdbcTemplate.update(sql, course.getId(), course.getName(), course.getDescription(), course.getCredit());
    }

    @Override
    public void updateCourseDescription(int id, String description) {
        String sql = "update course SET description = ? where id = ?";

        jdbcTemplate.update(sql, description, id);
    }

    public Course findByID(int id){
        String sql = "select * from course where id = ?";

        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> mapCourse(rs),id);
    }

    @Override
    public void deleteCourse(int id) {
        String sql = "delete from course where id = ?";

        jdbcTemplate.update(sql, id);
    }

    private Course mapCourse(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        int credit = rs.getInt("credit");

        return new Course(id,name,description,credit);
    }


    @PostConstruct
    public void initialize() {
        try {
            String sql_schema = Files.lines(Paths.get("src/main/resources/schema.sql"))
                    .collect(Collectors.joining("\n"));
            jdbcTemplate.execute(sql_schema);

            String sql_data = Files.lines(Paths.get("src/main/resources/data.sql"))
                    .collect(Collectors.joining("\n"));
            jdbcTemplate.execute(sql_data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
