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
import asset_dates_master.model.dto.AssetDateMaster_DTO;
import asset_dates_master.model.master.AssetDateMaster;
import asset_dates_master.model.repo.cud.AssetDateMastersPublicCUD_Repo;

@Service("assetDateMastersPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetDateMastersPublicCUD_Service implements I_AssetDateMastersPublicCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetDateMastersService.class);

	@Autowired
	private AssetDateMastersPublicCUD_Repo assetDateMastersPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AssetDateMaster_DTO> newAssetDateMaster(AssetDateMaster_DTO lMaster) 
	{
		CompletableFuture<AssetDateMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		AssetDateMaster_DTO assetDateMasters2 = null;
		if (!assetDateMastersPublicCUDRepo.existsById(lMaster.getDateSeqNo())) {
			assetDateMasters2 = getAssetDateMaster_DTO(assetDateMastersPublicCUDRepo.save(setAssetDateMaster(lMaster)));
		}
		return assetDateMasters2;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updAssetDateMaster(AssetDateMaster_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetDateMaster assetPriceMaster = null;

		if (lMaster != null) {
			if (assetDateMastersPublicCUDRepo.existsById(lMaster.getDateSeqNo())) 
			{
				assetPriceMaster = setAssetDateMaster(lMaster);
				assetPriceMaster.setDateSeqNo(lMaster.getDateSeqNo());
				assetDateMastersPublicCUDRepo.save(assetPriceMaster);
			}
		}
		return;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> delAllAssetDateMasters() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetDateMastersPublicCUDRepo.deleteAll();
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAssetDateMasters(CopyOnWriteArrayList<Long> seqNos) {
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (seqNos != null) {
			assetDateMastersPublicCUDRepo.deleteAllById(seqNos);

		}
		return;
   		},asyncExecutor);
		return future;

	}

	private CopyOnWriteArrayList<AssetDateMaster_DTO> getAssetDateMasterDtos(CopyOnWriteArrayList<AssetDateMaster> lMasters) {
		AssetDateMaster_DTO lDTO = null;
		CopyOnWriteArrayList<AssetDateMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetDateMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetDateMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetDateMaster_DTO getAssetDateMaster_DTO(AssetDateMaster lMaster) 
	{
		AssetDateMaster_DTO lDTO = new AssetDateMaster_DTO();
		lDTO.setDateSeqNo(lMaster.getDateSeqNo());
		lDTO.setDateType(lMaster.getDateType());
		return lDTO;
	}

	private AssetDateMaster setAssetDateMaster(AssetDateMaster_DTO lDTO) {
		AssetDateMaster lMaster = new AssetDateMaster();
		lMaster.setDateType(lDTO.getDateType());
		return lMaster;
	}

}