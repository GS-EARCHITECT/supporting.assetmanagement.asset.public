package asset_resserv_party_details.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import asset_resserv_party_details.model.dto.AssetResServPartyDetail_DTO;

public interface I_AssetResServPartyDetailsPublicCUD_Service
{
    public CompletableFuture<AssetResServPartyDetail_DTO> newAssetResServPartyDetail(AssetResServPartyDetail_DTO assetResServPartyDetail_DTO);
	public CompletableFuture<Void> updAssetResServPartyDetail(AssetResServPartyDetail_DTO lMaster);
    public CompletableFuture<Void> delSelectDetails(CopyOnWriteArrayList<Long> seqNos);
    public CompletableFuture<Void> delDetailsForParties(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delDetailsForResources(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delDetailsForServices(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delAllAssetResServPartyDetails();
}