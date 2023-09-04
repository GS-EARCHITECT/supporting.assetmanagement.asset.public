package asset_meter_details.controller.read;

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
import asset_meter_details.model.dto.AssetMeterDetail_DTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_meter_details.service.read.I_AssetMeterDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetMeterDetailsPublicReadMgmt")
public class AssetMeterDetailsPublicRead_Controller {

	// private static final Logger MeterUtilDetailsgger =
	// LoggerFactory.getLogger(AssetMeter_AssetMeter_Lontroller.class);

	@Autowired
	private I_AssetMeterDetailsPublicRead_Service assetMeterDetailsPublicReadServ;

	@GetMapping(value = "/getAllMeterUtilDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getAllAssetMeter_Details() {
		CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> completableFuture = assetMeterDetailsPublicReadServ
				.getAllMeterDetails();
		CopyOnWriteArrayList<AssetMeterDetail_DTO> assetMeterDetailsDTOs = completableFuture.join();
		// MeterUtilDetailsgger.info("size :"+assetMeterDetailsDTOs.size());
		return new ResponseEntity<>(assetMeterDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getSelectAssetMeter_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> completableFuture = assetMeterDetailsPublicReadServ
				.getSelectMeterDetails(seqNos);
		CopyOnWriteArrayList<AssetMeterDetail_DTO> assetMeterDetailsDTOs = completableFuture.join();
		// MeterUtilDetailsgger.info("size :"+assetMeterDetailsDTOs.size());
		return new ResponseEntity<>(assetMeterDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsBetween/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getDetailsBetweenTimes(@PathVariable String fr,
			@PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> completableFuture = assetMeterDetailsPublicReadServ
				.getMeterDetailsBetweenTimes(fr, to);
		CopyOnWriteArrayList<AssetMeterDetail_DTO> assetMeterDetailsDTOs = completableFuture.join();
		// MeterUtilDetailsgger.info("size :"+assetMeterDetailsDTOs.size());
		return new ResponseEntity<>(assetMeterDetailsDTOs, HttpStatus.OK);

	}

}