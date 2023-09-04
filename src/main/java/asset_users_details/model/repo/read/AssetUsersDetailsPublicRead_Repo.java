package asset_users_details.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_users_details.model.master.AssetUsersDetail;
import asset_users_details.model.master.AssetUsersDetailPK;

@Repository("assetUsersDetailsPublicReadRepo")
public interface AssetUsersDetailsPublicRead_Repo extends JpaRepository<AssetUsersDetail, AssetUsersDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Users_DETAILS order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetUsersDetail> getAllAssetUserssDetails();
	
	@Query(value = "SELECT * FROM ASSET_Users_DETAILS a WHERE a.asset_seq_no in :ids  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetUsersDetail> getSelectAssetUserssDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_Users_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetUsersDetail> getDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);
	
}