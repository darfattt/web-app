package my.com.mandrill.utilities.storage.model;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class InputContentFile extends ContentFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] file;

	private String path;

	private String key;

	private Map<String, String> metadata = new HashMap<>();

}
