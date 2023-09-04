package asset_meter_details.service.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import asset_meter_details.model.master.AssetMeterDetailPK;
import asset_meter_details.model.repo.cud.AssetMeterDetailsPublicCUD_Repo;

@Service("assetMeterDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMeterDetailsPublicCUD_Service implements I_AssetMeterDetailsPublicCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMeterDetailsService.class);

	@Autowired
	private AssetMeterDetailsPublicCUD_Repo assetMeterDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AssetMeterDetail_DTO> newMeterDetail(AssetMeterDetail_DTO lMaster) {
		CompletableFuture<AssetMeterDetail_DTO> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(lMaster.getOnDttm(), formatter);
			Timestamp ts_On = Timestamp.valueOf(dateOn);
			Optional<AssetMeterDetail> assetMeterDetails = null;
			AssetMeterDetail assetMeterDetails2 = null;
			AssetMeterDetailPK assetMeterDetailsPK = new AssetMeterDetailPK();
			assetMeterDetailsPK.setAssetSeqNo(lMaster.getAssetSeqNo());
			assetMeterDetailsPK.setOnDttm(ts_On);
			AssetMeterDetail_DTO lMaster2 = null;

			if (!assetMeterDetailsPublicCUDRepo.existsById(assetMeterDetailsPK)) {
				assetMeterDetails2 = setAssetMeterDetail(lMaster);
				assetMeterDetails2.setId(assetMeterDetailsPK);
				assetMeterDetails2 = assetMeterDetailsPublicCUDRepo.save(assetMeterDetails2);
				lMaster2 = getAssetMeterDetail_DTO(assetMeterDetails2);
			}
			return lMaster2;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updMeterDetail(AssetMeterDetail_DTO lMaster) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			AssetMeterDetail assetMeterMaster = null;
			AssetMeterDetailPK assetMeterDetailsPK = null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = null;
			Timestamp ts_On = null;

			if (lMaster != null) {
				assetMeterDetailsPK = new AssetMeterDetailPK();
				assetMeterDetailsPK.setAssetSeqNo(lMaster.getAssetSeqNo());
				dateOn = LocalDateTime.parse(lMaster.getOnDttm(), formatter);
				ts_On = Timestamp.valueOf(dateOn);
				assetMeterDetailsPK.setAssetSeqNo(lMaster.getAssetSeqNo());
				assetMeterDetailsPK.setOnDttm(ts_On);

				if (assetMeterDetailsPublicCUDRepo.existsById(assetMeterDetailsPK)) {
					assetMeterMaster = setAssetMeterDetail(lMaster);
					assetMeterMaster.setId(assetMeterDetailsPK);
					assetMeterDetailsPublicCUDRepo.save(assetMeterMaster);
				}
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllMeterDetails() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			assetMeterDetailsPublicCUDRepo.deleteAll();
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectMeterDetails(CopyOnWriteArrayList<AssetMeterDetailPK> seqNos) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (seqNos != null) {
				assetMeterDetailsPublicCUDRepo.deleteAllById(seqNos);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectMeterDetailsBetweenTimes(String fr, String to) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateFr = null;
			LocalDateTime dateTo = null;
			dateFr = LocalDateTime.parse(fr, formatter);
			dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateFr);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			assetMeterDetailsPublicCUDRepo.delMeterDetailsBetweenTimes(ts_Fr, ts_To);
			;
			return;
		}, asyncExecutor);
		return future;
	}

	private synchronized AssetMeterDetail_DTO getAssetMeterDetail_DTO(AssetMeterDetail lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetMeterDetail_DTO lDTO = new AssetMeterDetail_DTO();
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setOnDttm(formatter.format(lMaster.getId().getOnDttm().toLocalDateTime()));
		lDTO.setReading(lMaster.getReading());
		return lDTO;
	}

	private synchronized AssetMeterDetail setAssetMeterDetail(AssetMeterDetail_DTO lDTO) {
		AssetMeterDetail lMaster = new AssetMeterDetail();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(lDTO.getOnDttm(), formatter);
		Timestamp ts_On = Timestamp.valueOf(dateOn);
		AssetMeterDetailPK assetMeterDetailPK = new AssetMeterDetailPK();
		assetMeterDetailPK.setAssetSeqNo(lDTO.getAssetSeqNo());
		assetMeterDetailPK.setOnDttm(ts_On);
		lMaster.setId(assetMeterDetailPK);
		lMaster.setReading(lDTO.getReading());
		return lMaster;
	}

}