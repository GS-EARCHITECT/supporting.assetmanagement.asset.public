package asset_users_details.model.repo.cud;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asset_users_details.model.master.AssetUsersDetail;
import asset_users_details.model.master.AssetUsersDetailPK;

@Repository("assetUsersDetailsPublicCUDRepo")
public interface AssetUsersDetailsPublicCUD_Repo extends JpaRepository<AssetUsersDetail, AssetUsersDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Users_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))", nativeQuery = true)
	void delDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);

}