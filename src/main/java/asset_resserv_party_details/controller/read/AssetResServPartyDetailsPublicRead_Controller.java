package asset_resserv_party_details.controller.read;

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
import asset_resserv_party_details.model.dto.AssetResServPartyDetail_DTO;
import asset_resserv_party_details.service.read.I_AssetResServPartyDetailsPublicRead_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetResServPartyDetailsPublicReadMgmt")
public class AssetResServPartyDetailsPublicRead_Controller {

	// private static final Logger AssResServPartyDetailsgger =
	// LoggerFactory.getLogger(AssetResServParty_AssetResServParty_Lontroller.class);

	@Autowired
	private I_AssetResServPartyDetailsPublicRead_Service assetResServPartyDetailsPublicReadServ;

	@GetMapping(value = "/getAllAssResServPartyDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getAllAssetResServParty_Details() {
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> completableFuture = assetResServPartyDetailsPublicReadServ
				.getAllAssetResServPartyDetails();
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> assetResServPartyDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetResServPartyDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getSelectAssetResServParty_Details(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> completableFuture = assetResServPartyDetailsPublicReadServ
				.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> assetResServPartyDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetResServPartyDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsForParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForParties(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {

		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> completableFuture = assetResServPartyDetailsPublicReadServ
				.getDetailsForParties(seqNos);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> assetResServPartyDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetResServPartyDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsForResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForResources(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> completableFuture = assetResServPartyDetailsPublicReadServ
				.getDetailsForResources(seqNos);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> assetResServPartyDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetResServPartyDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsForServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForServices(
			@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> completableFuture = assetResServPartyDetailsPublicReadServ
				.getDetailsForServices(seqNos);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> assetResServPartyDetailsDTOs = completableFuture.join();
		return new ResponseEntity<>(assetResServPartyDetailsDTOs, HttpStatus.OK);
	}

}