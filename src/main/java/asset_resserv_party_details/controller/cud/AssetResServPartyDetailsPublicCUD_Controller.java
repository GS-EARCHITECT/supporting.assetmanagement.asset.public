package asset_resserv_party_details.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_resserv_party_details.model.dto.AssetResServPartyDetail_DTO;
import asset_resserv_party_details.service.cud.I_AssetResServPartyDetailsPublicCUD_Service;

@RestController
@RequestMapping("/assetResServPartyDetailsPublicCUDMgmt")
public class AssetResServPartyDetailsPublicCUD_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetResServParty_AssetResServParty_Lontroller.class);

	@Autowired
	private I_AssetResServPartyDetailsPublicCUD_Service assetResServPartyDetailsPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetResServPartyDetail_DTO> newAssetResServParty(
			@RequestBody AssetResServPartyDetail_DTO assetResServPartyDetailsDTO) 
	{
		CompletableFuture<AssetResServPartyDetail_DTO> completableFuture = assetResServPartyDetailsPublicCUDServ.newAssetResServPartyDetail(assetResServPartyDetailsDTO);
		AssetResServPartyDetail_DTO assetResServPartyDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetResServPartyDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAssetResServParty")
	public void updateAssetResServParty(@RequestBody AssetResServPartyDetail_DTO assetResServPartyDetailsDTO) {
		assetResServPartyDetailsPublicCUDServ.updAssetResServPartyDetail(assetResServPartyDetailsDTO);
	}

	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		assetResServPartyDetailsPublicCUDServ.delSelectDetails(seqNos);
	}

	@DeleteMapping("/delDetailsForParties")
	public void delDetailsForParties(@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		assetResServPartyDetailsPublicCUDServ.delDetailsForParties(seqNos);
	}

	@DeleteMapping("/delDetailsForResources")
	public void delDetailsForResources(@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		assetResServPartyDetailsPublicCUDServ.delDetailsForResources(seqNos);
	}

	@DeleteMapping("/delDetailsForServices")
	public void delDetailsForServices(@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		assetResServPartyDetailsPublicCUDServ.delDetailsForServices(seqNos);
	}

	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetResServParty() {
		assetResServPartyDetailsPublicCUDServ.delAllAssetResServPartyDetails();
	}

}