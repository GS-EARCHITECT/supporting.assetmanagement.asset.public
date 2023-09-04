package asset_price_details.controller.read;

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

import asset_price_details.model.dto.AssetPriceDetail_DTO;
import asset_price_details.service.read.I_AssetPriceDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetPriceDetailsPublicReadMgmt")
public class AssetPriceDetailsPublicRead_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetPriceDetailsPublicRead_Service assetPriceDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssUtilDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getAllAssetPrice_Details() {

		CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> completableFuture = assetPriceDetailsPublicReadServ
				.getAllAssetPriceDetails();
		CopyOnWriteArrayList<AssetPriceDetail_DTO> assetPriceDetailsDTOs = completableFuture.join();
		// AssUtilDetailsgger.info("size :"+assetPriceDetailsDTOs.size());
		return new ResponseEntity<>(assetPriceDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getSelectAssetPrice_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> completableFuture = assetPriceDetailsPublicReadServ
				.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetPriceDetail_DTO> assetPriceDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetPriceDetailsDTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsBetween/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getDetailsBetweenTimes(@PathVariable String fr,
			@PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> completableFuture = assetPriceDetailsPublicReadServ
				.getDetailsBetweenTimes(fr, to);
		CopyOnWriteArrayList<AssetPriceDetail_DTO> assetPriceDetailsDTOs2 = completableFuture.join();
		return new ResponseEntity<>(assetPriceDetailsDTOs2, HttpStatus.OK);
	}

}