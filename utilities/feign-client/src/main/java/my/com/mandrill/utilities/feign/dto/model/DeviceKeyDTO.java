package my.com.mandrill.utilities.feign.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceKeyDTO implements Serializable {

	private String id;

	private String deviceId;

	private String deviceModel;

}
