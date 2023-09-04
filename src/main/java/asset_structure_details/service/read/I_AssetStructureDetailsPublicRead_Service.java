package asset_structure_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_structure_details.model.dto.AssetStructureDetail_DTO;

public interface I_AssetStructureDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getAllAssetStructureDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<CopyOnWriteArrayList<AssetStructureDetail_DTO>> getDetailsBetweenTimes(String fr, String to);
}