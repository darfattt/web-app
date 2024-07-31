package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PropertyStagingDTO implements Serializable {

	private String id;

	private String userId;

	private String address1;

	private String address2;

	private String address3;

	private ObjectRequest utility;

}
