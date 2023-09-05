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
import asset_dates_master.model.dto.AssetDatesMaster_DTO;
import asset_dates_master.model.master.AssetDatesMaster;
import asset_dates_master.model.repo.read.AssetDatesMastersPublicRead_Repo;
import asset_master.model.dto.AssetMaster_DTO;

@Service("assetDatesMastersPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetDatesMastersPublicRead_Service implements I_AssetDatesMastersPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetDatesMastersService.class);

	@Autowired
	private AssetDatesMastersPublicRead_Repo assetDatesMastersPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> getAllAssetDatesMasters() {
		
		CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetDatesMaster> assetDatesMasters = (CopyOnWriteArrayList<AssetDatesMaster>) assetDatesMastersPublicReadRepo.getAllAssetDatesMasters();
		CopyOnWriteArrayList<AssetDatesMaster_DTO> lMasterss = assetDatesMasters != null
				? this.getAssetDatesMasterDtos(assetDatesMasters)
				: null;
				return lMasterss;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> getSelectAssetDatesMasters(CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetDatesMaster> assetDatesMasters = (CopyOnWriteArrayList<AssetDatesMaster>) assetDatesMastersPublicReadRepo.getSelectAssetDatesMasters(ids);
		CopyOnWriteArrayList<AssetDatesMaster_DTO> lMasterss = assetDatesMasters != null
				? this.getAssetDatesMasterDtos(assetDatesMasters)
				: null;
				return lMasterss;
   		},asyncExecutor);
		return future;

	}

	private synchronized CopyOnWriteArrayList<AssetDatesMaster_DTO> getAssetDatesMasterDtos(CopyOnWriteArrayList<AssetDatesMaster> lMasters) {
		AssetDatesMaster_DTO lDTO = null;
		CopyOnWriteArrayList<AssetDatesMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetDatesMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetDatesMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetDatesMaster_DTO getAssetDatesMaster_DTO(AssetDatesMaster lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetDatesMaster_DTO lDTO = new AssetDatesMaster_DTO();
		lDTO.setDateSeqNo(lMaster.getDateSeqNo());
		lDTO.setDateType(lMaster.getDateType());
		return lDTO;
	}

}