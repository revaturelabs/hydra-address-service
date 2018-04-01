package com.revature.hydra.address.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.revature.hydra.address.beans.Activatable;

/**
 * Created by August Duet on 11/29/2016.
 */
@NoRepositoryBean
public interface ActivatableObjectRepository<T extends Activatable, ID extends Serializable>
		extends BaseRepository<T, ID> {
	List<T> findByActiveIsTrue();
}
