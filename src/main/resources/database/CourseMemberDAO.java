package database;

import domain.Course;
import domain.CourseMember;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */

public class CourseMemberDAO extends DAO<CourseMember> {

    @Override
    public List<CourseMember> getAll () throws SQLException, ClassNotFoundException {
        super.getConnection();

        List<CourseMember> courseMemberList = new LinkedList<CourseMember>();

        String sql = "SELECT * FROM course_member";

        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            CourseMember courseMember = new CourseMember();

            courseMember.setId(resultSet.getInt("id"));
            courseMember.setFirstname(resultSet.getString("firstname"));
            courseMember.setPreposition(resultSet.getString("preposition"));
            courseMember.setLastname(resultSet.getString("lastname"));
            courseMember.setAddress(resultSet.getString("address"));
            courseMember.setCity(resultSet.getString("city"));

            courseMember.setCourseList(new CourseDAO().getCoursesOfSpecificMember(courseMember.getId()));

            courseMemberList.add(courseMember);
        }
        super.closeEverything();
        return courseMemberList;
    }

    @Override
    public CourseMember get(int id) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "SELECT * FROM course_member WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        CourseMember courseMember = new CourseMember();

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            courseMember.setId(resultSet.getInt("id"));
            courseMember.setFirstname(resultSet.getString("firstname"));
            courseMember.setPreposition(resultSet.getString("preposition"));
            courseMember.setLastname(resultSet.getString("lastname"));
            courseMember.setAddress(resultSet.getString("address"));
            courseMember.setCity(resultSet.getString("city"));
        }
        super.closeEverything();
        return courseMember;
    }

    @Override
    public int add(CourseMember courseMember) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "INSERT INTO course_member (firstname, preposition, lastname, address, city) VALUES (?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, courseMember.getFirstname());
        preparedStatement.setString(2, courseMember.getPreposition());
        preparedStatement.setString(3, courseMember.getLastname());
        preparedStatement.setString(4, courseMember.getAddress());
        preparedStatement.setString(5, courseMember.getCity());

        int result = preparedStatement.executeUpdate();
        super.closeEverything();
        return result;
    }

    @Override
    public int delete(int id) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "DELETE FROM course_member WHERE id = ?";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();

        super.closeEverything();
        return result;
    }

    @Override
    public int update(CourseMember courseMember) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "UPDATE course_member SET " +
                "firstname = ?, " +
                "preposition = ?, " +
                "lastname = ?, " +
                "address = ?, " +
                "city = ? " +
                "WHERE id = ?";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, courseMember.getFirstname());
        preparedStatement.setString(2, courseMember.getPreposition());
        preparedStatement.setString(3, courseMember.getLastname());
        preparedStatement.setString(4, courseMember.getAddress());
        preparedStatement.setString(5, courseMember.getCity());
        preparedStatement.setInt(6, courseMember.getId());

        int result = preparedStatement.executeUpdate();

        super.closeEverything();
        return result;
    }

    public boolean registerToCourse(int courseMemberID, int courseID) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "INSERT INTO member_link_course (course_member_id, course_id) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, courseMemberID);
        preparedStatement.setInt(2, courseID);

        boolean result = preparedStatement.execute();

        super.closeEverything();
        return result;
    }

    public List<CourseMember> getMembersOfSpecificCourse(int courseID) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String sql = "SELECT " +
                "course_member.id, " +
                "course_member.firstname, " +
                "course_member.preposition, " +
                "course_member.lastname, " +
                "course_member.address, " +
                "course_member.city " +
                "FROM course_member INNER JOIN member_link_course " +
                "ON member_link_course.course_member_id = course_member.id " +
                "WHERE member_link_course.course_id = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, courseID);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<CourseMember> members = getMemberList(resultSet);
        super.closeEverything();

        return members;
    }

    public List<CourseMember> getMemberList(ResultSet resultSet) throws SQLException {
        List<CourseMember> members = new LinkedList<>();

        while (resultSet.next()) {
            CourseMember courseMember = new CourseMember();
            courseMember.setId(resultSet.getInt("id"));
            courseMember.setFirstname(resultSet.getString("firstname"));
            courseMember.setPreposition(resultSet.getString("preposition"));
            courseMember.setLastname(resultSet.getString("lastname"));
            courseMember.setAddress(resultSet.getString("address"));
            courseMember.setCity(resultSet.getString("city"));

            members.add(courseMember);
        }
        return members;
    }
}
