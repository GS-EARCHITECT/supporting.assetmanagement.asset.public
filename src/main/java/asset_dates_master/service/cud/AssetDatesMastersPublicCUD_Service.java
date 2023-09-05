package asset_dates_master.service.cud;

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
import asset_dates_master.model.repo.cud.AssetDatesMastersPublicCUD_Repo;

@Service("assetDatesMastersPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetDatesMastersPublicCUD_Service implements I_AssetDatesMastersPublicCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetDatesMastersService.class);

	@Autowired
	private AssetDatesMastersPublicCUD_Repo assetDatesMastersPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AssetDatesMaster_DTO> newAssetDatesMaster(AssetDatesMaster_DTO lMaster) 
	{
		CompletableFuture<AssetDatesMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		AssetDatesMaster_DTO assetDatesMasters2 = null;
		if (!assetDatesMastersPublicCUDRepo.existsById(lMaster.getDateSeqNo())) {
			assetDatesMasters2 = getAssetDatesMaster_DTO(assetDatesMastersPublicCUDRepo.save(setAssetDatesMaster(lMaster)));
		}
		return assetDatesMasters2;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updAssetDatesMaster(AssetDatesMaster_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetDatesMaster assetPriceMaster = null;

		if (lMaster != null) {
			if (assetDatesMastersPublicCUDRepo.existsById(lMaster.getDateSeqNo())) 
			{
				assetPriceMaster = setAssetDatesMaster(lMaster);
				assetPriceMaster.setDateSeqNo(lMaster.getDateSeqNo());
				assetDatesMastersPublicCUDRepo.save(assetPriceMaster);
			}
		}
		return;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> delAllAssetDatesMasters() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetDatesMastersPublicCUDRepo.deleteAll();
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAssetDatesMasters(CopyOnWriteArrayList<Long> seqNos) {
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (seqNos != null) {
			assetDatesMastersPublicCUDRepo.deleteAllById(seqNos);

		}
		return;
   		},asyncExecutor);
		return future;

	}

	private CopyOnWriteArrayList<AssetDatesMaster_DTO> getAssetDatesMasterDtos(CopyOnWriteArrayList<AssetDatesMaster> lMasters) {
		AssetDatesMaster_DTO lDTO = null;
		CopyOnWriteArrayList<AssetDatesMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetDatesMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetDatesMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetDatesMaster_DTO getAssetDatesMaster_DTO(AssetDatesMaster lMaster) 
	{
		AssetDatesMaster_DTO lDTO = new AssetDatesMaster_DTO();
		lDTO.setDateSeqNo(lMaster.getDateSeqNo());
		lDTO.setDateType(lMaster.getDateType());
		return lDTO;
	}

	private AssetDatesMaster setAssetDatesMaster(AssetDatesMaster_DTO lDTO) {
		AssetDatesMaster lMaster = new AssetDatesMaster();
		lMaster.setDateType(lDTO.getDateType());
		return lMaster;
	}

}