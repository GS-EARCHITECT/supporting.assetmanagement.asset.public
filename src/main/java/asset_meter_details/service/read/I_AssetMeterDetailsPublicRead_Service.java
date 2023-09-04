package asset_meter_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_meter_details.model.dto.AssetMeterDetail_DTO;

public interface I_AssetMeterDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getAllMeterDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getSelectMeterDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<CopyOnWriteArrayList<AssetMeterDetail_DTO>> getMeterDetailsBetweenTimes(String fr, String to);
}