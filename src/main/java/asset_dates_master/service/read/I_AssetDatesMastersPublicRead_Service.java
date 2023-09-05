package asset_dates_master.service.read;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_dates_master.model.dto.AssetDatesMaster_DTO;

public interface I_AssetDatesMastersPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> getAllAssetDatesMasters();
    public CompletableFuture<CopyOnWriteArrayList<AssetDatesMaster_DTO>> getSelectAssetDatesMasters(CopyOnWriteArrayList<Long> seqNos);
      
}