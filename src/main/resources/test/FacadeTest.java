package test;

import database.DatabaseFacade;
import domain.Course;
import domain.Facade;
import domain.CourseMember;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by paisanrietbroek on 10/10/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class FacadeTest {

    @Mock
    Facade facade;

    @InjectMocks
    DatabaseFacade databaseFacade;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        when(facade.getAllCourseMembers()).thenReturn(new LinkedList<CourseMember>());
    }

    @Test
    public void checkMethodCall() throws SQLException, ClassNotFoundException {
        facade.getAllCourseMembers();
        verify(facade, times(1)).getAllCourseMembers();
    }

    @Test
    public void importFile() throws SQLException, IOException, ClassNotFoundException {
        List<Course> b = new Facade().importFile("courses.txt");

        assertThat(b.size() > 0, is(true));
    }
}