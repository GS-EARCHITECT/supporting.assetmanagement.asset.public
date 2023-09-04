package asset_master.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_master.model.dto.AssetMaster_DTO;

public interface I_AssetMasterPublicCUD_Service
{
    public CompletableFuture<AssetMaster_DTO> newAssetMaster(AssetMaster_DTO resourceCategoryMasterDTO);
    public CompletableFuture<Void> updAssetMaster(AssetMaster_DTO AssetMaster_DTO);
    public CompletableFuture<Void> setAssetDoneStatus(Long id, Character st);;
    public CompletableFuture<Void> delAllAssetMasters();    
    public CompletableFuture<Void> delSelectAssets(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectAssetsByResources(CopyOnWriteArrayList<Long> ids);
}