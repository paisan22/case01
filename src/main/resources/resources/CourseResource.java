package resources;

import domain.Facade;
import domain.Course;
import domain.ImportCourses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by paisanrietbroek on 10/10/16.
 */

@Path("/courses")
public class CourseResource implements Resource<Course> {
    @Override
    public List<Course> getAll() throws SQLException, ClassNotFoundException {
        return new Facade().getAllCourses();
    }

    @Override
    public Course get(int id) throws SQLException, ClassNotFoundException {
        return new Facade().getSpecificCourse(id);
    }

    @Override
    public Response add(Course course) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Response delete(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Response update(Course course) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Path("/import/{path:.+}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public List<Course> importCourses(@PathParam("path") String path) throws IOException, SQLException, ClassNotFoundException {
        return new Facade().importFile(path);
    }
}
