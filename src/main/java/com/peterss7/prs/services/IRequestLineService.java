package com.peterss7.prs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreate;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreateResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineDefaultResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineUpdate;

public interface IRequestLineService {

	public abstract ResponseEntity<RequestLineDefaultResponse> findRequestLineById(Integer id);

	public abstract ResponseEntity<RequestLineCreateResponse> createRequestLine(RequestLineCreate requestLineNew);

	public abstract ResponseEntity<String> updateRequestLine(RequestLineUpdate updateRequestLine);

	public abstract ResponseEntity<String> deleteRequestLine(Integer id);

	public abstract ResponseEntity<List<RequestLineDefaultResponse>> findByRequest(Integer requestId);

}
