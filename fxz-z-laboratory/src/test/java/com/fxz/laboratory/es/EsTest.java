package com.fxz.laboratory.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/28 21:53
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.17/docs.html"/>
 */
@Slf4j
@SpringBootTest
public class EsTest {

	@Resource
	private ElasticsearchClient elasticsearchClient;

	@Test
	public void createIndex() throws IOException {
		elasticsearchClient.indices().create(c -> c.index("test"));
	}

	@Test
	public void deleteIndex() throws IOException {
		elasticsearchClient.indices().delete(d -> d.index("text"));
	}

	/**
	 * @see <a href=
	 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/7.17/indexing.html"/>
	 */
	@Test
	public void createDocument() throws IOException {
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "fxz");
		map.put("age", "18");
		map.put("des", "emm..");

		elasticsearchClient.index(d -> d.index("test").id("1").document(map));

		IndexRequest<Map> request = IndexRequest.of(i -> i.index("test").id("2").document(map));
		elasticsearchClient.index(request);

		IndexRequest.Builder<Map> indexReqBuilder = new IndexRequest.Builder<>();
		indexReqBuilder.index("test");
		indexReqBuilder.id("3");
		indexReqBuilder.document(map);
		elasticsearchClient.index(indexReqBuilder.build());
	}

	@Test
	public void updateDocument() throws IOException {
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "2342342");
		map.put("age", "22");
		elasticsearchClient.update(u -> u.index("test").id("2").doc(map), HashMap.class);
	}

	@Test
	public void deleteDocument() throws IOException {
		elasticsearchClient.delete(d -> d.index("test").id("1"));
	}

	@Test
	public void getDocument() throws IOException {
		GetResponse<Map> response = elasticsearchClient.get(d -> d.index("test").id("1"), Map.class);
		Map source = response.source();
		log.info("map:{}", source);
	}

	/**
	 * @see <a href=
	 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/7.17/searching.html"/>
	 */
	@Test
	public void search() throws IOException {

		final String searchText = "fxz";
		SearchResponse<Map> response = elasticsearchClient
				.search(s -> s.index("test").query(q -> q.match(t -> t.field("name").query(searchText))), Map.class);
		log.info("命中数量:{}", response.hits().total().value());
		log.info("命中关系:{}", response.hits().total().relation());
		log.info("查询结果:{}", response.hits().hits());

		String age = "19";
		Query byName = MatchQuery.of(m -> m.field("name").query(searchText))._toQuery();
		Query byAge = MatchQuery.of(m -> m.field("age").query(age))._toQuery();
		response = elasticsearchClient.search(s -> s.index("test").query(q -> q.bool(b -> b.must(byName).must(byAge))),
				Map.class);
		List<Hit<Map>> hits = response.hits().hits();
		for (Hit<Map> hit : hits) {
			Map map = hit.source();
			log.info("map:{} ", map);
		}
	}

	/**
	 * @see <a href=
	 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/7.17/indexing-bulk.html"/>
	 */
	@Test
	public void bulk() throws IOException {
		List<BulkOperation> operations = new LinkedList<>();

		for (int i = 1; i <= 10; i++) {
			String id = String.valueOf(i);
			HashMap<String, String> map = new HashMap<>();
			map.put("name", "fxz");
			map.put("age", "18");
			map.put("des", "emm..");

			BulkOperation operation = new BulkOperation.Builder().index(c -> c.index("test").id(id).document(map))
					.build();
			operations.add(operation);
		}

		elasticsearchClient.bulk(b -> b.operations(operations));

		BulkRequest.Builder br = new BulkRequest.Builder();

		for (int i = 11; i <= 20; i++) {
			String id = String.valueOf(i);
			HashMap<String, String> map = new HashMap<>();
			map.put("name", "fxz");
			map.put("age", "19");
			map.put("des", "emm..");

			br.operations(op -> op.index(idx -> idx.index("test").id(id).document(map)));
		}

		elasticsearchClient.bulk(br.build());
	}

	/**
	 * @see <a href=
	 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/7.17/aggregations.html"/>
	 */
	@Test
	public void agg() throws IOException {
		String searchText = "fxz";

		Query query = MatchQuery.of(m -> m.field("name").query(searchText))._toQuery();

		SearchResponse<Void> response = elasticsearchClient.search(b -> b.index("test").size(0).query(query)
				.aggregations("ageAgg", a -> a.terms(t -> t.field("age.keyword"))), Void.class);

		List<StringTermsBucket> buckets = response.aggregations().get("ageAgg").sterms().buckets().array();

		for (StringTermsBucket bucket : buckets) {
			log.info("There are " + bucket.docCount() + " bikes under " + bucket.key());
		}
	}

}
