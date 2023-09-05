package asset_resserv_party_details.service.read;

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
import asset_resserv_party_details.model.repo.read.AssetResServPartyDetailsPublicRead_Repo;

@Service("assetResServPartyDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetResServPartyDetailsPublicRead_Service implements I_AssetResServPartyDetailsPublicRead_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetResServPartyDetailsService.class);
	
	
	@Autowired
    private AssetResServPartyDetailsPublicRead_Repo assetResServPartyDetailsPublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getAllAssetResServPartyDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
		CopyOnWriteArrayList<AssetResServPartyDetail> assetResServPartyList =  assetResServPartyDetailsPublicReadRepo.getAllDetails();
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> lMasterss = assetResServPartyList != null ? this.getAssetResServPartyDetailDtos(assetResServPartyList) : null;
		return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
		CopyOnWriteArrayList<AssetResServPartyDetail> assetResServPartyList =  (CopyOnWriteArrayList<AssetResServPartyDetail>) assetResServPartyDetailsPublicReadRepo.getSelectDetails(seqNos);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> lMasterss = assetResServPartyList != null ? this.getAssetResServPartyDetailDtos(assetResServPartyList) : null;
		return lMasterss;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForParties(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
		CopyOnWriteArrayList<AssetResServPartyDetail> assetResServPartyList =  assetResServPartyDetailsPublicReadRepo.getDetailsForParties(ids);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> lMasterss = assetResServPartyList != null ? this.getAssetResServPartyDetailDtos(assetResServPartyList) : null;
		return lMasterss;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForResources(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
		CopyOnWriteArrayList<AssetResServPartyDetail> assetResServPartyList =  assetResServPartyDetailsPublicReadRepo.getDetailsForResources(ids);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> lMasterss = assetResServPartyList != null ? this.getAssetResServPartyDetailDtos(assetResServPartyList) : null;
		return lMasterss;
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForServices(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
		CopyOnWriteArrayList<AssetResServPartyDetail> assetResServPartyList =  assetResServPartyDetailsPublicReadRepo.getDetailsForServices(ids);
		CopyOnWriteArrayList<AssetResServPartyDetail_DTO> lMasterss = assetResServPartyList != null ? this.getAssetResServPartyDetailDtos(assetResServPartyList) : null;
		return lMasterss;
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
	
}