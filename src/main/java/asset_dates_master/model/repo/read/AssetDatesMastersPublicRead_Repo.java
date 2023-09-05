package asset_dates_master.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asset_dates_master.model.master.AssetDatesMaster;

@Repository("assetDatesMastersPublicReadRepo")
public interface AssetDatesMastersPublicRead_Repo extends JpaRepository<AssetDatesMaster, Long> 
{
	@Query(value = "SELECT * FROM ASSET_Dates_MASTER a order by date_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDatesMaster> getAllAssetDatesMasters();
	
	@Query(value = "SELECT * FROM ASSET_Dates_MASTER a WHERE a.date_seq_no in :ids order by date_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDatesMaster> getSelectAssetDatesMasters(@Param("ids") CopyOnWriteArrayList<Long> ids);
		
}