package asset_price_details.service.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import asset_price_details.model.dto.AssetPriceDetail_DTO;
import asset_price_details.model.master.AssetPriceDetail;
import asset_price_details.model.master.AssetPriceDetailPK;
import asset_price_details.model.repo.cud.AssetPriceDetailsPublicCUD_Repo;

@Service("assetPriceDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetPriceDetailsPublicCUD_Service implements I_AssetPriceDetailsPublicCUD_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetPriceDetailsService.class);
	
	
	@Autowired
    private AssetPriceDetailsPublicCUD_Repo assetPriceDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AssetPriceDetail_DTO> newAssetPriceDetail(AssetPriceDetail_DTO lMaster) 
	{
		CompletableFuture<AssetPriceDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		AssetPriceDetail assetPriceDetails2 = null;
		AssetPriceDetailPK assetPriceDetailPK = new AssetPriceDetailPK();  		
		assetPriceDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		assetPriceDetailPK.setFrDttm(ts_Fr);
		assetPriceDetailPK.setToDttm(ts_To);
		
		if(!assetPriceDetailsPublicCUDRepo.existsById(assetPriceDetailPK))
		{			
		assetPriceDetails2 = setAssetPriceDetail(lMaster);	
		assetPriceDetails2.setId(assetPriceDetailPK);
		getAssetPriceDetail_DTO(assetPriceDetailsPublicCUDRepo.save(assetPriceDetails2));
		}
		return lMaster;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updAssetPriceDetail(AssetPriceDetail_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetPriceDetail assetPriceMaster = null;
		if(lMaster!=null)
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");	
		AssetPriceDetailPK assetPriceDetailPK = null;	
		assetPriceDetailPK = new AssetPriceDetailPK();
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		assetPriceDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		assetPriceDetailPK.setFrDttm(ts_Fr);
		assetPriceDetailPK.setToDttm(ts_To);		
		
		if (assetPriceDetailsPublicCUDRepo.existsById(assetPriceDetailPK))
		{
			assetPriceMaster = setAssetPriceDetail(lMaster); 
			assetPriceMaster.setId(assetPriceDetailPK);
			assetPriceDetailsPublicCUDRepo.save(assetPriceMaster);
		}
		return;
		}
   		},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<Void> delAllAssetPriceDetails()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetPriceDetailsPublicCUDRepo.deleteAll();
		return;		
   		},asyncExecutor);		
		return future;
	}

	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetPriceDetailPK> seqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if(seqNos!=null)
		{			
		assetPriceDetailsPublicCUDRepo.deleteAllById(seqNos);		
		}
		return;		
   		},asyncExecutor);		
		return future;

	}
	
	public CompletableFuture<Void> delSelectDetailsBetweenTimes(String fr, String to) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = null;
		LocalDateTime dateTo = null;	
		dateFr = LocalDateTime.parse(fr, formatter);
		dateTo = LocalDateTime.parse(to, formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		assetPriceDetailsPublicCUDRepo.delDetailsBetweenTimes(ts_Fr, ts_To);		
		return;		
   		},asyncExecutor);		
		return future;		
	}

	private synchronized  AssetPriceDetail_DTO getAssetPriceDetail_DTO(AssetPriceDetail lMaster) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetPriceDetail_DTO lDTO = new AssetPriceDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setPrice(lMaster.getPrice());
		lDTO.setPriceUnitSeqNo(lMaster.getPriceUnitSeqNo());
		return lDTO;
	}

	private synchronized AssetPriceDetail setAssetPriceDetail(AssetPriceDetail_DTO lDTO) {
		AssetPriceDetail lMaster = new AssetPriceDetail();
		AssetPriceDetailPK assetPriceDetailPK = new AssetPriceDetailPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = null;
		LocalDateTime dateTo = null;	
		dateFr = LocalDateTime.parse(lDTO.getFrDttm(), formatter);
		dateTo = LocalDateTime.parse(lDTO.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		assetPriceDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetPriceDetailPK.setFrDttm(ts_Fr);
		assetPriceDetailPK.setToDttm(ts_To);
		lMaster.setPrice(lDTO.getPrice());
		lMaster.setPriceUnitSeqNo(lDTO.getPriceUnitSeqNo());
		lMaster.setId(assetPriceDetailPK);
		return lMaster;
	}
	
}