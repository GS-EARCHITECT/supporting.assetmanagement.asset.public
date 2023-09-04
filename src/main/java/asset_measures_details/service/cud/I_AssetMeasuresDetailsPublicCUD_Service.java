package asset_measures_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_measures_details.model.dto.AssetMeasuresDetail_DTO;
import asset_measures_details.model.master.AssetMeasuresDetailPK;

public interface I_AssetMeasuresDetailsPublicCUD_Service {
	public CompletableFuture<AssetMeasuresDetail_DTO> newAssetMeasuresDetail(
			AssetMeasuresDetail_DTO assetMeasuresDetail_DTO);

	public CompletableFuture<Void> updAssetMeasuresDetail(AssetMeasuresDetail_DTO lMaster);

	public CompletableFuture<Void> delAllAssetMeasuresDetails();

	public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetMeasuresDetailPK> seqNos);


}