package com.peterss7.prs.services;

import java.net.http.HttpResponse;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.controllers.UserController;
import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.User;
import com.peterss7.prs.entities.dtos.request.RequestNew;
import com.peterss7.prs.entities.dtos.request.RequestNewResponse;
import com.peterss7.prs.entities.dtos.request.RequestRejection;
import com.peterss7.prs.entities.dtos.request.RequestReviewResponse;
import com.peterss7.prs.entities.dtos.request.RequestUpdate;
import com.peterss7.prs.entities.dtos.request.RequestDefaultResponse;
import com.peterss7.prs.entities.dtos.user.UserSecureView;
import com.peterss7.prs.repositories.RequestRepository;
import com.peterss7.prs.specifications.RequestSpecifications;

@Service
public class RequestService implements IRequestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestService.class);

	@Autowired
	private UserService userService;

	private final RequestRepository requestRepository;

	public RequestService(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}

	@Override
	public List<RequestDefaultResponse> findAllRequests() {

		return RequestDefaultResponse.getResponses(requestRepository.findAll());
	}

	@Override
	public ResponseEntity<RequestDefaultResponse> findRequestById(int id) {

		Optional<Request> optionalRequest = requestRepository.findById(id);

		if (optionalRequest.isPresent()) {

			RequestDefaultResponse request = RequestDefaultResponse.getResponse(optionalRequest.get());

			return new ResponseEntity<RequestDefaultResponse>(request, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public User getRequestUser(int id) {

		User user = new User();

		Optional<User> optionalUser = userService.findRawUserById(id);

		if (optionalUser.isEmpty()) {
			LOGGER.warn("no user found in getrequestuser");
			return user;
		} else {
			LOGGER.warn("user found in getrequestuser");
			return optionalUser.get();
		}

	}

	@Override
	public ResponseEntity<List<RequestDefaultResponse>> findRequestsByFields(Specification<Request> spec) {

		Optional<List<Request>> optionalRequests = requestRepository.findAll(spec);

		if (optionalRequests.isPresent()) {

			List<RequestDefaultResponse> response = RequestDefaultResponse.getResponses(optionalRequests.get());

			return new ResponseEntity<List<RequestDefaultResponse>>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	
	public List<Request> findRequestsByFieldsRaw(int id) {

		// Optional<List<Request>> optionalRequests = requestRepository.findAll(spec);
		LOGGER.warn("checking id: " + id);
		
		List<Request> requests = requestRepository.findAll();
		
		List<Request> foundUserRequests = new ArrayList<Request>();
		
		for (Request request : requests) {
			if (request.getUser().getId() == id) {
				foundUserRequests.add(request);
			}
		}
		LOGGER.warn("optional requests is: " + foundUserRequests.size());

		if (foundUserRequests.size() > 0) {
			
			LOGGER.warn("optional requests is: " + foundUserRequests.size());

			

			return foundUserRequests;
		} else {
			return null;
		}

	}

	@Override
	public ResponseEntity<RequestNewResponse> createRequest(RequestNew newRequestObject) {

		try {
			Request newRequest = new Request();

			newRequest.setDescription(newRequestObject.getDescription());
			newRequest.setJustification(newRequestObject.getJustification());
			newRequest.setDeliveryMode(newRequestObject.getDeliveryMode());
			newRequest.setDateNeeded(newRequestObject.getDateNeeded());
			newRequest.setUser(userService.findRawUserById(newRequestObject.getUserId()).get());

			Request savedRequest = requestRepository.save(newRequest);

			RequestNewResponse response = RequestNewResponse.getResponse(savedRequest);

			return new ResponseEntity<RequestNewResponse>(response, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<String> updateRequest(RequestUpdate requestUpdate) {

		try {
			Request request = new Request();

			Optional<Request> optionalRequest = requestRepository.findById(requestUpdate.getId());

			if (optionalRequest.isPresent()) {

				request = optionalRequest.get();

				if (requestUpdate.getDescription() != null) {
					request.setDescription(requestUpdate.getDescription());
				}
				if (requestUpdate.getJustification() != null) {
					request.setJustification(requestUpdate.getJustification());
				}
				if (requestUpdate.getRejectionReason() != null) {
					request.setRejectionReason(requestUpdate.getRejectionReason());
				}
				if (requestUpdate.getDateNeeded() != null) {
					request.setDateNeeded(requestUpdate.getDateNeeded());
				}
				if (requestUpdate.getTotal() != null) {
					request.setTotal(requestUpdate.getTotal());
				}
				if (requestUpdate.getDeliveryMode() != null) {
					request.setDeliveryMode(requestUpdate.getDeliveryMode());
				}				
				if (requestUpdate.getUserId() != null) {
					request.setUser(userService.findRawUserById(requestUpdate.getUserId()).get());
				}

				requestRepository.save(request);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Request updated");

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request not found");
		}

	}

	@Override
	public ResponseEntity<String> deleteRequest(int id) {

		try {

			Optional<Request> optionalRequest = requestRepository.findById(id);

			if (!optionalRequest.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("REQUEST NOT FOUND");
			} else {
				requestRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("REQUEST DELETED");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UNKNOWN ERROR OCCURED");
		}

	}

	@Override
	public ResponseEntity<Void> reviewRequest(int id) {

		try {
			
			

			Optional<Request> optionalRequest = requestRepository.findById(id);

			if (optionalRequest.isPresent()) {

				Request underReviewRequest = optionalRequest.get();
				Double underReviewTotal = underReviewRequest.getTotal();
				
				if (!underReviewRequest.getStatus().equals("PENDING")) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

				
					if (underReviewTotal == 0) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					} 
					else if (underReviewTotal >= 50) {
						underReviewRequest.setStatus("REVIEW");
						requestRepository.save(underReviewRequest);
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					} else if (underReviewTotal < 50) {
						underReviewRequest.setStatus("APPROVED");
						requestRepository.save(underReviewRequest);
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
					else {
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Void> approveRequest(int id) {
		LOGGER.warn("id: " + id);
		try {
			LOGGER.warn("id: " + id);

			Optional<Request> optionalRequest = requestRepository.findById(id);
			LOGGER.warn("id: " + id);

			if (optionalRequest.isPresent()) {

				Request underReviewRequest = optionalRequest.get();

				if (underReviewRequest.getStatus().equals("REVIEW")) {
					underReviewRequest.setStatus("APPROVED");
					LOGGER.warn("success??");
					requestRepository.save(underReviewRequest);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Void> rejectRequest(RequestRejection requestRejection) {

		try {

			Optional<Request> optionalRequest = requestRepository.findById(requestRejection.getId());

			if (optionalRequest.isPresent()) {

				Request underReviewRequest = optionalRequest.get();

				if (underReviewRequest.getStatus().equals("REVIEW")) {
					underReviewRequest.setStatus("REJECTED");
					underReviewRequest.setRejectionReason(requestRejection.getRejectionReason());
					requestRepository.save(underReviewRequest);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Override
	public ResponseEntity<List<RequestReviewResponse>> findRequestsInReview(int userId){
		
		try {
			
			Optional<List<Request>> optionalRequests = 
					requestRepository.findAll(RequestSpecifications.statusLike("REVIEW"));
			
			LOGGER.warn("optionalRequests isPresent: " + optionalRequests.isPresent());
			
			if (optionalRequests.isPresent()) {
				
				List<Request> requestsNotUserId = new ArrayList<Request>();
				List<Request> unfilteredRequests = optionalRequests.get();
				
				LOGGER.warn("unfilteredRequests size: " + unfilteredRequests.size());
				
				for (Request request : unfilteredRequests) {
					if (request.getUser().getId() != userId) {
						LOGGER.warn("Request added to requestsNotUserId");
						requestsNotUserId.add(request);
					}
				}
				
				return new ResponseEntity<List<RequestReviewResponse>>
					(RequestReviewResponse.getReviewResponses(requestsNotUserId), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}

}
