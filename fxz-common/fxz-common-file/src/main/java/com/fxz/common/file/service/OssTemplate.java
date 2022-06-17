package com.fxz.common.file.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.fxz.common.file.OssProperties;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author fxz
 */
@RequiredArgsConstructor
public class OssTemplate implements InitializingBean {

	private final OssProperties ossProperties;

	private AmazonS3 amazonS3;

	/*
	 * ===================================================================================
	 * =======================================创建桶、列出桶、删除桶=====================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/create-bucket-overview.
	 * html
	 */
	/**
	 * 创建桶
	 * @param bucketName bucket名称
	 */
	@SneakyThrows
	public void createBucket(String bucketName) {
		if (!amazonS3.doesBucketExistV2(bucketName)) {
			amazonS3.createBucket(new CreateBucketRequest(bucketName));
		}
	}

	/**
	 * 列出桶
	 */
	@SneakyThrows
	public List<Bucket> getAllBuckets() {
		ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
		return amazonS3.listBuckets(listBucketsRequest);
	}

	/**
	 * 删除桶
	 * @param bucketName bucket名称
	 */
	@SneakyThrows
	public void removeBucket(String bucketName) {
		amazonS3.deleteBucket(new DeleteBucketRequest(bucketName));
	}
	/*
	 * ===================================================================================
	 * ============================================创建桶、列出桶、删除桶============================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/create-bucket-overview.
	 * html
	 */

	/*
	 * ===================================================================================
	 * =================================================上传对象==============================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/upload-objects.html
	 */

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream 文件流
	 * @param contextType 文件类型
	 * @throws Exception
	 */
	public void putObject(String bucketName, String objectName, InputStream stream, String contextType)
			throws Exception {
		putObject(bucketName, objectName, stream, stream.available(), contextType);
	}

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream 文件流
	 * @throws Exception
	 */
	public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
		putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
	}

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream 文件流
	 * @param size 大小
	 * @param contextType 类型
	 * @throws Exception
	 */
	public PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size,
			String contextType) throws Exception {
		// String fileName = getFileName(objectName);
		byte[] bytes = IOUtils.toByteArray(stream);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);
		objectMetadata.setContentType(contextType);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		// 上传
		return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);

	}

	/*
	 * ===================================================================================
	 * =================================================上传对象==============================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/upload-objects.html
	 * ===================================================================================
	 * =================================================下载对象==============================
	 */
	/*
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/download-objects.html
	 */
	/**
	 * 获取文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @return 二进制流
	 */
	@SneakyThrows
	public S3Object getObject(String bucketName, String objectName) {
		return amazonS3.getObject(new GetObjectRequest(bucketName, objectName));
	}

	/**
	 * 获取文件信息
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 */
	public S3Object getObjectInfo(String bucketName, String objectName) throws Exception {
		@Cleanup
		S3Object object = amazonS3.getObject(bucketName, objectName);
		return object;
	}

	/*
	 * ===================================================================================
	 * =================================================下载对象==============================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/download-objects.html
	 * ===================================================================================
	 * =================================================删除对象==============================
	 * /* https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/delete-objects.html
	 */
	/**
	 * 删除文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @throws Exception
	 * @see <a href=
	 * "http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteObject">AWS API
	 * Documentation</a>
	 */
	public void removeObject(String bucketName, String objectName) throws Exception {
		amazonS3.deleteObject(new DeleteObjectRequest(bucketName, objectName));
	}

	/*
	 * ===================================================================================
	 * =================================================删除对象==============================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/delete-objects.html
	 * ===================================================================================
	 * =================================================使用前缀列出对象==========================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/ListingKeysUsingAPIs.
	 * html
	 */
	/**
	 * 根据文件前置查询文件
	 * @param bucketName bucket名称
	 * @param prefix 前缀
	 * @param recursive 是否递归查询
	 * @return S3ObjectSummary 列表
	 */
	@SneakyThrows
	public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
		ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
		return new ArrayList<>(objectListing.getObjectSummaries());
	}
	/*
	 * ===================================================================================
	 * =================================================使用前缀列出对象==========================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/ListingKeysUsingAPIs.
	 * htm
	 * ===================================================================================
	 */

	/*
	 * =================================================获取预签名url==========================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/ShareObjectPreSignedURL
	 * .html
	 */
	/**
	 * 获取文件外链
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param expires 过期时间 <=7
	 * @return url
	 * @see AmazonS3#generatePresignedUrl(String bucketName, String key, Date expiration)
	 */
	@SneakyThrows
	public String getObjectURL(String bucketName, String objectName, Integer expires) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, expires);
		URL url = amazonS3.generatePresignedUrl(bucketName, objectName, calendar.getTime());
		return url.toString();
	}
	/*
	 * =================================================获取预签名url==========================
	 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/userguide/ShareObjectPreSignedURL
	 * .html
	 */

	@Override
	public void afterPropertiesSet() {
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setProtocol(Protocol.HTTP);

		AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
				ossProperties.getEndpoint(), Region.getRegion(Regions.CN_NORTH_1).getName());

		AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(),
				ossProperties.getSecretKey());

		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

		this.amazonS3 = AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration)
				.withClientConfiguration(clientConfiguration).withCredentials(awsCredentialsProvider)
				.disableChunkedEncoding().withPathStyleAccessEnabled(ossProperties.getPathStyleAccess()).build();
	}

}
