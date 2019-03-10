package com.egakat.core.web.client.properties;

public interface RestTokenGeneratorProperties extends RestTokenProperties{
	String getApiTokenUrlGenerator();

	void setApiTokenUrlGenerator(String value);
}