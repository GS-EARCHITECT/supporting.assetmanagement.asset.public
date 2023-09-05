package asset_dates_master.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_dates_master.model.dto.AssetDateMaster_DTO;
import asset_dates_master.service.cud.I_AssetDateMastersPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetDateMastersPublicCUDMgmt")
public class AssetDateMastersPublicCUD_Controller {

	// private static final Logger AssDateMastersgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetDateMastersPublicCUD_Service assetDateMastersPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetDateMaster_DTO> newAssetPrice(@RequestBody AssetDateMaster_DTO assetDateMastersDTO) 
	{
		CompletableFuture<AssetDateMaster_DTO> completableFuture = assetDateMastersPublicCUDServ.newAssetDateMaster(assetDateMastersDTO);
		AssetDateMaster_DTO assetDateMastersDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetDateMastersDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAssetPrice")
	public void updateAssetPrice(@RequestBody AssetDateMaster_DTO assetDateMastersDTO) {
		assetDateMastersPublicCUDServ.updAssetDateMaster(assetDateMastersDTO);
	}

	@DeleteMapping("/delSelectAssDateMasters")
	public void deleteSelectAssDateMasters(@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		assetDateMastersPublicCUDServ.delSelectAssetDateMasters(seqNos);
	}

	@DeleteMapping("/delAllAssDateMasters")
	public void deleteAllAssetPrice() {
		assetDateMastersPublicCUDServ.delAllAssetDateMasters();
	}

}