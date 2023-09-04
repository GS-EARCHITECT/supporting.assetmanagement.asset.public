package asset_users_details.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import asset_users_details.model.dto.AssetUsersDetail_DTO;
import asset_users_details.service.read.I_AssetUsersDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetUsersDetailsPublicReadMgmt")
public class AssetUsersDetailsPublicRead_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetUsers_AssetUsers_Lontroller.class);

	@Autowired
	private I_AssetUsersDetailsPublicRead_Service assetUsersDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssUtilDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getAllAssetUsers_Details() {

		CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> completableFuture = assetUsersDetailsPublicReadServ
				.getAllAssetUsersDetails();
		CopyOnWriteArrayList<AssetUsersDetail_DTO> assetUsersDetailsDTOs = completableFuture.join();
		// AssUtilDetailsgger.info("size :"+assetUsersDetailsDTOs.size());
		return new ResponseEntity<>(assetUsersDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getSelectAssetUsers_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> completableFuture = assetUsersDetailsPublicReadServ
				.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetUsersDetail_DTO> assetUsersDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetUsersDetailsDTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsBetween/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getDetailsBetweenTimes(@PathVariable String fr,
			@PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> completableFuture = assetUsersDetailsPublicReadServ
				.getDetailsBetweenTimes(fr, to);
		CopyOnWriteArrayList<AssetUsersDetail_DTO> assetUsersDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetUsersDetailsDTOs2, HttpStatus.OK);
	}

}