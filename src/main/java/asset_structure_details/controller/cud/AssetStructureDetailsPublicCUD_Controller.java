package asset_structure_details.controller.cud;

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

import asset_structure_details.model.dto.AssetStructureDetail_DTO;
import asset_structure_details.model.master.AssetStructureDetailPK;
import asset_structure_details.service.cud.I_AssetStructureDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetStructureDetailsPublicCUDMgmt")
public class AssetStructureDetailsPublicCUD_Controller 
{

	//private static final Logger AssUtilDetailsgger = LoggerFactory.getLogger(AssetStructure_AssetStructure_Lontroller.class);

	@Autowired
	private I_AssetStructureDetailsPublicCUD_Service assetStructureDetailsPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetStructureDetail_DTO> newAssetStructure(@RequestBody AssetStructureDetail_DTO assetStructureDetailsDTO) 
	{
		CompletableFuture<AssetStructureDetail_DTO> completableFuture = assetStructureDetailsPublicCUDServ.newAssetStructureDetail(assetStructureDetailsDTO);
		AssetStructureDetail_DTO assetStructureDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetStructureDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updAssetStructure")
	public void updateAssetStructure(@RequestBody AssetStructureDetail_DTO assetStructureDetailsDTO) 
	{
		assetStructureDetailsPublicCUDServ.updAssetStructureDetail(assetStructureDetailsDTO);		
	}
	
	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<AssetStructureDetailPK> seqNos) {
		assetStructureDetailsPublicCUDServ.delSelectDetails(seqNos);
	}
	
	@DeleteMapping("/delDetailsBetween/{fr}/{to}")
	public void delDetailsBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		assetStructureDetailsPublicCUDServ.delSelectDetailsBetweenTimes(fr, to);		
		return;
	}
	
	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetStructure() {
		assetStructureDetailsPublicCUDServ.delAllAssetStructureDetails();
	}
	
	
	}