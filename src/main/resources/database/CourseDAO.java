package database;

import domain.Course;
import java.sql.Date;
import java.time.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paisanrietbroek on 10/10/16.
 */
public class CourseDAO extends DAO<Course> {
    @Override
    public List<Course> getAll() throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "SELECT * FROM course";
        preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Course> courses = new LinkedList<>();

        while (resultSet.next()) {
            Course course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setCursusCode(resultSet.getString("code"));
            course.setTitle(resultSet.getString("title"));
            course.setStartDate(resultSet.getDate("start_date").toLocalDate());
            course.setNumberOfDays(resultSet.getInt("during_in_days"));
            course.setCourseMemberList(new CourseMemberDAO().getMembersOfSpecificCourse(course.getId()));

            courses.add(course);
        }

        super.closeEverything();
        return courses;
    }

    @Override
    public Course get(int courseID) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "SELECT * FROM course WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, courseID);

        ResultSet resultSet = preparedStatement.executeQuery();

        Course course = new Course();
        if (resultSet.next()) {
            course.setId(resultSet.getInt("id"));
            course.setCursusCode(resultSet.getString("code"));
            course.setTitle(resultSet.getString("title"));
            course.setStartDate(resultSet.getDate("start_date").toLocalDate());
            course.setNumberOfDays(resultSet.getInt("during_in_days"));

            course.setCourseMemberList(new CourseMemberDAO().getMembersOfSpecificCourse(course.getId()));
        }

        super.closeEverything();
        return course;
    }

    @Override
    public int add(Course object) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "INSERT INTO course (code, title, start_date, during_in_days) VALUES (?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, object.getCursusCode());
        preparedStatement.setString(2, object.getTitle());

        Date date = Date.valueOf(object.getStartDate());
        preparedStatement.setDate(3, date);

        preparedStatement.setInt(4, object.getNumberOfDays());

        int result = preparedStatement.executeUpdate();
        super.closeEverything();
        return result;
    }

    @Override
    public int delete(int id) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int update(Course course) throws SQLException, ClassNotFoundException {
        return 0;
    }

    public List<Course> getCoursesOfSpecificMember(int memberID) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "SELECT course.id, course.code, course.title, course.start_date, course.during_in_days " +
                "FROM course INNER JOIN member_link_course " +
                "ON member_link_course.course_id = course.id WHERE member_link_course.course_member_id = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, memberID);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Course> courses = getCoursesList(resultSet);
        super.closeEverything();

        return courses;
    }

    public List<Course> getCoursesList(ResultSet resultSet) throws SQLException {
        List<Course> courses = new LinkedList<>();

        while (resultSet.next()) {
            Course course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setCursusCode(resultSet.getString("code"));
            course.setTitle(resultSet.getString("title"));
            course.setStartDate(resultSet.getDate("start_date").toLocalDate());
            course.setNumberOfDays(resultSet.getInt("during_in_days"));

            courses.add(course);
        }
        return courses;
    }

    public boolean importFile(List<Course> courseList) throws SQLException, ClassNotFoundException {

        for (Course course : courseList) {
            add(course);
        }
        return true;
    }
}
