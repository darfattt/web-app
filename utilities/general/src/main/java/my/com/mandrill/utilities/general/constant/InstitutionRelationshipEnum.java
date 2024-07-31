package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstitutionRelationshipEnum {

	PCM_MEMBER("PCM_MEMBER", "PCM Member");

	private final String code;

	private final String name;

}
