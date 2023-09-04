package asset_price_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_price_details.model.dto.AssetPriceDetail_DTO;
import asset_price_details.model.master.AssetPriceDetailPK;

public interface I_AssetPriceDetailsPublicCUD_Service
{
    public CompletableFuture<AssetPriceDetail_DTO> newAssetPriceDetail(AssetPriceDetail_DTO assetPriceDetail_DTO);
    public CompletableFuture<Void> updAssetPriceDetail(AssetPriceDetail_DTO lMaster);
    public CompletableFuture<Void> delAllAssetPriceDetails();
    public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetPriceDetailPK> seqNos);
    public CompletableFuture<Void> delSelectDetailsBetweenTimes(String fr, String to);
}