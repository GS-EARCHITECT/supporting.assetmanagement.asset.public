package asset_resserv_party_details.service.cud;

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
import asset_resserv_party_details.model.dto.AssetResServPartyDetail_DTO;
import asset_resserv_party_details.model.master.AssetResServPartyDetail;
import asset_resserv_party_details.model.repo.cud.AssetResServPartyDetailsPublicCUD_Repo;

@Service("assetResServPartyDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetResServPartyDetailsPublicCUD_Service implements I_AssetResServPartyDetailsPublicCUD_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetResServPartyDetailsService.class);
	
	
	@Autowired
    private AssetResServPartyDetailsPublicCUD_Repo assetResServPartyDetailsPublicCUDRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AssetResServPartyDetail_DTO> newAssetResServPartyDetail(AssetResServPartyDetail_DTO lMaster) 
	{
		CompletableFuture<AssetResServPartyDetail_DTO> future = CompletableFuture.supplyAsync(() -> {	
		AssetResServPartyDetail_DTO assetResServPartyDetail_DTO = null;  
		if(!assetResServPartyDetailsPublicCUDRepo.existsById(lMaster.getRessrvprdSeqNo()))
		{			
		assetResServPartyDetail_DTO = getAssetResServPartyDetail_DTO(assetResServPartyDetailsPublicCUDRepo.save(this.setAssetResServPartyDetail(lMaster)));
		}
		return assetResServPartyDetail_DTO;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updAssetResServPartyDetail(AssetResServPartyDetail_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if(lMaster!=null)
		{		
		if (assetResServPartyDetailsPublicCUDRepo.existsById(lMaster.getRessrvprdSeqNo()))
		{
		assetResServPartyDetailsPublicCUDRepo.save(this.setAssetResServPartyDetail(lMaster)); 
		}
		}
		return ;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<Long> seqNos) 
	{	
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {			
		if(seqNos!=null)
		{			
		assetResServPartyDetailsPublicCUDRepo.deleteAllById(seqNos);		
		}
		return ;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> delDetailsForParties(CopyOnWriteArrayList<Long> ids) 
	{		
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if(ids!=null)
		{			
		assetResServPartyDetailsPublicCUDRepo.delDetailsForParties(ids);		
		}
		return ;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> delDetailsForResources(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if(ids!=null)
		{			
		assetResServPartyDetailsPublicCUDRepo.delDetailsForResources(ids);		
		}
		return ;
		}, asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> delDetailsForServices(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if(ids!=null)
		{			
		assetResServPartyDetailsPublicCUDRepo.delDetailsForServices(ids);		
		}
		return ;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> delAllAssetResServPartyDetails()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {			
		assetResServPartyDetailsPublicCUDRepo.deleteAll();
		return ;
		}, asyncExecutor);
		return future;
	}


	private synchronized CopyOnWriteArrayList<AssetResServPartyDetail_DTO> getAssetResServPartyDetailDtos(CopyOnWriteArrayList<AssetResServPartyDetail> lMasters) {
		AssetResServPartyDetail_DTO lDTO = null;		
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetResServPartyDetail_DTO>();
		
		for (int i = 0; i < lMasters.size(); i++)
		{
			lDTO = getAssetResServPartyDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetResServPartyDetail_DTO getAssetResServPartyDetail_DTO(AssetResServPartyDetail lMaster) 
	{
		AssetResServPartyDetail_DTO lDTO = new AssetResServPartyDetail_DTO();		
		lDTO.setPartySeqNo(lMaster.getPartySeqNo());
		lDTO.setResourceSeqNo(lMaster.getResourceSeqNo());
		lDTO.setServiceSeqNo(lMaster.getServiceSeqNo());
		lDTO.setRessrvprdSeqNo(lMaster.getRessrvprdSeqNo());
		return lDTO;
	}

	private synchronized AssetResServPartyDetail setAssetResServPartyDetail(AssetResServPartyDetail_DTO lDTO) 
	{
		AssetResServPartyDetail lMaster = new AssetResServPartyDetail();
		lMaster.setPartySeqNo(lDTO.getPartySeqNo());
		lMaster.setResourceSeqNo(lDTO.getResourceSeqNo());
		lMaster.setServiceSeqNo(lDTO.getServiceSeqNo());
		return lMaster;
	}
	
}