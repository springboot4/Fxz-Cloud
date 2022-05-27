package com.fxz.common.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/24 21:52
 */
@Configuration
public class ElasticsearchClientConfig {

	@Resource
	private ObjectMapper objectMapper;

	@Bean
	public RestClient restClient() {
		return RestClient.builder(new HttpHost("fxz-elasticsearch", 9210)).build();
	}

	@Bean
	public JacksonJsonpMapper jacksonJsonpMapper() {
		return new JacksonJsonpMapper(objectMapper);
	}

	@Bean
	public ElasticsearchTransport elasticsearchTransport() {
		return new RestClientTransport(restClient(), jacksonJsonpMapper());
	}

	@Bean
	public ElasticsearchClient elasticsearchClient() {
		return new ElasticsearchClient(elasticsearchTransport());
	}

}
