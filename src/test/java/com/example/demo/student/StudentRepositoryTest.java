package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Bekjon Bakhromov
 * @created 31.03.2022-2:59 PM
 */

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    void itCheckStudentExistEmail() {
        //given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.FEMALE
        );
        underTest.save(student);
        //when
        boolean existsEmail = underTest.selectExistsEmail(email);
        //then
        assertThat(existsEmail).isTrue();
    }

    @Test
    void itCheckIfStudentEmailNotExist() {
        //given
        String email = "jamila@gmail.com";

        //when
        boolean existsEmail = underTest.selectExistsEmail(email);

        //then
        assertThat(existsEmail).isFalse();

    }


}