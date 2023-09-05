package asset_dates_master.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_dates_master.model.dto.AssetDatesMaster_DTO;
import asset_dates_master.service.cud.I_AssetDatesMastersPublicCUD_Service;

@RestController
@RequestMapping("/assetDatesMastersPublicCUDMgmt")
public class AssetDatesMastersPublicCUD_Controller {

	// private static final Logger AssDatesMastersgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetDatesMastersPublicCUD_Service assetDatesMastersPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetDatesMaster_DTO> newAssetPrice(@RequestBody AssetDatesMaster_DTO assetDatesMastersDTO) 
	{
		CompletableFuture<AssetDatesMaster_DTO> completableFuture = assetDatesMastersPublicCUDServ.newAssetDatesMaster(assetDatesMastersDTO);
		AssetDatesMaster_DTO assetDatesMastersDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetDatesMastersDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAssetPrice")
	public void updateAssetPrice(@RequestBody AssetDatesMaster_DTO assetDatesMastersDTO) {
		assetDatesMastersPublicCUDServ.updAssetDatesMaster(assetDatesMastersDTO);
	}

	@DeleteMapping("/delSelectAssDatesMasters")
	public void deleteSelectAssDatesMasters(@RequestBody CopyOnWriteArrayList<Long> seqNos) {
		assetDatesMastersPublicCUDServ.delSelectAssetDatesMasters(seqNos);
	}

	@DeleteMapping("/delAllAssDatesMasters")
	public void deleteAllAssetPrice() {
		assetDatesMastersPublicCUDServ.delAllAssetDatesMasters();
	}

}