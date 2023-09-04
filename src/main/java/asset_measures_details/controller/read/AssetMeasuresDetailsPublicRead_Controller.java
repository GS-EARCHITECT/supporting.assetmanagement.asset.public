package asset_measures_details.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import asset_measures_details.model.dto.AssetMeasuresDetail_DTO;
import asset_measures_details.service.read.I_AssetMeasuresDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetMeasuresDetailsPublicReadMgmt")
public class AssetMeasuresDetailsPublicRead_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetMeasures_AssetMeasures_Lontroller.class);

	@Autowired
	private I_AssetMeasuresDetailsPublicRead_Service assetMeasuresDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssUtilDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> getAllAssetMeasures_Details() {

		CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> completableFuture = assetMeasuresDetailsPublicReadServ
				.getAllAssetMeasuresDetails();
		CopyOnWriteArrayList<AssetMeasuresDetail_DTO> assetMeasuresDetailsDTOs = completableFuture.join();
		// AssUtilDetailsgger.info("size :"+assetMeasuresDetailsDTOs.size());
		return new ResponseEntity<>(assetMeasuresDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> getSelectAssetMeasures_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> completableFuture = assetMeasuresDetailsPublicReadServ
				.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetMeasuresDetail_DTO> assetMeasuresDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetMeasuresDetailsDTOs2, HttpStatus.OK);
	}

}