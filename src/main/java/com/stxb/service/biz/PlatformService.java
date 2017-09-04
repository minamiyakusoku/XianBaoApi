package com.stxb.service.biz;

import com.stxb.model.SysApiPlatform;
import com.stxb.model.SysApiPlatformCACfg;

public interface PlatformService {
	boolean save(SysApiPlatform p,SysApiPlatformCACfg pCACfg);
	boolean update(SysApiPlatform p,SysApiPlatformCACfg pCACfg);
}
