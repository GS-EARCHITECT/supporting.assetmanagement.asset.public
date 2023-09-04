package asset_users_details.service.cud;

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
import asset_users_details.model.dto.AssetUsersDetail_DTO;
import asset_users_details.model.master.AssetUsersDetail;
import asset_users_details.model.master.AssetUsersDetailPK;
import asset_users_details.model.repo.cud.AssetUsersDetailsPublicCUD_Repo;

@Service("assetUsersDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetUsersDetailsPublicCUD_Service implements I_AssetUsersDetailsPublicCUD_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetUsersDetailsService.class);
	
	
	@Autowired
    private AssetUsersDetailsPublicCUD_Repo assetUsersDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AssetUsersDetail_DTO> newAssetUsersDetail(AssetUsersDetail_DTO lMaster) 
	{
		CompletableFuture<AssetUsersDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		AssetUsersDetail assetUsersDetails2 = null;
		AssetUsersDetailPK assetUsersDetailPK = new AssetUsersDetailPK();  		
		assetUsersDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		assetUsersDetailPK.setFrDttm(ts_Fr);
		assetUsersDetailPK.setToDttm(ts_To);
		
		if(!assetUsersDetailsPublicCUDRepo.existsById(assetUsersDetailPK))
		{			
		assetUsersDetails2 = setAssetUsersDetail(lMaster);	
		assetUsersDetails2.setId(assetUsersDetailPK);
		getAssetUsersDetail_DTO(assetUsersDetailsPublicCUDRepo.save(assetUsersDetails2));
		}
		return lMaster;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updAssetUsersDetail(AssetUsersDetail_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetUsersDetail assetUsersMaster = null;
		if(lMaster!=null)
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");	
		AssetUsersDetailPK assetUsersDetailPK = null;	
		assetUsersDetailPK = new AssetUsersDetailPK();
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		assetUsersDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		assetUsersDetailPK.setFrDttm(ts_Fr);
		assetUsersDetailPK.setToDttm(ts_To);		
		
		if (assetUsersDetailsPublicCUDRepo.existsById(assetUsersDetailPK))
		{
			assetUsersMaster = setAssetUsersDetail(lMaster); 
			assetUsersMaster.setId(assetUsersDetailPK);
			assetUsersDetailsPublicCUDRepo.save(assetUsersMaster);
		}
		return;
		}
   		},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<Void> delAllAssetUsersDetails()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetUsersDetailsPublicCUDRepo.deleteAll();
		return;		
   		},asyncExecutor);		
		return future;
	}

	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetUsersDetailPK> seqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if(seqNos!=null)
		{			
		assetUsersDetailsPublicCUDRepo.deleteAllById(seqNos);		
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
		assetUsersDetailsPublicCUDRepo.delDetailsBetweenTimes(ts_Fr, ts_To);		
		return;		
   		},asyncExecutor);		
		return future;		
	}

	private synchronized  AssetUsersDetail_DTO getAssetUsersDetail_DTO(AssetUsersDetail lMaster) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetUsersDetail_DTO lDTO = new AssetUsersDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setPartySeqNo(lMaster.getId().getPartySeqNo());
		return lDTO;
	}

	private synchronized AssetUsersDetail setAssetUsersDetail(AssetUsersDetail_DTO lDTO) {
		AssetUsersDetail lMaster = new AssetUsersDetail();
		AssetUsersDetailPK assetUsersDetailPK = new AssetUsersDetailPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = null;
		LocalDateTime dateTo = null;	
		dateFr = LocalDateTime.parse(lDTO.getFrDttm(), formatter);
		dateTo = LocalDateTime.parse(lDTO.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		assetUsersDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetUsersDetailPK.setFrDttm(ts_Fr);
		assetUsersDetailPK.setToDttm(ts_To);
		assetUsersDetailPK.setPartySeqNo(lDTO.getPartySeqNo());		
		lMaster.setId(assetUsersDetailPK);
		return lMaster;
	}
	
}