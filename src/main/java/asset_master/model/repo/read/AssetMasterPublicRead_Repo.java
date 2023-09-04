package asset_master.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_master.model.master.AssetMaster;

@Repository("assetMasterPublicReadRepo")
public interface AssetMasterPublicRead_Repo extends JpaRepository<AssetMaster, Long> 
{

	@Query(value = "SELECT * FROM ASSET_MASTER a order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetMaster> getAllAssets();
	
	@Query(value = "SELECT * FROM ASSET_MASTER a WHERE a.asset_seq_no in :ids order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetMaster> getSelectAssets(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_MASTER a WHERE a.resource_seq_no in :ids order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetMaster> getSelectAssetsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT doneflag FROM ASSET_MASTER a WHERE a.asset_seq_no = :id", nativeQuery = true)
	Character getAssetDoneStatus(@Param("id") Long id);

}
