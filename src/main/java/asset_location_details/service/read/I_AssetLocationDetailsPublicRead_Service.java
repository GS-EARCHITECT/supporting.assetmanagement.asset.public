package asset_location_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_location_details.model.dto.AssetLocationDetail_DTO;

public interface I_AssetLocationDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getAllAssetLocationDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<CopyOnWriteArrayList<AssetLocationDetail_DTO>> getDetailsBetweenTimes(String fr, String to);
}