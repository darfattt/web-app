package my.com.mandrill.utilities.storage.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class Attachment extends ContentFile {

	private static final long serialVersionUID = 1L;

	private String url;

	private File file;

}
