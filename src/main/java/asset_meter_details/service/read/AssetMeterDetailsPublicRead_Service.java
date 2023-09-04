package asset_meter_details.service.read;

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
import asset_meter_details.model.dto.AssetMeterDetail_DTO;
import asset_meter_details.model.master.AssetMeterDetail;
import asset_meter_details.model.repo.read.AssetMeterDetailsPublicRead_Repo;

@Service("assetMeterDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMeterDetailsPublicRead_Service implements I_AssetMeterDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMeterDetailsService.class);

	@Autowired
	private AssetMeterDetailsPublicRead_Repo assetMeterDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getAllMeterDetails() {
		CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetMeterDetail> assetMeterList = (CopyOnWriteArrayList<AssetMeterDetail>) assetMeterDetailsPublicReadRepo
					.getAllMeterDetails();
			CopyOnWriteArrayList<AssetMeterDetail_DTO> lMasterss = assetMeterList != null
					? this.getAssetMeterDetailDtos(assetMeterList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getSelectMeterDetails(
			CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetMeterDetail> assetMeterList = (CopyOnWriteArrayList<AssetMeterDetail>) assetMeterDetailsPublicReadRepo
					.getSelectMeterDetails(seqNos);
			CopyOnWriteArrayList<AssetMeterDetail_DTO> lMasterss = assetMeterList != null
					? this.getAssetMeterDetailDtos(assetMeterList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getMeterDetailsBetweenTimes(String fr, String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateFr = null;
			LocalDateTime dateTo = null;
			dateFr = LocalDateTime.parse(fr, formatter);
			dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateFr);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AssetMeterDetail> lMasters2 = assetMeterDetailsPublicReadRepo
					.getMeterDetailsBetweenTimes(ts_Fr, ts_To);
			CopyOnWriteArrayList<AssetMeterDetail_DTO> lMasterss = lMasters2 != null
					? this.getAssetMeterDetailDtos(lMasters2)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetMeterDetail_DTO> getAssetMeterDetailDtos(
			CopyOnWriteArrayList<AssetMeterDetail> lMasters) {
		AssetMeterDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetMeterDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetMeterDetail_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetMeterDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetMeterDetail_DTO getAssetMeterDetail_DTO(AssetMeterDetail lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetMeterDetail_DTO lDTO = new AssetMeterDetail_DTO();
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setOnDttm(formatter.format(lMaster.getId().getOnDttm().toLocalDateTime()));
		lDTO.setReading(lMaster.getReading());
		return lDTO;
	}

}