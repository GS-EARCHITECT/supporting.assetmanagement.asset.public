package asset_date_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_date_details.model.dto.AssetDateDetail_DTO;

public interface I_AssetDateDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> getAllAssetDateDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetDateDetail_DTO>> getSelectAssetDateDetails(CopyOnWriteArrayList<Long> seqNos); 
   
}