package asset_dates_master.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_dates_master.model.dto.AssetDatesMaster_DTO;

public interface I_AssetDatesMastersPublicCUD_Service
{
    public CompletableFuture<AssetDatesMaster_DTO> newAssetDatesMaster(AssetDatesMaster_DTO aasssetMaintMastersDTO);
    public CompletableFuture<Void> updAssetDatesMaster(AssetDatesMaster_DTO lMasters);
    public CompletableFuture<Void> delAllAssetDatesMasters();
    public CompletableFuture<Void> delSelectAssetDatesMasters(CopyOnWriteArrayList<Long> seqNos);    
}