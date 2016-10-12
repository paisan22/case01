package domain;

import database.DatabaseFacade;
import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */

@Getter
public class Facade {
    DatabaseFacade databaseFacade;

    public Facade() {
        this.databaseFacade = new DatabaseFacade();
    }

    public List<CourseMember> getAllCourseMembers() throws SQLException, ClassNotFoundException {
        return this.databaseFacade.getCourseMemberList();
    }

    public CourseMember getCourseMember(int id) throws SQLException, ClassNotFoundException {
        return this.databaseFacade.getCourseMember(id);
    }

    public int addCourseMember(CourseMember courseMember) throws SQLException, ClassNotFoundException {
        return this.databaseFacade.addCourseMember(courseMember);
    }

    public int deleteCourseMember(int id) throws SQLException, ClassNotFoundException {
        return this.databaseFacade.deleteCourseMember(id);
    }

    public int updateCourseMember(CourseMember courseMember) throws SQLException, ClassNotFoundException {
        return this.databaseFacade.updateCourseMember(courseMember);
    }

    public boolean registerCourseMember(int courseMemberID, int courseID) throws SQLException, ClassNotFoundException {
        return this.databaseFacade.registerCourseMember(courseMemberID, courseID);
    }

    public List<Course> getAllCourses() throws SQLException, ClassNotFoundException{
        return this.databaseFacade.getAllCourses();
    }

    public Course getSpecificCourse(int courseID) throws SQLException, ClassNotFoundException {
        return this.databaseFacade.getSpecificCourse(courseID);
    }

    public List<Course> importFile(String pathToFile) throws IOException, SQLException, ClassNotFoundException {
        List<Course> courseList = new ImportCourses().importFile(pathToFile);
        return this.databaseFacade.importFile(courseList) ? courseList : null;
    }

}
