package my.com.mandrill.utilities.general.constant;

public enum InstitutionType {

	MAIN, SITE, ENTITY;

	public static InstitutionType fromHasSubsidiaries(Boolean hasSubsidiaries) {
		if (Boolean.TRUE.equals(hasSubsidiaries))
			return MAIN;
		else if (Boolean.FALSE.equals(hasSubsidiaries))
			return SITE;
		return null;
	}

}
