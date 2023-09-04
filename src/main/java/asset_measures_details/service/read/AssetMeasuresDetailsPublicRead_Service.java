package asset_measures_details.service.read;

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
import asset_measures_details.model.repo.read.AssetMeasuresDetailsPublicRead_Repo;

@Service("assetMeasuresDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMeasuresDetailsPublicRead_Service implements I_AssetMeasuresDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMeasuresDetailsService.class);

	@Autowired
	private AssetMeasuresDetailsPublicRead_Repo assetMeasuresDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> getAllAssetMeasuresDetails() {
		CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetMeasuresDetail> assetMeasuresList = (CopyOnWriteArrayList<AssetMeasuresDetail>) assetMeasuresDetailsPublicReadRepo
					.getAllAssetMeasuressDetails();
			CopyOnWriteArrayList<AssetMeasuresDetail_DTO> lMasterss = assetMeasuresList != null
					? this.getAssetMeasuresDetailDtos(assetMeasuresList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> getSelectDetails(
			CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetMeasuresDetail> assetMeasuresList = (CopyOnWriteArrayList<AssetMeasuresDetail>) assetMeasuresDetailsPublicReadRepo
					.getSelectAssetMeasuressDetails(seqNos);
			CopyOnWriteArrayList<AssetMeasuresDetail_DTO> lMasterss = assetMeasuresList != null
					? this.getAssetMeasuresDetailDtos(assetMeasuresList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetMeasuresDetail_DTO> getAssetMeasuresDetailDtos(
			CopyOnWriteArrayList<AssetMeasuresDetail> lMasters) {
		AssetMeasuresDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetMeasuresDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetMeasuresDetail_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetMeasuresDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetMeasuresDetail_DTO getAssetMeasuresDetail_DTO(AssetMeasuresDetail lMaster) 
	{
		AssetMeasuresDetail_DTO lDTO = new AssetMeasuresDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setQtyUnitSeqNo(lMaster.getId().getQtyUnitSeqNo());
		lDTO.setQty(lMaster.getQty());				
		return lDTO;
	}

}