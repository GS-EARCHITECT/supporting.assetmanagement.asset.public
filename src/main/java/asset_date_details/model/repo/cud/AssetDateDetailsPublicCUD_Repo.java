package asset_date_details.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import asset_date_details.model.master.AssetDateDetail;
import asset_date_details.model.master.AssetDateDetailPK;

@Repository("assetDateDetailsPublicCUDRepo")
public interface AssetDateDetailsPublicCUD_Repo extends JpaRepository<AssetDateDetail, AssetDateDetailPK> 
{}