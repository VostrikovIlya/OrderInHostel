package viv.hostel.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import viv.hostel.entity.Department;
import viv.hostel.entity.Employee;

import java.io.IOException;

public class DepartSerializer extends StdSerializer<Department> {
    public DepartSerializer() {
        this(null);
    }

    public DepartSerializer(Class<Department> src) {
        super(src);
    }

    @Override
    public void serialize(Department dep, JsonGenerator json, SerializerProvider serializerProvider) throws IOException {
        json.writeStartObject();
        json.writeStringField("name", dep.getName());
        json.writeStringField("slug", dep.getSlug());
        json.writeStringField("phone", dep.getPhone());
        json.writeFieldName("employees");
        json.writeStartArray();
        for (Employee emp : dep.getEmployees()) {
            if (emp.isEnabled()) {
                json.writeStartObject();
                json.writeStringField("name", emp.getFirstName() + " " + emp.getLastName());
                json.writeStringField("phone", emp.getPhone());
                json.writeEndObject();
            }
        }
        json.writeEndArray();
        json.writeEndObject();
    }


}
