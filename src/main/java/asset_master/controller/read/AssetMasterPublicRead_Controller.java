package asset_master.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import asset_master.model.dto.AssetMaster_DTO;
import asset_master.service.read.I_AssetMasterPublicRead_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetPublicReadManagement")
public class AssetMasterPublicRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMaster_Controller.class);

	@Autowired
	private I_AssetMasterPublicRead_Service assetMasterPublicReadServ;

	@GetMapping(value = "/getAllAssets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMaster_DTO>> getAllAssetMasters() 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> completableFuture = assetMasterPublicReadServ.getAllAssetMasters();
		CopyOnWriteArrayList<AssetMaster_DTO> assetDTOs = completableFuture.join();
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAssets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMaster_DTO>> getSelectAssetMasters(@RequestBody CopyOnWriteArrayList<Long> assetSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> completableFuture = assetMasterPublicReadServ.getSelectAssets(assetSeqNos);
		CopyOnWriteArrayList<AssetMaster_DTO> assetDTOs = completableFuture.join();
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAssetsByResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AssetMaster_DTO>> getSelectAssetMastersByResources(
			@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> completableFuture = assetMasterPublicReadServ.getSelectAssetsByResources(ids);
		CopyOnWriteArrayList<AssetMaster_DTO> assetDTOs = completableFuture.join();
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
		
	}

	@GetMapping(value = "/getDoneStatus/{assetSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getAssetDoneStatus(@PathVariable Long assetSeqNo) 
	{
		CompletableFuture<Character> completableFuture = assetMasterPublicReadServ.getAssetDoneStatus(assetSeqNo);
		Character assetSt = completableFuture.join();
		return new ResponseEntity<>(assetSt, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStatus/{assetSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getAssetStatus(@PathVariable Long assetSeqNo) 
	{
		CompletableFuture<Character> completableFuture = assetMasterPublicReadServ.getAssetStatus(assetSeqNo);
		Character assetSt = completableFuture.join();
		return new ResponseEntity<>(assetSt, HttpStatus.OK);
	}

}