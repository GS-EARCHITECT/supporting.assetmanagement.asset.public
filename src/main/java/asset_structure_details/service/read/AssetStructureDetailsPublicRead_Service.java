package asset_structure_details.service.read;

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
import asset_structure_details.model.repo.read.AssetStructureDetailsPublicRead_Repo;

@Service("assetStructureDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetStructureDetailsPublicRead_Service implements I_AssetStructureDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetStructureDetailsService.class);

	@Autowired
	private AssetStructureDetailsPublicRead_Repo assetStructureDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getAllAssetStructureDetails() {
		CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetStructureDetail> assetStructureList = (CopyOnWriteArrayList<AssetStructureDetail>) assetStructureDetailsPublicReadRepo
					.getAllAssetStructuresDetails();
			CopyOnWriteArrayList<AssetStructureDetail_DTO> lMasterss = assetStructureList != null
					? this.getAssetStructureDetailDtos(assetStructureList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getSelectDetails(
			CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetStructureDetail> assetStructureList = (CopyOnWriteArrayList<AssetStructureDetail>) assetStructureDetailsPublicReadRepo
					.getSelectAssetStructuresDetails(seqNos);
			CopyOnWriteArrayList<AssetStructureDetail_DTO> lMasterss = assetStructureList != null
					? this.getAssetStructureDetailDtos(assetStructureList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getDetailsBetweenTimes(String fr,
			String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AssetStructureDetail> lMasters2 = assetStructureDetailsPublicReadRepo
					.getDetailsBetweenTimes(ts_Fr, ts_To);
			CopyOnWriteArrayList<AssetStructureDetail_DTO> lMasterss = lMasters2 != null
					? this.getAssetStructureDetailDtos(lMasters2)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetStructureDetail_DTO> getAssetStructureDetailDtos(
			CopyOnWriteArrayList<AssetStructureDetail> lMasters) {
		AssetStructureDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetStructureDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetStructureDetail_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetStructureDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetStructureDetail_DTO getAssetStructureDetail_DTO(AssetStructureDetail lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetStructureDetail_DTO lDTO = new AssetStructureDetail_DTO();
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setParAssetSeqNo(lMaster.getId().getParAssetSeqNo());
		return lDTO;
	}

}