package oss.snafu.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;

/**
 *
 * The corresponding {@link ServletResponseWrapper} for mitigating the JSON injection.
 *
 *
 *
 * @author ckatzorke
 *
 *
 */

public class JsonInjectionMitigationResponseWrapper extends ServletResponseWrapper {

	public JsonInjectionMitigationResponseWrapper(ServletResponse response) {
		super(response);
	}

	private boolean prefixed = false;

	private static final String PRE = ")[}',\n";

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (prefixed) {
			return super.getOutputStream();
		} else {
			final ServletOutputStream outputStream = super.getOutputStream();
			outputStream.print(PRE);
			outputStream.flush();
			prefixed = true;
			return outputStream;
		}
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (prefixed) {
			return super.getWriter();
		} else {
			final PrintWriter writer = super.getWriter();
			writer.write(PRE);
			prefixed = true;
			return writer;
		}
	}

}