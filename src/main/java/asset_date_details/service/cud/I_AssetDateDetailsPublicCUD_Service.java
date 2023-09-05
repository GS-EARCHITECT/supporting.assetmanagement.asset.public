package asset_date_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_date_details.model.dto.AssetDateDetail_DTO;
import asset_date_details.model.master.AssetDateDetailPK;

public interface I_AssetDateDetailsPublicCUD_Service
{
    public CompletableFuture<AssetDateDetail_DTO> newAssetDateDetail(AssetDateDetail_DTO asssetMaintDetailsDTO);
    public CompletableFuture<Void> updAssetDateDetail(AssetDateDetail_DTO lDetail);
    public CompletableFuture<Void> delAllAssetDateDetails();
    public CompletableFuture<Void> delSelectAssetDateDetails(CopyOnWriteArrayList<AssetDateDetailPK> seqNos);    
}