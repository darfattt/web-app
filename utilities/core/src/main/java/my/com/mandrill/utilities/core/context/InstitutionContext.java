package my.com.mandrill.utilities.core.context;

public class InstitutionContext {

	private static final ThreadLocal<String> institutionId = new ThreadLocal<>();

	public static void setInstitutionId(String id) {
		institutionId.set(id);
	}

	public static String getInstitutionId() {
		return institutionId.get();
	}

	public static void clear() {
		institutionId.remove();
	}

}
