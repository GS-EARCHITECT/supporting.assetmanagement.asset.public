package asset_measures_details.service.cud;

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
import asset_measures_details.model.dto.AssetMeasuresDetail_DTO;
import asset_measures_details.model.master.AssetMeasuresDetail;
import asset_measures_details.model.master.AssetMeasuresDetailPK;
import asset_measures_details.model.repo.cud.AssetMeasuresDetailsPublicCUD_Repo;

@Service("assetMeasuresDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMeasuresDetailsPublicCUD_Service implements I_AssetMeasuresDetailsPublicCUD_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetMeasuresDetailsService.class);
	
	
	@Autowired
    private AssetMeasuresDetailsPublicCUD_Repo assetMeasuresDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AssetMeasuresDetail_DTO> newAssetMeasuresDetail(AssetMeasuresDetail_DTO lMaster) 
	{
		CompletableFuture<AssetMeasuresDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{		
		AssetMeasuresDetail assetMeasuresDetails2 = null;
		AssetMeasuresDetailPK assetMeasuresDetailPK = new AssetMeasuresDetailPK();  		
		assetMeasuresDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		assetMeasuresDetailPK.setQtyUnitSeqNo(lMaster.getQtyUnitSeqNo());
		
		if(!assetMeasuresDetailsPublicCUDRepo.existsById(assetMeasuresDetailPK))
		{			
		assetMeasuresDetails2 = setAssetMeasuresDetail(lMaster);	
		assetMeasuresDetails2.setId(assetMeasuresDetailPK);
		getAssetMeasuresDetail_DTO(assetMeasuresDetailsPublicCUDRepo.save(assetMeasuresDetails2));
		}
		return lMaster;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updAssetMeasuresDetail(AssetMeasuresDetail_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetMeasuresDetail assetMeasuresMaster = null;
		if(lMaster!=null)
		{
		AssetMeasuresDetailPK assetMeasuresDetailPK = null;	
		assetMeasuresDetailPK = new AssetMeasuresDetailPK();
		assetMeasuresDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());
		assetMeasuresDetailPK.setQtyUnitSeqNo(lMaster.getQtyUnitSeqNo());
		
		if (assetMeasuresDetailsPublicCUDRepo.existsById(assetMeasuresDetailPK))
		{
			assetMeasuresMaster = setAssetMeasuresDetail(lMaster); 
			assetMeasuresMaster.setId(assetMeasuresDetailPK);
			assetMeasuresDetailsPublicCUDRepo.save(assetMeasuresMaster);
		}
		return;
		}
   		},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<Void> delAllAssetMeasuresDetails()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetMeasuresDetailsPublicCUDRepo.deleteAll();
		return;		
   		},asyncExecutor);		
		return future;
	}

	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetMeasuresDetailPK> seqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if(seqNos!=null)
		{			
		assetMeasuresDetailsPublicCUDRepo.deleteAllById(seqNos);		
		}
		return;		
   		},asyncExecutor);		
		return future;

	}
	
	private synchronized AssetMeasuresDetail_DTO getAssetMeasuresDetail_DTO(AssetMeasuresDetail lMaster) 
	{
		AssetMeasuresDetail_DTO lDTO = new AssetMeasuresDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setQtyUnitSeqNo(lMaster.getId().getQtyUnitSeqNo());
		lDTO.setQty(lMaster.getQty());				
		return lDTO;
	}

	private synchronized AssetMeasuresDetail setAssetMeasuresDetail(AssetMeasuresDetail_DTO lDTO) {
		AssetMeasuresDetail lMaster = new AssetMeasuresDetail();
		AssetMeasuresDetailPK assetMeasuresDetailPK = new AssetMeasuresDetailPK();
		assetMeasuresDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetMeasuresDetailPK.setQtyUnitSeqNo(lDTO.getQtyUnitSeqNo());
		lMaster.setId(assetMeasuresDetailPK);
		lMaster.setQty(lDTO.getQty());
		return lMaster;
	}

}