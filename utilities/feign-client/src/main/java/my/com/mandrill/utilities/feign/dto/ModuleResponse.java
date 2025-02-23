package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleResponse implements Serializable {

	private String id;

	private String code;

	private String routes;

	private String icon;

	private List<SubModuleResponse> subModuleResponses;

	private Boolean active;

	private Integer sequence;

}
