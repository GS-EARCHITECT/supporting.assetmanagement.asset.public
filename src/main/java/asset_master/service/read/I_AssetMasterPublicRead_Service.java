package asset_master.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_master.model.dto.AssetMaster_DTO;

public interface I_AssetMasterPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> getAllAssetMasters();    
    public CompletableFuture<Character> getAssetDoneStatus(Long id);
    public CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> getSelectAssetsByResources(CopyOnWriteArrayList<Long> ids);    
    public CompletableFuture<CopyOnWriteArrayList<AssetMaster_DTO>> getSelectAssets(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Character> getAssetStatus(Long id);
}