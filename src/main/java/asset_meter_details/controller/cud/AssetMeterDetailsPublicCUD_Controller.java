package asset_meter_details.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import asset_meter_details.model.dto.AssetMeterDetail_DTO;
import asset_meter_details.model.master.AssetMeterDetailPK;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_meter_details.service.cud.I_AssetMeterDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetMeterDetailsPublicCUDMgmt")
public class AssetMeterDetailsPublicCUD_Controller {

	// private static final Logger AssUtilDetailsgger =
	// LoggerFactory.getLogger(AssetMeter_AssetMeter_Lontroller.class);

	@Autowired
	private I_AssetMeterDetailsPublicCUD_Service assetMeterDetailsPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetMeterDetail_DTO> newAssetMeter(@RequestBody AssetMeterDetail_DTO assetMeterDetailsDTO) {

		CompletableFuture<AssetMeterDetail_DTO> completableFuture = assetMeterDetailsPublicCUDServ
				.newMeterDetail(assetMeterDetailsDTO);
		AssetMeterDetail_DTO assetMeterDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetMeterDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAssetMeter")
	public void updateAssetMeter(@RequestBody AssetMeterDetail_DTO assetMeterDetailsDTO) {
		assetMeterDetailsPublicCUDServ.updMeterDetail(assetMeterDetailsDTO);
		return;
	}

	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<AssetMeterDetailPK> seqNos) {
		assetMeterDetailsPublicCUDServ.delSelectMeterDetails(seqNos);
	}

	@DeleteMapping("/delDetailsBetween/{fr}/{to}")
	public void delDetailsBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		assetMeterDetailsPublicCUDServ.delSelectMeterDetailsBetweenTimes(fr, to);
		return;
	}

	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetMeter() {
		assetMeterDetailsPublicCUDServ.delAllMeterDetails();
	}

}