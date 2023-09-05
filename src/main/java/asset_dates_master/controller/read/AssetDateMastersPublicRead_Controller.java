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
import asset_dates_master.model.dto.AssetDateMaster_DTO;
import asset_dates_master.service.read.I_AssetDateMastersPublicRead_Service;

@RestController
@RequestMapping("/assetDateMastersPublicReadMgmt")
public class AssetDateMastersPublicRead_Controller {

	// private static final Logger AssDateMastersgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetDateMastersPublicRead_Service assetDateMastersPublicReadServ;

	@GetMapping(value = "/getAllAssDateMasters", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetDateMaster_DTO>> getAllAssetPrice_DateMasters() {

		CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> completableFuture = assetDateMastersPublicReadServ
				.getAllAssetDateMasters();
		CopyOnWriteArrayList<AssetDateMaster_DTO> assetDateMastersDTOs = completableFuture.join();
		// AssDateMastersgger.info("size :"+assetDateMastersDTOs.size());
		return new ResponseEntity<>(assetDateMastersDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDateMasters", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetDateMaster_DTO>> getSelectAssetPrice_DateMasters(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {

		CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> completableFuture = assetDateMastersPublicReadServ
				.getSelectAssetDateMasters(seqNos);
		CopyOnWriteArrayList<AssetDateMaster_DTO> assetDateMastersDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetDateMastersDTOs2, HttpStatus.OK);
	}

}