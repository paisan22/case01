package test;

import domain.Course;
import domain.Invoice;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by paisanrietbroek on 12/10/16.
 */
public class InvoiceTest {
    @Test
    public void getCourses() throws Exception {

        List<Course> courseList = new Invoice().getCourses(42);

        assertThat(courseList.size() != 0, is(true));
    }

}