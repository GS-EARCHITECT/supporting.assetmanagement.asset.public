package asset_structure_details.service.cud;

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
import asset_structure_details.model.dto.AssetStructureDetail_DTO;
import asset_structure_details.model.master.AssetStructureDetail;
import asset_structure_details.model.master.AssetStructureDetailPK;
import asset_structure_details.model.repo.cud.AssetStructureDetailsPublicCUD_Repo;

@Service("assetStructureDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetStructureDetailsPublicCUD_Service implements I_AssetStructureDetailsPublicCUD_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetStructureDetailsService.class);
	
	
	@Autowired
    private AssetStructureDetailsPublicCUD_Repo assetStructureDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AssetStructureDetail_DTO> newAssetStructureDetail(AssetStructureDetail_DTO lMaster) 
	{
		CompletableFuture<AssetStructureDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		AssetStructureDetail assetStructureDetails2 = null;
		AssetStructureDetailPK assetStructureDetailPK = new AssetStructureDetailPK();  		
		assetStructureDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		assetStructureDetailPK.setFrDttm(ts_Fr);
		assetStructureDetailPK.setToDttm(ts_To);
		
		if(!assetStructureDetailsPublicCUDRepo.existsById(assetStructureDetailPK))
		{			
		assetStructureDetails2 = setAssetStructureDetail(lMaster);	
		assetStructureDetails2.setId(assetStructureDetailPK);
		getAssetStructureDetail_DTO(assetStructureDetailsPublicCUDRepo.save(assetStructureDetails2));
		}
		return lMaster;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updAssetStructureDetail(AssetStructureDetail_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetStructureDetail assetStructureMaster = null;
		if(lMaster!=null)
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");	
		AssetStructureDetailPK assetStructureDetailPK = null;	
		assetStructureDetailPK = new AssetStructureDetailPK();
		LocalDateTime dateOn = LocalDateTime.parse(lMaster.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		assetStructureDetailPK.setAssetSeqNo(lMaster.getAssetSeqNo());		
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		assetStructureDetailPK.setFrDttm(ts_Fr);
		assetStructureDetailPK.setToDttm(ts_To);		
		
		if (assetStructureDetailsPublicCUDRepo.existsById(assetStructureDetailPK))
		{
			assetStructureMaster = setAssetStructureDetail(lMaster); 
			assetStructureMaster.setId(assetStructureDetailPK);
			assetStructureDetailsPublicCUDRepo.save(assetStructureMaster);
		}
		return;
		}
   		},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<Void> delAllAssetStructureDetails()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		assetStructureDetailsPublicCUDRepo.deleteAll();
		return;		
   		},asyncExecutor);		
		return future;
	}

	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetStructureDetailPK> seqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if(seqNos!=null)
		{			
		assetStructureDetailsPublicCUDRepo.deleteAllById(seqNos);		
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
		assetStructureDetailsPublicCUDRepo.delDetailsBetweenTimes(ts_Fr, ts_To);		
		return;		
   		},asyncExecutor);		
		return future;		
	}

	private synchronized  AssetStructureDetail_DTO getAssetStructureDetail_DTO(AssetStructureDetail lMaster) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetStructureDetail_DTO lDTO = new AssetStructureDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setParAssetSeqNo(lMaster.getId().getParAssetSeqNo());
		return lDTO;
	}

	private synchronized AssetStructureDetail setAssetStructureDetail(AssetStructureDetail_DTO lDTO) {
		AssetStructureDetail lMaster = new AssetStructureDetail();
		AssetStructureDetailPK assetStructureDetailPK = new AssetStructureDetailPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = null;
		LocalDateTime dateTo = null;	
		dateFr = LocalDateTime.parse(lDTO.getFrDttm(), formatter);
		dateTo = LocalDateTime.parse(lDTO.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		assetStructureDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetStructureDetailPK.setFrDttm(ts_Fr);
		assetStructureDetailPK.setToDttm(ts_To);
		assetStructureDetailPK.setParAssetSeqNo(lDTO.getParAssetSeqNo());		
		lMaster.setId(assetStructureDetailPK);
		return lMaster;
	}
	
}