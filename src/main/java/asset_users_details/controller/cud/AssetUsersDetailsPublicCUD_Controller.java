package asset_users_details.controller.cud;

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

import asset_users_details.model.dto.AssetUsersDetail_DTO;
import asset_users_details.model.master.AssetUsersDetailPK;
import asset_users_details.service.cud.I_AssetUsersDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetUsersDetailsPublicCUDMgmt")
public class AssetUsersDetailsPublicCUD_Controller 
{

	//private static final Logger AssUtilDetailsgger = LoggerFactory.getLogger(AssetUsers_AssetUsers_Lontroller.class);

	@Autowired
	private I_AssetUsersDetailsPublicCUD_Service assetUsersDetailsPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetUsersDetail_DTO> newAssetUsers(@RequestBody AssetUsersDetail_DTO assetUsersDetailsDTO) 
	{
		CompletableFuture<AssetUsersDetail_DTO> completableFuture = assetUsersDetailsPublicCUDServ.newAssetUsersDetail(assetUsersDetailsDTO);
		AssetUsersDetail_DTO assetUsersDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetUsersDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updAssetUsers")
	public void updateAssetUsers(@RequestBody AssetUsersDetail_DTO assetUsersDetailsDTO) 
	{
		assetUsersDetailsPublicCUDServ.updAssetUsersDetail(assetUsersDetailsDTO);		
	}
	
	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<AssetUsersDetailPK> seqNos) {
		assetUsersDetailsPublicCUDServ.delSelectDetails(seqNos);
	}
	
	@DeleteMapping("/delDetailsBetween/{fr}/{to}")
	public void delDetailsBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		assetUsersDetailsPublicCUDServ.delSelectDetailsBetweenTimes(fr, to);		
		return;
	}
	
	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetUsers() {
		assetUsersDetailsPublicCUDServ.delAllAssetUsersDetails();
	}
	
	
	}