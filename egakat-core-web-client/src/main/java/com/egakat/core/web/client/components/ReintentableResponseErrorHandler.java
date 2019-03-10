package com.egakat.core.web.client.components;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.egakat.core.web.client.exception.ReintentableException;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReintentableResponseErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		boolean result = super.hasError(httpResponse);
		return result;
	}

	private static final Charset DEFAULT_CHARSET = ISO_8859_1;

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		boolean retry = false;

		retry = retry(httpResponse);

		if (retry) {
			val status = httpResponse.getStatusCode();
			val statusText = httpResponse.getStatusText();
			val body = getResponseBody(httpResponse);
			val charset = getCharset(httpResponse);

			log.debug("Status code: {}", status);
			log.debug("Status text: {}", statusText);
			log.debug("Body: {}", new String(body));
			log.debug("Charset: {}", charset.displayName());

			ReintentableException e = new ReintentableException(status, statusText, httpResponse.getHeaders(), body,
					charset);
			throw e;
		} else {
			super.handleError(httpResponse);
		}
	}

	protected boolean retry(ClientHttpResponse httpResponse) throws IOException {
		boolean result;

		val code = httpResponse.getStatusCode();
		switch (code) {
		case UNAUTHORIZED:
		case REQUEST_TIMEOUT:
		case BAD_GATEWAY:
		case SERVICE_UNAVAILABLE:
		case GATEWAY_TIMEOUT:
			result = true;
			break;
		default:
			result = false;
		}

		return result;
	}

	@Override
	protected byte[] getResponseBody(ClientHttpResponse response) {
		try {
			val result = FileCopyUtils.copyToByteArray(response.getBody());
			if (log.isDebugEnabled()) {
				log.debug("Body: {}", new String(result));
			}
			return result;
		} catch (IOException ex) {
			// ignore
			ex.printStackTrace();
		}
		return new byte[0];
	}

	@Override
	protected Charset getCharset(ClientHttpResponse response) {
		return Optional.ofNullable(super.getCharset(response)).orElse(DEFAULT_CHARSET);
	}
}