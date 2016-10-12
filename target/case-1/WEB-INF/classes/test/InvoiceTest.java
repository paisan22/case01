package test;

import domain.Course;
import domain.CourseMember;
import domain.Invoice;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by paisanrietbroek on 12/10/16.
 */
public class InvoiceTest {
    @Test
    public void getCourses() throws Exception {

        List<Course> courseList = new Invoice().getCourses(42);

        for (Course course : courseList) {
            System.out.println(course.getTitle());
        }
    }

    @Test
    public void getCourseMembersInSpecificWeek() throws SQLException, ClassNotFoundException {
        for (CourseMember courseMember : Invoice.getMembersInSpecificWeek(42)) {
            System.out.println(courseMember.getFirstname());
        }
    }
}