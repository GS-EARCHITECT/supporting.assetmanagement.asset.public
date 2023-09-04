package asset_structure_details.controller.read;

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

import asset_structure_details.model.dto.AssetStructureDetail_DTO;
import asset_structure_details.service.read.I_AssetStructureDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetStructureDetailsPublicReadMgmt")
public class AssetStructureDetailsPublicRead_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetStructure_AssetStructure_Lontroller.class);

	@Autowired
	private I_AssetStructureDetailsPublicRead_Service assetStructureDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssUtilDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getAllAssetStructure_Details() {

		CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> completableFuture = assetStructureDetailsPublicReadServ
				.getAllAssetStructureDetails();
		CopyOnWriteArrayList<AssetStructureDetail_DTO> assetStructureDetailsDTOs = completableFuture.join();
		// AssUtilDetailsgger.info("size :"+assetStructureDetailsDTOs.size());
		return new ResponseEntity<>(assetStructureDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getSelectAssetStructure_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> completableFuture = assetStructureDetailsPublicReadServ
				.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetStructureDetail_DTO> assetStructureDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetStructureDetailsDTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsBetween/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getDetailsBetweenTimes(@PathVariable String fr,
			@PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> completableFuture = assetStructureDetailsPublicReadServ
				.getDetailsBetweenTimes(fr, to);
		CopyOnWriteArrayList<AssetStructureDetail_DTO> assetStructureDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetStructureDetailsDTOs2, HttpStatus.OK);
	}

}