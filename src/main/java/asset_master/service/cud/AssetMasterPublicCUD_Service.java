package asset_master.service.cud;

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
import asset_master.model.dto.AssetMaster_DTO;
import asset_master.model.master.AssetMaster;
import asset_master.model.repo.cud.AssetMasterPublicCUD_Repo;

@Service("assetMasterPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMasterPublicCUD_Service implements I_AssetMasterPublicCUD_Service 
{

	@Autowired
	private AssetMasterPublicCUD_Repo assetMasterPublicCUDRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AssetMaster_DTO> newAssetMaster(AssetMaster_DTO lMaster) 
	{
		CompletableFuture<AssetMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		AssetMaster_DTO lMaster2 = getAssetMaster_DTO(assetMasterPublicCUDRepo.save(this.setAssetMaster(lMaster)));
		return lMaster2;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> setAssetDoneStatus(Long id, Character st)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetMasterPublicCUDRepo.setAssetDone(id, st);
		return ;
   		},asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> setAssetStatus(Long id, Character st)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetMasterPublicCUDRepo.setAssetStatus(id, st);
		return ;
   		},asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> updAssetMaster(AssetMaster_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetMaster assetMaster = this.setAssetMaster(lMaster);
		if (assetMasterPublicCUDRepo.existsById(lMaster.getAssetSeqNo())) 
				{		
			assetMaster.setAssetSeqNo(lMaster.getAssetSeqNo());			
			assetMasterPublicCUDRepo.save(assetMaster);			
		}
		return ;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllAssetMasters() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetMasterPublicCUDRepo.deleteAll(); 
		return ;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAssets(CopyOnWriteArrayList<Long> assetSeqNos) 
	{
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetMasterPublicCUDRepo.deleteAllById(assetSeqNos); 
		return ;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAssetsByResources(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetMasterPublicCUDRepo.delSelectAssetsByResources(ids); 
		return ;
   		},asyncExecutor);
		return future;
	}
	
	private synchronized AssetMaster_DTO getAssetMaster_DTO(AssetMaster lMaster) 
	{		
		AssetMaster_DTO lDTO = new AssetMaster_DTO();
		lDTO.setDoneFlag(lMaster.getDoneFlag());
		lDTO.setAssetSeqNo(lMaster.getAssetSeqNo());
		lDTO.setAsset(lMaster.getAsset());
		lDTO.setAssetId(lMaster.getAssetId());
		lDTO.setResourceSeqNo(lMaster.getResourceSeqNo());
		lDTO.setSpecSeqNo(lMaster.getSpecSeqNo());
		return lDTO;
	}

	private synchronized AssetMaster setAssetMaster(AssetMaster_DTO lDTO) 
	{
		AssetMaster lMaster = new AssetMaster();				
		lMaster.setDoneFlag(lDTO.getDoneFlag());		
		lMaster.setAsset(lDTO.getAsset());
		lMaster.setAssetId(lDTO.getAssetId());
		lMaster.setResourceSeqNo(lDTO.getResourceSeqNo());
		lMaster.setSpecSeqNo(lDTO.getSpecSeqNo());		
		return lMaster;
	}
}