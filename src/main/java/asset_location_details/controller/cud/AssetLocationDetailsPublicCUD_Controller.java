package asset_location_details.controller.cud;

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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import asset_location_details.model.dto.AssetLocationDetail_DTO;
import asset_location_details.model.master.AssetLocationDetailPK;
import asset_location_details.service.cud.I_AssetLocationDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetLocationDetailsPublicCUDMgmt")
public class AssetLocationDetailsPublicCUD_Controller 
{

	//private static final Logger AssUtilDetailsgger = LoggerFactory.getLogger(AssetLocation_AssetLocation_Lontroller.class);

	@Autowired
	private I_AssetLocationDetailsPublicCUD_Service assetLocationDetailsPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetLocationDetail_DTO> newAssetLocation(@RequestBody AssetLocationDetail_DTO assetLocationDetailsDTO) 
	{
		CompletableFuture<AssetLocationDetail_DTO> completableFuture = assetLocationDetailsPublicCUDServ.newAssetLocationDetail(assetLocationDetailsDTO);
		AssetLocationDetail_DTO assetLocationDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetLocationDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updAssetLocation")
	public void updateAssetLocation(@RequestBody AssetLocationDetail_DTO assetLocationDetailsDTO) 
	{
		assetLocationDetailsPublicCUDServ.updAssetLocationDetail(assetLocationDetailsDTO);		
	}
	
	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<AssetLocationDetailPK> seqNos) {
		assetLocationDetailsPublicCUDServ.delSelectDetails(seqNos);
	}
	
	@DeleteMapping("/delDetailsBetween/{fr}/{to}")
	public void delDetailsBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		assetLocationDetailsPublicCUDServ.delSelectDetailsBetweenTimes(fr, to);		
		return;
	}
	
	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetLocation() {
		assetLocationDetailsPublicCUDServ.delAllAssetLocationDetails();
	}
	
	
	}