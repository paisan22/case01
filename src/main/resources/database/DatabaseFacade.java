package database;

import domain.Course;
import domain.CourseMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */

@Getter
@NoArgsConstructor
public class DatabaseFacade {

    public List<CourseMember> getCourseMemberList () throws SQLException, ClassNotFoundException {
        return new CourseMemberDAO().getAll();
    }

    public CourseMember getCourseMember(int id) throws SQLException, ClassNotFoundException {
        return new CourseMemberDAO().get(id);
    }

    public int addCourseMember(CourseMember courseMember) throws SQLException, ClassNotFoundException {
        return new CourseMemberDAO().add(courseMember);
    }

    public int deleteCourseMember(int id) throws SQLException, ClassNotFoundException {
        return new CourseMemberDAO().delete(id);
    }

    public int updateCourseMember(CourseMember courseMember) throws SQLException, ClassNotFoundException {
        return new CourseMemberDAO().update(courseMember);
    }

    public boolean registerCourseMember(int courseMemberID, int courseID) throws SQLException, ClassNotFoundException {
        return new CourseMemberDAO().registerToCourse(courseMemberID, courseID);
    }

    public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
        return new CourseDAO().getAll();
    }

    public Course getSpecificCourse(int courseID) throws SQLException, ClassNotFoundException {
        return new CourseDAO().get(courseID);
    }

    public boolean importFile(List<Course> courseList) throws SQLException, ClassNotFoundException {
        return new CourseDAO().importFile(courseList);
    }
}
