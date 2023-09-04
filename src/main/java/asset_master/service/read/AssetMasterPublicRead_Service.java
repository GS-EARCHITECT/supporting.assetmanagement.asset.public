package asset_master.service.read;

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
import asset_master.model.repo.read.AssetMasterPublicRead_Repo;

@Service("assetMasterPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMasterPublicRead_Service implements I_AssetMasterPublicRead_Service 
{

	@Autowired
	private AssetMasterPublicRead_Repo assetMasterPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> getAllAssetMasters() 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetMaster> assetList = assetMasterPublicReadRepo.getAllAssets();
		CopyOnWriteArrayList<AssetMaster_DTO> lMasterss = assetList != null ? this.getAssetMaster_DTOs(assetList) : null;
		return lMasterss;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> getSelectAssets(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetMaster> assetList = assetMasterPublicReadRepo.getSelectAssets(ids);
		CopyOnWriteArrayList<AssetMaster_DTO> lMasterss = assetList != null ? this.getAssetMaster_DTOs(assetList) : null;
		return lMasterss;
   		},asyncExecutor);
		return future;
	}
   
	public CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> getSelectAssetsByResources(CopyOnWriteArrayList<Long> resSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetMaster> assetList = assetMasterPublicReadRepo.getSelectAssetsByResources(resSeqNos);
		CopyOnWriteArrayList<AssetMaster_DTO> lMasterss = assetList != null ? this.getAssetMaster_DTOs(assetList) : null;
		return lMasterss;
   		},asyncExecutor);
		return future;	
	}
	
	public CompletableFuture<Character> getAssetDoneStatus(Long id)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character assetSt = assetMasterPublicReadRepo.getAssetDoneStatus(id);		
		return assetSt;
   		},asyncExecutor);
		return future;
	}
	
	private synchronized CopyOnWriteArrayList<AssetMaster_DTO> getAssetMaster_DTOs(CopyOnWriteArrayList<AssetMaster> lMasters) 
	{
		AssetMaster_DTO lDTO = null;
		CopyOnWriteArrayList<AssetMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetMaster_DTO>();		
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetMaster_DTO(lMasters.get(i));			
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
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
}