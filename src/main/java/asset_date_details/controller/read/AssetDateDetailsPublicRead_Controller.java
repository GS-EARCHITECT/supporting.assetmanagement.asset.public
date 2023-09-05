package asset_date_details.controller.read;

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
import asset_date_details.model.dto.AssetDateDetail_DTO;
import asset_date_details.service.read.I_AssetDateDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetDateDetailsPublicReadMgmt")
public class AssetDateDetailsPublicRead_Controller {

	// private static final Logger AssDateDetailsgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetDateDetailsPublicRead_Service assetDateDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssDateDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetDateDetail_DTO>> getAllAssetPrice_DateDetails() {

		CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> completableFuture = assetDateDetailsPublicReadServ
				.getAllAssetDateDetails();
		CopyOnWriteArrayList<AssetDateDetail_DTO> assetDateDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetDateDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDateDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetDateDetail_DTO>> getSelectAssetPrice_DateDetails(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {

		CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> completableFuture = assetDateDetailsPublicReadServ
				.getSelectAssetDateDetails(seqNos);
		CopyOnWriteArrayList<AssetDateDetail_DTO> assetDateDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetDateDetailsDTOs, HttpStatus.OK);
	}

}