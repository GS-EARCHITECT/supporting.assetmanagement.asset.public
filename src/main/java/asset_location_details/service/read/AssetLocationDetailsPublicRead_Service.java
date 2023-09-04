package asset_location_details.service.read;

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
import asset_location_details.model.repo.read.AssetLocationDetailsPublicRead_Repo;

@Service("assetLocationDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetLocationDetailsPublicRead_Service implements I_AssetLocationDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetLocationDetailsService.class);

	@Autowired
	private AssetLocationDetailsPublicRead_Repo assetLocationDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getAllAssetLocationDetails() {
		CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetLocationDetail> assetLocationList = (CopyOnWriteArrayList<AssetLocationDetail>) assetLocationDetailsPublicReadRepo
					.getAllAssetLocationsDetails();
			CopyOnWriteArrayList<AssetLocationDetail_DTO> lMasterss = assetLocationList != null
					? this.getAssetLocationDetailDtos(assetLocationList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getSelectDetails(
			CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetLocationDetail> assetLocationList = (CopyOnWriteArrayList<AssetLocationDetail>) assetLocationDetailsPublicReadRepo
					.getSelectAssetLocationsDetails(seqNos);
			CopyOnWriteArrayList<AssetLocationDetail_DTO> lMasterss = assetLocationList != null
					? this.getAssetLocationDetailDtos(assetLocationList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getDetailsBetweenTimes(String fr,
			String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AssetLocationDetail> lMasters2 = assetLocationDetailsPublicReadRepo
					.getDetailsBetweenTimes(ts_Fr, ts_To);
			CopyOnWriteArrayList<AssetLocationDetail_DTO> lMasterss = lMasters2 != null
					? this.getAssetLocationDetailDtos(lMasters2)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetLocationDetail_DTO> getAssetLocationDetailDtos(
			CopyOnWriteArrayList<AssetLocationDetail> lMasters) {
		AssetLocationDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetLocationDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetLocationDetail_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetLocationDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetLocationDetail_DTO getAssetLocationDetail_DTO(AssetLocationDetail lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetLocationDetail_DTO lDTO = new AssetLocationDetail_DTO();
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setPartySeqNo(lMaster.getPartySeqNo());
		lDTO.setLocationSeqNo(lMaster.getId().getLocationSeqNo());
		return lDTO;
	}

}