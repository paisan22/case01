package resources;

import domain.Facade;
import domain.CourseMember;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */

@Path("/members")
public class CourseMemberResource implements Resource <CourseMember> {

    @Override
    public List<CourseMember> getAll() throws SQLException, ClassNotFoundException {
        return new Facade().getAllCourseMembers();
    }

    @Override
    public CourseMember get(int id) throws SQLException, ClassNotFoundException {
        return new Facade().getCourseMember(id);
    }

    @Override
    public Response add(CourseMember courseMember) throws SQLException, ClassNotFoundException {

        int i = new Facade().addCourseMember(courseMember);

        if (i == 1) {
            return Response.ok().build();
        } else {
            return Response.noContent().build();
        }
    }

    @Override
    public Response delete(int id) throws SQLException, ClassNotFoundException {

        int i = new Facade().deleteCourseMember(id);

        if (i == 1) {
            return Response.ok().build();
        } else {
            return Response.noContent().build();
        }
    }

    @Override
    public Response update(CourseMember courseMember) throws SQLException, ClassNotFoundException {

        int i = new Facade().updateCourseMember(courseMember);

        if (i == 1) {
            return Response.ok().build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/register/{memberID}/{courseID}")
    public Response registerCourseMember(@PathParam("memberID") int memberID, @PathParam("courseID") int courseID)
            throws SQLException, ClassNotFoundException {

        boolean result = new Facade().registerCourseMember(memberID, courseID);

        if (result) {
            return Response.ok().build();
        } else {
            return Response.noContent().build();
        }
    }
}
