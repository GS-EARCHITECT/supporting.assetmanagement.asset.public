package asset_users_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_users_details.model.dto.AssetUsersDetail_DTO;
import asset_users_details.model.master.AssetUsersDetailPK;

public interface I_AssetUsersDetailsPublicCUD_Service
{
    public CompletableFuture<AssetUsersDetail_DTO> newAssetUsersDetail(AssetUsersDetail_DTO assetUsersDetail_DTO);
    public CompletableFuture<Void> updAssetUsersDetail(AssetUsersDetail_DTO lMaster);
    public CompletableFuture<Void> delAllAssetUsersDetails();
    public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<AssetUsersDetailPK> seqNos);
    public CompletableFuture<Void> delSelectDetailsBetweenTimes(String fr, String to);
}