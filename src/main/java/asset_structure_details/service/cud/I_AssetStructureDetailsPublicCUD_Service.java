package asset_structure_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_structure_details.model.dto.AssetStructureDetail_DTO;
import asset_structure_details.model.master.AssetStructureDetailPK;

public interface I_AssetStructureDetailsPublicCUD_Service
{
    public CompletableFuture<AssetStructureDetail_DTO> newAssetStructureDetail(AssetStructureDetail_DTO assetStructureDetail_DTO);
    public CompletableFuture<Void> updAssetStructureDetail(AssetStructureDetail_DTO lMaster);
    public CompletableFuture<Void> delAllAssetStructureDetails();
    public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetStructureDetailPK> seqNos);
    public CompletableFuture<Void> delSelectDetailsBetweenTimes(String fr, String to);
}