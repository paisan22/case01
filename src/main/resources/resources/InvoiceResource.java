package resources;

import domain.Invoice;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by paisanrietbroek on 12/10/16.
 */

@Path("/invoices")
public class InvoiceResource implements Resource{
    @Override
    public List<?> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Object get(int weekNumber) throws SQLException, ClassNotFoundException {
        return Invoice.getMembersInSpecificWeek(weekNumber);
    }

    @Override
    public Response add(Object o) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Response delete(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Response update(Object o) throws SQLException, ClassNotFoundException {
        return null;
    }
}
