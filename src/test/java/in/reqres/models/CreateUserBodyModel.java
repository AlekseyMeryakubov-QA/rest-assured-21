package in.reqres.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class CreateUserBodyModel {
    String name,job;
    @JsonIgnoreProperties(ignoreUnknown = true)
    Integer id;
    @JsonIgnoreProperties(ignoreUnknown = true)
    String createdAt;
}