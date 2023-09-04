package asset_measures_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_measures_details.model.dto.AssetMeasuresDetail_DTO;

public interface I_AssetMeasuresDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> getAllAssetMeasuresDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetMeasuresDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos);    
}