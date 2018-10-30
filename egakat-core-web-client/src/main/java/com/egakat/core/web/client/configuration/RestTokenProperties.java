package com.egakat.core.web.client.configuration;

public interface RestTokenProperties {
	String getApiKeyHeader();

	void setApiKeyHeader(String value);

	String getApiKeyValue();

	void setApiKeyValue(String value);

	String getApiTokenHeader();

	void setApiTokenHeader(String value);

	String getApiTokenValue();

	void setApiTokenValue(String value);
}