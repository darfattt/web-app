package my.com.mandrill.utilities.core.filter.wrapper;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class CachedHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final String body;

	@SuppressWarnings("all")
	public CachedHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();

			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

				char[] charBuffer = new char[128];
				int bytesRead = -1;

				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		}
		catch (IOException e) {
			log.error("Failed when read input stream from request. Error: {}", e.getMessage());
			throw e;
		}
		finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

		body = stringBuilder.toString();
	}

	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// Do nothing
			}

			public int read() {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

}
