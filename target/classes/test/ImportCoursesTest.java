package test;

import database.CourseDAO;
import domain.Course;
import domain.ImportCourses;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by paisanrietbroek on 10/10/16.
 */
public class ImportCoursesTest {
    private ImportCourses importCourses;

    @Before
    public void initObject() {
        importCourses = new ImportCourses();
    }

    @Test
    public void readFile() throws IOException {
        importCourses.readLines("courses.txt");
        assertThat(importCourses.getLines().size(), is(CoreMatchers.<Integer>notNullValue()));
    }

    @Test
    public void convertToObject() throws IOException {
        importCourses.readLines("courses.txt");
        importCourses.convertToObjects();

        for (Course course : importCourses.getCourseList()) {
            assertThat(course.getTitle(), is(CoreMatchers.<String>notNullValue()));
            assertThat(course.getCursusCode(), is(CoreMatchers.<String>notNullValue()));
            assertThat(course.getNumberOfDays(), is(CoreMatchers.<Integer>notNullValue()));
            assertThat(course.getStartDate(), is(CoreMatchers.<LocalDate>notNullValue()));
        }
    }

    @Test
    public void importFile() throws IOException, SQLException, ClassNotFoundException {
        List<Course> courseList = importCourses.importFile("courses.txt");
        boolean b = new CourseDAO().importFile(courseList);
        assertThat(b, is(true));
    }
}
