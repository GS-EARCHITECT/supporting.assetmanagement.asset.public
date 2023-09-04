package asset_meter_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_meter_details.model.dto.AssetMeterDetail_DTO;
import asset_meter_details.model.master.AssetMeterDetailPK;

public interface I_AssetMeterDetailsPublicCUD_Service
{
    public CompletableFuture<AssetMeterDetail_DTO> newMeterDetail(AssetMeterDetail_DTO assetMeterDetail_DTO);
    public CompletableFuture<Void> updMeterDetail(AssetMeterDetail_DTO lMaster);
    public CompletableFuture<Void> delAllMeterDetails();
    public CompletableFuture<Void> delSelectMeterDetails(CopyOnWriteArrayList<AssetMeterDetailPK> seqNos);
    public CompletableFuture<Void> delSelectMeterDetailsBetweenTimes(String fr, String to);
}