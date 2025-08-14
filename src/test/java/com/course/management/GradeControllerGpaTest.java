package com.course.management;

import com.course.management.Controller.GradeController;
import com.course.management.Services.GradeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GradeControllerUnitTest {

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeController gradeController;

    @Test
    void testGetStudentGPA() {
        Long studentId = 1L;
        int year = 2024;
        double expectedGPA = 3.5;

        Mockito.when(gradeService.calculateGPA(eq(studentId), eq(year)))
                .thenReturn(expectedGPA);

        double actualGPA = gradeController.getStudentGPA(studentId, year);

        assertEquals(expectedGPA, actualGPA);

        Mockito.verify(gradeService).calculateGPA(studentId, year);
    }
}
