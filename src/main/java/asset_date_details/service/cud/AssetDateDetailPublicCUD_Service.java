package asset_date_details.service.cud;

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
import asset_date_details.model.dto.AssetDateDetail_DTO;
import asset_date_details.model.master.AssetDateDetail;
import asset_date_details.model.master.AssetDateDetailPK;
import asset_date_details.model.repo.cud.AssetDateDetailsPublicCUD_Repo;

@Service("assetDateDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetDateDetailPublicCUD_Service implements I_AssetDateDetailsPublicCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetDateDetailsService.class);

	@Autowired
	private AssetDateDetailsPublicCUD_Repo assetDateDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AssetDateDetail_DTO> newAssetDateDetail(AssetDateDetail_DTO lDetail) 
	{
		CompletableFuture<AssetDateDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		AssetDateDetail_DTO assetDateDetail_DTO = null;
		AssetDateDetailPK assetDateDetailPK = new AssetDateDetailPK();
		assetDateDetailPK.setAssetSeqNo(lDetail.getAssetSeqNo());
		assetDateDetailPK.setDateSeqNo(lDetail.getDateSeqNo());

		if (!assetDateDetailsPublicCUDRepo.existsById(assetDateDetailPK)) {
			assetDateDetail_DTO = getAssetDateDetail_DTO(assetDateDetailsPublicCUDRepo.save(setAssetDateDetail(lDetail)));
		}
		return assetDateDetail_DTO;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updAssetDateDetail(AssetDateDetail_DTO lDetail) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		AssetDateDetailPK assetDateDetailPK = new AssetDateDetailPK();
		assetDateDetailPK.setAssetSeqNo(lDetail.getAssetSeqNo());
		assetDateDetailPK.setDateSeqNo(lDetail.getDateSeqNo());

		if (lDetail != null) 
		{
			if (assetDateDetailsPublicCUDRepo.existsById(assetDateDetailPK)) 
			{
			assetDateDetailsPublicCUDRepo.save(this.setAssetDateDetail(lDetail));
			}
		}
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllAssetDateDetails() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{	
		assetDateDetailsPublicCUDRepo.deleteAll();
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAssetDateDetails(CopyOnWriteArrayList<AssetDateDetailPK> seqNos) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (seqNos != null) {
			assetDateDetailsPublicCUDRepo.deleteAllById(seqNos);
		}
		return;
   		},asyncExecutor);
		return future;
	}

	private synchronized AssetDateDetail_DTO getAssetDateDetail_DTO(AssetDateDetail lDetail) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetDateDetail_DTO lDTO = new AssetDateDetail_DTO();
		lDTO.setDateSeqNo(lDetail.getId().getDateSeqNo());
		lDTO.setAssetSeqNo(lDetail.getId().getAssetSeqNo());
		lDTO.setDttm(formatter.format(lDetail.getDttm().toLocalDateTime()));
		return lDTO;
	}

	private synchronized AssetDateDetail setAssetDateDetail(AssetDateDetail_DTO lDTO) {
		AssetDateDetail lDetail = new AssetDateDetail();
		AssetDateDetailPK assetDateDetailPK = new AssetDateDetailPK();
		assetDateDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetDateDetailPK.setDateSeqNo(lDTO.getDateSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dtTm = LocalDateTime.parse(lDTO.getDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dtTm);
		lDetail.setId(assetDateDetailPK);
		lDetail.setDttm(ts_Fr);
		return lDetail;
	}

}