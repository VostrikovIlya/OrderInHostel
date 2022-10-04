package viv.hostel.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import viv.hostel.entity.Order;

import java.io.IOException;

public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        this(null);
    }

    protected OrderSerializer(Class<Order> src) {
        super(src);
    }

    @Override
    public void serialize(Order order, JsonGenerator json, SerializerProvider serializerProvider) throws IOException {
        json.writeStartObject();
        json.writeStringField("name", order.getName());
        json.writeStringField("description", order.getDescription());
        json.writeStringField("complete", Boolean.toString(order.isComplete()));
        json.writeStringField("dateOfApp", order.getDateOfApplication().toString());
        if(order.getDateOfExecution() != null)
            json.writeStringField("dateOfExec", order.getDateOfExecution().toString());
        json.writeFieldName("department");
        json.writeStartObject();
        json.writeStringField("name", order.getDepartment().getName());
        json.writeStringField("slug", order.getDepartment().getSlug());
        json.writeStringField("phone", order.getDepartment().getPhone());
        json.writeEndObject();
        json.writeStringField("user", order.getUser().getFirstName() + " " + order.getUser().getLastName());
        if(order.getEmployee() != null)
            json.writeStringField("employee", order.getEmployee().getFirstName() + " " + order.getEmployee().getLastName());
        else
            json.writeStringField("employee", "no appointment");
        json.writeEndObject();
    }
}
