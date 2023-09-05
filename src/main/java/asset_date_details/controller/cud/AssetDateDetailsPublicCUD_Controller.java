package asset_date_details.controller.cud;

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
import asset_date_details.model.dto.AssetDateDetail_DTO;
import asset_date_details.model.master.AssetDateDetailPK;
import asset_date_details.service.cud.I_AssetDateDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/assetDateDetailsPublicCUDMgmt")
public class AssetDateDetailsPublicCUD_Controller {

	// private static final Logger AssDateDetailsgger =
	// LoggerFactory.getLogger(AssetPrice_AssetPrice_Lontroller.class);

	@Autowired
	private I_AssetDateDetailsPublicCUD_Service assetDateDetailsPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<AssetDateDetail_DTO> newAssetPrice(@RequestBody AssetDateDetail_DTO assetDateDetailsDTO) 
	{
		CompletableFuture<AssetDateDetail_DTO> completableFuture = assetDateDetailsPublicCUDServ.newAssetDateDetail(assetDateDetailsDTO);
		AssetDateDetail_DTO assetDateDetailsDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetDateDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAssetPrice")
	public void updateAssetPrice(@RequestBody AssetDateDetail_DTO assetDateDetailsDTO) {
		assetDateDetailsPublicCUDServ.updAssetDateDetail(assetDateDetailsDTO);
	}

	@DeleteMapping("/delSelectAssDateDetails")
	public void deleteSelectAssDateDetails(@RequestBody CopyOnWriteArrayList<AssetDateDetailPK> seqNos) 
	{
		assetDateDetailsPublicCUDServ.delSelectAssetDateDetails(seqNos);
	}

	@DeleteMapping("/delAllAssDateDetails")
	public void deleteAllAssetPrice() {
		assetDateDetailsPublicCUDServ.delAllAssetDateDetails();
	}

}