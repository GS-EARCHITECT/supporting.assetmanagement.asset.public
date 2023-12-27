package asset_master.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_master.model.master.AssetMaster;

@Repository("assetMasterPublicCUDRepo")
public interface AssetMasterPublicCUD_Repo extends JpaRepository<AssetMaster, Long> {

	@Query(value = "update ASSET_MASTER set doneflag = :st WHERE a.asset_seq_no = :id", nativeQuery = true)
	void setAssetDone(@Param("id") Long id, @Param("st") Character st);

	@Query(value = "delete FROM ASSET_MASTER a WHERE a.resource_seq_no in :ids", nativeQuery = true)
	void delSelectAssetsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update ASSET_MASTER a set status = :st WHERE a.asset_seq_no = :id", nativeQuery = true)
	void setAssetStatus(@Param("id") Long id, @Param("st") Character st);	
}
