package asset_dates_master.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_dates_master.model.dto.AssetDateMaster_DTO;

public interface I_AssetDateMastersPublicCUD_Service
{
    public CompletableFuture<AssetDateMaster_DTO> newAssetDateMaster(AssetDateMaster_DTO aasssetMaintMastersDTO);
    public CompletableFuture<Void> updAssetDateMaster(AssetDateMaster_DTO lMasters);
    public CompletableFuture<Void> delAllAssetDateMasters();
    public CompletableFuture<Void> delSelectAssetDateMasters(CopyOnWriteArrayList<Long> seqNos);    
}