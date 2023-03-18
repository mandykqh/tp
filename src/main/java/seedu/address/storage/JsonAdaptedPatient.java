package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedPatient extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private ArrayList<JsonAdaptedAppointment> patientAppointments = new ArrayList<>();

    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("nric") String nric,
                              @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("patientAppointments") ArrayList<JsonAdaptedAppointment> patientAppointments) {
        super(name, phone, email, nric, address, tagged);
        this.patientAppointments = patientAppointments;

        if (this.patientAppointments != null) {
            this.patientAppointments.addAll(patientAppointments);
        }
    }

    public JsonAdaptedPatient(Person source) {
        super(source);
        Patient patient = (Patient) source;

        patientAppointments.addAll(patient.getPatientAppointments().stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    @Override
    public Patient toModelType() throws IllegalValueException {
        Person person = super.toModelType();
        final ArrayList<Appointment> appointments = new ArrayList<>();
        for (JsonAdaptedAppointment appointment : patientAppointments) {
            appointments.add(appointment.toModelType());
        }
        final ArrayList<Appointment> modelAppointments = new ArrayList<>(appointments);
        return new Patient(person.getName(), person.getPhone(), person.getEmail(), person.getNric(), person.getAddress(),
                person.getTags(), modelAppointments);
    }
}

