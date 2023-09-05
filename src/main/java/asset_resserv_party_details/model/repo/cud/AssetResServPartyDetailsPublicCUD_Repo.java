package asset_resserv_party_details.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_resserv_party_details.model.master.AssetResServPartyDetail;

@Repository("assetResServPartyDetailsPublicCUDRepo")
public interface AssetResServPartyDetailsPublicCUD_Repo extends JpaRepository<AssetResServPartyDetail, Long> 
{
	
	@Query(value = "delete FROM ASSET_ResServ_Party_DETAILS a WHERE party_seq_no in :ids", nativeQuery = true)
	void delDetailsForParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM ASSET_ResServ_Party_DETAILS a WHERE resource_seq_no in :ids", nativeQuery = true)
	void delDetailsForResources(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM ASSET_ResServ_Party_DETAILS a WHERE service_seq_no in :ids", nativeQuery = true)
	void delDetailsForServices(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
}