package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Description;
import seedu.address.model.client.Email;
import seedu.address.model.client.Frequency;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Priority;
import seedu.address.model.client.ProductPreference;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String productPreference;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final int frequency;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("productPreference") String productPreference,
                             @JsonProperty("frequency") int frequency,
                             @JsonProperty("description") String description,
                             @JsonProperty("priority") String priority) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.productPreference = productPreference;
        this.frequency = frequency;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        productPreference = source.getProductPreference()
                .map(ProductPreference::toString)
                .orElse(null);
        frequency = source.getProductPreference()
                .map(preference -> preference.getFrequency().frequency)
                .orElse(0);
        description = source.getDescription()
                .map(Description::toString)
                .orElse(null);
        priority = source.getPriority()
                .map(Priority::toString)
                .orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            clientTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(clientTags);

        final Optional<ProductPreference> modelProductPreference;

        final Priority modelPriority = priority == null ? null : Priority.fromString(priority);

        if (productPreference == null) {
            modelProductPreference = Optional.empty();
        } else {

            if (!Frequency.isValidFrequency(frequency)) {
                throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
            }
            final Frequency productFrequency = new Frequency(frequency);

            modelProductPreference = Optional.of(new ProductPreference(productPreference, productFrequency));
        }

        final Optional<Description> modelDescription;

        if (description == null) {
            modelDescription = Optional.empty();
        } else {
            if (!Description.isValidDescription(description)) {
                throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
            }
            modelDescription = Optional.of(new Description(description));
        }

        return new Client(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                modelProductPreference, modelDescription, Optional.ofNullable(modelPriority));
    }

}
