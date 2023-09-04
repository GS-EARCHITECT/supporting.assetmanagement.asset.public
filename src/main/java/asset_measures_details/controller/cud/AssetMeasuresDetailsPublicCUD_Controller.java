package asset_measures_details.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import asset_measures_details.model.dto.AssetMeasuresDetail_DTO;
import asset_measures_details.model.master.AssetMeasuresDetailPK;
import asset_measures_details.service.cud.I_AssetMeasuresDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetMeasuresDetailsPublicCUDMgmt")
public class AssetMeasuresDetailsPublicCUD_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetMeasures_AssetMeasures_Lontroller.class);

	@Autowired
	private I_AssetMeasuresDetailsPublicCUD_Service assetMeasuresDetailsPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetMeasuresDetail_DTO> newAssetMeasures(
			@RequestBody AssetMeasuresDetail_DTO assetMeasuresDetailsDTO) {
		CompletableFuture<AssetMeasuresDetail_DTO> completableFuture = assetMeasuresDetailsPublicCUDServ
				.newAssetMeasuresDetail(assetMeasuresDetailsDTO);
		AssetMeasuresDetail_DTO assetMeasuresDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetMeasuresDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAssetMeasures")
	public void updateAssetMeasures(@RequestBody AssetMeasuresDetail_DTO assetMeasuresDetailsDTO) {
		assetMeasuresDetailsPublicCUDServ.updAssetMeasuresDetail(assetMeasuresDetailsDTO);
	}

	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<AssetMeasuresDetailPK> seqNos) {
		assetMeasuresDetailsPublicCUDServ.delSelectDetails(seqNos);
	}

	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetMeasures() {
		assetMeasuresDetailsPublicCUDServ.delAllAssetMeasuresDetails();
	}

}