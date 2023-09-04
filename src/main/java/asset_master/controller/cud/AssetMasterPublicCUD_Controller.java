package asset_master.controller.cud;

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
import asset_master.model.dto.AssetMaster_DTO;
import asset_master.service.cud.I_AssetMasterPublicCUD_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetMasterPublicCUDManagement")
public class AssetMasterPublicCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMaster_Controller.class);

	@Autowired
	private I_AssetMasterPublicCUD_Service assetMasterPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetMaster_DTO> newasset(@RequestBody AssetMaster_DTO assetDTO) 
	{
		CompletableFuture<AssetMaster_DTO> completableFuture = assetMasterPublicCUDServ.newAssetMaster(assetDTO);
		AssetMaster_DTO assetDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updasset")
	public void updateasset(@RequestBody AssetMaster_DTO assetDTO) 
	{
		assetMasterPublicCUDServ.updAssetMaster(assetDTO);
		return;
	}

	@PutMapping("/updAssetDoneStatus{assetSeqNo}/{assetSt]")
	public void updateAssetStatus(@PathVariable Long id, @PathVariable Character assetSt) 
	{
		assetMasterPublicCUDServ.setAssetDoneStatus(id, assetSt);
		return;
	}

	@DeleteMapping("/delSelectasset")
	public void deleteSelectasset(@RequestBody CopyOnWriteArrayList<Long> assetSeqNoList) 
	{
		assetMasterPublicCUDServ.delSelectAssets(assetSeqNoList);	
		return;
	}

	@DeleteMapping("/delSelectAssetsByResources")
	public void delSelectAssetsByResources(@RequestBody CopyOnWriteArrayList<Long> rSeqNoList) {
		assetMasterPublicCUDServ.delSelectAssetsByResources(rSeqNoList);
		return;
	}

	@DeleteMapping("/delAllasset")
	public void deleteAllassets() {
		assetMasterPublicCUDServ.delAllAssetMasters();
		;
		return;
	}
}