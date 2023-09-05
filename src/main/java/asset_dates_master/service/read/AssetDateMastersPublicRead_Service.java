package asset_dates_master.service.read;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import asset_dates_master.model.dto.AssetDateMaster_DTO;
import asset_dates_master.model.master.AssetDateMaster;
import asset_dates_master.model.repo.read.AssetDateMastersPublicRead_Repo;
import asset_master.model.dto.AssetMaster_DTO;

@Service("assetDateMastersPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetDateMastersPublicRead_Service implements I_AssetDateMastersPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetDateMastersService.class);

	@Autowired
	private AssetDateMastersPublicRead_Repo assetDateMastersPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> getAllAssetDateMasters() {
		
		CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetDateMaster> assetDateMasters = (CopyOnWriteArrayList<AssetDateMaster>) assetDateMastersPublicReadRepo.getAllAssetDateMasters();
		CopyOnWriteArrayList<AssetDateMaster_DTO> lMasterss = assetDateMasters != null
				? this.getAssetDateMasterDtos(assetDateMasters)
				: null;
				return lMasterss;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> getSelectAssetDateMasters(CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetDateMaster> assetDateMasters = (CopyOnWriteArrayList<AssetDateMaster>) assetDateMastersPublicReadRepo.getSelectAssetDateMasters(ids);
		CopyOnWriteArrayList<AssetDateMaster_DTO> lMasterss = assetDateMasters != null
				? this.getAssetDateMasterDtos(assetDateMasters)
				: null;
				return lMasterss;
   		},asyncExecutor);
		return future;

	}

	private synchronized CopyOnWriteArrayList<AssetDateMaster_DTO> getAssetDateMasterDtos(CopyOnWriteArrayList<AssetDateMaster> lMasters) {
		AssetDateMaster_DTO lDTO = null;
		CopyOnWriteArrayList<AssetDateMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetDateMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetDateMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetDateMaster_DTO getAssetDateMaster_DTO(AssetDateMaster lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetDateMaster_DTO lDTO = new AssetDateMaster_DTO();
		lDTO.setDateSeqNo(lMaster.getDateSeqNo());
		lDTO.setDateType(lMaster.getDateType());
		return lDTO;
	}

}