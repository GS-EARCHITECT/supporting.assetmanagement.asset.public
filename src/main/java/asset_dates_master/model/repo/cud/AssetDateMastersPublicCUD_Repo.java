package asset_dates_master.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import asset_dates_master.model.master.AssetDateMaster;

@Repository("assetDateMastersPublicCUDRepo")
public interface AssetDateMastersPublicCUD_Repo extends JpaRepository<AssetDateMaster, Long> 
{}