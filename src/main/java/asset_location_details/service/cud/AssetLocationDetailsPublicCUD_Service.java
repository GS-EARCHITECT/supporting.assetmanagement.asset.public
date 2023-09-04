package asset_location_details.service.cud;

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
import asset_location_details.model.dto.AssetLocationDetail_DTO;
import asset_location_details.model.master.AssetLocationDetail;
import asset_location_details.model.master.AssetLocationDetailPK;
import asset_location_details.model.repo.cud.AssetLocationDetailsPublicCUD_Repo;

@Service("assetLocationDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetLocationDetailsPublicCUD_Service implements I_AssetLocationDetailsPublicCUD_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetLocationDetailsService.class);
	
	
	@Autowired
    private AssetLocationDetailsPublicCUD_Repo assetLocationDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AssetLocationDetail_DTO> newAssetLocationDetail(AssetLocationDetail_DTO lMaster) 
	{
		CompletableFuture<AssetLocationDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		AssetLocationDetail assetLocationDetails2 = null;
		AssetLocationDetailPK assetLocationDetailPK = new AssetLocationDetailPK();  		
		assetLocationDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		assetLocationDetailPK.setFrDttm(ts_Fr);
		assetLocationDetailPK.setToDttm(ts_To);
		
		if(!assetLocationDetailsPublicCUDRepo.existsById(assetLocationDetailPK))
		{			
		assetLocationDetails2 = setAssetLocationDetail(lMaster);	
		assetLocationDetails2.setId(assetLocationDetailPK);
		getAssetLocationDetail_DTO(assetLocationDetailsPublicCUDRepo.save(assetLocationDetails2));
		}
		return lMaster;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updAssetLocationDetail(AssetLocationDetail_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetLocationDetail assetLocationMaster = null;
		if(lMaster!=null)
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");	
		AssetLocationDetailPK assetLocationDetailPK = null;	
		assetLocationDetailPK = new AssetLocationDetailPK();
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		assetLocationDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		assetLocationDetailPK.setFrDttm(ts_Fr);
		assetLocationDetailPK.setToDttm(ts_To);		
		
		if (assetLocationDetailsPublicCUDRepo.existsById(assetLocationDetailPK))
		{
			assetLocationMaster = setAssetLocationDetail(lMaster); 
			assetLocationMaster.setId(assetLocationDetailPK);
			assetLocationDetailsPublicCUDRepo.save(assetLocationMaster);
		}
		return;
		}
   		},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<Void> delAllAssetLocationDetails()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetLocationDetailsPublicCUDRepo.deleteAll();
		return;		
   		},asyncExecutor);		
		return future;
	}

	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetLocationDetailPK> seqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if(seqNos!=null)
		{			
		assetLocationDetailsPublicCUDRepo.deleteAllById(seqNos);		
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
		assetLocationDetailsPublicCUDRepo.delDetailsBetweenTimes(ts_Fr, ts_To);		
		return;		
   		},asyncExecutor);		
		return future;		
	}

	private synchronized  AssetLocationDetail_DTO getAssetLocationDetail_DTO(AssetLocationDetail lMaster) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetLocationDetail_DTO lDTO = new AssetLocationDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setPartySeqNo(lMaster.getPartySeqNo());
		lDTO.setLocationSeqNo(lMaster.getId().getLocationSeqNo());		
		return lDTO;
	}

	private synchronized AssetLocationDetail setAssetLocationDetail(AssetLocationDetail_DTO lDTO) {
		AssetLocationDetail lMaster = new AssetLocationDetail();
		AssetLocationDetailPK assetLocationDetailPK = new AssetLocationDetailPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = null;
		LocalDateTime dateTo = null;	
		dateFr = LocalDateTime.parse(lDTO.getFrDttm(), formatter);
		dateTo = LocalDateTime.parse(lDTO.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		assetLocationDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetLocationDetailPK.setFrDttm(ts_Fr);
		assetLocationDetailPK.setToDttm(ts_To);
		assetLocationDetailPK.setLocationSeqNo(lDTO.getLocationSeqNo());
		lMaster.setPartySeqNo(lDTO.getPartySeqNo());
		lMaster.setId(assetLocationDetailPK);
		return lMaster;
	}
	
}