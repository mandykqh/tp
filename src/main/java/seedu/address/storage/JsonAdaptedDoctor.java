package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;

import java.util.List;

public class JsonAdaptedDoctor extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Doctor's %s field is missing!";

    public JsonAdaptedDoctor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("nric") String nric, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, phone, email, nric, address, tagged);
    }

    public JsonAdaptedDoctor(Person source) {
        super(source);
        Doctor doctor = (Doctor) source;
    }

    @Override
    public Doctor toModelType() throws IllegalValueException {
        Person person = super.toModelType();

        return new Doctor(person.getName(), person.getPhone(), person.getEmail(), person.getNric(), person.getAddress(),
                person.getTags());
    }
}
