package asset_location_details.controller.read;

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
import asset_location_details.model.dto.AssetLocationDetail_DTO;
import asset_location_details.service.read.I_AssetLocationDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetLocationDetailsPublicReadMgmt")
public class AssetLocationDetailsPublicRead_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetLocation_AssetLocation_Lontroller.class);

	@Autowired
	private I_AssetLocationDetailsPublicRead_Service assetLocationDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssUtilDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getAllAssetLocation_Details() {

		CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> completableFuture = assetLocationDetailsPublicReadServ
				.getAllAssetLocationDetails();
		CopyOnWriteArrayList<AssetLocationDetail_DTO> assetLocationDetailsDTOs = completableFuture.join();
		// AssUtilDetailsgger.info("size :"+assetLocationDetailsDTOs.size());
		return new ResponseEntity<>(assetLocationDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getSelectAssetLocation_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> completableFuture = assetLocationDetailsPublicReadServ
				.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetLocationDetail_DTO> assetLocationDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetLocationDetailsDTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsBetween/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getDetailsBetweenTimes(@PathVariable String fr,
			@PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> completableFuture = assetLocationDetailsPublicReadServ
				.getDetailsBetweenTimes(fr, to);
		CopyOnWriteArrayList<AssetLocationDetail_DTO> assetLocationDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetLocationDetailsDTOs2, HttpStatus.OK);
	}

}