package br.com.sisdb.vendas.services;


import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;

@Service
public class S3Service {
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public void uploadFile(String localFilePath) {
		
	   try {	   
           File file = new File(localFilePath);
           LOG.info("Iniciando upload");
           s3client.putObject(bucketName, "teste", file);
           LOG.info("Finalizando upload");
	   }catch (AmazonServiceException e) {
		   LOG.info("AmazonServiceException: "+e.getErrorMessage());
		   LOG.info("Status code: "+e.getStatusCode());
	   }catch (AmazonClientException e) {
		   LOG.info("AmazonClientException: "+e.getMessage());
	   }
	}
	

}















