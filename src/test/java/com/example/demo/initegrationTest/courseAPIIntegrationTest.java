package com.example.demo.initegrationTest;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.repository.ICourseRepository;
import com.example.demo.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Base64Utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class courseAPIIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-validation-report", "true");
    }

    private String createURLWithPort(String path) {
        return "http://localhost:" + port + "/api/courses/" + path;
    }

    private static void addAuth(){
        String auth = "user:user";
        String encodedAuth = Base64Utils.encodeToString(auth.getBytes());
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth);
    }

    @Test
    @Sql(statements = "INSERT INTO course(id, name, description, credit) VALUES (101, 'CSEN101','Intro to programming', 4)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM course WHERE id='101'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCourseById() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<CourseDTO> response = restTemplate.exchange(
                createURLWithPort("view/101"), HttpMethod.GET, entity, CourseDTO.class);

        CourseDTO courseRes = response.getBody();
        String expected = "{\"id\":101,\"name\":\"CSEN101\",\"description\":\"Intro to programming\",\"credit\":4}";

        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(expected, objectMapper.writeValueAsString(courseRes));
        assertNotNull(courseRes);
        assertThat(courseRes).usingRecursiveComparison().isEqualTo(courseService.findByID(101L));
        assertThat(courseRes).usingRecursiveComparison().isEqualTo(courseRepository.findById(101L).orElse(null));
    }

    @Test
    @Sql(statements = "DELETE FROM course WHERE name='Math 301'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCreateCourse() throws JsonProcessingException {
        CourseDTO course = new CourseDTO("MATH 301", "This is an intro to math course", 4);

        addAuth();
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(course), headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                createURLWithPort("/add"), HttpMethod.POST, entity, Void.class);

        assertEquals(response.getStatusCodeValue(), 201);
        Course addedCourse = courseRepository.findByName("MATH 301");
        assertNotNull(addedCourse);
        assertThat(addedCourse.getName()).isEqualTo("MATH 301");
        assertThat(addedCourse.getDescription()).isEqualTo("This is an intro to math course");
        assertThat(addedCourse.getCredit()).isEqualTo(4);
    }

    @Test
    @Sql(statements = "INSERT INTO course(id, name, description, credit) VALUES (101, 'CSEN101','Intro to programming', 4)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM course WHERE id='101'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteCourse() {
        addAuth();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                createURLWithPort("delete/101"), HttpMethod.DELETE, entity, Void.class);

        assertEquals(response.getStatusCodeValue(), 204);
        assertNull(courseRepository.findById(101L).orElse(null));
    }

    @Test
    @Sql(statements = "INSERT INTO course (id, name, description, credit) VALUES (101, 'MATH 301', 'This is an intro to math course', 4)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM course WHERE id = 101", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateCourseDescription(){
        String newDescription = "Updated description for math course";

       addAuth();
        HttpEntity<String> entity = new HttpEntity<>(newDescription, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                createURLWithPort("update/101"), HttpMethod.PUT, entity, Void.class);

        assertEquals(204, response.getStatusCodeValue());

        Course updatedCourse = courseRepository.findById(101L).orElse(null);
        assertNotNull(updatedCourse);
        assertThat(updatedCourse.getDescription()).isEqualTo(newDescription);
    }
}
