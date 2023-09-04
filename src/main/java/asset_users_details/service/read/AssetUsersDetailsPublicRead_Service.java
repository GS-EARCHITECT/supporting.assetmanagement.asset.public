package asset_users_details.service.read;

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
import asset_users_details.model.repo.read.AssetUsersDetailsPublicRead_Repo;

@Service("assetUsersDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetUsersDetailsPublicRead_Service implements I_AssetUsersDetailsPublicRead_Service 
{
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetUsersDetailsService.class);

	@Autowired
	private AssetUsersDetailsPublicRead_Repo assetUsersDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getAllAssetUsersDetails() {
		CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetUsersDetail> assetUsersList = (CopyOnWriteArrayList<AssetUsersDetail>) assetUsersDetailsPublicReadRepo
					.getAllAssetUserssDetails();
			CopyOnWriteArrayList<AssetUsersDetail_DTO> lMasterss = assetUsersList != null
					? this.getAssetUsersDetailDtos(assetUsersList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getSelectDetails(
			CopyOnWriteArrayList<Long> seqNos) {
		CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AssetUsersDetail> assetUsersList = (CopyOnWriteArrayList<AssetUsersDetail>) assetUsersDetailsPublicReadRepo
					.getSelectAssetUserssDetails(seqNos);
			CopyOnWriteArrayList<AssetUsersDetail_DTO> lMasterss = assetUsersList != null
					? this.getAssetUsersDetailDtos(assetUsersList)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getDetailsBetweenTimes(String fr,
			String to) {
		CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AssetUsersDetail> lMasters2 = assetUsersDetailsPublicReadRepo
					.getDetailsBetweenTimes(ts_Fr, ts_To);
			CopyOnWriteArrayList<AssetUsersDetail_DTO> lMasterss = lMasters2 != null
					? this.getAssetUsersDetailDtos(lMasters2)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AssetUsersDetail_DTO> getAssetUsersDetailDtos(
			CopyOnWriteArrayList<AssetUsersDetail> lMasters) {
		AssetUsersDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AssetUsersDetail_DTO> lMasterDTOs = new CopyOnWriteArrayList<AssetUsersDetail_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getAssetUsersDetail_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AssetUsersDetail_DTO getAssetUsersDetail_DTO(AssetUsersDetail lMaster) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetUsersDetail_DTO lDTO = new AssetUsersDetail_DTO();
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setFrDttm(formatter.format(lMaster.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getId().getToDttm().toLocalDateTime()));
		lDTO.setPartySeqNo(lMaster.getId().getPartySeqNo());
		return lDTO;
	}

}