package domain;

import database.CourseDAO;

import java.sql.SQLException;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import static java.util.stream.Collectors.toList;

/**
 * Created by paisanrietbroek on 12/10/16.
 */

public class Invoice {

    public static List<CourseMember> getMembersInSpecificWeek(int weekNumber) throws SQLException, ClassNotFoundException {
        List<CourseMember> courseMemberList = new LinkedList<>();

        for (Course course : getCourses(weekNumber)) {
            for (CourseMember courseMember : course.getCourseMemberList()) {
                courseMemberList.add(courseMember);
            }
        }
        return courseMemberList;
    }

    public static List<Course> getCourses(int weekNumber) throws SQLException, ClassNotFoundException {
        TemporalField weekOfWeekBasedYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

        // get all courses of specific week
        List<Course> courseList = new CourseDAO().getAll().stream()
                .filter(c -> {
                    int week = c.getStartDate().get(weekOfWeekBasedYear);
                    return week == weekNumber;
                })
                .collect(toList());

        return courseList;
    }
}
