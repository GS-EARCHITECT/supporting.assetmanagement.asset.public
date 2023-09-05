package asset_resserv_party_details.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_resserv_party_details.model.dto.AssetResServPartyDetail_DTO;

public interface I_AssetResServPartyDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getAllAssetResServPartyDetails();
    public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getSelectDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForParties(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForResources(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AssetResServPartyDetail_DTO>> getDetailsForServices(CopyOnWriteArrayList<Long> ids);
}