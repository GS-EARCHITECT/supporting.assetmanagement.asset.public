package asset_dates_master.service.read;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_dates_master.model.dto.AssetDateMaster_DTO;

public interface I_AssetDateMastersPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> getAllAssetDateMasters();
    public CompletableFuture<CopyOnWriteArrayList<AssetDateMaster_DTO>> getSelectAssetDateMasters(CopyOnWriteArrayList<Long> seqNos);
      
}