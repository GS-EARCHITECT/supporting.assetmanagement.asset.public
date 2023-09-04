package asset_price_details.controller.cud;

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

import asset_price_details.model.dto.AssetPriceDetail_DTO;
import asset_price_details.model.master.AssetPriceDetailPK;
import asset_price_details.service.cud.I_AssetPriceDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetPriceDetailsPublicCUDMgmt")
public class AssetPriceDetailsPublicCUD_Controller 
{

	//private static final Logger AssUtilDetailsgger = LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetPriceDetailsPublicCUD_Service assetPriceDetailsPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetPriceDetail_DTO> newAssetPrice(@RequestBody AssetPriceDetail_DTO assetPriceDetailsDTO) 
	{
		CompletableFuture<AssetPriceDetail_DTO> completableFuture = assetPriceDetailsPublicCUDServ.newAssetPriceDetail(assetPriceDetailsDTO);
		AssetPriceDetail_DTO assetPriceDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetPriceDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updAssetPrice")
	public void updateAssetPrice(@RequestBody AssetPriceDetail_DTO assetPriceDetailsDTO) 
	{
		assetPriceDetailsPublicCUDServ.updAssetPriceDetail(assetPriceDetailsDTO);		
	}
	
	@DeleteMapping("/delSelectAssUtilDetails")
	public void deleteSelectAssUtilDetails(@RequestBody CopyOnWriteArrayList<AssetPriceDetailPK> seqNos) {
		assetPriceDetailsPublicCUDServ.delSelectDetails(seqNos);
	}
	
	@DeleteMapping("/delDetailsBetween/{fr}/{to}")
	public void delDetailsBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		assetPriceDetailsPublicCUDServ.delSelectDetailsBetweenTimes(fr, to);		
		return;
	}
	
	@DeleteMapping("/delAllAssUtilDetails")
	public void deleteAllAssetPrice() {
		assetPriceDetailsPublicCUDServ.delAllAssetPriceDetails();
	}
	
	
	}