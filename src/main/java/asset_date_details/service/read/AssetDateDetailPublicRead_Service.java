package asset_date_details.service.read;

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
import asset_date_details.model.repo.read.AssetDateDetailsPublicRead_Repo;

@Service("assetDateDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetDateDetailPublicRead_Service implements I_AssetDateDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetDateDetailsService.class);

	@Autowired
	private AssetDateDetailsPublicRead_Repo assetDateDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	
	public CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> getAllAssetDateDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<AssetDateDetail> assetDateDetails = (CopyOnWriteArrayList<AssetDateDetail>) assetDateDetailsPublicReadRepo.findAll();
		CopyOnWriteArrayList<AssetDateDetail_DTO> lDetailss = assetDateDetails != null
				? this.getAssetDateDetailDtos(assetDateDetails)
				: null;
				return lDetailss;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> getSelectAssetDateDetails(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
	
		CopyOnWriteArrayList<AssetDateDetail> assetDateDetails = (CopyOnWriteArrayList<AssetDateDetail>) assetDateDetailsPublicReadRepo
				.getSelectAssetDateDetails(ids);
		CopyOnWriteArrayList<AssetDateDetail_DTO> lDetailss = assetDateDetails != null
				? this.getAssetDateDetailDtos(assetDateDetails)
				: null;
				return lDetailss;
   		},asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetDateDetail_DTO> getAssetDateDetailDtos(CopyOnWriteArrayList<AssetDateDetail> lDetails) {
		AssetDateDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetDateDetail_DTO> lDetailDTOs = new CopyOnWriteArrayList<AssetDateDetail_DTO>();

		for (int i = 0; i < lDetails.size(); i++) {
			lDTO = getAssetDateDetail_DTO(lDetails.get(i));
			lDetailDTOs.add(lDTO);
		}
		return lDetailDTOs;
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