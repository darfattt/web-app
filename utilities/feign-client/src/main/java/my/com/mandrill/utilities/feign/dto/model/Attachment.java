package my.com.mandrill.utilities.feign.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment implements Serializable {

	private String fileName;

	private String url;

	private File file;

}
