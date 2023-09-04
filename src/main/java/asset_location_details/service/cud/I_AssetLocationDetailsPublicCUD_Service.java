package asset_location_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_location_details.model.dto.AssetLocationDetail_DTO;
import asset_location_details.model.master.AssetLocationDetailPK;

public interface I_AssetLocationDetailsPublicCUD_Service
{
    public CompletableFuture<AssetLocationDetail_DTO> newAssetLocationDetail(AssetLocationDetail_DTO asssetMaintMasterDTO);
    public CompletableFuture<Void> updAssetLocationDetail(AssetLocationDetail_DTO lMaster);
    public CompletableFuture<Void> delAllAssetLocationDetails();
    public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetLocationDetailPK> seqNos);
    public CompletableFuture<Void> delSelectDetailsBetweenTimes(String fr, String to);
}