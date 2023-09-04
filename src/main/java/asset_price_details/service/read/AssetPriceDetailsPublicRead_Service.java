package asset_price_details.service.read;

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
import asset_price_details.model.dto.AssetPriceDetail_DTO;
import asset_price_details.model.master.AssetPriceDetail;
import asset_price_details.model.repo.read.AssetPriceDetailsPublicRead_Repo;

@Service("assetPriceDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetPriceDetailsPublicRead_Service implements I_AssetPriceDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetPriceDetailsService.class);

	@Autowired
	private AssetPriceDetailsPublicRead_Repo assetPriceDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getAllAssetPriceDetails() {
		CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetPriceDetail> assetPriceList = (CopyOnWriteArrayList<AssetPriceDetail>) assetPriceDetailsPublicReadRepo
					.getAllAssetPricesDetails();
			CopyOnWriteArrayList<AssetPriceDetail_DTO> lMasterss = assetPriceList != null
					? this.getAssetPriceDetailDtos(assetPriceList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getSelectDetails(
			CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetPriceDetail> assetPriceList = (CopyOnWriteArrayList<AssetPriceDetail>) assetPriceDetailsPublicReadRepo
					.getSelectAssetPricesDetails(seqNos);
			CopyOnWriteArrayList<AssetPriceDetail_DTO> lMasterss = assetPriceList != null
					? this.getAssetPriceDetailDtos(assetPriceList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getDetailsBetweenTimes(String fr,
			String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AssetPriceDetail> lMasters2 = assetPriceDetailsPublicReadRepo
					.getDetailsBetweenTimes(ts_Fr, ts_To);
			CopyOnWriteArrayList<AssetPriceDetail_DTO> lMasterss = lMasters2 != null
					? this.getAssetPriceDetailDtos(lMasters2)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetPriceDetail_DTO> getAssetPriceDetailDtos(
			CopyOnWriteArrayList<AssetPriceDetail> lMasters) {
		AssetPriceDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetPriceDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetPriceDetail_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetPriceDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized  AssetPriceDetail_DTO getAssetPriceDetail_DTO(AssetPriceDetail lMaster) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetPriceDetail_DTO lDTO = new AssetPriceDetail_DTO();		
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setPrice(lMaster.getPrice());
		lDTO.setPriceUnitSeqNo(lMaster.getPriceUnitSeqNo());
		return lDTO;
	}


}