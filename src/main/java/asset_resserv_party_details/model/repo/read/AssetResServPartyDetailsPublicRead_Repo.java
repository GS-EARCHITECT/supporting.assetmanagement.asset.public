package asset_resserv_party_details.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_resserv_party_details.model.master.AssetResServPartyDetail;

@Repository("assetResServPartyDetailsPublicReadRepo")
public interface AssetResServPartyDetailsPublicRead_Repo extends JpaRepository<AssetResServPartyDetail, Long> 
{
	@Query(value = "SELECT * FROM ASSET_ResServ_Party_DETAILS a WHERE RESSRVPRD_SEQ_NO in :ids  order by RESSRVPRD_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetResServPartyDetail> getSelectDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_ResServ_Party_DETAILS a order by RESSRVPRD_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetResServPartyDetail> getAllDetails();
	
	@Query(value = "SELECT * FROM ASSET_ResServ_Party_DETAILS a WHERE party_seq_no in :ids  order by party_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetResServPartyDetail> getDetailsForParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_ResServ_Party_DETAILS a WHERE resource_seq_no in :ids  order by resource_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetResServPartyDetail> getDetailsForResources(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_ResServ_Party_DETAILS a WHERE service_seq_no in :ids  order by service_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetResServPartyDetail> getDetailsForServices(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
}