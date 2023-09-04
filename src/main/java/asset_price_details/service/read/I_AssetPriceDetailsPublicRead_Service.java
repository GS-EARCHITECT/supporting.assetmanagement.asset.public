package asset_price_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_price_details.model.dto.AssetPriceDetail_DTO;

public interface I_AssetPriceDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getAllAssetPriceDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<CopyOnWriteArrayList<AssetPriceDetail_DTO>> getDetailsBetweenTimes(String fr, String to);
}