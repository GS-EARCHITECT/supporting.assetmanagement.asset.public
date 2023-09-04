package asset_users_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import asset_users_details.model.dto.AssetUsersDetail_DTO;

public interface I_AssetUsersDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getAllAssetUsersDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<CopyOnWriteArrayList<AssetUsersDetail_DTO>> getDetailsBetweenTimes(String fr, String to);
}