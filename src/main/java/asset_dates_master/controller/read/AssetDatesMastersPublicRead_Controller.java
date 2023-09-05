package asset_dates_master.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_dates_master.model.dto.AssetDatesMaster_DTO;
import asset_dates_master.service.read.I_AssetDatesMastersPublicRead_Service;

@RestController
@RequestMapping("/assetDatesMastersPublicReadMgmt")
public class AssetDatesMastersPublicRead_Controller {

	// private static final Logger AssDatesMastersgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetDatesMastersPublicRead_Service assetDatesMastersPublicReadServ;

	@GetMapping(value = "/getAllAssDatesMasters", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetDatesMaster_DTO>> getAllAssetPrice_DatesMasters() {

		CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> completableFuture = assetDatesMastersPublicReadServ
				.getAllAssetDatesMasters();
		CopyOnWriteArrayList<AssetDatesMaster_DTO> assetDatesMastersDTOs = completableFuture.join();
		// AssDatesMastersgger.info("size :"+assetDatesMastersDTOs.size());
		return new ResponseEntity<>(assetDatesMastersDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDatesMasters", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetDatesMaster_DTO>> getSelectAssetPrice_DatesMasters(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {

		CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> completableFuture = assetDatesMastersPublicReadServ
				.getSelectAssetDatesMasters(seqNos);
		CopyOnWriteArrayList<AssetDatesMaster_DTO> assetDatesMastersDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetDatesMastersDTOs2, HttpStatus.OK);
	}

}